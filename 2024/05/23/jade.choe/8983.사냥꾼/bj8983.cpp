// https://www.acmicpc.net/problem/8983
// 사냥꾼 - gold 4
#include <iostream>
#include <vector>
#include <cmath>
#include <algorithm>
using namespace std;

int bj_8983() {
	ios::sync_with_stdio(0);
	cout.tie(0);
	int M, N, L, ans, dummy;
	// 동물 좌표
	pair<int, int> pos[100002];
	cin >> M >> N >> L;
	// 사대 배열
	vector<int> guns(M);
	for (int& gun : guns) {
		cin >> gun;
	}
	// 사대 정렬(이분탐색을 위해)
	sort(guns.begin(), guns.end());
	for (int i = 0; i < N; i++) {
		cin >> pos[i].first >> pos[i].second;
	}
	ans = 0;
	int start, mid, end, startX, endX;
	for (int i = 0; i < N; i++) {
		if (pos[i].second > L) continue;
		// dummy: y좌표의 차이 => +-x 범위
		dummy = L-pos[i].second;
		startX = pos[i].first - dummy;
		// NullPointerException 처리
		if (startX < 0) startX = 0;
		endX = pos[i].first + dummy;
		// 이분탐색
		start = 0;
		end = guns.size();
		mid = (start + end) / 2;
		while (start<=end) {
			if (guns[start] == startX) {
				ans++;
				break;
			}
			else if (guns[end - 1] == endX) {
				ans++;
				break;
			}
			else if (guns[mid] < startX) {
				start = mid+1;
				mid = (start + end) / 2;
			}
			else if (guns[mid] > endX) {
				end = mid-1;
				mid = (start + end) / 2;
			}
			else {
				ans++;
				break;
			}
		}
	}
	cout << ans;

	return 0;
}
