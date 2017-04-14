package bean;

import java.util.ArrayList;

public class MyXml {
	private String folder,filename;
	private String sodatebase,sosource,soimage;//source:database\source\image
	private String oname,mname;//owner:name,marker:name;
	private String siwidth,siheight,sidepth;//size:width\height\depth
	private String segmented;
	private ArrayList<MyObj> obj;//object list
	public MyXml(){
		this.folder = "logos";
		this.sodatebase = "The logs Database";
		this.sosource = "The logs Database";
		this.soimage = "traffic video of Qingdao";
		this.oname = "Qingdao University";
		this.sidepth = "3";
		this.segmented = "0";
		this.obj = new ArrayList<MyObj>();
		//txt中不存在该信息
		this.siwidth = "0";
		this.siheight = "0";
	}
	
	public String getFolder() {
		return folder;
	}
	public void setFolder(String folder) {
		this.folder = folder;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public String getSodatebase() {
		return sodatebase;
	}
	public void setSodatebase(String sodatebase) {
		this.sodatebase = sodatebase;
	}
	public String getSosource() {
		return sosource;
	}
	public void setSosource(String sosource) {
		this.sosource = sosource;
	}
	public String getSoimage() {
		return soimage;
	}
	public void setSoimage(String soimage) {
		this.soimage = soimage;
	}
	public String getOname() {
		return oname;
	}
	public void setOname(String oname) {
		this.oname = oname;
	}
	public String getMname() {
		return mname;
	}
	public void setMname(String mname) {
		this.mname = mname;
	}
	public String getSiwidth() {
		return siwidth;
	}
	public void setSiwidth(String siwidth) {
		this.siwidth = siwidth;
	}
	public String getSiheight() {
		return siheight;
	}
	public void setSiheight(String siheight) {
		this.siheight = siheight;
	}
	public String getSidepth() {
		return sidepth;
	}
	public void setSidepth(String sidepth) {
		this.sidepth = sidepth;
	}
	public String getSegmented() {
		return segmented;
	}
	public void setSegmented(String segmented) {
		this.segmented = segmented;
	}
	public ArrayList<MyObj> getObj() {
		return obj;
	}
	public void setObj(MyObj obj) {
		this.obj.add(obj);
	}
	
}

