package utils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 특정 클래스에 속하지 않고 자주 사용하는 메서드들의 집합
 */
public final class StringUtil {

    /**
     * 문자열을 파싱해 long 값을 구한다. 파싱에 문제가 있으면 기본값 반환
     * 
     * <pre>
     * long n = Util.parseLong(&quot;12345&quot;, 0); // 12345 반환
     * long x = Util.parseLong(&quot;12345L&quot;, 0); // 0 반환
     * </pre>
     * 
     * @param num
     *            문자열
     * @param defaultVal
     *            오류 발생시 기본값
     * @return 변환된 long 값
     */
    public static long parseLong(String num, long defaultVal) {
        try {
            return Long.parseLong(num);
        } catch (Exception e) {
            return defaultVal;
        }
    }

    /**
     * 문자열을 파싱해 int 값을 구한다. 파싱에 문제가 있으면 기본값 반환
     * 
     * <pre>
     * int n = Util.parseInt(&quot;12345&quot;, 0); // 12345 반환
     * int x = Util.parseInt(&quot;0x12345&quot;, 0); // 0 반환
     * </pre>
     * 
     * @param num
     *            문자열
     * @param defaultVal
     *            오류 발생시 기본값
     * @return 변환된 int 값
     */
    public static int parseInt(String num, int defaultVal) {
        try {
            return Integer.parseInt(num);
        } catch (Exception e) {
            return defaultVal;
        }
    }

    /**
     * 문자열을 파싱해 double 값을 구한다. 파싱에 문제가 있으면 기본값 반환
     * 
     * <pre>
     * double n = Util.parseDouble(&quot;+12.345&quot;, 0); // 12.345 반환
     * double x = Util.parseDouble(&quot;-.123&quot;, 0); // -0.123 반환
     * double y = Util.parseDouble(&quot;.123.&quot;, 0); // 0 반환
     * </pre>
     * 
     * @param num
     *            문자열
     * @param defaultVal
     *            오류 발생시 기본값
     * @return 변환된 double 값
     */
    public static double parseDouble(String num, double defaultVal) {
        try {
            return Double.parseDouble(num);
        } catch (Exception e) {
            // Double.parseDouble에 null을 전달하면
            // NumberFormatException이 아니라
            // NullPointerException이 발생한다.....
            return defaultVal;
        }
    }

    final private static Pattern digitYmdPattern1 = Pattern.compile("^(\\d+)\\D+(\\d+)\\D+(\\d+)"); // 09/09/09
                                                                                                    // 또는
                                                                                                    // 09-09-09
                                                                                                    // 또는
                                                                                                    // 2009-09-09
                                                                                                    // 또는
                                                                                                    // 2009년
                                                                                                    // 9월
                                                                                                    // 9일
                                                                                                    // 형식
    final private static Pattern digitYmdPattern2 = Pattern.compile("^(\\d{8}|\\d{6})"); // 20090909
                                                                                         // 또는
                                                                                         // 090909
                                                                                         // 형식
    final private static Pattern nameYmdPattern = Pattern.compile("^(\\w+)\\W+(\\w+)\\W+(\\d+)"); // Sep
                                                                                                  // 09
                                                                                                  // 09
                                                                                                  // 또는
                                                                                                  // 09
                                                                                                  // Sep
                                                                                                  // 09
                                                                                                  // 형식
    final private static Pattern hmsPattern = Pattern
            .compile("(^|\\b)(\\d{1,2})\\D+(\\d{1,2})(\\D+(\\d{1,2})\\D*)?$"); // 12:34:56
                                                                               // 또는
                                                                               // 12시
                                                                               // 34분
                                                                               // 56초
                                                                               // 형식
    final private static String[] monthNames = { "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul",
            "Aug", "Sep", "Oct", "Nov", "Dec" };

    /**
     * 2009-01-01, 20090101, 090101, 09/01/01, Sep 09 09 등 다양한 형식의 날짜, 시간을 자동
     * 인식하여 unix 시간으로 반환한다.
     * 
     * @param dateStr
     * @param defaultMillis
     *            파싱 오류 발생시 defaultMillis를 반환한다.
     * @return
     */
    public static long parseFuzzyDateTime(String dateStr, long defaultMillis) {
        return parseFuzzyDateTime(dateStr, defaultMillis, false);
    }

    /**
     * 2009-01-01, 20090101, 090101, 09/01/01, Sep 09 09 등 다양한 형식의 날짜, 시간을 자동
     * 인식하여 unix 시간으로 반환한다.
     * 
     * @param dateStr
     * @param defaultMillis
     *            파싱 오류 발생시 defaultMillis을 반환한다.
     * @param defaultMonthFirst
     *            01-01-2009와 같은 형식일 때 월을 우선하게 한다
     * @return
     */
    public static long parseFuzzyDateTime(String dateStr, long defaultMillis,
            boolean defaultMonthFirst) {
        final Calendar now = Calendar.getInstance();
        final int currYear = now.get(Calendar.YEAR);

        if (dateStr == null || (dateStr = dateStr.trim()).length() < 6)
            return defaultMillis;

        Matcher matcher;
        int year = -1;
        int month = 0;
        int day = 0;

        if ((matcher = digitYmdPattern1.matcher(dateStr)).find()) {
            int val1 = Integer.parseInt(matcher.group(1));
            int val2 = Integer.parseInt(matcher.group(2));
            int val3 = Integer.parseInt(matcher.group(3));

            if (val3 > 31 || (val1 < 13 && val2 > 12) || (val1 > 12 && val1 < 32 && val2 < 13)) { // val3가
                                                                                                  // 연도인
                                                                                                  // 경우
                year = val3;
                if (val2 > 12) {
                    month = val1 - 1;
                    day = val2;
                } else {
                    month = (defaultMonthFirst ? val1 : val2) - 1;
                    day = (defaultMonthFirst ? val2 : val1);
                }
            } else { // val1이 연도인 경우
                year = val1;
                month = val2 - 1;
                day = val3;
            }

        } else if ((matcher = digitYmdPattern2.matcher(dateStr)).find()) {
            int val = Integer.parseInt(matcher.group(1));
            year = val / 10000;
            month = val % 10000 / 100 - 1;
            day = val % 100;

        } else if ((matcher = nameYmdPattern.matcher(dateStr)).find()) {
            String val1 = matcher.group(1);
            String val2 = matcher.group(2);
            year = Integer.parseInt(matcher.group(3));

            if (val1.length() <= 2) {
                String temp = val2;
                val2 = val1;
                val1 = temp;
            }

            for (int i = 0; i < monthNames.length; i++)
                if (monthNames[i].equals(val1)) {
                    month = i;
                    break;
                }

            day = Integer.parseInt(val2);

        } else
            matcher = null;

        if (year >= 0 && matcher != null) {
            if (year <= currYear % 100)
                year += 2000;
            else if (year < 1000)
                year += 1900;

            int hour = 0, min = 0, sec = 0;

            String hms = dateStr.substring(matcher.group().length()).trim();
            if (hms.length() > 0 && (matcher = hmsPattern.matcher(hms)).find()) {
                hour = Integer.parseInt(matcher.group(2));
                min = Integer.parseInt(matcher.group(3));
                String val = matcher.group(5);
                sec = val != null && val.length() > 0 ? Integer.parseInt(val) : 0;
            }

            Calendar cal = new GregorianCalendar(year, month, day, hour, min, sec);
            return cal.getTimeInMillis();

        } else
            return defaultMillis;
    }

    private final static char[] upperLetters = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',
            'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };
    private final static char[] lowerLetters = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j',
            'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z' };

    /**
     * Java 식별어 형식의 문자열 만들기. 모든 단어의 첫 글자는 대문자가 되며 언더스코어(_)는 제거된다. 영문 알파벳에 대해서만
     * 처리된다.
     * 
     * @param str
     *            원래 문자열
     * @param initialUpper
     *            참이면 원래 문자열의 첫 글자를 대문자로 만든다.
     * @return TODO BeanListHandler와 같은 경우 언더스코어 처리 방법을 지정할 수 있다. 아예 이런 명칭 변환에
     *         대해 별도의 클래스로 뽑아내는 것이 어떨지!
     */
    public static String makeJavaName(String str, boolean initialUpper) {
        char[] buf = str.toCharArray();
        int len = buf.length;
        int count = 0;

        for (int i = 0; i < len; i++) {
            char c = buf[i];

            if (c == '_') {
                initialUpper = true;
            } else {
                if (initialUpper) {
                    buf[count++] = 'a' <= c && c <= 'z' ? upperLetters[c - 'a'] : c;
                } else {
                    buf[count++] = 'A' <= c && c <= 'Z' ? lowerLetters[c - 'A'] : c;
                }
                initialUpper = false;
            }
        }

        return new String(buf, 0, count);
    }

    static final char[] htmlRaw = { '<', '>', '&', '"', '\'', (char) 0xa0 };
    static final String[] htmlEncoded = { "&lt;", "&gt;", "&amp;", "&quot;", "&#39;", "&nbsp;" };
    static final String[] htmlEncoded2 = { "&lt;", "&gt;", "&amp;", "&quot;", "&apos;", "&nbsp;" };

    /**
     * html, xml 태그 등을 있는 그대로 웹페이지에 나타내기 위해 이스케이핑하는 메서드 &lt;는 &amp;lt;로, &amp;는
     * &amp;amp;로, "는 &amp;quot;로 변환한다.
     * 
     * @param raw
     *            원래의 html
     * @return 이스케이프된 html
     */
    public static String escapeHTML(String raw) {
        if (raw == null)
            return null;

        int n = raw.length();
        StringBuffer sb = new StringBuffer(n + 100);

        outer:

        for (int i = 0; i < n; i++) {
            char c = raw.charAt(i);

            for (int j = 0; j < htmlRaw.length; j++)
                if (c == htmlRaw[j]) {
                    sb.append(htmlEncoded[j]);
                    continue outer;
                }

            sb.append(c);
        }

        return sb.toString();
    }

    final static Pattern htmlEntityPattern = Pattern.compile("&#(\\d+);");

    /**
     * html, xml 인코드된 문자열을 원래의 문자로 변환한다. &amp;lt;는 &lt;로, &amp;amp;는 &amp;로,
     * &amp;quot;는 " 등으로 변환한다.
     * 
     * @param raw
     *            원래의 html
     * @return 이스케이프된 html
     */
    public static String unescapeHTML(String encoded) {
        if (encoded == null)
            return null;

        // 먼저 숫자로 인코드된 문자열을 복원
        Matcher m = htmlEntityPattern.matcher(encoded);
        StringBuffer sb = new StringBuffer();

        while (m.find()) {
            char ch = (char) Integer.parseInt(m.group(1)); // 찾은 숫자 문자열을 문자로 변환
            m.appendReplacement(sb, Character.toString(ch));
        }
        m.appendTail(sb);

        // 흔히 사용하는 기호 복원
        // TODO htmlEncoded에 들어있는 값의 순서에 따라 결과가 달라질 수 있으므로 수정 필요
        for (int i = 0; i < htmlEncoded2.length; i++) {
            int occ = 0;
            int start = 0;
            encoded = htmlEncoded2[i];
            int encodedLen = encoded.length();

            while (-1 != (occ = sb.indexOf(encoded, start))) {
                String replace = Character.toString(htmlRaw[i]);
                sb.replace(occ, occ + encodedLen, replace);
                start = occ + replace.length();
            }
        }

        return sb.toString();
    }

    /**
     * html, xml에서 태그를 걷어내는 메서드. &nbsp;를 빈칸으로 바꾸고 무조건 &lt;부터 &gt; 사이의 내용을 걷어내므로
     * 정확한 결과가 나오지 않을 수 있다.
     * 
     * @param raw
     *            원래의 html
     * @return 태그가 제거된 htmls
     */
    public static String stripHTMLSimple(String raw) {
        if (raw == null)
            return null;

        int n = raw.length();
        StringBuffer sb = new StringBuffer(n + 100);

        for (int i = 0; i < n; i++) {
            char c = raw.charAt(i);
            if (c == '<') {
                do
                    i++;
                while (i < n && raw.charAt(i) != '>');

            } else
                sb.append(c);
        }

        return StringUtil.replace(StringUtil.replace(sb.toString(), "&nbsp;", " "), "&nbsp", " ");
    }

    /**
     * 배열의 값들을 연결하여 문자열로 만든다. null인 값은 연결되지 않는다. StringBuffer를 사용하지 않고 char 배열을
     * 직접 처리하는데 20% 이상 속도 향상이 있는 것 같다.
     * 
     * <pre>
     * String[] values = { &quot;I&quot;, &quot;am&quot;, &quot;a&quot;, &quot;boy&quot; };
     * String result = Util.joinArray(&quot; &quot;, values); // &quot;I am a boy&quot; 반환
     * </pre>
     * 
     * @param values
     *            연결할 값의 배열
     * @param delimiter
     *            연결점마다 반복해서 끼워넣을 문자열. null이면 끼워넣지 않는다.
     * @return 새로운 문자열
     */
    public static String joinArray(String delimiter, Object... values) {
        if (values == null || values.length == 0)
            return "";

        String[] stringValues = new String[values.length];
        int delimiterLen = delimiter == null ? 0 : delimiter.length();
        int length = delimiterLen * (values.length - 1);

        for (int i = 0; i < values.length; i++) {
            if (values[i] != null)
                length += (stringValues[i] = values[i].toString()).length();
        }

        char[] delim;
        if (delimiterLen > 0) {
            delim = new char[delimiterLen];
            delimiter.getChars(0, delimiterLen, delim, 0);
        } else
            delim = null;

        char[] buf = new char[length];
        length = stringValues[0].length();
        stringValues[0].getChars(0, length, buf, 0);

        for (int i = 1; i < stringValues.length; i++) {
            String s = stringValues[i];
            if (s != null) {
                if (delimiterLen > 0) {
                    System.arraycopy(delim, 0, buf, length, delimiterLen);
                    length += delimiterLen;
                }
                s.getChars(0, s.length(), buf, length);
                length += s.length();
            }
        }

        return new String(buf);
    }

    /**
     * 한 문자열을 반복하여 연결한 새로운 문자열 만들기. StringBuffer를 사용하지 않고 char 배열을 직접 처리하는데 20%
     * 이상 속도 향상이 있는 것 같다.
     * 
     * <pre>
     * String s = Util.repeat(&quot;?&quot;, 5, &quot;, &quot;); // &quot;?, ?, ?, ?, ?&quot; 반환
     * </pre>
     * 
     * @param s
     *            반복할 문자열
     * @param count
     *            반복 횟수
     * @param delimiter
     *            연결점마다 반복해서 끼워넣을 문자열. null이면 끼워넣지 않는다.
     * @return
     */
    public static String repeat(String s, int count, String delimiter) {
        if (s == null || count <= 0)
            return "";

        int delimiterLen = delimiter == null ? 0 : delimiter.length();
        int strLen = s.length();
        int length = delimiterLen * (count - 1) + strLen * count;

        char[] delim;
        if (delimiterLen > 0) {
            delim = new char[delimiterLen];
            delimiter.getChars(0, delimiterLen, delim, 0);
        } else
            delim = null;

        char[] buf = new char[length];
        char[] str = new char[strLen];
        s.getChars(0, strLen, str, 0);

        length = strLen;
        System.arraycopy(str, 0, buf, 0, length);

        for (int i = 1; i < count; i++) {
            if (delimiterLen > 0) {
                System.arraycopy(delim, 0, buf, length, delimiterLen);
                length += delimiterLen;
            }
            System.arraycopy(str, 0, buf, length, strLen);
            length += strLen;
        }

        return new String(buf);
    }

    /**
     * 문자열에서 특정 부분 문자열을 전부 다른 문자열로 치환하는 메서드
     * 
     * @param orig
     *            원래의 문자열
     * @param o
     *            찾을 문자열
     * @param n
     *            새로 대치하는 문자열
     * @return 변환된 문자열
     */
    public static String replace(String orig, String o, String n) {
        return replace(orig, o, n, false);
    }

    /**
     * 문자열에서 특정 부분 문자열을 전부 다른 문자열로 치환하는 메서드
     * 
     * @param orig
     *            원래의 문자열
     * @param o
     *            찾을 문자열
     * @param n
     *            새로 대치하는 문자열
     * @param ignoreCase
     *            대소문자 구별 여부
     * @return 변환된 문자열
     */
    public static String replace(String orig, String o, String n, boolean ignoreCase) {
        if (orig == null || orig.length() == 0 || o == null || o.length() == 0 || n == null)
            return orig;

        int olen = o.length();
        StringBuffer sb = new StringBuffer(olen + 100);
        int occ = 0;
        int start = 0;
        String newOrig = ignoreCase ? orig.toLowerCase() : orig;
        if (ignoreCase)
            o = o.toLowerCase();

        while (-1 != (occ = newOrig.indexOf(o, start))) {
            sb.append(orig.substring(start, occ));
            sb.append(n);
            start = occ + olen;
        }

        sb.append(orig.substring(start));
        return sb.toString();
    }

    private static final Pattern tagPattern = Pattern.compile("&lt;(/?)(\\w+)\\b([^>]*+)(>?)",
            Pattern.DOTALL);
    private static final Pattern goodTags = Pattern.compile("^string|b|em|i|strike|u|"
            + "li|br|sup|sub|blockquote|tbody|thead|tfoot|div|span|code|pre|address|"
            + "h[1-6]|dd|dl|dt|cite|abbr|acronym|bdo|dfn|fieldset|kbd|legend|samp|"
            + "small|tt|var|big$");
    private static final Pattern attribPattern = Pattern.compile(
            "\\s(\\w+)(?:\\s*=\\s*(\"[^\"]*(?:\"|$)|'[^']*(?:'|$)|[^\"'\\s]+))?", Pattern.DOTALL);
    private static final Pattern goodAttribs = Pattern.compile("^title|dir|lang$");
    private static final HashMap<String, Pattern> goodTagsWithAttribs = new HashMap<String, Pattern>();
    private static final String[][] tagsWithAttribs = {
            { "a", "^rel|rev|charset|hreflang|tabindex|accesskey|type|name|href|target|title$" },
            { "ol", "^type|compact$" },
            { "ul", "^type|compact$" },
            {
                    "table",
                    "^border|cellspacing|cellpadding|width|frame|rules|height|align|summary|bgcolor|background|bordercolor$" },
            { "tr", "^rowspan|width|height|align|valign|bgcolor|background|bordercolor$" },
            { "td",
                    "^colspan|rowspan|width|height|align|valign|bgcolor|background|bordercolor|scope$" },
            { "th", "^colspan|rowspan|width|height|align|valign|scope$" },
            { "hr", "^size|noshade$" }, { "font", "^face|size|color$" },
            { "del", "^datetime|cite$" }, { "ins", "^datetime|cite$" }, { "map", "^name$" },
            { "area", "^shape|coords|href|alt|target$" },
            { "col", "^align|char|charoff|span|valign|width$" },
            { "colgroup", "^align|char|charoff|span|valign|width$" }, { "label", "^for$" },
            { "optgroup", "^label|disabled$" }, { "option", "^disabled|label|selected|value$" },
            { "q", "^cite$" },
            { "img", "^longdesc|usemap|src|border|alt|title|hspace|vspace|width|height|align$" } };
    static {
        for (int i = 0; i < tagsWithAttribs.length; i++)
            goodTagsWithAttribs.put(tagsWithAttribs[i][0], Pattern.compile(tagsWithAttribs[i][1]));
    };

    /**
     * 허용된 태그가 아니면 <를 &lt;로 바꾸고 속성은 제거하는 메서드. 또한 태그, 속성 이름은 모두 소문자로 변환된다.
     * 
     * @param html
     * @return
     */
    public static String sanitizeHtml(String html) {
        // 일단 모든 <를 &lt;로 바꿔 태그가 아닌 것들이 잘 처리되도록 한다.
        html = replace(html, "<", "&lt;");

        Matcher m = tagPattern.matcher(html);
        StringBuffer sb = new StringBuffer();
        while (m.find()) {
            String tag = m.group(2).toLowerCase();
            String rest = m.group(3);

            StringBuffer sb2 = new StringBuffer("<").append(m.group(1)).append(tag);
            Pattern p = goodTagsWithAttribs.get(tag);
            if (p != null) {
                Matcher m2 = attribPattern.matcher(rest);
                while (m2.find()) {
                    String attrib = m2.group(1).toLowerCase();
                    String value = m2.group(2);
                    if ((attrib.equals("href") || attrib.equals("src")) && badProtocolsIn(value)) {
                        sb2.append(" ").append(attrib).append("=\"#\"");
                        continue;
                    }
                    if (goodAttribs.matcher(attrib).matches() || p.matcher(attrib).matches()) {
                        appendAttrib(sb2, attrib, value);
                    }
                }
                sb2.append(m.group(4));
            } else if (goodTags.matcher(tag).matches()) {
                Matcher m2 = attribPattern.matcher(rest);
                while (m2.find()) {
                    String attrib = m2.group(2).toLowerCase();
                    String value = m2.group(2);
                    if (goodAttribs.matcher(attrib).matches()) {
                        appendAttrib(sb2, attrib, value);
                    }
                }
                sb2.append(m.group(4));
            } else {
                sb2 = new StringBuffer("&lt;").append(m.group(1)).append(tag).append("&gt;");
            }

            m.appendReplacement(sb, sb2.toString());
        }
        m.appendTail(sb);

        return sb.toString();
    }

    private static boolean badProtocolsIn(String value) {
        if (value.charAt(0) == '"' || value.charAt(0) == '\'')
            value = value.substring(1);

        value = unescapeHTML(value).trim();
        String[] protocols = { "javascript:", "vbscript:", "about:" };
        for (int i = 0; i < protocols.length; i++)
            if (value.regionMatches(true, 0, protocols[i], 0, protocols[i].length()))
                return true;

        return false;
    }

    private static void appendAttrib(StringBuffer buf, String attrib, String val) {
        buf.append(" ").append(attrib).append("=").append(val);
        if (val.charAt(0) == '"') {
            if (val.charAt(val.length() - 1) != '"')
                buf.append('"');
        } else if (val.charAt(0) == '\'') {
            if (val.charAt(val.length() - 1) != '\'')
                buf.append('\'');
        }
    }

    private static final char[] CHO =
    /* ㄱ ㄲ ㄴ ㄷ ㄸ ㄹ ㅁ ㅂ ㅃ ㅅ ㅆ ㅇ ㅈ ㅉ ㅊ ㅋ ㅌ ㅍ ㅎ */
    { 0x3131, 0x3132, 0x3134, 0x3137, 0x3138, 0x3139, 0x3141, 0x3142, 0x3143, 0x3145, 0x3146,
            0x3147, 0x3148, 0x3149, 0x314a, 0x314b, 0x314c, 0x314d, 0x314e };
    private static final char[] CHO2 =
    /*
     * ㄱ ㄴ ㄷ ㄹ ㅁㅂ ㅅ ㅇ ㅈ ㅊㅋ ㅌ ㅍ ㅎ
     */
    { 0x3131, 0x3134, 0x3137, 0x3139, 0x3141, 0x3142, 0x3145, 0x3147, 0x3148, 0x314a, 0x314b,
            0x314c, 0x314d, 0x314e, 0X317f };

    private static final String[] CHO3 =
    /*
     * ㄱ ㄴ ㄷ ㄹ ㅁㅂ ㅅ ㅇ ㅈ ㅊㅋ ㅌ ㅍ ㅎ
     */
    { "가", "나", "다", "라", "마", "바", "사", "아", "자", "차", "카", "타", "파", "하", "힣" };

    private static final char[] JUN =
    /* ㅏㅐㅑㅒㅓㅔㅕㅖㅗㅘㅙㅚㅛㅜㅝㅞㅟㅠㅡㅢㅣ */
    { 0x314f, 0x3150, 0x3151, 0x3152, 0x3153, 0x3154, 0x3155, 0x3156, 0x3157, 0x3158, 0x3159,
            0x315a, 0x315b, 0x315c, 0x315d, 0x315e, 0x315f, 0x3160, 0x3161, 0x3162, 0x3163 };
    /* X ㄱㄲㄳㄴㄵㄶㄷㄹㄺㄻㄼㄽㄾㄿㅀㅁㅂㅄㅅㅆㅇㅈㅊㅋㅌㅍㅎ */
    private static final char[] JON = { 0x0000, 0x3131, 0x3132, 0x3133, 0x3134, 0x3135, 0x3136,
            0x3137, 0x3139, 0x313a, 0x313b, 0x313c, 0x313d, 0x313e, 0x313f, 0x3140, 0x3141, 0x3142,
            0x3144, 0x3145, 0x3146, 0x3147, 0x3148, 0x314a, 0x314b, 0x314c, 0x314d, 0x314e };

    public static void main(String[] arg) throws Exception {

        List<Map<String, Integer>> list = new ArrayList<Map<String, Integer>>();
        String tempStr = "가나다라마바사아자차카타파하ㄱㄴㄷㄹㅁㅂㅅㅇㅈㅊㅋㅍㅎ1234567890abcd";
        // String tempStr = "1234567890";
        String lastStr = "";
        System.out.println(tempStr);
        for (int i = 0; i < tempStr.length(); i++)
        // for(int i = 0 ; i < 1;i++)
        {
            Map<String, Integer> map = new HashMap<String, Integer>();
            char test = tempStr.charAt(i);

            if (test >= 0xAC00) {
                char uniVal = (char) (test - 0xAC00);

                char cho = (char) (((uniVal - (uniVal % 28)) / 28) / 21);
                char jun = (char) (((uniVal - (uniVal % 28)) / 28) % 21);
                char jon = (char) (uniVal % 28);

                // for(int c=0; c<100 ; c++){
                // System.out.println(""+(char)(test+c)+"// 0x" +
                // Integer.toHexString((char) (test+c)) +", " + (int)test);
                // }

                System.out.println("" + test + "// 0x" + Integer.toHexString((char) test) + ", "
                        + (int) test);

                System.out.println("" + CHO[cho] + "// 0x" + Integer.toHexString((char) cho));
                System.out.println("" + JUN[jun] + "// 0x" + Integer.toHexString((char) jun));
                if ((char) jon != 0x0000)
                    System.out.println("" + JON[jon] + "// 0x" + Integer.toHexString((char) jon));

                int jj = 0;
                for (; jj < CHO2.length; jj++) {

                    if (CHO[cho] < CHO2[jj]) {
                        break;
                    }
                }

                // System.out.println("test : "+ (char)(CHO2[jj]));
                System.out.println("test : " + (CHO3[jj]) + ", " + ((int) CHO2[jj]));

                map.put("cho", (int) cho);
                map.put("jun", (int) jun);
                map.put("jon", (int) jon);
                list.add(map);

            } else if (test >= 0x3131 && test <= 0X317f) {
                // private static final char[] CHO2 =
                // /*ㄱ ㄴ ㄷ ㄹ ㅁ
                // *ㅂ ㅅ ㅇ ㅈ ㅊ
                // *ㅋ ㅌ ㅍ ㅎ */
                // { 0x3131, 0x3134, 0x3137, 0x3139, 0x3141,
                // 0x3142, 0x3145, 0x3147, 0x3148, 0x314a,
                // 0x314b, 0x314c, 0x314d, 0x314e, 0X317f};

                int jj = 0;
                for (; jj < CHO2.length; jj++) {

                    if (test < CHO2[jj]) {
                        break;
                    }
                }

                // System.out.println("test : "+ (char)(CHO2[jj]));
                System.out.println(jj + ", " + "test : " + (CHO3[jj]) + ", " + ((int) CHO2[jj]));
            } else {

                // 한글이 아니무니다.
                System.out.println((char) (test));
            }
        }

        for (int i = 0; i < list.size(); i++) {
            int a = (int) (list.get(i)).get("cho");
            int b = (int) (list.get(i)).get("jun");
            int c = (int) (list.get(i)).get("jon");

            char temp = (char) (0xAC00 + 28 * 21 * (a) + 28 * (b) + (c));

            lastStr = lastStr.concat(String.valueOf(temp));
            // System.out.println(""+ (char)(0xAC00 + 28 * 21 *(a) + 28 * (b) +
            // (c) ));

        }

        System.out.println("" + lastStr);
        /*
         * System.out.println(""+ (((Map)(list.get(0))).get("cho")) );
         * System.out.println(""+ ((Map)(list.get(0))).get("jun") );
         * System.out.println(""+ ((Map)(list.get(0))).get("jon") );
         */

    }
    
    public static Map getParamListToOne(Map origin){
        HashMap returnMap = new HashMap();

        if(origin==null) return  returnMap;
        Set set = origin.keySet();
        Object[] keys = set.toArray();

        for (int j = 0; j < keys.length; j++) {
            if(origin.get(keys[j]).getClass().isArray()){
                Object[] obj1 =(String[]) origin.get(keys[j]);
                if(obj1.length==1){
                    returnMap.put(keys[j].toString(), obj1[0]);
                }else{
                    returnMap.put(keys[j].toString(), obj1);    
                }
            }else{
                Object obj1 =(String[]) origin.get(keys[j]);
                returnMap.put(keys[j].toString(), origin.get(keys[j]));
            }
        }

        return returnMap;

    }
}
