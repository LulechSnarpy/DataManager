package filewriters;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import com.fileio.FileIO;

/*���ַ���д���ļ�*/
/**
 * ���ַ���д���ļ�
 * @version 2017_11_14
 * @author Lulech
 * */
public class TextWriter {
	/**
	 * ���ַ���д���ļ�
	 * @param �ļ�·��
	 * @param Ҫд�������
	 * @return void
	 * */
	public static void writeText(String path,String z){
		File file = new File(path);
		FileIO.checkAndCreateFile(file);
		try(BufferedWriter bw = new BufferedWriter(new FileWriter(file))){
			bw.write(z);
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
}
