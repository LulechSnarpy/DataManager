package runing;

import operation.Iou_Operation5;

public class Iou_Operation5_Run {
	public static void main(String[] args) throws Exception{
		String xmlPath = "file/onedata"; 					//xml�ļ������ļ���·��
		String textPath = "file/onedata";					//txt�ļ������ļ���·��
		String outPath = "file";					//����ļ����ļ���·��
		boolean isEdgeBox = true;		//proposal�ǹ�Ϊ���ϳ����ʽ
		boolean tran = false;				//proposal��x,y�����Ƿ���Ҫ����
		Iou_Operation5 operation = new Iou_Operation5(xmlPath,textPath,outPath);
		operation.getProIouSaveAsFiles(isEdgeBox, tran);
	}
}
