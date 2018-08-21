
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class Test4 {
    @Test
    public void test() {
        List<String> strings = new ArrayList<String>();
        strings.add("System.out.println(\"\\tabc\")\\t");
        strings.add("\tSystem.out.println(\"\\tabc\")\\t\\t\\t");
        strings.add("\t System.out.println(\"\\tabc\")\\t");
        strings.add("\t\tSystem.out.println(\"\\tabc\")\\t\\t");
        strings.add("\t\t\tSystem.out.println(\"\\tabc\")\\t\\t");
        strings.add("\t\t System.out.println(\"\\tabc\")\\t");
        strings.add("\t \tSystem.out.println(\"\\tabc\") \\t");
        strings.add(" \t\tSystem.out.println(\"\\tabc\")\\t ");
        strings.add("\t\t  \tSystem.out.println(\"\\tabc\")");
        StringBuffer sb = new StringBuffer();
        // space 몇번 처리할지
        int INPUT = 20;
        for (int i = 0; i < INPUT; i++) {
            sb.append(" ");
        }

        String space = sb.toString();
        strings.stream()
                .map(a->{
                    String s = a.replaceAll("(\\\\t|\\s)*$", "");
                    String regex = "^[\\\\t\\s]";
                    Pattern pattern = Pattern.compile(regex);
                    Matcher matcher = pattern.matcher(s);
                    List<String> strs = new ArrayList<String>();
                    while(matcher.find()) {
                        strs.add(matcher.group());
                    }
                    if(strs.isEmpty()) {
                        return s;
                    } else {
                        String result = strs.stream()
                                .reduce("", (str, item) ->
                                {
                                    return str + (item.equals("\t") ? space : item);
                                })
                                .replace(regex,"");
                        return result+s;
                    }
                })
                .forEach(a->{
                    System.out.println(a);
                });
    }
}
