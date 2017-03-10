package filereaders;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import bean.MyXml;
import bean.MyObj;

public class TextReader {
	private ArrayList<MyXml> mxlist;
	
	/*
	 * using in MyPhoto text
	 */
	public ArrayList<MyXml> readText(String path) throws FileNotFoundException{
		mxlist = new ArrayList<>();
		File file = new File(path);
		Scanner sc = new Scanner(new FileInputStream(file));
		while(sc.hasNext()){
			String tstr = sc.nextLine();
			String []tlist = tstr.split(" ");
			MyXml mx = new MyXml();
			ArrayList<MyObj> objlist = mx.getObj();
			mx.setFilename(tlist[0]);
			mx.setMname(tlist[1]);
		
			for(int i=2; i<tlist.length; ){
				MyObj mo = new MyObj();
				mo.name = tlist[i++];
				mo.pose = tlist[i++];
				mo.color = tlist[i++];
				mo.truncated = tlist[i++];
				mo.area = tlist[i++];
				mo.difficult = tlist[i++];
				mo.shadow = tlist[i++];
				mo.xmin = tlist[i++];
				mo.ymin = tlist[i++];
				mo.xmax = tlist[i++];
				mo.ymax = tlist[i++];
				objlist.add(mo);
			}
			mxlist.add(mx);
		}
		sc.close();
		return mxlist;
	}
	/*
	 * using in 1_4 box reading 
	 * @tran 需要调换x，y的位置设置为true
	 */
	
	public ArrayList<MyObj> readBox(String path,boolean tran) throws FileNotFoundException{
		ArrayList<MyObj> obl = new ArrayList<>();
		File file = new File(path);
		Scanner sc = new Scanner(new FileInputStream(file));
		while(sc.hasNext()){
			MyObj obj = new MyObj();
			String t = sc.nextLine();
			String tm[] = t.split(" ");
			if(tran){
				obj.xmin = tm[1];
				obj.ymin = tm[0];
				obj.xmax = tm[3];
				obj.ymax = tm[2];
			}else{
				obj.xmin = tm[0];
				obj.ymin = tm[1];
				obj.xmax = tm[2];
				obj.ymax = tm[3];
			}
			obl.add(obj);
		}
		sc.close();
		return obl;
	}
}
