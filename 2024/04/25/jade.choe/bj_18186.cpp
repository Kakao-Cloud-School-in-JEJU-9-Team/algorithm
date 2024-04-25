//https://www.acmicpc.net/problem/18186
// �ݷ� ã�� ��������
// ���� : �ִ� 3���� ������ ���忡�� ���Ű���
// B�� C���� Ŭ ���
// B�� C���� ũ�� �ִ��� ��� �����ؼ� �����ϴ°� �̵�
// �ڿ��� ������ ���ӵ� 3���� ������ ã�� ����
// ���ӵ� 3���� ������ �Ϸ�Ǹ� ���ӵ� 2���� ������ ��Ž��
// ������ ���� ������� ���

// �ѹ� Ʋ��
// Ʋ������ : B+2C ���̶�°� i�� ���忡�� B��, i+1, i+2���忡�� C�� ��ٴ���
// ���� ���� ������ B+2C�� ��ٴ� �Ҹ�����? �ƴѰŰ�����

// �� Ʋ��
// Ʋ������ : �ʹݺ� ����ó���� ������

// �� Ʋ��
// ������ �𸣰ڳ�
#include <iostream>
#include <algorithm>
using namespace std;
#define MAX 1000001
long long arr[MAX];
long long n, b, c;
void getCost() {
	long long ans = 0;
	long long minus = 0;
	if (b > c) {
		// b�� c���� ũ�� ����ؾ���
		// ���ӵ� 3�� ���� Ž��
		for (int i = n; i >= 2; i--) {
			// 3���� ���ӵǸ� �ּҰ���ŭ arr[]--, ans++
			minus = min(arr[i], arr[i - 1]);
			minus = min(arr[i - 2], minus);
			// 0�� üũ ���ؼ� ���ӵ� 2�� Ž��
			if (minus == 0) {
				minus = min(arr[i], arr[i - 1]);
				// 0�� üũ ���ؼ� ���� �� ó��
				if (minus == 0) {
					ans += arr[i] * b;
					arr[i] -= arr[i];
					// continue������ ���� ���� ��ŵ
					continue;
				}
				//ans += (arr[i - 1] * minus * b) +
				//	(arr[i] * minus * c);
				ans += (b + c) * minus;
				arr[i] -= minus;
				arr[i - 1] -= minus;
				minus = 0;
				continue;
			}
			//ans += (arr[i - 2] * minus * b) + 
			//	((arr[i - 1] + arr[i]) * minus * c);
			ans += (b + (2 * c)) * minus;
			arr[i] = arr[i] - minus;
			arr[i - 1] = arr[i - 1] - minus;
			arr[i - 2] = arr[i - 2] - minus;
		}
		minus = 0;
		// �迭�� ���� �� ó��
		// �ٸ� ������� ó���ؾ���
		// �迭 ���� ù�κ� ����ó���� �ʿ�
		{ // �迭 ��
			minus = min(arr[n - 1], arr[n]);
			ans += (b + c) * minus;
			arr[n - 1] -= minus;
			arr[n] -= minus;
			ans += b * arr[n - 1];
			ans += b * arr[n];
		}
		{ // �迭 ó��
			minus = min(arr[1], arr[2]);
			ans += (b + c) * minus;
			arr[1] -= minus;
			arr[2] -= minus;
			ans += b * arr[1];
			ans += b * arr[2];
		}

		cout << ans << '\n';
	}
	else {
		// b�� c���� �۰ų� ������ ���� ���Ѱ� ����
		for (int i = 0; i < n; i++) {
			ans += arr[i] * b;
		}
		cout << ans << '\n';
	}
	return;
}
int bj_16496() {
	ios::sync_with_stdio(false);
	cin.tie(NULL); cout.tie(NULL);
	// �Էºκ�
	cin >> n >> b >> c;
	for (int i = 0; i < n; i++) {
		cin >> arr[i];
	}
	greedy();
}