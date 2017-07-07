package filewriters;

import java.io.IOException;
import java.util.ArrayList;
import bean.MyObj;
import bean.MyXml;

/*未完成的类*/
public class YamlWrite {
	private final String head="%YAML:1.0";
	private final String annotation="annotation:";
	private final String folder="folder: VOC2007";
	private final String filename="filename: ";
	private final String source ="source :";
	private final String owner="owner:";
	private final String flickrid="flickrid: ";
	private final String name="name: ";
	private final String size="size: ";
	private final String segmented="segmented: '0'";
	private final String object="object: ";
	private final String bndbox="- bndbox: ";
	private final String pose="pose: ";
	private final String truncated="truncated: ";
	private final String difficult="difficult: ";
	public void write(MyXml mx,String path) throws IOException{
		StringBuilder sb = new StringBuilder();
		sb.append(head).append(System.lineSeparator()).append(System.lineSeparator());
		sb.append(annotation).append(System.lineSeparator());
		sb.append("  ").append(folder).append(System.lineSeparator());
		sb.append("  ").append(filename).append("\""+mx.getFilename()+"\"").append(System.lineSeparator());
		
		
		sb.append(size).append("{width: ").append("\'"+mx.getSiwidth()+"\', height: ")
		  .append("\'"+mx.getSiheight()+"\', depth: ").append("\'"+mx.getSidepth()+"\'}")
		  .append(System.lineSeparator());
		sb.append(segmented);
		ArrayList<MyObj> mos = mx.getObj();
		for(int i=0; i<mos.size(); i++){
			MyObj mo = mos.get(i);
			sb.append(object).append(System.lineSeparator());
			sb.append(bndbox);
			sb.append("{").append("xmin: ").append("\'"+mo.xmin+"\', ").append("ymin: ").append("\'"+mo.ymin+"\', ")
						  .append("xmax: ").append("\'"+mo.xmax+"\', ").append("ymax: ").append("\'"+mo.ymax+"\'}")
						  .append(System.lineSeparator());
			sb.append(pose).append(mo.pose).append(System.lineSeparator());
			sb.append(truncated).append(mo.truncated).append(System.lineSeparator());
			sb.append(difficult).append(mo.difficult).append(System.lineSeparator());
		}
		TextWriter.writeText(path, sb.toString());
	}
}
