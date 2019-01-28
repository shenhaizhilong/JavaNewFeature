import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: shenhaizhilong
 * @date: 2019/1/28 16:44
 */
public class StringDemo {

    public static void main(String[] args) {
        // Unmodifiable Lists
        List<String> words = Arrays.asList("Hello","World","2019");
        List<String> words2 = List.of("Hello", "World","2019","2");
       // words.add("1");   // java.lang.UnsupportedOperationException
       // words2.add("3");
        String msg1 = String.join(",", words);
        String msg2 = String.join(",", words2);
        System.out.println(msg1);
        System.out.println(msg2);

        int codepoint[] = new int[]{167988,65,168177,66};
        String s = new String(codepoint,0, codepoint.length);
        System.out.println("Str = " + s);
        System.out.println("code points int stream:");
        s.codePoints().forEach(x -> System.out.println(x));
        System.out.println("*******************");
        System.out.println("chars int stream:");
        s.chars().forEach(x -> System.out.println(x));
        System.out.println("*******************");

        System.out.println("char array length: " + s.length());
        System.out.println("code Point Length: "+ s.codePointCount(0,s.length()));
        System.out.println("*******************");


        // isBlank()
        String s2 = "abc";
        System.out.println(s2.isBlank());
        s2 = "";
        System.out.println(s2.isBlank());

        // lines()
        String s1 = "Hi\nHello\rHowdy";
        System.out.println(s1);
        //// 收集到List
        List lines = s1.lines().collect(Collectors.toList());
        System.out.println(lines);

        // strip(), stripLeading(), stripTrailing()
        //  两端，头部，尾部去white space
        String s3 = "  Java,  \tPython\t ";
        System.out.println("#" + s3 + "#");
        System.out.println("#" + s3.strip() + "#");
        System.out.println("#" + s3.stripLeading() + "#");
        System.out.println("#" + s3.stripTrailing() + "#");

        // repeat()
        // repteat n 次,为了性能内部实现为System.arrayCopy() 方法
        //final int limit = len * count;
        //final byte[] multiple = new byte[limit];
        //System.arraycopy(value, 0, multiple, 0, len);
        //int copied = len;
        //for (; copied < limit - copied; copied <<= 1) {
        //    System.arraycopy(multiple, 0, multiple, copied, copied);
        //}
        //System.arraycopy(multiple, 0, multiple, copied, limit - copied);
        String s4 = "Hello\n";
        System.out.println(s4.repeat(3));
        s4 = "Co";
        System.out.println(s4.repeat(2));
    }
}
