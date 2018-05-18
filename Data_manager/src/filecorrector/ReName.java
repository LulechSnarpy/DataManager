package filecorrector;

import java.io.File;

public class ReName{
	 /** *//**文件重命名 
	    * @param path 文件目录 
	    * @param oldname  原来的文件名 
	    * @param newname 新文件名 
	    */ 
	    public void renameFile(String path,String oldname,String newname){ 
            File oldfile=new File(path+"/"+oldname); 
            File newfile=new File(path+"/"+newname); 
            if(!oldfile.exists()){
                return;//重命名文件不存在
            }
            oldfile.renameTo(newfile); 
	    }
}

