//https://www.acmicpc.net/problem/16496
#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

vector<string> number;

// ���� �������� �����ϴ� sort �˰���, cmp�Լ��� ��¦ �ٲ㼭 ���� ����
// �⺻�� : return a < b;
// false�� ������ ��ü�ϴ� ����̹Ƿ�, ba�� ū ��� false�� �����ؾ���
// �ܼ� ���ڸ� ���ϸ� �ڸ����� ���� ���� ������ ����, ���ڿ��� ���� ���� �ڸ������� ��
bool cmp(string a, string b) {
	if (a == b) return true;
	string ab = a + b;
	string ba = b + a;

	if (ab > ba) {
		return true;
	}
	else {
		return false;
	}
}

int main() {
	ios::sync_with_stdio(false);
	cin.tie(NULL); cout.tie(NULL);
	int n;
	cin >> n;
	bool check = false; // �ʱ�ȭ ���ϸ� ������ �ȵ�
	string a;
	// �Է� �޾ƿ���
	for (int i = 0; i < n; i++) {
		cin >> a;
		number.push_back(a);
		if (a != "0") check = true; // 0 0 0 0�� ������ 0�� ����ؾ���
	}
	if (!check) cout << 0;
	else {
		sort(number.begin(), number.end(), cmp);
		for (int i = 0; i < n; i++) {
			cout << number[i];
		}
	}
	// ���ĸ� �ߴµ� Ǯ��
	return 0;
}