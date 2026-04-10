class Solution {

    public int[] sortItems(int n, int m, int[] group, List<List<Integer>> beforeItems) {

        for (int i = 0; i < n; i++) {
            if (group[i] == -1) {
                group[i] = m++;
            }
        }

        List<List<Integer>> itemGraph = new ArrayList<>();
        List<List<Integer>> groupGraph = new ArrayList<>();

        for (int i = 0; i < n; i++) itemGraph.add(new ArrayList<>());
        for (int i = 0; i < m; i++) groupGraph.add(new ArrayList<>());

        int[] itemIndegree = new int[n];
        int[] groupIndegree = new int[m];

        for (int i = 0; i < n; i++) {
            for (int prev : beforeItems.get(i)) {
                itemGraph.get(prev).add(i);
                itemIndegree[i]++;

                if (group[i] != group[prev]) {
                    groupGraph.get(group[prev]).add(group[i]);
                    groupIndegree[group[i]]++;
                }
            }
        }

        List<Integer> itemOrder = topoSort(itemGraph, itemIndegree, n);
        List<Integer> groupOrder = topoSort(groupGraph, groupIndegree, m);

        if (itemOrder.size() == 0 || groupOrder.size() == 0) return new int[0];

        Map<Integer, List<Integer>> groupToItems = new HashMap<>();
        for (int item : itemOrder) {
            groupToItems.computeIfAbsent(group[item], k -> new ArrayList<>()).add(item);
        }

        int[] result = new int[n];
        int idx = 0;

        for (int g : groupOrder) {
            List<Integer> items = groupToItems.getOrDefault(g, new ArrayList<>());
            for (int item : items) {
                result[idx++] = item;
            }
        }

        return result;
    }

    private List<Integer> topoSort(List<List<Integer>> graph, int[] indegree, int size) {
        Queue<Integer> q = new LinkedList<>();
        List<Integer> res = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            if (indegree[i] == 0) q.offer(i);
        }

        while (!q.isEmpty()) {
            int u = q.poll();
            res.add(u);

            for (int v : graph.get(u)) {
                if (--indegree[v] == 0) {
                    q.offer(v);
                }
            }
        }

        return res.size() == size ? res : new ArrayList<>();
    }
}