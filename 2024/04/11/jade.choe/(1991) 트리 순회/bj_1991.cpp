// https://www.acmicpc.net/problem/1991
// 실버 1
// 해결 - 나중에 재귀함수 1개만 써서 구현해보기
#include <iostream>
#include <queue>
using namespace std;

class Node { // 이진 트리 클래스
public:
	int data;
	bool visited = false;
	Node* left;
	Node* right;
	
	// 생성자
	Node(int value) {
		data = value;
		left = NULL;
		right = NULL;
	}
	void pushTree(int child) {
		if (data > child) {
			left = new Node(child);
		}
		else {
			right = new Node(child);
		}
	}
	Node* pushL(int value) {
		left = new Node(value);
		return left;
	}
	Node* pushR(int value) {
		right = new Node(value);
		return right;
	}
	// 트리 내부의 요소를 찾는 함수(전위 순회)
	// BFS로 구현하지 않은 이유 : queue로 인해 시간이 더 소요됨
	// DFS는 인접리스트or인접행렬 구현하기 귀찮아서, 재귀나 스택쓰면 어차피 비슷해서
	// 방문 여부를 체크 -> value를 이미 체크하는중이라 성능향상X
	// static 없이 짜보기 - 그냥 static 쓰는게 메모리+성능면에서 더 좋을듯
	Node* find(Node* node, int value) {
		// node 보존을 위해 temp로 null check 진행
		Node* temp = NULL;
		if (node == NULL || value == node->data) return node;
		// null check
		if (temp = find(node->left, value)) {
			return temp;
		}
		// left child에서 답안나오면 무조건 right child return
		else {
			return find(node->right, value);
		}
	}
	// 전위순회
	void preorder(Node* node) {
		if (node == NULL) return;
		cout << (char)(node->data);
		preorder(node->left);
		preorder(node->right);
	}
	// 중위순회
	void inorder(Node* node) {
		if (node == NULL) return;
		inorder(node->left);
		cout << (char)(node->data);
		inorder(node->right);
	}
	// 후위순회
	void postorder(Node* node) {
		if (node == NULL) return;
		postorder(node->left);
		postorder(node->right);
		cout << (char)(node->data);
	}
	// BFS가 끝나고 방문여부 초기화
	void false_init(Node* node) {
		if (node == NULL) return;
		node->visited = false;
		false_init(node->left);
		false_init(node->right);
	}
	void BFS(Node* node) {
		// queue를 활용한 BFS 구현
		queue<Node*> q;
		// 루트 노드를 queue에 넣고, 방문 표시
		q.push(node);
		node->visited = true;
		// 큐가 빌때까지(트리를 전부 순회할때까지)
		while (!q.empty()) {
			// 큐의 앞에서부터 원소를 하나씩 pop
			Node* x = q.front();
			q.pop();
			cout << x->data << ' ';

			// pop한 노드의 자식 중 방문안한 노드들을 큐에 삽입 - 자식이 없을경우도 생각
			if ((x->left!=NULL) && (!x->left->visited)) {
				q.push(x->left);
				x->left->visited = true;
			}
			else if ((x->right!=NULL)&&(!x->right->visited)) {
				q.push(x->right);
				x->right->visited = true;
			}
		}
		cout << '\n';
		// 성능 향상 필요하면 이부분 생략(일회용일 경우)
		false_init(node);
	}
};

// 백준 1991-트리 순회(전위, 중위, 후위 순회 결과 출력 프로그램)
int baekjoon_1991() {
	// 성능향상(buffer flush, buffer sync 해제)
	ios::sync_with_stdio(false);
	cin.tie(NULL); cout.tie(NULL);
	int N = 0;
	int i = 0;
	cin >> N;
	char input = 0;
	cin >> input;
	// root노드 선언
	Node* root = new Node((int)input);
	for (i = 0; i < 2; i++) {
		cin >> input;
		if (input != '.') {
			i % 2 == 0 ? root->pushL((int)input) : root->pushR((int)input);
		}
	}
	N--;
	// input 받기
	while (N--) {
		cin >> input;
		Node* node = root->find(root, (int)input);
		for (i = 0; i < 2; i++) {
			cin >> input;
			if (input != '.') {
				// if문대신 삼항연산자로
				i % 2 == 0 ? node->pushL((int)input) : node->pushR((int)input);
			}
		}
	}
	// 트리 완성, 순회 시작
	root->preorder(root);
	cout << '\n';
	root->inorder(root);
	cout << '\n';
	root->postorder(root);
	cout << '\n';

	return 0;
}