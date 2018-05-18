package test;


import java.io.File;
import java.io.IOException;
import org.ujmp.core.Matrix;
import org.ujmp.jmatio.ImportMatrixMAT;

public class Main {
	  public  static void main(String[] args)throws IOException{
          //相对路径的根目录是当前工程的目录（C:\Users\hfz\Desktop\test）。另外相对路径的起始处无“/”
          ImportMatrixMAT test=new ImportMatrixMAT();
          File file=new File("file/boxes.mat");
          Matrix testMatrix= ImportMatrixMAT.fromFile(file);
		  testMatrix.showGUI();
		  System.out.println("ss");
      }

}
