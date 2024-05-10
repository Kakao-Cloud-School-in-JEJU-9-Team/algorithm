#include <iostream>
#include <queue>
#include <limits>
#define MAX 130
#define INF numeric_limits<int>::max()
using namespace std;

int dx[] = {0,0,1,-1}; 
int dy[] = {1,-1,0,0};
int map[MAX][MAX];
int dist[MAX][MAX];
bool flag;

void init() {
	for (int i = 0; i < MAX; i++) {
		for (int j = 0; j < MAX; j++) {
			dist[i][j] = INF;
		}
	
	}
}

void input(int* n) {
	cin >> *n;
	if (*n == 0) {
		flag = true;
		return;
	}
	for (int i = 0; i < *n; i++) {
		for (int j = 0; j < *n; j++) {
			cin >> map[i][j];
		}
	}

}
// 금액이 0~9이니 다익스트라 알고리즘으로 풀 수 있음
void dijkstra(int *n, int *ans) {
	// 작은 우선순위 큐(비용, (x, y))
	// 숫자를 음수로 바꿔서 push
	priority_queue<pair<int, pair<int, int>>> pq;
	// 0, 0좌표부터 시작
	pq.push(make_pair(-map[0][0], make_pair(0, 0)));
	dist[0][0] = map[0][0];

	while (!pq.empty()) {
		// 꺼낼땐 양수로 변환
		int cost = -pq.top().first;
		int x = pq.top().second.first;
		int y = pq.top().second.second;
		// 큐에서 제거
		pq.pop();

		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];

			if (nx >= 0 && ny >= 0 && nx < (*n) && ny < (*n)) {
				// 비용 = 현재 비용 + 우/좌/하/상 노드의 비용
				int ncost = cost + map[nx][ny];
				// 다음 노드의 dist가 비용보다 크면 갱신
				if (dist[nx][ny] > ncost) {
					dist[nx][ny] = ncost;
					// 다음 노드를 pq에 push
					pq.push(make_pair(-dist[nx][ny], make_pair(nx, ny)));
				}
			}
		}
	}
	// 맨 우측 아래 노드에 최소비용이 나옴
	*ans = dist[(*n) - 1][(*n) - 1];
}

int main() {
	ios::sync_with_stdio(false);
	cin.tie(NULL); cout.tie(NULL);
	int n, ans, i;
	i = 1;
	while (!flag) {
		init();
		input(&n);
		if(flag) return 0;
		dijkstra(&n, &ans);
		cout << "Problem " << i++ << ": " << ans << '\n';
	}
	return 0;
}
