package filereaders;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

import bean.MyObj;
import bean.MyXml;

/**
 * 读取Txt文件
 * 
 * @version 2017_11_14
 * @author Lulech
 */
public class TextReader {
	private LinkedList<MyXml> mxlist;

	/*
	 * using in MyPhoto text
	 */
	/**
	 * using in MyPhoto text
	 * 
	 * @param 文件路径
	 * @return ArrayList<MyXML>
	 */
	public ArrayList<MyXml> readText(String path) {
		mxlist = new LinkedList<>();
		File file = new File(path);
		try (Scanner sc = new Scanner(new FileInputStream(file))) {
			while (sc.hasNext()) {
				String tstr = sc.nextLine();
				String[] tlist = tstr.split(" ");
				MyXml mx = new MyXml();
				ArrayList<MyObj> objlist = mx.getObj();
				mx.setFilename(tlist[0]);
				mx.setMname(tlist[1]);

				for (int i = 2; i < tlist.length;) {
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
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return new ArrayList<>(mxlist);
	}

	/*
	 * using in 1_4 box reading
	 * 
	 * @tran 需要调换x，y的位置设置为true
	 */
	/**
	 * 读取box信息
	 * 
	 * @param 文件路径
	 * @param boolean
	 *            tran 需要调换x,y的位置,设置为true
	 */
	public ArrayList<MyObj> readBox(String path, boolean tran) {
		LinkedList<MyObj> obl = new LinkedList<>();
		File file = new File(path);
		try (Scanner sc = new Scanner(new FileInputStream(file))) {
			while (sc.hasNext()) {
				MyObj obj = new MyObj();
				String t = sc.nextLine();
				String tm[] = t.split("\t");// 以txt输出文件为准数据间的分割符 一般为 \t 和空格
				tm = trimArray(tm);
				if (tm.length < 4){
					tm = t.split(" ");
					tm = trimArray(tm);
				}
				if (tran) {
					obj.xmin = tm[1];
					obj.ymin = tm[0];
					obj.xmax = tm[3];
					obj.ymax = tm[2];
				} else {
					obj.xmin = tm[0];
					obj.ymin = tm[1];
					obj.xmax = tm[2];
					obj.ymax = tm[3];
					// System.out.println(obj.xmin);
					// System.out.println(obj.ymin);
					// System.out.println(obj.xmax);
					// System.out.println(obj.ymax);
				}
				obl.add(obj);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return new ArrayList<>(obl);
	}

	private String[] trimArray(String[] o) {
		int l = 0;
		String[] t = new String[o.length];
		for (int i = 0; i < o.length; i++) {
			if (!"".equals(o[i]))
				t[l++] = o[i];
		}
		String[] r = new String[l];
		System.arraycopy(t, 0, r, 0, l);
		return r;
	}
}
