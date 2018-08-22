import org.junit.Test;

import java.util.HashMap;
import java.util.Scanner;

public class Test1 {
    public static void main() {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        String[] split = s.split(" ");
        int[] A = new int[split.length + 1];
        int[] D = new int[split.length + 1];
        A[0] = 0;
        for (int i = 1; i <= split.length; i++) {
            A[i] = Integer.valueOf(split[i - 1]);
        }

        int target = sc.nextInt();

        System.out.println(solve(D, A, target));
    }
    @Test
    public void test() {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        String[] split = s.split(" ");
        int[] A = new int[split.length + 1];
        int[] D = new int[split.length + 1];
        A[0] = 0;
        for (int i = 1; i <= split.length; i++) {
            A[i] = Integer.valueOf(split[i - 1]);
        }

        int target = sc.nextInt();

        System.out.println(solve(D, A, target));

    }

    public static int[] solve(int[] D, int[] A, int target) {
        D[0] = 1;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 1; i < A.length; i++) {
            for (int j = A[i]; j <= target ;j++) {

            }
        }
        return D;
    }
}
