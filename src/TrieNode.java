public class TrieNode<T> {

    private static final int size = 26 ;
    boolean isEnd ;
    TrieNode<T>[] children = new TrieNode[size];
    T object = null ;


    public TrieNode() {
        isEnd = false;
        for (int i = 0; i < size; i++)
            children[i] = null;
    }

}
