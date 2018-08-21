
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test4 {
    @Test
    public void test() {
        List<String> strings = new ArrayList<String>();
        strings.add("{\\t\\t");
        strings.add("\\tList<String> strings = new ArrayList<String>();\\t");
        strings.add("\\tstrings.add(\"asds\");\\t");
        strings.add("\\tfor (int i = 0; i < 4; i++) { ");
        strings.add("\\t\\tsb.append(\" \");\\t\n" +
                    "\\t}");
        strings.add("\\tstrings.add(\"asds\");\\t\\t\\t");
        strings.add("}\\t\\t");

        StringBuffer sb = new StringBuffer();

        int INPUT = 4;
        for (int i = 0; i < 4; i++) {
            sb.append(" ");
        }

        String space = sb.toString();
        strings.stream()
                .map(a->{
                    return a.replaceAll("(\\\\t|\\s)*$","").replaceAll("(\\\\t)", space);
                })
                .forEach(a->{
                    System.out.println(a);
                });


    }
}
