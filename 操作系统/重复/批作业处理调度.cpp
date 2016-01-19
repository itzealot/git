#include<iostream>
using namespace std;
#include<cstring>
#define N 100//作业数目
#define MAX INT_MAX
int x[N+1];
int m[N+1][3];
int bestx[N+1];//用于保存结果调度顺序
int f2[N+1];//第i阶段机器2完成处理的时间
int f1=0;//机器1完成处理时间
int f=0;//当前完成时间和
int bestf=MAX;
int n;
void swap(int &a,int &b)
{
    int temp=a;
    a=b;
    b=temp;
}
void Backtrace(int t)
{
    if(t>n)
    {
        bestf=f;
        for(int i=1;i<=n;i++)
        {
            bestx[i]=x[i];
        }
    }
    else
    {
        for(int i=t;i<=n;i++)
        {
            f1+=m[x[i]][1];
            f2[t]=(f2[t-1]>f1?f2[t-1]:f1)+m[x[i]][2];
            f+=f2[t];
            if(f<bestf)
            {
				swap(x[t],x[i]);
                Backtrace(t+1);
				swap(x[t],x[i]);
            }
            f1-=m[x[i]][1];
            f-=f2[t];
       }
    }
}
int main()
{
	while(cin>>n)
	{
		for(int i=1;i<=n;i++)
		{
			for(int j=1;j<=2;j++)
				cin>>m[i][j];
			x[i]=i;
		}
		memset(bestx,0,(n+1)*sizeof(int));
	    memset(f2,0,(n+1)*sizeof(int));/*
	    for(int i=1;i<=n;i++)
	    {
    		bestx[i]=0;
    		f2[i]=0;
    	}*/
	    Backtrace(1);
	    printf("该作业调度的最优完成时间和为：%d\n调度顺序为：",bestf);
	    for(int i=1;i<=n;i++)
	    {
	        printf("%d ",bestx[i]);
	    }
	    cout<<endl;
	    bestf=MAX;
	}
    return 0;
}
/*
3
2 1
3 1
2 3
*/ 