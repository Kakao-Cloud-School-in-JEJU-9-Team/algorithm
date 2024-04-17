// https://www.acmicpc.net/problem/7576
// 7576번: 토마토

#include <queue>
#include <algorithm>
#include <iostream>
#include <vector>
#include <string.h>
using namespace std;

// 간선 연결 함수
// 벡터 밖을 참조하지 않도록 범위 설정을 해줘야함
void insert_vertex(int n, int m, int rows, int cols,
	vector<int> vertex[], vector<int> matrix[]) {
	if ( (n>0) &&
		(matrix[n - 1][m] != -1)) {
		vertex[n*cols+m].push_back(((n - 1) * cols + m));
	}
	if ( (n<rows-1) &&
		(matrix[n + 1][m] != -1)) {
		vertex[n * cols + m].push_back(((n + 1) * cols + m));
	}
	if ( (m>0) &&
		(matrix[n][m-1] != -1)) {
		vertex[n * cols + m].push_back((n * cols + m-1));
	}
	if ( (m<cols-1) &&
		(matrix[n][m+1] != -1)) {
		vertex[n * cols + m].push_back((n * cols + m+1));
	}
}
// 전부 익었는지 체크하는 함수
bool check_visit(int rows, int cols, vector<bool> visit) {
	for (int i = 0; i < rows; i++) {
		for (int j = 0; j < cols; j++) {
			if (!visit[i * cols + j]) {
				return false;
			}
		}
	}
	return true;
}

// BFS 알고리즘
void bfs(int rows, int cols, 
	vector<int> vertex[], vector<int> matrix[], vector<bool> visit) {
	int day = 0;
	
	
	// 큐 2개를 돌려가며 써야 날짜 세기가 편할 것 같다
	queue<int> q;
	queue<int> q2;

	// 편의성을 위한 포인터
	// flush : 이번 탐색에서 비울 큐
	queue<int>* flush;
	// save : 이번 탐색에서 저장할 큐
	queue<int>* save;
	// 처음엔 q에 저장
	queue<int>* temp;
	flush = &q;
	save = &q2;
	
	// 익은 토마토 먼저 체크
	for (int i = 0; i < rows; i++) {
		for (int j = 0; j < cols; j++) {
			if (matrix[i][j] == 1) {
				visit[i*cols + j] = true;
				// 익었으면 큐에 push
				q.push(i*cols + j);
			}
			// 토마토가 없는 경우도 visit=true로 체크
			else if (matrix[i][j] == -1) {
				visit[i * cols + j] = true;
			}
			else {
				visit[i*cols + j] = false;
			}
		}
	}
	int size;
	// bfs 시작
	while (!(*flush).empty()) {
		while (!(*flush).empty()) {
			// 익은 토마토가 여러개일 경우가 있으니 2중 반복문
			int x = (*flush).front();
			(*flush).pop();

			// vertex[x].size()를 조건으로 넣을 시 .pop_back()할때마다 size가 1씩 작아지므로 미리 선언
			size = vertex[x].size();
			for (int i = 0; i < size; i++) {
				// 이러면 O(n^3)인가?
				// 인접 토마토 탐색
				if (visit[vertex[x].back()] == false) {
					// 익지 않은 토마토일 경우 save 큐에 push, 익은걸로 체크
					(*save).push(vertex[x].back());
					visit[vertex[x].back()] = true;
					// 간선에서 삭제
					vertex[x].pop_back();
				}
				else {
					// 이미 익었으면 간선에서 삭제
					vertex[x].pop_back();
				}
			}
		}
		// 큐 교대
		temp = flush;
		flush = save;
		save = temp;

		// 날짜 지나감 - 마지막 다 익었을때도 한번 더 실행되니 출력할때 -1
		// 한번 더 실행하는걸 막으려면 매번 visit배열을 체크해야하는데, 시간소모가 심함
		day++;
	}

	// 다 익기까지 걸린 날짜 출력, 안익은 토마토가 있으면 -1 출력
	if (check_visit(rows, cols, visit)) {
		cout << day-1;
	}
	else {
		cout << "-1";
	}
	return;
}


int main() {
	ios::sync_with_stdio(false);
	cin.tie(NULL); cout.tie(NULL);

	// input 2 <= M,N <= 1000, -1 <= tomato <= 1
	// 둘째 줄 부터는 상자에 저장된 토마토들의 정보가 MxN행렬 형태로 주어짐
	int m, n, tomato;
	cin >> m >> n;
	vector<int>* matrix = new vector<int>[n];
	vector<int>* vertex = new vector<int>[n * m];
	vector<bool> visit;
	visit.resize(n * m);
	// tomato input
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < m; j++) {
			cin >> tomato;
			matrix[i].push_back(tomato);
		}
	}
	// vertex init
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < m; j++) {
			if ( matrix[i][j] != -1 ) {
				insert_vertex(i, j, n, m, vertex, matrix);
			}
		}
	}
	// BFS
	bfs(n, m, vertex, matrix, visit);
	delete[] matrix;
	delete[] vertex;
	return 0;
}