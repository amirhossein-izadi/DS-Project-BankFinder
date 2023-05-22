import java.util.Scanner;

public class Command {

    KDTree allBanks = new KDTree() ;
    TrieTree <MainBank> mainBanks = new TrieTree<>() ;
    BankLinkedList mainBankLinkedList = new BankLinkedList ();
    TrieTree <State> states = new TrieTree<>();
    Scanner input = new Scanner(System.in);

    void addN() {

        System.out.print("please insert the state name : ");
        String stateName = input.next().toLowerCase();

        double x , y ;
        double x_max = Double.MIN_VALUE , y_max = Double.MIN_VALUE;
        double x_min = Double.MAX_VALUE , y_min = Double.MAX_VALUE ;

        System.out.println();

        for (int i = 1; i <= 4; i++) {

            System.out.println("please insert the coordinate" + i + ". ");

            System.out.print(" X : ");
            x = input.nextDouble();
            x_max = Math.max(x,x_max);
            x_min = Math.min(x,x_min);

            System.out.print(" Y : ");
            y = input.nextDouble();
            y_max = Math.max(y,y_max);
            y_min = Math.min(y,y_min);

        }
        states.insert(new State(stateName,x_min,y_min,x_max,y_max) , stateName );
    }

    void addB() {

        System.out.print("please insert the bank name : ");
        String bankName = input.next();

        double []coordinate = new double[2] ;
        String exceptionHandler = " " ;

        do {
            if (!exceptionHandler.equals(" "))
             System.out.println(exceptionHandler);

             System.out.println("please insert the coordinates.");

             System.out.print(" X : ");
             coordinate[0] = input.nextDouble();

             System.out.print(" Y : ");
             coordinate[1] = input.nextDouble();

             exceptionHandler = " this coordinate is already taken. " ;

        } while (allBanks.search(coordinate));

        KdNode kdNode = new KdNode(true,bankName,coordinate,null);
        MainBank mainBank = (MainBank) kdNode.bank;

        allBanks.insert(kdNode);
        mainBanks.insert( mainBank,bankName );
        mainBankLinkedList.add(mainBank);

    }

    void addBr() {

        System.out.print("please insert the branch name : ");
        String branchName = input.next();

        System.out.print("please insert the bank name : ");
        String bankName = input.next().toLowerCase();

        double []coordinate = new double[2] ;
        String exceptionHandler = " " ;

        do {
            if (!exceptionHandler.equals(" "))
                System.out.println(exceptionHandler);

            System.out.println("please insert the coordinates.");

            System.out.print(" X : ");
            coordinate[0] = input.nextDouble();

            System.out.print(" Y : ");
            coordinate[1] = input.nextDouble();

            exceptionHandler = " this coordinate is already taken. " ;

        } while (allBanks.search(coordinate));


        MainBank mainBank = mainBanks.search(bankName);

        if (allBanks.getRoot()==null){
            System.out.println("No mainBank exists");
            Main.cmd = "unsuccessful";
            return;
        }

        if ( mainBank == null){
            System.out.println("invalid mainBank !");
            Main.cmd = "unsuccessful";
            return;
        }

        mainBank.branchNum++;
        mainBank.branches.insert(new KdNode(false,branchName,coordinate,mainBank));
        allBanks.insert(new KdNode(false,branchName,coordinate,mainBank));

    }

    void delBr() {

        double []coordinate = new double[2] ;

        System.out.println("please insert the coordinates.");

        System.out.print(" X : ");
        coordinate[0] = input.nextDouble();

        System.out.print(" Y : ");
        coordinate[1] = input.nextDouble();

        Bank bank = allBanks.inCoordinate(coordinate);

        if (bank == null){
            System.out.println("No branch exists in this coordinate");
        }
        else if (bank instanceof MainBank){
            System.out.println("mainBank's cant be deleted");
        }
        else {
            allBanks.delete(coordinate);
            mainBanks.search(((BranchBank)  bank).mainBank.name).branches.delete(coordinate);
        }

    }

    void listB() {

        System.out.print("please insert the state name : ");
        String stateName = input.next();

        if (states.numOfWords == 0){
            System.out.println("No state exists");
            return;
        }

        State state = states.search(stateName);

        if (state == null)
            System.out.println("No state with this name exists");

        allBanks.inState(state);

    }

    void listBrs() {

        System.out.print("please insert the bank name : ");
        String bankName = input.next();
        MainBank mainBank =mainBanks.search(bankName) ;

        if ( mainBank == null){
            System.out.println("invalid mainBank !");
            Main.cmd = "unsuccessful";
            return;
        }

        if (mainBank.branches.getRoot() == null)
            System.out.println("No branch exists");
         else{
            mainBank.branches.printTree();
            System.out.println();
        }
    }

    void nearB() {

        double[] coordinate = new double[2] ;
        System.out.println("please insert the coordinates.");

        System.out.print(" X : ");
        coordinate[0] = input.nextDouble();

        System.out.print(" Y : ");
        coordinate[1] = input.nextDouble();

        if (mainBankLinkedList.size != 0)
            allBanks.mostClose(coordinate);
        else
            System.out.println("No Bank exists");
    }

    void nearBr() {


        System.out.print("please insert the bank name : ");
        String bankName = input.next();

        double[] coordinate = new double[2] ;
        System.out.println("please insert the coordinates.");

        System.out.print(" X : ");
        coordinate[0] = input.nextDouble();

        System.out.print(" Y : ");
        coordinate[1] = input.nextDouble();

        MainBank mainBank =mainBanks.search(bankName) ;

        if ( mainBank == null){
            System.out.println("invalid mainBank !");
            Main.cmd = "unsuccessful";
            return;
        }

        if (mainBank.branches.getRoot() == null)
            System.out.println("No branch exists");
        else
            mainBank.branches.mostClose(coordinate);
    }

    void availB() {

        System.out.print("please insert the radius : ");
        double radius = input.nextDouble();

        double[] center = new double[2] ;
        System.out.println("please insert the coordinates.");

        System.out.print(" X : ");
        center[0] = input.nextDouble();

        System.out.print(" Y : ");
        center[1] = input.nextDouble();

        if (allBanks.getRoot()==null)
            System.out.println("No bank exists");

        allBanks.inRange(center,radius);
    }

    void mostBrs() {
        mainBankLinkedList.mostBrs();}

    void cmdResult(String cmd) {

        System.out.println();
        System.out.println(cmd.equals("unsuccessful") ?"please input correct data " :"Command run successfully.");
        System.out.println("-------------------------------------------------");
    }
}
