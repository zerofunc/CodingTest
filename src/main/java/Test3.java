import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test3 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        List<String> strings = new ArrayList<String>();
        String read = null;
        while(( read = br.readLine())!= null) {
            strings.add(read);
        }

        br.close();
        // yyyy년mm월dd일, yyyy년월d일, yyyy/mm/dd, yyyy/m/d, yyyy-mm-dd, yyyy-m-d,
        Pattern pattern = Pattern.compile("(19|2\\d)\\d{2}[-/년]((1[0-2]|0[1-9]|[1-9])[-/]([1-2]\\d|3[0-1]|[1-9]|0[1-9])|(1[0-2]|0[1-9]|[1-9])[월]([1-2]\\d|3[0-1]|[1-9]|0[1-9])일)");
        // 변환 포맷
        List<String> formats = new ArrayList<String>();

        formats.add("YYYY년M월D일");
        formats.add("YYYY/M/D");
        formats.add("YYYY-M-D");
        strings.stream()
                .map(s -> {
                    Memo memo = new Memo();
                    // match이용해서 정규식으로 날짜 뽑기

                    Matcher a = pattern.matcher(s);
                    Date memoDate = null;

                    // pattern에 매칭되는 날짜를 찾았다면 날짜를 뽑아온다.
                    if (a.find()) {
                        memoDate = formats.stream()
                                .map(str -> {
                                    // DateFormat으로 변경가능한 데이터를 Date형식으로 변환
                                    SimpleDateFormat dateTimeFormatter = new SimpleDateFormat(str);
                                    Date date = null;
                                    try {
                                        date = dateTimeFormatter.parse(a.group());
                                    } catch (ParseException e) {

                                    }
                                    return date;
                                })
                                .filter(date -> {
                                    return date != null;
                                })
                                .findAny().orElseGet(Date::new);
                    }

                    // Date가 null이 될 수 있을려나..
                    memo.setDate(memoDate);
                    memo.setMemo(s);
                    return memo;
                })
                .sorted(Comparator.comparing(Memo::getDate))
                .forEach((memo) -> {
                    System.out.println(memo.getMemo());
                });
    }
}


class Memo {
    private Date date;
    private String memo;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;

    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }
}