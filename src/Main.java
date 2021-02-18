import edu.princeton.cs.algs4.Queue;

import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {
	// write your code here
        CircularSuffixArray c = new CircularSuffixArray("boggle");

//        Arrays.stream(new int[]{1,2,3}).forEach(System.out::println);
//        Stream.of(new int[]{1,2,3}).forEach(i -> System.out.println((int)i));
        Stream.of(new String[]{"a","b","c"}).forEach(System.out::println);
    }
}
