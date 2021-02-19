import edu.princeton.cs.algs4.BinaryStdIn;
import edu.princeton.cs.algs4.BinaryStdOut;

public class MoveToFront {
    private static class LinkedArrayList{
        // a array implementation of linked list
        // supports constant time random access, constant time move to the front operation

        private static final int R = 256;
        private final Node[] references;
        private Node start;
        private Node end;

        private class Node{
            private char c;
            private Node prev;
            private Node next;

            public Node(char c, Node prev, Node next){
                this.c =c;
                this.prev = prev;
                this.next = next;
            }

            public Node(char c){
                this.c =c;
            }
        }

        public LinkedArrayList(){
            references = new Node[R];
            for(int i = 0; i< R; i++){
                references[i] = new Node((char)i);
            }

            references[0].next = references[1];
            references[R-1].prev = references[R-2];

            for(int i = 1; i< R-1; i++){
                references[i].prev = references[i-1];
                references[i].next = references[i+1];
            }

            start = references[0];
            end = references[R-1];
        }

        private int where(char c){
            Node n = start;
            int result = 0;
            while(n.c!=c){
                result++;
                n = n.next;
            }
            return result;
        }

        public int moveToFront(char c){
            Node node = references[c];

            int result = where(c);

            // Linking c.prev and c.next
            // first element
            if(node == start){
                return result;
            }
            // last element
            else if(node == end){
                // making prev the new end
                end = node.prev;
                node.prev.next = null;
            }
            else {
                // middle
                node.prev.next = node.next;
                node.next.prev = node.prev;
            }
            start.prev = node;
            node.next = this.start;
            this.start = node;

            return result;
        }

        public char moveToFrontInverse(int idx){
            Node node = start;
            int pointer = 0;
            while(pointer != idx){
                node = node.next;
                pointer++;

            }
            moveToFront(node.c);
            return node.c;
        }
    }

    // apply move-to-front encoding, reading from standard input and writing to standard output
    public static void encode(){
        StringBuilder lineBuilder = new StringBuilder();
        while (!BinaryStdIn.isEmpty()){
            lineBuilder.append((char)Byte.toUnsignedInt(BinaryStdIn.readByte()));
        }
        String line = lineBuilder.toString();
        LinkedArrayList l = new LinkedArrayList();
        for(char c:line.toCharArray()){
            BinaryStdOut.write(l.moveToFront(c), 8);
        }
        BinaryStdOut.close();
    }

    // apply move-to-front decoding, reading from standard input and writing to standard output
    public static void decode(){
        // string of char(binary representation of ints)
        StringBuilder encodedBuilder = new StringBuilder();
        while (!BinaryStdIn.isEmpty()){
            encodedBuilder.append((char) Byte.toUnsignedInt(BinaryStdIn.readByte()));
        }
        String encoded = encodedBuilder.toString();
        LinkedArrayList l = new LinkedArrayList();
        for (int i = 0; i< encoded.length(); i++) {
            BinaryStdOut.write(l.moveToFrontInverse(encoded.charAt(i)));
        }
        BinaryStdOut.close();
    }

    // if args[0] is "-", apply move-to-front encoding
    // if args[0] is "+", apply move-to-front decoding
    public static void main(String[] args){
        if(args[0].equals("-")) encode();
        else if(args[0].equals("+")) decode();
        else {
            throw new IllegalArgumentException();
        }
    }
}