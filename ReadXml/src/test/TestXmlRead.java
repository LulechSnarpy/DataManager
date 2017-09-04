package test;

import java.io.IOException;

import org.dom4j.DocumentException;

import bean.MyXml;
import filereaders.XmlReader;
import filewriters.WriteXmlData;

public class TestXmlRead {
		public static void main(String[] args) throws DocumentException, IOException {
			XmlReader xr = new XmlReader();
			MyXml mx = xr.readAll("file/010.xml");
			WriteXmlData.fomateAndOutput("file", mx);
		}
}
