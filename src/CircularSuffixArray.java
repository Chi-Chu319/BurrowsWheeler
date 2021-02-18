import edu.princeton.cs.algs4.Quick;

public class CircularSuffixArray {

    private stringIdx[] suffixes;
    private int[] indices;

    private class stringIdx implements Comparable<stringIdx>{
        private final String s;
        private final int idx;

        public stringIdx(String s, int idx){
            this.s = s;
            this.idx = idx;
        }

        @Override
        public int compareTo(stringIdx that) {
            return s.compareTo(that.s);
        }

        public String toString(){return this.s;}
    }

    // circular suffix array of s
    public CircularSuffixArray(String s){
        if (s == null) throw new IllegalArgumentException();
        int length = s.length();
        indices = new int[length];
        suffixes = new stringIdx[length];
        String stringDouble = s + s;
        for (int i = 0; i < length; i++){
            suffixes[i] = new stringIdx(stringDouble.substring(i, i+length), i);
        }
        Quick.sort(suffixes);
        for(int i = 0; i < length; i++){indices[i] = suffixes[i].idx;}
    }

    // length of s
    public int length(){
        return indices.length;
    }

    // returns index of ith sorted suffix
    public int index(int i){
        if (i <= -1 || i >= indices.length) throw new IllegalArgumentException();
        return indices[i];
    }

    // unit testing (required)
    public static void main(String[] args){
//        CircularSuffixArray c = new CircularSuffixArray(StdIn.readLine());
//        Stream.of(c.suffixes).forEach(i->System.out.println(i.s));
//        for(int i : c.indices){System.out.println(i);}
    }

}