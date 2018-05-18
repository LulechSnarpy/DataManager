package operation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import bean.MyBox;
import bean.MyObj;

/*���㵥��proposal��iou������*/
/**
 * ���㵥��proposal��iou������
 * @version 2017_11_14
 * @author Lulech
 * */
public class Iou_Operation2 {
	private Iou_Operation iouOp;
	/**
	 * ���캯��
	 * */
	public Iou_Operation2() {
		iouOp = new Iou_Operation();
	}
	
	/**
	 * ����IOU���򲢷��غ�ѡ��
	 * @param ArrayList<MyObj> proposals
	 * @param ArrayList<MyObj> groundTruth
	 * @param boolean eage �Ƿ������ϳ������͵�����
	 * @return ArrayList<MyObj> proposals
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
	/**
	 * ����proposal�������IOU
	 * @param ArrayList<MyObj> proposals
	 * @param ArrayList<MyObj> groundTruth
	 * @param boolean eage �Ƿ������ϳ������͵�����
	 * @return ArrayList<MyObj> proposals
	 * */
	public  ArrayList<MyBox> getIOUP(ArrayList<MyObj> proposals, ArrayList<MyObj> groundTruth, boolean eage) {
		ArrayList<MyBox> pro = iouOp.ObjToBox(proposals);
		ArrayList<MyBox> gt = iouOp.ObjToBox(groundTruth);
		if(eage) pro = iouOp.toObjEageBox(pro);
		for(int i=0; i<pro.size(); i++){
			IoUOnePro(pro.get(i), gt);
		}
		return pro;
	}
	
	//���㵥��iou
	/**
	 * ���㵥��iou ����proposal�����е�groundTruth
	 * @param MyBox proposal
	 * @param ArrayList<MyBox> gt
	 * @return void
	 * */
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
			System.out.println(iou);
			probox.gT=f;
		}
	
}
