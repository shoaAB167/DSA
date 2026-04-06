import java.util.ArrayList;
import java.util.List;

public class GenerateParenthesis {
    private void backtrack(int openN, int closedN, int n, List<String> res, StringBuilder sb) {
        if (openN == closedN && openN == n) {
            res.add(sb.toString());
            return;
        }

        if (openN < n) {
            sb.append('(');
            backtrack(openN + 1, closedN, n, res, sb);
            sb.deleteCharAt(sb.length() - 1);
        }
        if (closedN < openN) {
            sb.append(')');
            backtrack(openN, closedN + 1, n, res, sb);
            sb.deleteCharAt(sb.length() - 1);
        }
    }

    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        backtrack(0, 0, n, res, sb);
        return res;
    }

    public static void main(String args[]){
        GenerateParenthesis gp = new GenerateParenthesis();
        List<String> res = gp.generateParenthesis(3);
        System.out.println(res);
    }
}