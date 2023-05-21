import java.util.Scanner;

public class Main {

    static String cmd ;

    public static void main(String[] args) /*throws InterruptedException*/ {

        Scanner input = new Scanner(System.in) ;
        Command command = new Command();
        boolean flag = true ;

        while (flag) {

            System.out.print("Please input your command : ");
             cmd = input.next() ;
            switch (cmd) {
                case "addN"    -> command.addN();
                case "addB"    -> command.addB();
                case "addBr"   -> command.addBr();
                case "delBr"   -> command.delBr();
                case "listB"   -> command.listB();
                case "listBrs" -> command.listBrs();
                case "nearB"   -> command.nearB();
                case "nearBr"  -> command.nearBr();
                case "availB"  -> command.availB();
                case "mostBrs" -> command.mostBrs();
                case "exit"    -> flag = false ;
                default        -> cmd = "unsuccessful";
            }

            command.cmdResult(cmd);

            // Thread.sleep(3000);
            // System.out.print("\033[H\033[2J");
            // System.out.flush();
        }
    }
}
