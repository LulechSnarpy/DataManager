package test;

import java.io.IOException;

import org.dom4j.DocumentException;

import filecorrector.XmlCorrector;

public class XmlCorrectorTest {
	public static void main(String[] args) {
		XmlCorrector xc = new XmlCorrector();
	
		try {
			xc.tryCorrect("file/204044.xml");
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
