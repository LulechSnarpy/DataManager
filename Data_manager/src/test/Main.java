package test;


import java.io.File;
import java.io.IOException;
import org.ujmp.core.Matrix;
import org.ujmp.jmatio.ImportMatrixMAT;

public class Main {
	  public  static void main(String[] args)throws IOException{
          //���·���ĸ�Ŀ¼�ǵ�ǰ���̵�Ŀ¼��C:\Users\hfz\Desktop\test�����������·������ʼ���ޡ�/��
          ImportMatrixMAT test=new ImportMatrixMAT();
          File file=new File("file/boxes.mat");
          Matrix testMatrix= ImportMatrixMAT.fromFile(file);
		  testMatrix.showGUI();
		  System.out.println("ss");
      }

}
