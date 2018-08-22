import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test4 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        List<String> strings = new ArrayList<String>();
        String read = null;
        while(( read = br.readLine())!= null) {
            strings.add(read);
        }

        br.close();
        StringBuffer sb = new StringBuffer();

        // space 몇번 처리할지
        int INPUT = 4;
        for (int i = 0; i < INPUT; i++) {
            sb.append("");
        }
        String space = sb.toString();
        strings.stream()
                .map(a->{
                    // 우선 뒤에 공백제거
                    String s = a.replaceAll("(\\\\t|\\s)*$", "");

                    return getString(space, s);
                })
                .forEach(a->{
                    System.out.println(a);
                });
    }

    /**
     * 앞에 탭 문자를 스페이스로 치환해서 가져오기
     * @param space
     * @param s
     * @return
     */
    private static String getString(String space, String s) {
        // 앞에 있는 탭 문자 찾기
        String regex = "^[^\\S]*\\t";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(s);
        String ma = null;
        while(matcher.find()) {
            ma = matcher.group();
        }

        // 탭문자 없으면 그대로 리턴
        if(ma == null) {
            return s;
        } else {
            // 탭문자가 있으면 스페이스로 변환시키고 원래 문자열에 있던 탭문자는 삭제.
            return ma.replace("\t",space)+s.replaceAll(matcher.pattern().pattern(),"");
        }
    }
}
