package lw2;

public class Main {
    public static void main(String[] args) {
    Matrix a = new Matrix(3,3);
    ImutableMatrix c = new ImutableMatrix(a);

    a.init("{{1,3,4},{1,3,5},{1,2,11}}");
    /*a.init(1,7);*/
        System.out.println(a);

    System.out.println(a.hashCode());
    System.out.println(c.equals(a));

    System.out.println(a.element(1,1));
    System.out.println(a.row(1));
    System.out.println(a.column(1));
    System.out.println(a.dimension());


    a.multiplication("{{1,3,9},{3,3,5},{1,1,1}}","{{1,4,4},{1,3,6},{7,2,9}}");
    Matrix b = new Matrix(5,5);
    b.vector("{1,2,3,4,5}");

    }
}


