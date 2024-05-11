import java.util.*;
import java.io.*;

public class Main {

    static int answer, n, d;
    static List<Node> graph[];
    static int[] distance;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());

        distance = new int[10001];
        graph = new List[10001];
        
        for (int i = 0; i < graph.length; i++) { // 각 노드별 리스트 초기화
            graph[i] = new ArrayList<>(); 
            distance[i] = i;
        }

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            graph[start].add(new Node(end, w));
        }

        shortcut(0);

        System.out.println(distance[d]);
    }

    static void shortcut(int start){
        if(start > d) { // 목적지까지 도달했을 때 종료
			return;
		}
		if(distance[start+1] > distance[start] + 1) { // 지름길을 안타고 1km 직접 이동하는 거리가 더 짧은 경우
			distance[start+1] = distance[start] + 1;
		}
		
        for (int i = 0; i < graph[start].size(); i++) { // 이동가능한 모든 노드 탐색
            if (distance[start] + graph[start].get(i).weight < distance[graph[start].get(i).endNode]) {
                distance[graph[start].get(i).endNode] = distance[start] + graph[start].get(i).weight;
            }
        }
        
		shortcut(start+1);
    }

    
    static class Node {
        int endNode;
        int weight;

        Node(int endNode, int weight){
            this.endNode = endNode;
            this.weight = weight;
        }
    }
}
