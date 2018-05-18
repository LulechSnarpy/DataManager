package test;


import java.io.FileNotFoundException;
import java.io.IOException;

import com.jmatio.io.MatFileReader;
import com.jmatio.types.MLArray;
import com.jmatio.types.MLDouble;



public class Test {
	 public  static void main(String[] args) throws FileNotFoundException, IOException{
		 MatFileReader read = new MatFileReader("file/boxes.mat");
		 MLArray mlArray=read.getMLArray("boxes");//mat存储的就是img矩阵变量的内容
		 MLDouble d=(MLDouble)mlArray;
		 double[][] matrix=(d.getArray());//只有jmatio v0.2版本中才有d.getArray方法
		 for(int i=0; i<matrix.length/4; i++){
			 for(int j=0; j<4; j++)
			 System.out.print(matrix[i][j]);
			 System.out.println();
		 }
	 }

}
