#include<stdio.h>  
#include<math.h>
/*
首先还是i从2开始到sqrt(n)的每一个i由n试除 ,如果i能整除n，那么不用判断i，
i必为n的质因子，将n=n/i  ，因为n可能有多个相同的质因子，为了避免遗漏，
只需将i-- ，当跳到下一步循环的时候与i++抵消，i的值不变，由于由2~i的每
一个数都已经判断过是否能整除n，所以不必要再将i回退到2，只需另i在跳到
下步循环的时候值不变即可，最后n也会被约成质数，也是一个质因子，所以
写成程序就是开头的代码，至于效率吗 我认为还是比较高的。。
*/
int main() {  
    int n,i;  
    while(scanf("%d", &n) && n) {  
        for(i = 2; i <= (int)sqrt((double)n); i++) { 
            if(n % i == 0 && (n /= i)) { 
                printf("%d ", i--);
            }
		}
        printf("%d\n", n);  
    }  
    return 0;  
} 