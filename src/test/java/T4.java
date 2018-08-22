
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class T4 {
    @Test
    public void test() {
        List<String> strings = new ArrayList<String>();
        //strings.add("System.out.println(\"\\tabc\")\\t");
        //strings.add("\tSystem.out.println(\"\\tabc\")\\t\\t\\t");
        //strings.add("\t System.out.println(\"\\tabc\")\\t");
        strings.add("\t\tSystem.out.println(\"\\tabc\")\\t\\t");
        strings.add("\t\t\tSystem.out.println(\"\\tabc\")\\t\\t");
       // strings.add("\t\t System.out.println(\"\\tabc\")\\t");
       // strings.add("\t \tSystem.out.println(\"\\tabc\") \\t");
       // strings.add(" \t\tSystem.out.println(\"\\tabc\")\\t ");
        strings.add("\t\t  \tSystem.out.println(\"\\tabc\")");
        StringBuffer sb = new StringBuffer();
        // space 몇번 처리할지
        int INPUT = 4;
        for (int i = 0; i < INPUT; i++) {
            sb.append("1");
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
    private String getString(String space, String s) {
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
