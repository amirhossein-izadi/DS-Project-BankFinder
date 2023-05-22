public class Bank {

    public String name ;
    double[] coordinate  ;

    public  Bank ( String name , double[] coordinate) {
        this.name = name ;
        this.coordinate = coordinate ;
    }


}


class MainBank extends Bank {

    int branchNum = 0 ;
    KDTree branches = new KDTree();

    public MainBank(String name, double[] coordinate) {super(name,coordinate);}

}

class BranchBank extends Bank {

    MainBank mainBank = null ;

    public BranchBank(String name, double[] coordinate,MainBank mainBank) {
        super(name,coordinate);
        this.mainBank = mainBank ;
    }


}






