import org.junit.Assert;
import org.junit.Test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.TemporalAccessor;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class Test3 {
    @Test
    public void 패턴_매칭_테스트() {
        final String memo = "2018-12-21 문이 잠겨있다.";

        Pattern pattern = Pattern.compile("(19|2\\d)\\d{2}[-/년]((1[0-2]|0[1-9]|[1-9])[-/]([1-2]\\d|3[0-1]|[1-9]|0[1-9])|(1[0-2]|0[1-9]|[1-9])[월]([1-2]\\d|3[0-1]|[1-9]|0[1-9])일)");
        Matcher a = pattern.matcher(memo);
        List<String> formats = new ArrayList<String>();

        formats.add("YYYY년M월D일");
        formats.add("YYYY/M/D");
        formats.add("YYYY-M-D");
        Date memDate = null;
        if (a.find()) {

            memDate = formats.stream()
                    .filter(str -> {
                        SimpleDateFormat dateTimeFormatter = new SimpleDateFormat(str);
                        try {
                            dateTimeFormatter.parse(a.group());
                        } catch (ParseException e) {
                            return false;
                        }
                        return true;
                    })
                    .map(str -> {
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
        Assert.assertNotNull(memDate);
        System.out.println(memDate.toString());
    }

    /**
     * 답안
     */
    @Test
    public void main() {
        List<String> strings = new ArrayList<>();
        strings.add("2018-12-21 문이 잠겨있다.");
        strings.add("2016년4월1일 피자가 배달왔다");
        strings.add("춤을 추고있다 - 2017/9/22 ");
        strings.add("2018-02-21 배가 고파오기 시작했다.");
        strings.add("2016년03월02일 오늘은 날씨가 맑다 ");
        strings.add("비가 쏟아진다 - 2017/06/09 ");

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
                                .filter(str -> {
                                    // 위에 정의한 DateFormat으로 변경가능한거 찾기
                                    SimpleDateFormat dateTimeFormatter = new SimpleDateFormat(str);
                                    try {
                                        dateTimeFormatter.parse(a.group());
                                    } catch (ParseException e) {
                                        return false;
                                    }
                                    return true;
                                })
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