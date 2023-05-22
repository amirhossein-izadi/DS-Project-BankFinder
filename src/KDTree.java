public class KDTree {

    private final int k = 2;
    private KdNode root = null ;


    KdNode getRoot(){
        return root;}

    KdNode insert(KdNode root, KdNode kdNode, int depth) {

        if (root == null) {
            return kdNode;
        }

        int i = depth % k ;
        boolean isBigger = kdNode.bank.coordinate[i] < root.bank.coordinate[i];


        if ( isBigger )
            root.left = insert(root.left , kdNode,depth + 1);
         else
             root.right = insert(root.right , kdNode, depth + 1);


        return root;
    }
    void insert (KdNode kdNode) {
        root = insert(root, kdNode,0);
    }


    boolean search (KdNode root , double[] coordinate , int depth) {

        if (root == null)
            return false;


        if (root.bank.coordinate[0] == coordinate[0] && root.bank.coordinate[1] == coordinate[1])
            return true;

        int i = depth % k ;
        boolean isBigger = coordinate[i] < root.bank.coordinate[i];


        if (isBigger)
            return search(root.left, coordinate , depth + 1);
        else
            return search(root.right, coordinate , depth + 1);

    }
    boolean search ( double[] coordinate) {return search(root,coordinate,0) ;}


    KdNode minNode(KdNode root, KdNode leftMin, KdNode rightMin, int i) {

        KdNode minNode = root ;
        if (leftMin != null && leftMin.bank.coordinate[i]<root.bank.coordinate[i])
            minNode = leftMin;
        if (rightMin != null && rightMin.bank.coordinate[i]<root.bank.coordinate[i])
            minNode = rightMin;
        return minNode;
    }
    KdNode findMin(KdNode root, int i , int depth) {
        if (root==null)
            return null ;

        if (i == depth%k){
            if (root.left==null)
                return root;

            return findMin(root.left,i,depth+1);
        }

        KdNode leftMin = findMin(root.left,i,depth+1);
        KdNode rightMin = findMin(root.right,i,depth+1);

        return minNode(root,leftMin,rightMin,i);
    }


    KdNode delete (KdNode root , double[] coordinate , int depth) {

        if (root==null)
            return null;

        int i = depth%2;

        if (root.bank.coordinate[0] == coordinate[0] && root.bank.coordinate[1] == coordinate[1]) {

            if (root.right != null){

                KdNode min = findMin (root.right,i,0);

                Bank temp = root.bank;
                root.bank = min.bank;
                min.bank  = temp ;

                root.right = delete(root.right,min.bank.coordinate,depth+1);

            }

            else if (root.left != null) {

                KdNode min = findMin (root.left,i,0);

                Bank temp = root.bank;
                root.bank = min.bank;
                min.bank  = temp ;

                root.right = delete(root.left,min.bank.coordinate,depth+1);

            }
            else
                return null ;

            return root;
        }

            boolean isBigger = coordinate[i] < root.bank.coordinate[i];
        if ( isBigger )
            root.left = delete(root.left , coordinate,depth + 1);
        else
            root.right = delete(root.right , coordinate, depth + 1);

        return root;



    }
    void delete (double[] coordinate) {
        root = delete(root,coordinate,0);
    }


    void inState ( KdNode root , State state , int depth) {

        if (root == null)
            return ;

        boolean xWiseInState = state.min[0] <= root.bank.coordinate[0] && root.bank.coordinate[0] <= state.max[0] ;
        boolean yWiseInState = state.min[1] <= root.bank.coordinate[1] && root.bank.coordinate[1] <= state.max[1] ;

        if ( xWiseInState && yWiseInState )
            root.printValue(true);

        inState(root.left , state ,depth + 1);
        inState(root.right, state, depth + 1);

    }
    void inState (State state) { inState(root,state,0);}

    KdNode mostClose (KdNode root , double[] coordinate , KdNode mostClose , int depth ){

        if (root == null)
            return mostClose ;

        if ( root.distance(coordinate) < mostClose.distance(coordinate))
            mostClose = root ;


        int i = depth % k ;
        boolean isBigger = coordinate[i] < root.bank.coordinate[i];
        KdNode goodSide = isBigger ? root.left  : root.right ;
        KdNode badSide  = isBigger ? root.right : root.left ;

        mostClose = mostClose(goodSide , coordinate , mostClose ,depth + 1) ;

        if ( Math.abs(mostClose.bank.coordinate[i]-coordinate[i]) < mostClose.distance(coordinate))
            mostClose = mostClose(badSide , coordinate , mostClose ,depth + 1);


        return mostClose ;

    }
    void mostClose (double[] coordinate) { mostClose(root,coordinate,root,0).printValue(false);}

    Bank inCoordinate (KdNode root,double[] coordinate, int depth) {

        if (root == null)
            return null;

        if (root.bank.coordinate[0] == coordinate[0] && root.bank.coordinate[1] == coordinate[1])
            return root.bank;

        int i = depth % k ;
        boolean isBigger = coordinate[i] < root.bank.coordinate[i];


        if (isBigger)
            return inCoordinate(root.left, coordinate , depth + 1);
        else
            return inCoordinate(root.right, coordinate , depth + 1);
    }
    Bank inCoordinate (double[] coordinate) {
        return inCoordinate(root,coordinate,0);
    }



    void inRange (KdNode root , double[] center , double r , int depth) {

        if (root == null)
            return;

        double xWiseDistance = root.bank.coordinate[0]-center[0] ;
        double yWiseDistance = root.bank.coordinate[1]-center[1] ;
        boolean inRange = Math.sqrt(Math.pow(xWiseDistance,2)+Math.pow(yWiseDistance,2)) <= r ;

        if (inRange) {

            String info =  "-" + root.bank.name + " As a " + (root.bank instanceof MainBank ? "mainBank" : "branch") ;
            String coordinate = " (" + root.bank.coordinate[0] + " , " + root.bank.coordinate[1] + ") " ;
            System.out.println(info+coordinate);

            inRange(root.left , center , r ,depth + 1);
            inRange(root.right , center , r ,depth + 1);
        }

        else {

            int i = depth % k ;
            boolean isBigger = center[i] < root.bank.coordinate[i];


            if ( isBigger )
                inRange(root.left , center , r ,depth + 1);
            else
                inRange(root.right , center , r ,depth + 1);

        }
    }
    void inRange (double[] center , double r) {inRange(root,center,r,0);}


    void printTree (KdNode root) {

        if (root == null)
            return;

            printTree(root.left);
            root.printValue(false);
            printTree(root.right);

    }
    void printTree() {printTree(root);}


}
