// 백준 1504-특정한 최단 경로
//https://www.acmicpc.net/problem/1504
#include <iostream>
#include <vector>
#include <queue>
#include <limits>
#define MAX 801
#define INF numeric_limits<int>::max()
#define min(a,b) ((a)<(b)?(a):(b))
using namespace std;

int N, E, v1, v2;
int dist[MAX];
// 간선 벡터 V[a]=<b,c>
// a정점과 b정점이 c거리로 양방향 연결
vector<pair<int, int>> V[MAX];

void init() {
	cin >> N >> E;
	int a, b, c;
	for (int i = 0; i < E; i++) {
		cin >> a >> b >> c;
		V[a].push_back(make_pair(b, c));
		V[b].push_back(make_pair(a, c));
	}
	cin >> v1 >> v2;
	return;
}

void dijkstra(int start) {
	// pq: 거리, 정점
	priority_queue<pair<int, int>> pq;
	dist[start] = 0;
	pq.push(make_pair(0, start));
	while (!pq.empty()) {
		// 거리는 음수로 변환해 작은순으로 정렬되게끔
		int cost = -pq.top().first;
		int node = pq.top().second;
		pq.pop();
		// vector pop이 없으니 size를 조건으로 써도 됨
		for (int i = 0; i < V[node].size(); i++) {
			int next = V[node][i].first;
			int ncost = V[node][i].second;
			// 현재 cost+다음 정점 cost보다 거리가 더 멀면
			if (dist[next] > cost + ncost) {
				dist[next] = cost + ncost;
				pq.push(make_pair(-dist[next], next));
			}
		}
	}
}

int main() {
	ios::sync_with_stdio(false);
	cin.tie(NULL); cout.tie(NULL);
	init();
	for (int i = 1; i <= N; i++) dist[i] = INF;
	bool v1f = true;
	bool v2f = true;

	// 1번 정점부터 다익스트라
	dijkstra(1);
	// 1번 정점부터 v1, v2까지의 루트
	int route1 = dist[v1];
	if (route1 == INF) v1f = false;
	int route2 = dist[v2];
	if (route2 == INF) v2f = false;

	// 거리 초기화
	for (int i = 1; i <= N; i++) dist[i] = INF;
	// v1 정점부터 다익스트라
	dijkstra(v1);
	// v2 또는 N까지 경로가 없을경우 -1 출력 후 종료
	if ((dist[v2] == INF) || (dist[N]==INF)) {
		cout << "-1";
		return 0;
	}
	// 1->v1->v2 까지 경로 추가
	if (v1f) route1 = route1 + dist[v2];

	// 1->v2->v1->N 까지 경로 추가
	if (v2f) route2 = route2 + dist[v2] + dist[N];

	// 거리 초기화
	for (int i = 1; i <= N; i++) dist[i] = INF;
	// v2 정점부터 다익스트라
	dijkstra(v2);
	// 1->v1->v2->N 까지 경로 추가
	if (v1f) route1 = route1 + dist[N];

	int ans = min(route1, route2);

	// N까지 경로가 없을 경우 -1 return
	if (v1f==false && v2f==false) {
		ans = -1;
	}
	else if (ans==INF) {
		ans = -1;
	}
	cout << ans;

	return 0;
}
