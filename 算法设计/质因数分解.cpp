#include<iostream>
#include<cmath>
using namespace std;
/*
质因数分解 
Prime[]：素数表，至少需要达到sqrt(n)
pn：素数表的元素个数
N：待分解的数
Rest：分解结果，按照升序排列
返回值：分解因子个数
*/
int reduce(int prime[], int pn, int n, int rest[])
{
	int i, k = 0;
	for(i = 0; i < pn; i++) {
		if(n == 1) {
			break;
		} 
		if(prime[i] * prime[i] > n) {
			rest[k++] = n;
			break;
		}
		while(n % prime[i] == 0) {
			n /= prime[i];
			rest[k++] = prime[i];
		}
	}
	return k;
}
int isPrime(int n)
{
	if(n < 2) { 
		return 0;
	}
	int length = (int)(sqrt(double(n)));
	for(int i = 2; i <= length; i++) {
		if(n % i == 0) { 
			return 0;
		} 
	}
	return 1;
}
int main()
{
	int i, prime[100], Max = 100, n, rest[20], count=0;
	for(i = 2; count < Max; i++) {
		if(isPrime(i)) {
			prime[count] = i;
			count++;
		}
	}
	while(cin >> n) {
		int k = reduce(prime, Max, n, rest);
		for(i = 0; i < k; i++) {
			cout << rest[i] << " ";
		}
		cout<<endl;
	}
	return 0;
}