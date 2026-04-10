class Solution {
    public List<List<String>> accountsMerge(List<List<String>> accounts) {

        Map<String, String> parent = new HashMap<>();
        Map<String, String> owner = new HashMap<>();

        // Initialize
        for (List<String> acc : accounts) {
            String name = acc.get(0);
            for (int i = 1; i < acc.size(); i++) {
                parent.put(acc.get(i), acc.get(i));
                owner.put(acc.get(i), name);
            }
        }

        // Union
        for (List<String> acc : accounts) {
            String firstEmail = acc.get(1);
            for (int i = 2; i < acc.size(); i++) {
                union(firstEmail, acc.get(i), parent);
            }
        }

        // Group emails by root
        Map<String, List<String>> groups = new HashMap<>();
        for (String email : parent.keySet()) {
            String root = find(email, parent);
            groups.computeIfAbsent(root, k -> new ArrayList<>()).add(email);
        }

        // Build result
        List<List<String>> result = new ArrayList<>();
        for (String root : groups.keySet()) {
            List<String> emails = groups.get(root);
            Collections.sort(emails);

            List<String> acc = new ArrayList<>();
            acc.add(owner.get(root));
            acc.addAll(emails);

            result.add(acc);
        }

        return result;
    }

    private String find(String x, Map<String, String> parent) {
        if (!parent.get(x).equals(x)) {
            parent.put(x, find(parent.get(x), parent));
        }
        return parent.get(x);
    }

    private void union(String a, String b, Map<String, String> parent) {
        String rootA = find(a, parent);
        String rootB = find(b, parent);

        if (!rootA.equals(rootB)) {
            parent.put(rootB, rootA);
        }
    }
}