package Matrix;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public final class ImmutableMatrix {
    private final int row;
    private final int colum;
    private final double[][] matrix;

    public ImmutableMatrix() {
        row = 0;
        colum = 0;
        matrix = new double[0][0];
    }

    public ImmutableMatrix(int row, int colum){
        this.row = row;
        this.colum = colum;
        matrix = new double[row][colum];
    }

    public ImmutableMatrix(ImmutableMatrix matrix){
        this.row = matrix.row;
        this.colum = matrix.colum;
        this.matrix = new double[row][colum];
        for(int i=0; i<matrix.row; i++){
            for(int j=0; j<matrix.colum; j++){
                this.matrix[i][j] = matrix.getElement(i, j);
            }
        }
    }
    public void printMatrix(){
        for(int i = 0; i<row; i++){
            for(int j=0; j<colum; j++){
                System.out.print(Double.toString(getElement(i, j)) + "  " );
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
        if(!(obj instanceof ImmutableMatrix)) {
            return false;
        }
        ImmutableMatrix matrixe = (ImmutableMatrix) obj;
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
}
