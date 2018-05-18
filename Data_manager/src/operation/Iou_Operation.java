package operation;

import java.util.ArrayList;
import java.util.Iterator;

import bean.MyBox;
import bean.MyObj;

/**
 * 计算IOU
 * @version 2017_11_14
 * @author Lulech
 * */
public class Iou_Operation {

	private ArrayList<ArrayList<Double>> ioulist;//存放iou的二维数组 最外层是pro 里层是pro与各个groundTruth的iou
	private ArrayList<Integer> matchNum;//存放符合标准的个数的数组
	private ArrayList<Double> rr;//存放召回率的数组
	
	/**获取IOU,RecallRate,匹配的数目
	 * @param proposals 候选框数组
	 * @param groundTruth 
	 * @param standard 预期的iou标准
	 * @param eage 是否为eagebox类型的数据
	 * @return void
	 * */
	public void getIOU(ArrayList<MyObj> proposals, ArrayList<MyObj> groundTruth, double standard,boolean eage) {
		ArrayList<MyBox> pro = ObjToBox(proposals);
		ArrayList<MyBox> gt = ObjToBox(groundTruth);
		if(eage) pro = toObjEageBox(pro);
		ioulist = new ArrayList<ArrayList<Double>>(pro.size());
		Iterator<MyBox> prot = pro.iterator();
		Iterator<MyBox> gtt = gt.iterator();
		while (prot.hasNext()) {
			prot.next();
			ioulist.add(new ArrayList<Double>(gt.size()));
		}
		Iterator<ArrayList<Double>> iout = ioulist.iterator();
		while (gtt.hasNext()) {
			MyBox gtbox = gtt.next();
			IoUOneGT(pro, gtbox);//多个proposal与一个groundTruth计算iou
		}
		getMatchNum(standard);//根据阈值筛选个数
		getRecallRate(gt.size());//计算召回率根据当前proposal的个数计算当前召回率
	}
	//计算RecallRate
	/**
	 * 计算RecallRate召回率
	 * @param gourndTruth的总数
	 * @return void
	 * */
	public void getRecallRate(int total) {
		rr = new ArrayList<>();
		Iterator<Integer> mt = matchNum.iterator();
		while (mt.hasNext()) {
			rr.add(mt.next() * 1.0 / total);
		}
	}
	//计算符合iou要求的数目
	/**
	 * 计算符合iou要求的proposal数目
	 * @param double standard 阈值
	 * @return void
	 * */
	public void getMatchNum(double standard) {
		matchNum = new ArrayList<>();
		Iterator<ArrayList<Double>> iout = ioulist.iterator();
		while (iout.hasNext()) {
			Iterator<Double> liniou = iout.next().iterator();
			int count = 0;
			while (liniou.hasNext()) {
				if (liniou.next() >= standard)
					count++;
			}
			matchNum.add(count);
		}
	}
	//计算单个iou
	/**
	 * 计算单个GroundTruth的iou
	 * @param ArrayList<MyBox> pro
	 * @param MyBox gt
	 * @return void
	 * */
	public void IoUOneGT(ArrayList<MyBox> pro, MyBox gt) {
		double iou = 0;
		boolean flag = false;
		Iterator<ArrayList<Double>> iout = ioulist.iterator();
		Iterator<MyBox> prot = pro.iterator();
		while (prot.hasNext()) {
			MyBox probox = prot.next();
//			if(iou>1 && !flag){
//				flag = true;
//				showOneBox(probox);
//			}
			iou = maxdouble(iou, IoUOpertion(probox, gt));
			iout.next().add(iou);
		}
	}
	//计算iou的公式
	/**
	 * 计算iou的公式
	 * @param MyBox proposal
	 * @param MyBox groundTruth
	 * @return double
	 * */
	public double IoUOpertion(MyBox pro, MyBox gt) {
		if (gt.x2 < pro.x1 || pro.x2 < gt.x1 || pro.y2 < gt.y1 || pro.y1 > gt.y2)
			return 0;
		int intersection = (min(gt.y2, pro.y2) - max(gt.y1, pro.y1)) * (min(gt.x2, pro.x2) - max(gt.x1, pro.x1));
		return intersection * 1.0
				/ ((gt.x2 - gt.x1) * (gt.y2 - gt.y1) + (pro.x2 - pro.x1) * (pro.y2 - pro.y1) - intersection);
	}
	
	/**
	 * 最小值
	 * @param int a
	 * @param int b
	 * @return int 
	 * */
	public int min(int a, int b) {
		return a > b ? b : a;
	}
	
	/**
	 * 最大值
	 * @param int a
	 * @param int b
	 * @return int 
	 * */
	public int max(int a, int b) {
		return a > b ? a : b;
	}

	/**
	 * 最大值
	 * @param double a
	 * @param double b
	 * @return double
	 * */
	public double maxdouble(double a, double b) {
		return a > b ? a : b;
	}
	//转换数据类型
	/**
	 * 把MyObj 转换成MyBox
	 * @param ArrayList<MyObj> obj
	 * @return ArrayList<MyBox>
	 * */
	public ArrayList<MyBox> ObjToBox(ArrayList<MyObj> obj) {
		ArrayList<MyBox> box = new ArrayList<>(obj.size());
		Iterator<MyObj> obt = obj.iterator();
		while (obt.hasNext()) {
			MyObj ob = obt.next();
			MyBox bo = new MyBox();
			bo.x1 = Integer.parseInt(ob.xmin);
			bo.y1 = Integer.parseInt(ob.ymin);
			bo.x2 = Integer.parseInt(ob.xmax);
			bo.y2 = Integer.parseInt(ob.ymax);
			box.add(bo);
		}
		return box;
	}
	//转换eagebox的数据
	/**
	 * 把左上长宽类型的数据转换成左上右下形式的数据
	 * @param ArrayList<MyBox> 
	 * @return ArrayList<MyBox>
	 * */
	public ArrayList<MyBox> toObjEageBox(ArrayList<MyBox> box){
		ArrayList<MyBox> bo = new ArrayList<>(box.size());
		Iterator<MyBox> bot = box.iterator();
		while(bot.hasNext()){
			MyBox o = new MyBox();
			MyBox t = bot.next();
			o.x1 = t.x1;
			o.y1 = t.y1;
			o.x2 = t.x1 + t.x2;
			o.y2 = t.y1 + t.y2;
			bo.add(o);
		}
		return bo;
	}
	

	
	//打印boxlist的内容
	/**
	 * 打印boxlist的内容
	 *@return void
	 * */
	public void showBox(ArrayList<MyBox> bol) {
		Iterator<MyBox> bot = bol.iterator();
		while (bot.hasNext()) {
			showOneBox(bot.next());
		}
	}
	/**
	 * 打印MyBox的内容
	 *@return void
	 * */
	public void showOneBox(MyBox mb) {
		System.out.println("x1=" + mb.x1 + "; y1=" + mb.y1 + "; x2=" + mb.x2 + "; y2=" + mb.y2);
	}
	/**
	 *获得ioulist
	 *@return ArrayList<ArrayList<Double>>
	 * */
	public ArrayList<ArrayList<Double>> getIoulist() {
		return ioulist;
	}
	/**
	 *获得召回率数组
	 *@return ArrayList<Double> 
	 * */
	public ArrayList<Double> getRr() {
		return rr;
	}
	/**
	 *获得符合要求框数的数组
	 *@return ArrayList<Integer> 
	 * */
	public ArrayList<Integer> getMatchNum() {
		return matchNum;
	}	
}
