// 백준 11049_행렬 곱셈 순서
//https://www.acmicpc.net/problem/11049
#include <iostream>
#include <vector>
#include <limits>
using namespace std;
#define INF numeric_limits<int>::max();
// 설명 잘해준 블로그
//https://velog.io/@blacklandbird/Chained-Matrix-Multiplication
struct MATRIX {
	int x;
	int y;
};
int main() {
	ios::sync_with_stdio(0);
	cout.tie(0);
	int number;
	cin >> number;
	// 행렬 곱셈 연산수를 나타내는 행렬(최적해 행렬)
	int** matmul = new int*[number];
	// 행렬 array - 순서 교체 X
	MATRIX* mat = new MATRIX[number];
	for (int i = 0; i < number; ++i) {
		cin >> mat[i].x >> mat[i].y;
		matmul[i] = new int[number];
		// 행렬곱 초기화
		matmul[i][i] = 0;
	}
	// matmul[i][j] = 행렬 i부터 행렬 j까지 부분 행렬의 최적해
	// i번째 행렬과(부터) j번째 행렬을(까지) 행렬곱한 결과는 matmul[i][j]에 저장된다.
	int index;
	int temp;
	// 왜 1부터 시작? - 0,0은 연산할 필요가 없어서
	for (int i = 1; i < number; ++i) {
		for (int j = 0; j < number - i; ++j) {
			// 연산을 행렬의 대각 방향으로 할 수 있게끔 변수 선언
			index = i + j;
			// 곧 계산할 행렬곱 자리 초기화
			matmul[j][index] = INF;
			for (int k = j; k < index; ++k) {
				// X행렬과 Y행렬의 행렬곱 : X.row*X.col*Y.col
				// 최적해 행렬에서 j~k, k+1~index까지
				// 두개의 부분 행렬의 최적해를 더한 뒤 
				// 부분 행렬(i ~ k, k+1 ~ index)의 행렬곱 횟수 더하기
				int temp = matmul[j][k] + matmul[k + 1][index] +
					mat[j].x * mat[k].y * mat[index].y;
				// 위에서 INF로 초기화한 이유 - 행렬곱 최적해 갱신
				if (temp < matmul[j][index]) {
					matmul[j][index] = temp;
				}
			}
		}
	}
	// 정답은 행렬의 우측 위 모서리임(0번째 행렬부터 마지막 행렬까지 곱하기 위한 최적해)
	cout << matmul[0][number-1];

	for (int i = 0; i < number; i++) {
		delete[] matmul[i];
	}
	delete[] matmul;
	delete[] mat;
	return 0;
}
