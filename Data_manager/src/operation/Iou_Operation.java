package operation;

import java.util.ArrayList;
import java.util.Iterator;

import bean.MyBox;
import bean.MyObj;

public class Iou_Operation {

	private ArrayList<ArrayList<Double>> ioulist;//���iou�Ķ�ά����
	private ArrayList<Integer> matchNum;//��ŷ��ϱ�׼�ĸ���������
	private ArrayList<Double> rr;//����ٻ��ʵ�����
	
	
	/*��ȡIOU,RecallRate,ƥ�����Ŀ
	 * @proposals ��ѡ������
	 * @groundTruth 
	 * @standard Ԥ�ڵ�iou��׼
	 * @eage �Ƿ�Ϊeagebox���͵�����
	 * */
	public void getIOU(ArrayList<MyObj> proposals, ArrayList<MyObj> groundTruth, double standard,boolean eage) {
		ArrayList<MyBox> pro = ObjToBox(proposals);
		ArrayList<MyBox> gt = ObjToBox(groundTruth);
		if(eage) pro = toObjEageBox(pro);
		ioulist = new ArrayList<ArrayList<Double>>();
		Iterator<MyBox> prot = pro.iterator();
		Iterator<MyBox> gtt = gt.iterator();
		while (prot.hasNext()) {
			prot.next();
			ioulist.add(new ArrayList<Double>());
		}
		Iterator<ArrayList<Double>> iout = ioulist.iterator();
		while (gtt.hasNext()) {
			MyBox gtbox = gtt.next();
			IoUOneGT(pro, gtbox);
		}
		getMatchNum(standard);
		getRecallRate(gt.size());
	}
	//����RecallRate
	public void getRecallRate(int total) {
		rr = new ArrayList<>();
		Iterator<Integer> mt = matchNum.iterator();
		while (mt.hasNext()) {
			rr.add(mt.next() * 1.0 / total);
		}
	}
	//�������iouҪ�����Ŀ
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
	//���㵥��iou
	public void IoUOneGT(ArrayList<MyBox> pro, MyBox gt) {
		double iou = 0;
		boolean flag = false;
		Iterator<ArrayList<Double>> iout = ioulist.iterator();
		Iterator<MyBox> prot = pro.iterator();
		while (prot.hasNext()) {
			MyBox probox = prot.next();
			if(iou>1 && !flag){
				flag = true;
				showOneBox(probox);
			}
			iou = maxdouble(iou, IoUOpertion(probox, gt));
			iout.next().add(iou);
		}
	}
	//����iou�Ĺ�ʽ
	public double IoUOpertion(MyBox pro, MyBox gt) {
		if (gt.x2 < pro.x1 || pro.x2 < gt.x1 || pro.y2 < gt.y1 || pro.y1 > gt.y2)
			return 0;
		int intersection = (min(gt.y2, pro.y2) - max(gt.y1, pro.y1)) * (min(gt.x2, pro.x2) - max(gt.x1, pro.x1));
		return intersection * 1.0
				/ ((gt.x2 - gt.x1) * (gt.y2 - gt.y1) + (pro.x2 - pro.x1) * (pro.y2 - pro.y1) - intersection);
	}

	public int min(int a, int b) {
		return a > b ? b : a;
	}

	public int max(int a, int b) {
		return a > b ? a : b;
	}

	public double maxdouble(double a, double b) {
		return a > b ? a : b;
	}
	//ת����������
	public ArrayList<MyBox> ObjToBox(ArrayList<MyObj> obj) {
		ArrayList<MyBox> box = new ArrayList<>();
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
	//ת��eagebox������
	public ArrayList<MyBox> toObjEageBox(ArrayList<MyBox> box){
		ArrayList<MyBox> bo = new ArrayList<>();
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
	

	
	//��ӡboxlist������
	public void showBox(ArrayList<MyBox> bol) {
		Iterator<MyBox> bot = bol.iterator();
		while (bot.hasNext()) {
			showOneBox(bot.next());
		}
	}

	public void showOneBox(MyBox mb) {
		System.out.println("x1=" + mb.x1 + "; y1=" + mb.y1 + "; x2=" + mb.x2 + "; y2=" + mb.y2);
	}

	public ArrayList<ArrayList<Double>> getIoulist() {
		return ioulist;
	}

	public ArrayList<Double> getRr() {
		return rr;
	}
	
}
