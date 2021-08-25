package com.ssafy.algo16;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class BJ1753 {

	private static final int INF = Integer.MAX_VALUE;
	
	public static int V, E; // 정점의 개수, 간선의 개수
	
	public static List<Node>[] adjList;
	public static int[] dist;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		String info[] = br.readLine().split(" ");
		V = Integer.parseInt(info[0]);
		E = Integer.parseInt(info[1]);
		
		adjList = new ArrayList[V+1];
		for(int i = 1; i <= V; i++)
			adjList[i] = new ArrayList<>();
		
		dist = new int[V+1];
		
		Arrays.fill(dist, INF);
		
		int start = Integer.parseInt(br.readLine());
		
		int u, v, w;
		for(int e=0; e<E; e++) {
			info = br.readLine().split(" ");
			u = Integer.parseInt(info[0]);
			v = Integer.parseInt(info[1]);
			w = Integer.parseInt(info[2]);
			adjList[u].add(new Node(v, w));
		}
		
		dijkstra(start);
		
		for(int i=1; i<=V; i++) {
			if(dist[i]==INF)
				sb.append("INF\n");
			else
				sb.append(dist[i]+"\n");
		}
		
		System.out.println(sb);
		br.close();
	}
	
	private static void dijkstra(int start) {
		
		// PriorityQueue : 최단 거리를 갖는 노드를 찾음
		PriorityQueue<Node> pq = new PriorityQueue<>();
		boolean[] visited = new boolean[V+1];
		
		// 다익스트라 시작
		pq.offer(new Node(start, 0));
		dist[start] = 0;
		
		while(!pq.isEmpty()) {
			// 현재 방문한 정점
			Node now = pq.poll();
			
			// 이미 방문한 정점인 경우
			if(visited[now.v]==true) 
				continue;
			
			// 현재 정점과 인접한 정점들을 방문하며 최단 거리 갱신 
			for(Node next : adjList[now.v]) {
				if(dist[next.v] > dist[now.v] + next.weight) {
					dist[next.v] = dist[now.v] + next.weight;
					// 최단 거리를 갱신했으면 우선순위 큐에 삽입
					pq.offer(new Node(next.v, dist[next.v]));
				}
			}
			
			visited[now.v] = true;
		}
	}

}

class Node implements Comparable<Node>{
	int v;
	int weight;
	
	public Node(int v, int weight) {
		super();
		this.v = v;
		this.weight = weight;
	}

	@Override
	public int compareTo(Node o) {
		return this.weight - o.weight;
	}
	
}
