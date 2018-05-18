package operation;

import java.util.ArrayList;

import bean.MyBox;
import bean.MyObj;

public class Percision_Operation extends Iou_Operation{
	private ArrayList<Double> ioulist;//���iou�Ķ�ά���� �������pro �����pro�����groundTruth��iou
	private ArrayList<Integer> matchNums;//��ŷ��ϱ�׼�ĸ���������
	private ArrayList<Double> precisions;//�����ȷ�ʵ�����
	private Iou_Operation2 operation2 = new Iou_Operation2();
	/**��ȡPrecision,��ȷ����
	 * @param proposals ��ѡ������
	 * @param groundTruth 
	 * @param standard Ԥ�ڵ�iou��׼
	 * @param eage �Ƿ�Ϊeagebox���͵�����
	 * @return void
	 * */
	@Override
	public void getIOU(ArrayList<MyObj> proposals, ArrayList<MyObj> groundTruth, double standard,boolean eage) {
		ArrayList<MyBox> pros = ObjToBox(proposals);
		ArrayList<MyBox> gts = ObjToBox(groundTruth);
		if(eage) pros = toObjEageBox(pros);
		ioulist = new ArrayList<Double>(proposals.size());
		for(MyBox pro:pros){
			operation2.IoUOnePro(pro, gts);
			ioulist.add(pro.iou);
		}
		matchNums = new ArrayList<>(proposals.size());
		precisions = new ArrayList<>(proposals.size());
		getMatchNum(standard);//������ֵɸѡ����
		getRecallRate(proposals.size());
	}
	public ArrayList<Integer> getMatchNums() {
		return matchNums;
	}
	public ArrayList<Double> getPrecisions() {
		return precisions;
	}
	//����RecallRate
	/**
	 * ����Precision��ȷ��
	 * @param proposal������
	 * @return void
	 * */
	public void getRecallRate(int total) {
		int k =0;
		for(int matchNum:matchNums){
			k++;
			precisions.add(matchNum*1.0/k);
		}
	}
	//�������iouҪ�����Ŀ
	/**
	 * �������iouҪ���proposal��Ŀ
	 * @param double standard ��ֵ
	 * @return void
	 * */
	public void getMatchNum(double standard) {
		int i=0;
		int count = 0;
		for(Double iou:ioulist){
			if(iou>=standard){
				count++;
			}
			matchNums.add(count);
		}
	}
}
