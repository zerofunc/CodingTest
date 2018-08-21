import java.util.*;
import java.util.stream.Collectors;

public class Test1 {
    public void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        String[] split = s.split(" ");

        // 입력밥은 걸 A[i]에 넣음
        int[] A = new int[split.length];
        for (int i = 0; i < split.length; i++) {
            A[i] = Integer.valueOf(split[i]);
        }


        // 목표 숫자
        int target = sc.nextInt();

        // 집합 리스트
        List<List<Integer>> lists = new ArrayList<>();
        for (int i = 1; i < (1 << A.length); i++) {
            // 부분집합을 모을 set
            HashSet<Integer> set = new HashSet<>();
            int sum = 0;
            for (int k = 0; k < A.length; k++) {
                if ((i & (1 << k)) != 0) {
                    sum += A[k];
                    set.add(A[k]);
                }
            }

            // 목표 숫자와 부분집합의 합이 동일하면
            if (target == sum) {
                // 집합 리스트에 부분 집합을 넣음
                List<Integer> collect = set.stream().collect(Collectors.toList());
                lists.add(collect);
            }
        }

        // 부분 집합을 사이즈로 정렬
        // 사이즈 작은 순으로 3개만
        lists.stream()
                .sorted(Comparator.comparing(List::size))
                .limit(3)
                .forEach(set -> {
                    List<String> strings = set.stream()
                            .sorted(Comparator.comparing(Integer::new))
                            .map(String::valueOf)
                            .collect(Collectors.toList());
                    System.out.println(String.join(",", strings));
                });
    }
}

