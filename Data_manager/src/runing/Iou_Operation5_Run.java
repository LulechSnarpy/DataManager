package runing;

import operation.Iou_Operation5;

public class Iou_Operation5_Run {
	public static void main(String[] args) throws Exception{
		String xmlPath = "file/onedata"; 					//xml文件所在文件夹路径
		String textPath = "file/onedata";					//txt文件所在文件夹路径
		String outPath = "file";					//输出文件的文件夹路径
		boolean isEdgeBox = true;		//proposal是够为左上长宽格式
		boolean tran = false;				//proposal的x,y坐标是否需要调换
		Iou_Operation5 operation = new Iou_Operation5(xmlPath,textPath,outPath);
		operation.getProIouSaveAsFiles(isEdgeBox, tran);
	}
}
