// https://www.acmicpc.net/problem/1260
// 백준 1260-DFS와 BFS
// https://velog.io/@lucky-korma/DFS-BFS%EC%9D%98-%EC%84%A4%EB%AA%85-%EC%B0%A8%EC%9D%B4%EC%A0%90

#include <iostream>
#include <vector>
#include <string.h>
#include <queue>
#include <algorithm>
#include <stack>
using namespace std;

vector<int> result_dfs;
vector<int> result_bfs;
bool visit[1002];

void visit_init(int node) {
	for (int i = 1; i <= node; i++) {
		visit[i] = false;
	}
}

void bfs(int start, vector<int> vertex[]) {
	queue<int> q;
	q.push(start);
	visit[start] = true;
	while (!q.empty()) {
		int x = q.front();
		q.pop();
		result_bfs.push_back(x);
		for (int i = 0; i < vertex[x].size(); i++) {
			if (visit[vertex[x][i]] == false) {
				q.push(vertex[x][i]);
				visit[vertex[x][i]] = true;
			}
		}
	}
	for (int i = 0; i < result_bfs.size(); i++) {
		cout << result_bfs[i] << ' ';
	}
	cout << '\n';
}
// stack을 이용한 dfs 구현
void dfs(int start, vector<int> vertex[]) {
	stack<int> s;
	s.push(start);
	visit[start] = true;
	result_dfs.push_back(start);
	while (!s.empty()) {
		int current_node = s.top();
		s.pop();
		for (int i = 0; i < vertex[current_node].size(); i++) {
			int next_node = vertex[current_node][i];

			if (visit[next_node] == false) {
				result_dfs.push_back(next_node);
				visit[next_node] = true;
				// pop()을 했었기 때문에 current_node도 넣어줘야 한다
				s.push(current_node);
				s.push(next_node);
				break;
			}
		}
	}
	for (int i = 0; i < result_dfs.size(); i++) {
		cout << result_dfs[i] << ' ';
	}
	cout << '\n';

}

int main() {
	ios::sync_with_stdio(false);
	cin.tie(NULL); cout.tie(NULL);

	// input: 1 <= node <= 1000, 1<= vertex <= 10000
	// 첫번째 입력 : node 개수, vertex 개수, start 정점 번호
	int n, m, start, v1, v2;
	cin >> n >> m >> start;
	vector<int>* vertex = new vector<int>[n + 1];
	// 두번째 입력 : vertex (정점 번호 두개)
	for (int i = 0; i < m; i++) {
		cin >> v1 >> v2;
		vertex[v1].push_back(v2);
		vertex[v2].push_back(v1);
		// vertex 양방향 연결
	}
	// 하나의 정점에서 다음을 탐색할 때 node값에 순차적으로 접근해야함
	// graph를 sort해서 오름차순으로 자동 접근
	for (int i = 1; i <= n; i++) {
		// begin() : 해당 정점과 연결된 간선 vector의 iterator를 반환
		// end() : 마지막 element 뒤의 past-the-end element를 나타내는 iterator를 반환
		sort(vertex[i].begin(), vertex[i].end());
	}
	dfs(start, vertex);
	visit_init(n);
	bfs(start, vertex);

	// 동적할당 해제
	delete[] vertex;
	return 0;
}