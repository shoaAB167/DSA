
import java.util.*;

public class AccountsMerge {

     public List<List<String>> accountsMerge(List<List<String>> accounts) {
        Map<String, Integer> mailMap = new HashMap<>();
        int n = accounts.size();
        UnionFind uf = new UnionFind(n);

        //this will map email to account index and find union if same mail present in different account
        for(int i=0; i<n; i++){
            List<String> mailAcc = accounts.get(i);
            for(int j=1; j<mailAcc.size(); j++){
                if(!mailMap.containsKey(mailAcc.get(j))){
                    mailMap.put(mailAcc.get(j), i);
                }else {
                    uf.union(mailMap.get(mailAcc.get(j)), i);
                }
            }
        }

        //combine account as common leader
        Map<Integer, List<String>> mailGroup = new HashMap<>();
        for(Map.Entry<String, Integer> entry : mailMap.entrySet()){
            String mail = entry.getKey();
            int value = entry.getValue();
            int leader = uf.find(value);
            mailGroup.putIfAbsent(leader, new ArrayList<>());
            mailGroup.get(leader).add(mail);
        }

        //create a result by putting name
        List<List<String>> res = new ArrayList<>();
        for (Map.Entry<Integer, List<String>> entry : mailGroup.entrySet()) {
            int accId = entry.getKey();
            List<String> emails = entry.getValue();
            Collections.sort(emails);
            List<String> merged = new ArrayList<>();
            merged.add(accounts.get(accId).get(0)); // Add account name
            merged.addAll(emails);
            res.add(merged);
        }

        return res;
     }

     public static void main(String args[]){
         AccountsMerge obj = new AccountsMerge();
         List<List<String>> accounts = new ArrayList<>();
         List<String> acc1 = new ArrayList<>();
         acc1.add("John");
         acc1.add("M1@gmail.com");
         acc1.add("M2@gmail.com");
         acc1.add("M3@gmail.com");
         acc1.add("M4@gmail.com");
         List<String> acc2 = new ArrayList<>();
         acc2.add("John");
         acc2.add("M3@gmail.com");
         acc2.add("M4@gmail.com");
         accounts.add(acc1);
         accounts.add(acc2);
         List<List<String>> res = obj.accountsMerge(accounts);
         System.out.println(res);
     }
}