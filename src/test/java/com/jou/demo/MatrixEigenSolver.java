package com.jou.demo;

import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.EigenDecomposition;
import org.apache.commons.math3.linear.RealMatrix;

import java.util.Scanner;

public class MatrixEigenSolver {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("请输入行列式的阶数:");
        int n = scanner.nextInt();

        double[][] data = new double[n][n];

        System.out.println("请输入行列式的元素:");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                data[i][j] = scanner.nextDouble();
            }
        }

        RealMatrix matrix = new Array2DRowRealMatrix(data);
        EigenDecomposition ed = new EigenDecomposition(matrix);

        System.out.println("特征值:");
        for (double eigenvalue : ed.getRealEigenvalues()) {
            System.out.println(eigenvalue);
        }

        System.out.println("特征向量:");
        for (int i = 0; i < n; i++) {
            double[] eigenvector = ed.getEigenvector(i).toArray();
            for (double v : eigenvector) {
                System.out.print(v + " ");
            }
            System.out.println();
        }
    }
}