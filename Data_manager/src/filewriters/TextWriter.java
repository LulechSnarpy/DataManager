package filewriters;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import com.fileio.FileIO;

public class TextWriter {
	public static void writeText(String path,String z) throws IOException{
		File file = new File(path);
		FileIO.checkAndCreateFile(file);
		BufferedWriter bw = new BufferedWriter(new FileWriter(file));
		bw.write(z);
		bw.close();
	}
}
