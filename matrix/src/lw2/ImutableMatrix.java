package lw2;

import java.util.Arrays;
import java.util.Objects;
import java.util.Random;

public class ImutableMatrix {
    private final Matrix a;
    public static final int h=31;
    public ImutableMatrix(Matrix b) {
        this.a = b.copy();
    }
    public String dimension() {
        return (this.a.dimension());
    }
    public int[] dim() {
        return (this.a.dim());
    }
    public static void init(int from, int to) {
        Random rand = new Random();
        for (int n1 = 0; n1 < Matrix.n; n1++) {
            for (int m1 = 0; m1 < Matrix.m; m1++)
                Matrix.matrix[n1][m1] = rand.nextInt(to - from + 1) + from;
        }
    }
    public ImutableMatrix copy() {
        Matrix copy = new Matrix(this.a.dim()[0],this.a.dim()[1]);
        for (int n1 = 0; n1 < this.a.dim()[0]; n1++) {
            for (int m1 = 0; m1 < this.a.dim()[1]; m1++)
                copy.set_element(n1,m1,this.a.element(n1,m1));
        }
        return new ImutableMatrix(copy);
    }
    public int element(int n1,int m1) {
        return (this.a.element(n1,m1));
    }
    public String row(int n1) {
        StringBuilder res = new StringBuilder();
        if (n1 >= a.n)
            throw new RuntimeException("row: going out of array\n");
        else {
            for (int j = 0; j < a.m; j++) {
                res.append(" ").append(element(n1, j));
            }
            res.append("\n");
        }
        return res.toString();
    }
    public String column(int m1) {
        StringBuilder res = new StringBuilder();
        if (m1 >= a.m)
            throw new RuntimeException("column: going out of array\n");
        else {
            for (int i = 0; i < a.n; i++) {
                res.append(" ").append(element(i, m1));
                res.append("\n");
            }
            res.append("\n");
        }
        return res.toString();
    }
    public String toString() {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < this.a.dim()[0]; i++) {
            for (int j = 0; j < this.a.dim()[1]; j++) {
                res.append(" ").append(element(i, j));
            }
            res.append("\n");
        }
        return res.toString();
    }
    public boolean equals(Object obj) {
        boolean match = false;
        if (obj instanceof ImutableMatrix) {
            ImutableMatrix otherMatrix = (ImutableMatrix) obj;
            for (int n1 = 0; n1 < a.n; n1++) {
                for (int m1= 0; m1 < a.m; m1++)
                    match = (a.matrix[n1] == otherMatrix.a.matrix[n1] && a.matrix[m1] == otherMatrix.a.matrix[m1]);
            }
        }
        return match;
    }
    public int hashCode() {
        int match= Objects.hash(Integer.valueOf(a.n),Integer.valueOf(a.m),Integer.valueOf(this.element(0,0)),
                Arrays.hashCode(this.dim()),h);
        return match;
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




        int[][] matrix3 = new int[Matrix.n][column];
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
