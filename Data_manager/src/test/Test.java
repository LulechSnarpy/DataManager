package test;


import java.io.FileNotFoundException;
import java.io.IOException;

import com.jmatio.io.MatFileReader;
import com.jmatio.types.MLArray;
import com.jmatio.types.MLDouble;



public class Test {
	 public  static void main(String[] args) throws FileNotFoundException, IOException{
		 MatFileReader read = new MatFileReader("file/boxes.mat");
		 MLArray mlArray=read.getMLArray("boxes");//mat�洢�ľ���img�������������
		 MLDouble d=(MLDouble)mlArray;
		 double[][] matrix=(d.getArray());//ֻ��jmatio v0.2�汾�в���d.getArray����
		 for(int i=0; i<matrix.length/4; i++){
			 for(int j=0; j<4; j++)
			 System.out.print(matrix[i][j]);
			 System.out.println();
		 }
	 }

}
