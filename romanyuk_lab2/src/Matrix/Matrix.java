package Matrix;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;
import java.lang.*;

public class Matrix {
    private int row;
    private int colum;
    private double[][] matrix;

    public Matrix(){
        row = 0;
        colum = 0;
        matrix = new double[0][0];
    }

    public Matrix(int row, int colum){
        this.row = row;
        this.colum = colum;
        matrix = new double[row][colum];
    }

    public Matrix(Matrix matrix){
        this.row = matrix.row;
        this.colum = matrix.colum;
        this.matrix = new double[row][colum];
        for(int i=0; i<matrix.row; i++){
            for(int j=0; j<matrix.colum; j++){
                this.matrix[i][j] = matrix.getElement(i, j);
            }
        }
    }
    public void fillMatrix(){
        Scanner scanner = new Scanner(System.in);
        for(int i=0; i<row; i++){
            for(int j=0; j<colum; j++){
                System.out.print("Enter ["+i+"]["+j+"] element:");
                matrix[i][j] = scanner.nextDouble();
            }
        }
    }
    public void fillRandomMatrix(){
        for(int i=0; i<row; i++){
            for(int j=0; j<colum; j++){
                double random = Math.random();
                matrix[i][j] = -10 + (int)(random * 20);
            }
        }
    }
    public void printMatrix(){
        for(int i = 0; i<row; i++){
            for(int j=0; j<colum; j++){
                String element = String.format("%.3f",getElement(i,j));
                System.out.print(element + "  " );
            }
            System.out.println();
        }
        System.out.println();
    }
    public double getElement(int i, int j){
        return matrix[i][j];
    }
    public double[] getRow(int rowIndex){
        return matrix[rowIndex];
    }
    public ArrayList<Double> getColum(int columIndex){
        ArrayList<Double> result = new ArrayList<>();
        for(int i=0; i<row; i++){
            result.add(matrix[i][columIndex]);
        }
        return result;
    }
    public String getSize(){
        String size = row + "x" + colum;
        return size;
    }
    @Override
    public boolean equals(Object obj){
        if(obj == this){
            return true;
        }
        if(!(obj instanceof Matrix)) {
            return false;
        }
        Matrix matrixe = (Matrix) obj;
        if(row!= matrixe.row | colum!= matrixe.colum){
            return false;
        }
        for(int i=0; i<row; i++){
            if(!Arrays.equals(matrix[i], matrixe.matrix[i])){
                return false;
            }
        }
        return true;
    }
    @Override
    public int hashCode(){
        int res = 1;
        for(double[] arr:matrix){
            res = 31*res + Arrays.hashCode(arr);
        }
        return res;
    }
    public static Matrix rowMatrix(int colum){
        Matrix matrixRow = new Matrix(1, colum);
        matrixRow.fillRandomMatrix();
        return matrixRow;
    }
    public Matrix upperTriangle(){
        Matrix upper = new Matrix(this);
        for(int k=0; k<upper.row; k++) {
            for(int i=k+1; i<upper.row; i++){
                double index = upper.matrix[i][k] / upper.matrix[k][k];
                double[] upperList = upper.matrix[i];
                for (int j = 0; j < upper.colum;) {
                    for (double element : upperList) {
                        upper.matrix[i][j] = element - upper.matrix[k][j] * index;
                        j++;
                    }
                }
            }
        }
        return upper;
    }
    public Matrix lowerTriangle(){
        Matrix lower = new Matrix(this);
        for(int k=lower.row-1; k>0;k--) {
            for(int i=k-1; i>-1;i--){
                double index = lower.matrix[i][k] / lower.matrix[k][k];
                double[] lowerList = lower.matrix[i];
                for (int j = 0; j < lower.colum; ) {
                    for (double element : lowerList) {
                        lower.matrix[i][j] = element - lower.matrix[k][j] * index;
                        j++;
                    }
                }
            }
        }
        return lower;
    }
}
