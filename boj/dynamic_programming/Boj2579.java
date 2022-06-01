import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Main {
    public static void solution(int n, int[] arr) {
        int[] dp = new int[n + 1];
        dp[0] = arr[0];
        dp[1] = arr[1] + arr[0];
        
        for (int i = 3 ; i < n + 1; i++) {           
            dp[i] = Math.max(arr[n] + arr[n - 1] + dp[n - 3], arr[n] + dp[n - 2]);
        }
        
        
        return ;
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());            
        }
        
        solution(arr);
        
    }
}