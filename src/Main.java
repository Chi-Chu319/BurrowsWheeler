import edu.princeton.cs.algs4.BinaryStdIn;
import edu.princeton.cs.algs4.BinaryStdOut;
import edu.princeton.cs.algs4.Queue;

import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {
	// write your code here
        int length = 100;
        int pointer = 0;
        while(!BinaryStdIn.isEmpty() && pointer < 100){
            byte b = BinaryStdIn.readByte();
            BinaryStdOut.write(b);
//            System.out.print(Integer.toBinaryString((b+256)%256));
//            System.out.print(b);
            System.out.print(Byte.toUnsignedInt(b));
            System.out.print(" ");
            pointer++;
        }
        System.out.println();
        BinaryStdOut.close();
    }
}
