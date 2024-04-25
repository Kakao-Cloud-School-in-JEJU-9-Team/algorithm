//https://www.acmicpc.net/problem/16496
#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

vector<string> number;

// 원래 오름차순 정렬하는 sort 알고리즘, cmp함수를 살짝 바꿔서 정렬 적용
// 기본형 : return a < b;
// false가 나오면 교체하는 방식이므로, ba가 큰 경우 false를 리턴해야함
// 단순 숫자를 비교하면 자릿수가 많은 수가 앞으로 가니, 문자열을 합쳐 같은 자릿수에서 비교
bool cmp(string a, string b) {
	if (a == b) return true;
	string ab = a + b;
	string ba = b + a;

	if (ab > ba) {
		return true;
	}
	else {
		return false;
	}
}

int main() {
	ios::sync_with_stdio(false);
	cin.tie(NULL); cout.tie(NULL);
	int n;
	cin >> n;
	bool check = false; // 초기화 안하면 실행이 안됨
	string a;
	// 입력 받아오기
	for (int i = 0; i < n; i++) {
		cin >> a;
		number.push_back(a);
		if (a != "0") check = true; // 0 0 0 0이 들어오면 0을 출력해야함
	}
	if (!check) cout << 0;
	else {
		sort(number.begin(), number.end(), cmp);
		for (int i = 0; i < n; i++) {
			cout << number[i];
		}
	}
	// 정렬만 했는데 풀림
	return 0;
}