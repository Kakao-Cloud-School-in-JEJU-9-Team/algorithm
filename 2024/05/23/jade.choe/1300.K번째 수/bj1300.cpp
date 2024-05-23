// https://www.acmicpc.net/problem/1300
// K번째 수 - gold 1
#include <iostream>
using namespace std;
long long n, k, ans;


int bj_1300() {
	ios::sync_with_stdio(0);
	cout.tie(0);
	cin >> n;
	// index k : 배열 A에서 k번째 작은 수
	cin >> k;
	long long start = 1;
	long long end = n * n;
	long long mid, temp, smaller=0;
	
	// 이분 탐색 - k번째 작은 수를 찾을때까지
	// start, mid, end는 수, smaller와 k는 갯수(index)
	while (start < end) {
		mid = (start + end) / 2;
		smaller = 0;
		// 배열 A에서 mid보다 작은 값(smaller)이 몇개인지
		for (int i = 1; i <= n; i++) {
			temp = mid / i;
			if (temp > n) temp = n;
			smaller+=temp;
		}
		// smaller를 기준으로 분할
		if (smaller < k) {
			start = mid + 1;
		}
		else{
			// end = mid-1로 하니까 틀림
			end = mid;
		}
	}
	// start==end니 둘중 아무거나 넣어도 정답
	// mid를 넣으면 틀림-초기화문제 아님
	cout << end;
	return 0;
}
