// https://www.acmicpc.net/problem/18258
// 실버4
// 해결
#include <iostream>
#include <queue>
using namespace std;

int baekjoon_18258() {
	ios_base::sync_with_stdio(false);
	// cin, cout과 printf, scanf의 입출력 버퍼 동기화 해제
	cin.tie(0);
	// cin, cout의 출력 버퍼 자동 비우기 해제


	// 기능
	// 1.queue.push
	// 2.queue.pop ? int : -1
	// 3.queue.size
	// 4.queue.empty ? 1 : 0
	// 5.queue.front ? int : -1
	// 6.queue.back ? int : -1
	
	int N;
	int param;
	string inst;
	queue<int> q;

	cin >> N;
	while (N--) {
		cin >> inst;
		if (inst == "push") {
			cin >> param;
			q.push(param);
		}
		else if (inst == "pop") {
			if (!q.empty()) {
				cout << q.front() << '\n';
				q.pop();
			}
			else {
				cout << -1 << '\n';
			}
		}
		else if (inst == "size") {
			cout << q.size() << '\n';
		}
		else if (inst == "empty") {
			cout << q.empty() << '\n';
		}
		else if (inst == "front") {
			if (!q.empty()) cout << q.front() << '\n';
			else cout << -1 << '\n';
		}
		else { //if (inst == "back") {
			if (!q.empty()) cout << q.back() << '\n';
			else cout << -1 << '\n';
		}
	}


	return 0;
}