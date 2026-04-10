class Solution {
    public int[] shortestAlternatingPaths(int n, int[][] redEdges, int[][] blueEdges) {

        List<Integer>[] red = new ArrayList[n];
        List<Integer>[] blue = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            red[i] = new ArrayList<>();
            blue[i] = new ArrayList<>();
        }

        for (int[] e : redEdges) {
            red[e[0]].add(e[1]);
        }

        for (int[] e : blueEdges) {
            blue[e[0]].add(e[1]);
        }

        int[][] dist = new int[n][2]; // 0 = red, 1 = blue
        for (int i = 0; i < n; i++) {
            dist[i][0] = dist[i][1] = -1;
        }

        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{0, 0}); // last edge red
        q.offer(new int[]{0, 1}); // last edge blue
        dist[0][0] = dist[0][1] = 0;

        while (!q.isEmpty()) {
            int[] curr = q.poll();
            int node = curr[0], color = curr[1];

            if (color == 0) { // last was red → take blue
                for (int next : blue[node]) {
                    if (dist[next][1] == -1) {
                        dist[next][1] = dist[node][0] + 1;
                        q.offer(new int[]{next, 1});
                    }
                }
            } else { // last was blue → take red
                for (int next : red[node]) {
                    if (dist[next][0] == -1) {
                        dist[next][0] = dist[node][1] + 1;
                        q.offer(new int[]{next, 0});
                    }
                }
            }
        }

        int[] res = new int[n];
        for (int i = 0; i < n; i++) {
            if (dist[i][0] == -1) res[i] = dist[i][1];
            else if (dist[i][1] == -1) res[i] = dist[i][0];
            else res[i] = Math.min(dist[i][0], dist[i][1]);
        }

        return res;
    }
}