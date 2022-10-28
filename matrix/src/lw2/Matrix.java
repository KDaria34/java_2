package lw2;
import java.util.Arrays;
import java.util.Objects;
import java.util.Random;


public class Matrix {
    public static int n = 0;
    public static int m = 0;
    public static int[][] matrix;
    public static int h=29;

    public Matrix() {
        n = 0;
        m = 0;
    }
    public Matrix(int n1, int m1) {
        n=n1;
        m=m1;
        matrix = new int[n1][m1];
        for (int i = 0; i < n1; i++)
            for (int j = 0; j < m1; j++)
                matrix[i][j] = 0;
    }
    public static void init(int from, int to) {
        Random rand = new Random();
        for (int n1 = 0; n1 < n; n1++) {
            for (int m1 = 0; m1 < m; m1++)
                matrix[n1][m1] = rand.nextInt(to - from + 1) + from;
        }
    }
    public static void init(String string){
        string = string.replaceAll("}|[{]| ", "");
        String[] ss;
        ss=string.split(" |,");
        int i = 0;
        for (int n1= 0; n1 < n; n1++) {
            for (int m1 = 0; m1 < m; m1++){
                matrix[n1][m1] = Integer.parseInt(ss[i]);
                i++;
            }
        }
    }
    public Matrix copy() {
        Matrix copy = new Matrix(n,m);
        for (int n1 = 0; n1 < n; n1++) {
            for (int m1 = 0; m1 < m; m1++){
                copy.set_element(n1,m1,matrix[n1][m1]);
            }
        }
        return copy;
    }
    public String dimension() {
        return("Dimension :"+n+"x"+m);
    }

    public int[] dim() {
        return new int[]{n,m};
    }
    public void set_element(int n1, int m1, int v) {
        if (n1 > n || m1 > m)
            throw new RuntimeException("set_element: going out of array\n");
        else {
            matrix[n1][m1] = v;
        }
    }
    public int element(int n1, int m1) {
        if (n1 >= n || m1 >= m)
            throw new RuntimeException("element: going out of array\n");
        else {
            return matrix[n1][m1];
        }
    }

    public String row(int n1) {
        StringBuilder res = new StringBuilder();
        if (n1 >= n)
            throw new RuntimeException("row: going out of array\n");
        else {
            for (int j = 0; j < m; j++) {
                res.append(" ").append(element(n1, j));
            }
            res.append("\n");
        }
        return res.toString();
    }

    public String column(int m1) {
        StringBuilder res = new StringBuilder();
        if (m1 >= m)
            throw new RuntimeException("coloumn: going out of array\n");
        else {
            for (int i = 0; i < n; i++) {
                res.append(" ").append(element(i, m1));
                res.append("\n");
            }
            res.append("\n");
        }
        return res.toString();
    }
    public String toString() {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                res.append(" ").append(element(i, j));
            }
            res.append("\n");
        }
        return res.toString();
    }
    public boolean equals(Object obj) {
        boolean match = false;
        if (obj instanceof Matrix) {
            Matrix otherMatrix = (Matrix) obj;
            for (int n1 = 0; n1 < n; n1++) {
                for (int m1 = 0; m1 < m; m1++)
                    match = (matrix[n1] == otherMatrix.matrix[n1] && matrix[m1] == otherMatrix.matrix[m1]);
            }
        }
        return match;
    }

    public int hashCode() {
        int match= Objects.hash(Integer.valueOf(n),Integer.valueOf(m),Integer.valueOf(this.element(0,0)), Arrays.hashCode(this.dim()),h);
        return match;
    }

    public static void vector(String string){
        string = string.replaceAll("}|[{]| ", "");
        String[] ss;
        ss=string.split(" |,");
        int i = 0;
        for (int n1= 0; n1 < n; n1++) {
                matrix[n1][n1] = Integer.parseInt(ss[i]);
                i++;
        }
    }
    public static void multiplication(String a, String b){
        //matrix a
        String a1 = a.substring(1, a.length() - 1);
        int row = a1.split("}").length;
        a1 = a1.replaceAll("}|[{]| ", "");
        int column = a1.split(" |,").length/row;
        int [][] matrix2 = new int[row][column];
        for (int i = 0; i < row; i++)
            for (int j = 0; j < column; j++)
                matrix2[i][j] = 0;
        int i=0;
        for (int n1= 0; n1 < row; n1++) {
            for (int m1 = 0; m1 < column; m1++){
                matrix2[n1][m1] = Integer.parseInt(a1.split(" |,")[i]);
                i++;
            }
        }
        //matrix b
        String b1 = b.substring(1, a.length() - 1);
        int row1 = b1.split("}").length;
        b1 = b1.replaceAll("}|[{]| ", "");
        int column1 = b1.split(" |,").length/row;
        int [][] matrix1 = new int[row1][column1];
        for (int i3 = 0; i3 < row1; i3++)
            for (int j = 0; j < column1; j++)
                matrix1[i3][j] = 0;
        int ii=0;
        for (int n1= 0; n1 < row; n1++) {
            for (int m1 = 0; m1 < column; m1++){
                matrix1[n1][m1] = Integer.parseInt(b1.split(" |,")[ii]);
                i++;
            }
        }




        int[][] matrix3 = new int[row][column1];
        for (int i3 = 0; i3 < row; i3++) {
            for (int j = 0; j < column1; j++) {
                for (int r = 0; r < column; r++) {
                    matrix3[i3][j] += matrix2[i3][r] * matrix1[r][j];
                }
            }
        }
        for(int i2=0;i2<row;i2++)
        {
            for(int j=0;j<column1;j++)
            {
                System.out.print(matrix3[i2][j]+" ");

            }
            System.out.println();
        }
    }
}
