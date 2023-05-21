public class KdNode {

    KdNode left,right;
    Bank bank ;

    KdNode(boolean isMain , String name , double[] coordinate , MainBank mainBank ) {
        left = right = null ;
        bank = isMain ? new MainBank(name,coordinate) : new BranchBank(name,coordinate,mainBank) ;
    }

    double distance ( double[] coordinate ){
        double xWiseDistance = bank.coordinate[0] - coordinate[0] ;
        double yWiseDistance = bank.coordinate[1] - coordinate[1] ;
        return Math.sqrt(Math.pow(xWiseDistance,2)+Math.pow(yWiseDistance,2));
    }

    void printValue (boolean println) {
        String info = "-" + bank.name + " (" + bank.coordinate[0] + " , " + bank.coordinate[1] + ") " ;
        System.out.print(info);

        if (println)
            System.out.println();
    }
}


