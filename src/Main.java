import edu.princeton.cs.algs4.BinaryStdIn;
import edu.princeton.cs.algs4.BinaryStdOut;
import edu.princeton.cs.algs4.Queue;

import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {
	// write your code here
        while(!BinaryStdIn.isEmpty()){
            BinaryStdOut.write(BinaryStdIn.readByte());
        }
        BinaryStdOut.close();
    }
}
