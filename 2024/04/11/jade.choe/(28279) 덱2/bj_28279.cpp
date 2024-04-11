// https://www.acmicpc.net/problem/28279
// 실버 4
// 해결, scanf()때문에 컴파일할때 계속 오류나서 주석처리

/*
#include <iostream>
#include <deque>
#define _CRT_SECURE_NO_WARNINGS
using namespace std;
// 덱(deque)는 double-ended queue의 줄임말, queue 2개를 조합한것
// 양방향으로 push, pop이 가능하다

// 기능
// 1.deque.pushfront
// 2.deque.pushback
// 3.deque.popfirst ? int : -1
// 4.deque.poplast ? int : -1
// 5.deque.size
// 6.deque.empty() ? 1 : 0
// 7.deque.frontfirst ? int : -1
// 8.deque.backfirst ? int : -1

int baekjoon_28279() {
	//이번엔 scanf, printf로 해결(cin, cout 사용 X)
	int N;
	int inst;
	int param;
	deque<int> dq;

	scanf("%d", &N);
	while (N--) {
		scanf("%d", &inst);
		switch (inst)
		{
		case 1:
			scanf("%d", &param);
			dq.push_front(param);
			break;
		case 2:
			scanf("%d", &param);
			dq.push_back(param);
			break;
		case 3:
			if (!dq.empty()) {
				printf("%d\n", dq.front());
				dq.pop_front();
			}
			else {
				printf("%d\n", -1);
			}
			break;
		case 4:
			if (!dq.empty()) {
				printf("%d\n", dq.back());
				dq.pop_back();
			}
			else {
				printf("%d\n", -1);
			}
			break;
		case 5:
			printf("%d\n", dq.size());
			break;
		case 6:
			printf("%d\n", dq.empty());
			break;
		case 7:
			if (!dq.empty()) {
				printf("%d\n", dq.front());
			}
			else {
				printf("%d\n", -1);
			}
			break;
		case 8:
			if (!dq.empty()) {
				printf("%d\n", dq.back());
			}
			else {
				printf("%d\n", -1);
			}
			break;
		}
	}


	return 0;
}
*/