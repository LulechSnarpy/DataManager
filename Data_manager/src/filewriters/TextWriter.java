package filewriters;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import com.fileio.FileIO;

/*将字符串写入文件*/
/**
 * 将字符串写入文件
 * @version 2017_11_14
 * @author Lulech
 * */
public class TextWriter {
	/**
	 * 将字符串写入文件
	 * @param 文件路径
	 * @param 要写入的内容
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
