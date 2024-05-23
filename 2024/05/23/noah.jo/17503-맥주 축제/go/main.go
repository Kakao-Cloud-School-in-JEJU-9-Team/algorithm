package main

import (
	"bufio"
	"fmt"
	"os"
	"sort"
	"strconv"
	"strings"
)

type Bear struct {
	preference int
	abv        int // Alcohol by volume
}

var buffer []Bear

func main() {
	reader := bufio.NewReader(os.Stdin)
	writer := bufio.NewWriter(os.Stdout)
	defer writer.Flush()

	line, _ := reader.ReadString('\n')
	line = strings.TrimSpace(line)
	params := strings.Split(line, " ")
	N, _ := strconv.Atoi(params[0])
	M, _ := strconv.Atoi(params[1])
	K, _ := strconv.Atoi(params[2])

	// 최소 도수 = 1, 최대 도수는 맥주들 중 max
	left := 1
	right := 0

	bears := make([]Bear, K)

	for i := 0; i < K; i++ {
		line, _ := reader.ReadString('\n')
		line = strings.TrimSpace(line)
		params := strings.Split(line, " ")
		preference, _ := strconv.Atoi(params[0])
		abv, _ := strconv.Atoi(params[1])

		if abv > right {
			right = abv
		}

		bears[i] = Bear{preference: preference, abv: abv}
	}

	// 도수 기준 오름차순 정렬
    // - 선호도는 mid 도수 미만의 맥주 선택 후 재정렬하기 때문에 오름차순, 내림차순 상관 없음
	sort.Slice(bears, func(i, j int) bool {
		return bears[i].abv < bears[j].abv
	})

	// MAX_VALUE
	minAbv := int(^uint(0) >> 1)

	for left <= right {
		mid := (left + right) / 2

		if check(N, M, bears, mid) {
			if mid < minAbv {
				minAbv = mid
			}
			right = mid - 1
		} else {
			left = mid + 1
		}
	}

	if minAbv == int(^uint(0)>>1) {
		fmt.Fprintln(writer, -1)
	} else {
		fmt.Fprintln(writer, minAbv)
	}
}

// 맥주를 N개 이상 먹을 수 있는지, 선호도를 만족하는지 탐색
// mid = 도수
func check(N int, M int, bears []Bear, mid int) bool {
	buffer = buffer[:0]

	// 도수에 대한 오름차순 정렬이기 때문에 bear[i].abv > mid라면 i 이후의 abv는 힝상 mid보다 크다.
	for _, bear := range bears {
		if bear.abv > mid {
			break
		}
		buffer = append(buffer, bear)
	}

	// 최대 도수(mid)가 너무 낮기 때문에 N개 이상의 맥주를 선택할 수 없다.
    // 최대 도수(mid)를 증가시켜 선택 가능한 맥주의 양을 늘릴 필요가 있다.
	if len(buffer) < N {
		return false
	}

	// 조건 1. 최소 N개의 맥주 만족

	// 최대 도수(mid) 미만의 맥주들 기준으로 선호도 내림차순
	sort.Slice(buffer, func(i, j int) bool {
		return buffer[i].preference > buffer[j].preference
	})

	// 상위 선호도 N개의 도수 계산
	totalPreference := 0

	for i := 0; i < N; i++ {
		totalPreference += buffer[i].preference
	}

	// 선호도 기준(M)을 만족하는가?
    // - 만족: 현재 맥주 리스트는 모든 조건(N개, M 이상)을 만족한다, 더 낮은 최대 도수(mid)도 가능한지 확인한다. (right = mid - 1)
    // - 불만: 현재의 맥주 리스트는 선호도를 만족시킬 수 없다. 도수를 높여 맥주 선택지를 늘린다. (left = mid + 1)
	return totalPreference >= M
}
