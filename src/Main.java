public class Main {

    public static String gcdOfString(String s1, String s2){
        int a = s1.length();
        int b = s2.length();
        if (!(s1 + s2).equals(s2 + s1)) {
            return "";
        }
        int len = Math.min(a,b);
        String bigStr = a > b ? s1 : s2;
        for(int i = len; i>=1; i--){
            String candidate = bigStr.substring(0, i);
            if((a % i == 0) && (b % i == 0)){
                if(isDivisible(s1,candidate) && isDivisible(s2, candidate)){
                    return candidate;
                }
            }
        }
        return "";
    }

    public static boolean isDivisible(String s1, String candidate){
        int times = s1.length()  / candidate.length();
        StringBuilder sb = new StringBuilder();
        for(int i=1; i<=times; i++){
            sb.append(candidate);
        }
        return sb.toString().equals(s1);
    }
    public static void main(String[] args) {
        String s1 = "ABABAB";
        String s2 = "ABAB";
        String result = gcdOfString(s1, s2);
        System.out.println(result);
    }
}