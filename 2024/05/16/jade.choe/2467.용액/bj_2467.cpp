
//https://www.acmicpc.net/problem/2467
// 용액
#include <iostream>
#include <limits>
#include <cmath>
using namespace std;
#define INF numeric_limits<int>::max()

int arr[100000];

int bj_2467() {
	ios::sync_with_stdio(false);
	cin.tie(NULL); cout.tie(NULL);
	int n;
	cin >> n;
	for (int i = 0; i < n; i++) {
		cin >> arr[i];
	}

	int left = 0;
	int right = n - 1;
	int a, b, add;
	a = 0;
	b = 0;
	int min = INF;

	while (left < right) {
		add = arr[left] + arr[right];
		// 음수때문에 절댓값 취해야됨
		if (min > abs(add)) {
			min = abs(add);
			a = arr[left];
			b = arr[right];
		}
		else if (add < 0) {
			left++;
		}
		else {
			right--;
		}
	}
	cout << a << " " << b;

	return 0;
}
