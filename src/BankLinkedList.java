public class BankLinkedList {

    Node head;
    int size = 0;


    static class Node {
        MainBank bank ;
        Node next;

        Node( MainBank bank ) {
            this.bank = bank ;
            next = null;
        }
    }

    void add( MainBank bank ) {

        Node node = new Node(bank);
        size++;

        if (head == null)
            head = node;
        else {
            Node iterator = head;
            while (iterator.next != null)
                iterator = iterator.next;

            iterator.next = node;

        }

    }

    void mostBrs () {

        int max = Integer.MIN_VALUE ;
        MainBank mostBrs = null;

        if (head == null) {
            System.out.print("No bank");
            return;
        }


        Node iterator = head;
        while (iterator.next != null) {

            if ( max < iterator.bank.branchNum)
                max = iterator.bank.branchNum ;

            iterator = iterator.next;
        }

        iterator = head ;
        while (iterator.next != null) {

            if ( max == iterator.bank.branchNum && max != 0 ) {
                mostBrs = iterator.bank ;
                String info = "-" + mostBrs.name + " (" + mostBrs.coordinate[0] + " , " + mostBrs.coordinate[1] + ") " ;
                System.out.println(info);
            }

            iterator = iterator.next;
        }


        if (mostBrs == null)
            System.out.print("No bank hasn't any branch!");
    }
}
