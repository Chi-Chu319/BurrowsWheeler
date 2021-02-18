import edu.princeton.cs.algs4.BinaryStdIn;
import edu.princeton.cs.algs4.BinaryStdOut;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class BurrowsWheeler {

    private static class KeyIndexCounting{
        // linear time sorting for ASCII chars.
        private char[] aux;
        private int[] countArray;
        private int R = 256;

        public KeyIndexCounting(char[] chars){
            char[] aux = new char[chars.length];
            int[] count = new int[R+1];
            // counting the appearance
            for (char aChar : chars) {
                count[aChar + 1]++;
            }
            // compute the cumulates
            for(int r = 0; r < R; r++){
                if (count[r] != 0){
                    count[r+1] += count[r];
                }
            }
            this.countArray = count.clone();
            // place corresponding elements into aux.
            for (char c: chars){
                aux[count[c]++] = c;
            }
            this.aux = aux;
        }

        public char[] getAux(){ return aux; }

        public int[] getCount(){ return countArray;}
    }

    // apply Burrows-Wheeler transform,
    // reading from standard input and writing to standard output
    public static void transform(){
        StringBuilder originalBuilder = new StringBuilder();
        while (!BinaryStdIn.isEmpty()){
            originalBuilder.append((char) Byte.toUnsignedInt(BinaryStdIn.readByte()));
        }
        String original = originalBuilder.toString();
        CircularSuffixArray circularSuffixArray = new CircularSuffixArray(original);

        StringBuilder builder = new StringBuilder();
        for(int i = 0; i< original.length(); i++){
            int idx = (circularSuffixArray.index(i) - 1 + original.length())%original.length();
            builder.append(original.charAt(idx));

            // print out the idx
            if(circularSuffixArray.index(i) == 0){
                BinaryStdOut.write(i, 32);
//                System.out.print(i);
            }
        }

        String transformed = builder.toString();
        for(char c:transformed.toCharArray()){
            BinaryStdOut.write(c, 8);
        }
        BinaryStdOut.close();
    }


    // apply Burrows-Wheeler inverse transform,
    // reading from standard input and writing to standard output
    public static void inverseTransform(){
        int first = BinaryStdIn.readInt(32);
        StringBuilder transformedBuilder = new StringBuilder();
        while (!BinaryStdIn.isEmpty()){
            transformedBuilder.append((char) Byte.toUnsignedInt(BinaryStdIn.readByte()));
        }
        String transformed = transformedBuilder.toString();

        KeyIndexCounting keyIndexCounting = new KeyIndexCounting(transformed.toCharArray());
        // sorted.
        String firstLetters = new String(keyIndexCounting.getAux());

        int[] count = keyIndexCounting.getCount();

        int[] next = new int[transformed.length()];


        // constructing the next array.
        for(int i = 0; i < next.length; i++){
            next[count[transformed.charAt(i)]++] = i;
        }

        // builder for the original string
        StringBuilder originalBuilder = new StringBuilder();
        int idx = first;
        for(int i = 0; i < next.length; i++){
            char c = firstLetters.charAt(idx);
            originalBuilder.append(c);
            idx = next[idx];
        }
        BinaryStdOut.write(originalBuilder.toString());
        BinaryStdOut.close();
    }

    // if args[0] is "-", apply Burrows-Wheeler transform
    // if args[0] is "+", apply Burrows-Wheeler inverse transform
    public static void main(String[] args){
        if(args[0].equals("-")) transform();
        else if(args[0].equals("+")) inverseTransform();
        else {
            throw new IllegalArgumentException();
        }
    }
}

