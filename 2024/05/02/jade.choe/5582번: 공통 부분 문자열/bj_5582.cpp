// 백준 5582 - 공통 부분 문자열
//https://www.acmicpc.net/problem/5582
#include <iostream>
#include <string>
using namespace std;

string str1, str2;

int main() {
	ios::sync_with_stdio(0);
	cout.tie(0);
	int len = 0;
	cin >> str1 >> str2;
	int len1 = str1.length(), len2 = str2.length();
	// 처음부터 예외처리
	if (len1 == 0 || len2 == 0) {
		cout << len1;
		return 0;
	}
	else if (str1 == str2) {
		cout << len1;
		return 0;
	}
	// 4000X4000은 크기가 너무 커서 동적할당
	int** matrix = new int*[len1];
	// 배열 동적할당하면서 초기화
	for (int i = 0; i < len1; ++i) {
		matrix[i] = new int[len2];
		for (int j = 0; j < len2; ++j) {
			// 같은글자면 값 1, 아니면 값 0
			if (str1.at(i) == str2.at(j)) matrix[i][j] = 1;
			else matrix[i][j] = 0;
		}
	}
	// LCS
	// https://velog.io/@emplam27/%EC%95%8C%EA%B3%A0%EB%A6%AC%EC%A6%98-%EA%B7%B8%EB%A6%BC%EC%9C%BC%EB%A1%9C-%EC%95%8C%EC%95%84%EB%B3%B4%EB%8A%94-LCS-%EC%95%8C%EA%B3%A0%EB%A6%AC%EC%A6%98-Longest-Common-Substring%EC%99%80-Longest-Common-Subsequence
	for (int i = 1; i < len1; ++i) {
		for (int j = 1; j < len2; ++j) {
			if (str1.at(i) == str2.at(j)) {
				matrix[i][j] = matrix[i-1][j-1] + 1;
				if (len < matrix[i][j]) len = matrix[i][j];
			}
		}
	}
	cout << len;
	for (int i = 0; i < len1; i++) {
		delete[] matrix[i];
	}
	delete[] matrix;
	return 0;
}
