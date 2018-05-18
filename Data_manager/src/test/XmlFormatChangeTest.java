package test;

import java.io.IOException;

import org.dom4j.DocumentException;

import bean.MyXml;
import filereaders.XmlReader;
import filewriters.XmlWriter;

public class XmlFormatChangeTest {
	public static void main(String[] args) {
		MyXml mx = null;
		XmlReader xr = new XmlReader();
		mx = xr.readAll("file/100001.xml");
		XmlWriter xw = new XmlWriter("file/100001.xml", mx);
		xw.createDocument();
	}
}
