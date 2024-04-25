//https://www.acmicpc.net/problem/18186
// 반례 찾는 문제같음
// 조건 : 최대 3개의 연속한 공장에서 구매가능
// B가 C보다 클 경우
// B가 C보다 크면 최대한 길게 연속해서 구매하는게 이득
// 뒤에서 앞으로 연속된 3개의 공장을 찾아 구매
// 연속된 3개의 공장이 완료되면 연속된 2개의 공장을 재탐색
// 마지막 남은 공장들을 계산

// 한번 틀림
// 틀린이유 : B+2C 원이라는게 i번 공장에서 B원, i+1, i+2공장에서 C원 든다는줄
// 개당 구매 가격이 B+2C원 든다는 소리였음? 아닌거같은데

// 또 틀림
// 틀린이유 : 초반부 예외처리에 실패함

// 또 틀림
// 이유를 모르겠네
#include <iostream>
#include <algorithm>
using namespace std;
#define MAX 1000001
long long arr[MAX];
long long n, b, c;
void getCost() {
	long long ans = 0;
	long long minus = 0;
	if (b > c) {
		// b가 c보다 크면 계산해야함
		// 연속된 3개 먼저 탐색
		for (int i = n; i >= 2; i--) {
			// 3개가 연속되면 최소값만큼 arr[]--, ans++
			minus = min(arr[i], arr[i - 1]);
			minus = min(arr[i - 2], minus);
			// 0값 체크 통해서 연속된 2개 탐색
			if (minus == 0) {
				minus = min(arr[i], arr[i - 1]);
				// 0값 체크 통해서 단일 값 처리
				if (minus == 0) {
					ans += arr[i] * b;
					arr[i] -= arr[i];
					// continue문으로 현재 공장 스킵
					continue;
				}
				//ans += (arr[i - 1] * minus * b) +
				//	(arr[i] * minus * c);
				ans += (b + c) * minus;
				arr[i] -= minus;
				arr[i - 1] -= minus;
				minus = 0;
				continue;
			}
			//ans += (arr[i - 2] * minus * b) + 
			//	((arr[i - 1] + arr[i]) * minus * c);
			ans += (b + (2 * c)) * minus;
			arr[i] = arr[i] - minus;
			arr[i - 1] = arr[i - 1] - minus;
			arr[i - 2] = arr[i - 2] - minus;
		}
		minus = 0;
		// 배열에 남은 값 처리
		// 다른 방식으로 처리해야함
		// 배열 끝과 첫부분 예외처리가 필요
		{ // 배열 끝
			minus = min(arr[n - 1], arr[n]);
			ans += (b + c) * minus;
			arr[n - 1] -= minus;
			arr[n] -= minus;
			ans += b * arr[n - 1];
			ans += b * arr[n];
		}
		{ // 배열 처음
			minus = min(arr[1], arr[2]);
			ans += (b + c) * minus;
			arr[1] -= minus;
			arr[2] -= minus;
			ans += b * arr[1];
			ans += b * arr[2];
		}

		cout << ans << '\n';
	}
	else {
		// b가 c보다 작거나 같으면 전부 더한게 정답
		for (int i = 0; i < n; i++) {
			ans += arr[i] * b;
		}
		cout << ans << '\n';
	}
	return;
}
int bj_16496() {
	ios::sync_with_stdio(false);
	cin.tie(NULL); cout.tie(NULL);
	// 입력부분
	cin >> n >> b >> c;
	for (int i = 0; i < n; i++) {
		cin >> arr[i];
	}
	greedy();
}