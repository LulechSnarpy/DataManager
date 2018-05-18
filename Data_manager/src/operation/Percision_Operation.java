package operation;

import java.util.ArrayList;

import bean.MyBox;
import bean.MyObj;

public class Percision_Operation extends Iou_Operation{
	private ArrayList<Double> ioulist;//存放iou的二维数组 最外层是pro 里层是pro与各个groundTruth的iou
	private ArrayList<Integer> matchNums;//存放符合标准的个数的数组
	private ArrayList<Double> precisions;//存放正确率的数组
	private Iou_Operation2 operation2 = new Iou_Operation2();
	/**获取Precision,正确个数
	 * @param proposals 候选框数组
	 * @param groundTruth 
	 * @param standard 预期的iou标准
	 * @param eage 是否为eagebox类型的数据
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
		getMatchNum(standard);//根据阈值筛选个数
		getRecallRate(proposals.size());
	}
	public ArrayList<Integer> getMatchNums() {
		return matchNums;
	}
	public ArrayList<Double> getPrecisions() {
		return precisions;
	}
	//计算RecallRate
	/**
	 * 计算Precision正确率
	 * @param proposal的总数
	 * @return void
	 * */
	public void getRecallRate(int total) {
		int k =0;
		for(int matchNum:matchNums){
			k++;
			precisions.add(matchNum*1.0/k);
		}
	}
	//计算符合iou要求的数目
	/**
	 * 计算符合iou要求的proposal数目
	 * @param double standard 阈值
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
