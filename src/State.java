public class State {
    String name ;
    double[] min , max ;

    State(String name , double x_min , double y_min , double x_max , double y_max){

        this.name = name ;
        min = new double[]{x_min , y_min};
        max = new double[]{x_max , y_max};
    }



}
