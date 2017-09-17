package operation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;

import javax.annotation.processing.Completion;

import bean.MyBox;
import bean.MyObj;

/*计算单个proposal的iou并排序*/
public class Iou_Operation2 {
	private Iou_Operation iouOp;

	public Iou_Operation2() {
		iouOp = new Iou_Operation();
	}
	
	/*
	 * 根据IOU排序并返回候选框
	 * */
	public  ArrayList<MyBox> getIOURank(ArrayList<MyObj> proposals, ArrayList<MyObj> groundTruth, boolean eage) {
		ArrayList<MyBox> pro = iouOp.ObjToBox(proposals);
		ArrayList<MyBox> gt = iouOp.ObjToBox(groundTruth);
		if(eage) pro = iouOp.toObjEageBox(pro);
		for(int i=0; i<pro.size(); i++){
			IoUOnePro(pro.get(i), gt);
		}
		Collections.sort(pro,new Comparator<MyBox>() {

			@Override
			public int compare(MyBox o1, MyBox o2) {
				return Double.compare(o2.iou, o1.iou);
			}
		});
		return pro;
	}
	
	//计算单个iou
	public void IoUOnePro(MyBox probox, ArrayList<MyBox> gt) {
			double iou = 0;	
			int f=-1;
			for(int i=0;  i<gt.size(); i++){
				MyBox gtbox = gt.get(i);
				double tiou =  iouOp.IoUOpertion(probox, gtbox);
				if(tiou>iou) {
					iou = tiou;
					f=i+1;
				}
			}
			probox.iou = iou;
			probox.gT=f;
		}
	
}
