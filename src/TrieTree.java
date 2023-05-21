public class TrieTree<T> {

    TrieNode<T> root = new TrieNode<>();
    int numOfWords = 0 ;


    void insert ( T object , String key ) {

        int length = key.length();
        TrieNode<T> node = root ;


        for (int i = 0; i < length ; i++) {

            int index = key.charAt(i) - 'a';
            if (  node.children[index] == null )
                node.children[index] = new TrieNode<>();

            node = node.children[index];
        }

        node.isEnd = true ;
        node.object = object ;
        numOfWords++;
    }

    T search (String key) {

        int length = key.length();
        TrieNode<T> node = root ;

        for (int i = 0; i < length ; i++) {

            int index = key.charAt(i) - 'a';
            if (node.children[index] == null)
                 return null ;

            node = node.children[index];
        }

        return node.isEnd ?  node.object : null ;
    }

}
