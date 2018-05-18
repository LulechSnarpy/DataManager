package filewriters;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import com.fileio.FileIO;

import bean.MyBox;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class XlsWriter {
	public void WriteRandBoxes(String path,ArrayList<MyBox> boxes) throws IOException, RowsExceededException, WriteException{
		 File file = new File(path);
		FileIO.checkAndCreateFile(file);
		WritableWorkbook workbook = Workbook.createWorkbook(file);
		WritableSheet sheet = workbook.createSheet("sheet1", 0);
		sheet.addCell(new Label(0, 0, "左上x坐标"));
		sheet.addCell(new Label(1, 0, "左上y坐标"));
		sheet.addCell(new Label(2, 0, "右下x坐标"));
		sheet.addCell(new Label(3, 0, "右下y坐标"));
		sheet.addCell(new Label(4, 0, "IOU"));
		sheet.addCell(new Label(5, 0, "对应的GroundTruth"));
		for(int i=0; i<boxes.size();i++){
			MyBox mb = boxes.get(i);
			sheet.addCell(new Label(0,i+1,""+mb.x1));
			sheet.addCell(new Label(1,i+1,""+mb.y1));
			sheet.addCell(new Label(2,i+1,""+mb.x2));
			sheet.addCell(new Label(3,i+1,""+mb.y2));
			sheet.addCell(new Label(4,i+1,""+mb.iou));
			sheet.addCell(new Label(5,i+1,""+mb.gT));
		}
		workbook.write();
		workbook.close();
	}
}
