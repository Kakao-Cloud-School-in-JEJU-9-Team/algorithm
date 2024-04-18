// https://www.acmicpc.net/problem/10026
// 백준 10026 - 적록색약
#include <iostream>
#include <cstring>

struct COLOR{
	char color;
	bool visit;
};
using namespace std;
COLOR matrix[101][101];

int dx[] = { 1, -1, 0, 0 }; // 좌우탐색
int dy[] = { 0, 0, 1, -1 }; // 상하탐색

// 재귀 dfs
void dfs(int x, int y) {
	// 방문 처리가 이중으로 처리되는데, 재귀 구현이라 어쩔 수 없는듯
	if (matrix[x][y].visit) return;
	matrix[x][y].visit = true; // 방문 처리
	// 상하좌우 탐색에 더 깔끔한 코드가 있어 가져와봄
	for (int i = 0; i < 4; i++) {
		int moveX = dx[i] + x;
		int moveY = dy[i] + y;
		
		// 같은 지역 & 탐색하지 않은 지역만 탐색
		if ((matrix[x][y].color == matrix[moveX][moveY].color) &&
			!matrix[moveX][moveY].visit) {
			dfs(moveX, moveY);
		}
	}

}

int main() {
	ios::sync_with_stdio(false);
	cin.tie(NULL); cout.tie(NULL);
	int n;
	int area = 0;
	string input;
	// input : 1 <= N <= 100, N개의 string(N글자)
	cin >> n;
	for (int i = 0; i < n; i++) {
		cin >> input;
		for (int j = 0; j < n; j++) {
			// 공백이 없어 string으로 input을 받고 하나씩 넣어주자
			matrix[i][j].color = input[j];
		}
	}

	// 적녹색약이 아닌 경우
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < n; j++) {
			if (!matrix[i][j].visit) { 
				// 방문하지 않았을 시 그 지역 탐색
				dfs(i, j);
				area++; // 구역의 수 1 증가
			}
		}
	}
	// 적녹색약이 아닌 경우 구역의 수
	cout << area << ' ';
	area = 0; // 구역 수 초기화
	// 벡터를 사용하지 않으면 초기화하는데 반복문을 써야한다..
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < n; j++) {
			matrix[i][j].visit = false;
			// 적녹색약이므로 G==R
			if (matrix[i][j].color == 'G') matrix[i][j].color = 'R';
		}
	}

	// 적녹색약인 경우
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < n; j++) {
			if (!matrix[i][j].visit) {
				// 방문하지 않았을 시 그 지역 탐색
				dfs(i, j);
				area++; // 구역의 수 1 증가
			}
		}
	}
	// 적녹색약인 경우 구역의 수
	cout << area << ' ';
	
	// 스택으로 구현하면 탐색을 두번 하는것보다 더 좋은 방법이 있을수도
	return 0;
}