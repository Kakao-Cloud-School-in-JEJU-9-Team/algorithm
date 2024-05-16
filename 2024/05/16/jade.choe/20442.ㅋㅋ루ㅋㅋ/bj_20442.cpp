//https://www.acmicpc.net/problem/20442
//ㅋㅋ루ㅋㅋ
#include <iostream>
#include <vector>
using namespace std;

int leftk[3000001];
int rightk[3000001];

int main() {
	ios::sync_with_stdio(false);
	cin.tie(NULL); cout.tie(NULL);

	int kcount = 0;
	int res = 0;
	string input;
	cin >> input;
	int left = 0;
	int a, b, save;
	
	vector<int> rindex;
	
	for (int i = 0; i < input.size(); i++) {
		if (input[i] == 'R') {
			rindex.push_back(i);
		}
		else {
			kcount++;
			continue;
		}
		leftk[i] = kcount;
	}
	kcount = 0;
	for (int i = input.size() - 1; i >= 0; i--) {
		if (input[i] == 'K') {
			kcount++;
			continue;
		}
		rightk[i] = kcount;
	}
	// two pointer
	int right = rindex.size() - 1;
	while (left <= right) {
		// left기준 왼쪽 k 개수와 right기준 오른쪽 k 개수 구하기
		int a = leftk[rindex[left]];
		int b = rightk[rindex[right]];
		
		// 결과값 갱신
		// right가 R index의 vector size와 같으니
		// right-left+1은 R의 갯수와 같음
		// min(a, b)는 왼쪽or오른쪽 중 더 적은 K갯수
		// 문자열이 KKKKKRRKRKRRKK 이면 KKRRRRRKK가 추출됨
		save = max(res, (right - left + 1 + (min(a, b) * 2)));
		if (res < save) {
			res = save;
		}
		
		// 여기까지하면 KRRRKKKKKRKKKKKKRK가 나왔을때 KRRRRRK밖에 추출이 안됨
		// 그래서 아래 조건문을 추가해야함
		
		// 왼쪽 K가 더 많으면
		if (a > b) {
			right--;
		}
		// 오른쪽 K가 더 많으면
		else {
			left++;
		}
	}
	cout << res;
	// R로만 이루어진 문자열에서 ㅋㅋ루ㅋㅋ찾기
	// R과 양옆에 K가 붙은 문자열에서 ㅋㅋ루ㅋㅋ찾기
	// K가 몇개 붙었는지도 봐야할듯
	// K를 지우는거보다 R을 지우는게 더 길수도 있음
	// 위 세개중에 제일 긴거 리턴하면될듯
	// 굳이 rcount 셀 필요 없으니 삭제

	return 0;
}
