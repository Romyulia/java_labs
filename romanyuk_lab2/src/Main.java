import Matrix.Matrix;
import Matrix.ImmutableMatrix;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        //Matrix matrix1 = new Matrix();
        //matrix1.printMatrix();

        Matrix matrix2 = new Matrix(3,3);
        matrix2.fillRandomMatrix();
        //matrix2.fillMatrix();
        System.out.println("---Your matrix---");
        matrix2.printMatrix();

        Matrix matrix3 = new Matrix(matrix2);
        //matrix3.printMatrix();

        double[] row = matrix2.getRow(0);
        System.out.println("0 row of matrix: ");
        for(double element:row){
            System.out.print(element+"  ");
        }
        System.out.println();

        ArrayList<Double> colum = matrix2.getColum(1);
        System.out.println("1 colum of matrix: ");
        for(double element:colum){
            System.out.println(element);
        }
        System.out.println();

        System.out.println("Matrix size:" + matrix2.getSize());

        System.out.println("equals: " + matrix2.equals(matrix3));

        boolean hash = matrix2.hashCode()==matrix3.hashCode();
        System.out.println("hashCode: " + hash);

        Matrix rowmatr = Matrix.rowMatrix(3);
        System.out.println("---Your row matrix---");
        rowmatr.printMatrix();

        Matrix upper = matrix2.upperTriangle();
        System.out.println("---Upper triangle matrix---");
        upper.printMatrix();
        Matrix lower = matrix2.lowerTriangle();
        System.out.println("---Lower triangle matrix---");
        lower.printMatrix();
    }
}