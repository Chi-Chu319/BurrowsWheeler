import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdIn;


public class CircularSuffixArray {

    private final int[] indices;
    private int length;
    private String sDoubled;

    // sorting the indices without constructing the substrings

    private static class MSDCircular{
        private static int[] indices;
        private static String stringDoubled;
        private static Stack<Integer> los;
        private static Stack<Integer> his;
        private static Stack<Integer> ds;

        public static void sort(int[] indices_, String stringDoubled_){
            indices = indices_;
            stringDoubled = stringDoubled_;
            los = new Stack<>();
            his = new Stack<>();
            ds = new Stack<>();
            los.push(0);
            his.push(indices.length - 1);
            ds.push(0);
            sort();
        }

        private static void sort (){
            // lo and hi are both inclusive

            while (!los.isEmpty()){
                int lo = los.pop();
                int hi = his.pop();
                int d = ds.pop();
                if(lo>=hi) return;
                int lt = lo, gt = hi;
                int i = lo + 1;
                char v = CharAt(stringDoubled, lo, d, indices);
                while (i <= gt){
                    char t = CharAt(stringDoubled, i, d, indices);
                    if      (v > t) exch(indices, lt++, i++);
                    else if (v < t) exch(indices, gt--, i);
                    else            i++;
                }
                los.push(lo);
                his.push(lt-1);
                ds.push(d);
                los.push(lt);
                his.push(gt);
                ds.push(d+1);
                los.push(gt+1);
                his.push(hi);
                ds.push(d);
            }

        }

        private static char CharAt(String stringDoubled, int i, int charIdx, int[] indices){
            return stringDoubled.charAt(indices[i] +charIdx);
        }

        public static void exch(int[] a, int i, int j){
            int temp = a[i];
            a[i] = a[j];
            a[j] = temp;
        }
    }


    // circular suffix array of s
    public CircularSuffixArray(String s){
        if (s == null) throw new IllegalArgumentException();
        length = s.length();
        indices = new int[length];
        length = indices.length;

        sDoubled = s + s;
        for (int i = 0; i < length; i++){
            indices[i] = i;
        }

        MSDCircular.sort(indices, sDoubled);
    }

    // length of s
    public int length(){
        return length;
    }

    // returns index of ith sorted suffix
    public int index(int i){
        if (i <= -1 || i >= indices.length) throw new IllegalArgumentException();
        return indices[i];
    }

    private String[] constructSuffix(){
        String[] suffixes = new String[length];
        for (int i = 0; i< length; i++){
            suffixes[i] = sDoubled.substring(i, i + length);
        }
        return suffixes;
    }

    // unit testing (required)
    public static void main(String[] args){
        CircularSuffixArray c = new CircularSuffixArray(StdIn.readLine());
        String[] suffixes = c.constructSuffix();
        for(int i : c.indices){
            System.out.println(suffixes[i]);
        }
        for(int i : c.indices){
            System.out.println(i);
        }
    }

}