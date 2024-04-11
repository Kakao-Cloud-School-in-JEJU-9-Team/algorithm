// https://www.acmicpc.net/problem/28278
// 실버 4
// 해결
#include <iostream>
#include <stack>
using namespace std;

int baekjoon_28278() {
	// 시간 초과의 주범들
	ios_base::sync_with_stdio(false);
	// cin, cout과 printf, scanf의 입출력 버퍼 동기화 해제
	// cin과 cout을 번갈아 사용할 경우 출력 버퍼 flush 발생
	// endl은 개행문자 출력과 동시에 버퍼를 flush함, 느림

	cin.tie(0);
	// cin, cout의 출력 버퍼 자동 비우기 해제


	// 구현해야 하는 기능들
	// 1.stack.push
	// 2.stack.pop
	// 3.stack.size
	// 4.stack.empty
	// 5.stack.top

	int N = 0;
	int inst = 0;
	int param = 0;
	cin >> N;
	stack<int> st;

	while(N--) { // 0이 되면 반복문 종료(시간절약)
		cin >> inst;
		switch (inst)
		{
		case 1:
			cin >> param;
			st.push(param);
			break;
		case 2:
			if (!st.empty()) { // 비어있는 경우가 더 적지 않을까? - if->else 시간 절약
				cout << st.top() << '\n';
				st.pop();
			}
			else {
				cout << -1 << '\n';
			}
			break;
		case 3:
			cout << st.size() << '\n';
			break;
		case 4:
			if (!st.empty()) cout << 0 << '\n';
			else cout << 1 << '\n';
			break;
		case 5:
			if (!st.empty()) cout << st.top() << '\n';
			else cout << -1 << '\n';
			break;
		}
	}
	return 0;
}