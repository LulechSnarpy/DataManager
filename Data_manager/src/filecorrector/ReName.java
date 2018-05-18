package filecorrector;

import java.io.File;

public class ReName{
	 /** *//**�ļ������� 
	    * @param path �ļ�Ŀ¼ 
	    * @param oldname  ԭ�����ļ��� 
	    * @param newname ���ļ��� 
	    */ 
	    public void renameFile(String path,String oldname,String newname){ 
            File oldfile=new File(path+"/"+oldname); 
            File newfile=new File(path+"/"+newname); 
            if(!oldfile.exists()){
                return;//�������ļ�������
            }
            oldfile.renameTo(newfile); 
	    }
}

