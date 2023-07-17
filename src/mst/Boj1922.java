package mst;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

import java.util.StringTokenizer;
import java.util.Arrays;

public class Boj1922 {

    static int[] parent;

    public static void solution(int m, int[][] data) {
        int result = 0;

        for (int i = 0; i < m; i++) {
            if (data[i][0] == data[i][1]) {
                continue;
            }

            if (find(data[i][0]) != find(data[i][1])) {
                result += data[i][2];
                union(data[i][0], data[i][1]);
            }
        }

        System.out.println(result);
    }

    public static int find(int a) {
        if (a == parent[a])
            return a;
        else
            return parent[a] = find(parent[a]);
    }

    public static void union(int a, int b) {
        int Pa = find(a);
        int Pb = find(b);

        if (Pa != Pb) {
            if (Pa < Pb)
                parent[Pa] = Pb;
            else
                parent[Pb] = Pa;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());
        parent = new int[n + 1];

        for (int i = 1; i < n + 1; i++) {
            parent[i] = i;
        }

        int[][] data = new int[m][3];

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            data[i][0] = Integer.parseInt(st.nextToken());
            data[i][1] = Integer.parseInt(st.nextToken());
            data[i][2] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(data, (x, y) -> x[2] - y[2]);
        solution(m, data);

    }
}