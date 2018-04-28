package com.xh.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

public class Dom4jOperationXML {
	
	private static List<String> urls = null;
	
	public static void main(String[] args) throws Exception {
		getUrls("F:\\myResource\\mytest.xml", "Key");
		for (String string : urls) {
			System.out.println(string);
		}
	}
	
	public static List<String> getUrls (String xml, String elementName) throws DocumentException {
		urls = new ArrayList<String>();
		Document document = getXmlByFile("F:\\myResource\\mytest.xml");
		treeWalk(document,"Key");
		return urls;
	}
	
	public static void getXmlByUrl () {
		
	}

	public static Document getXmlByFile (String file) throws DocumentException {
		// file == "F:\\myResource\\mytest.xml"
		// 创建saxReader对象
		SAXReader reader = new SAXReader();
		// 通过read方法读取一个文件 转换成Document对象
		return reader.read(new File(file));
	}
	
	public static void treeWalk(Document document, String elementName) {
	    treeWalk(document.getRootElement(), elementName);
	}

	public static void treeWalk(Element element, String elementName) {
	    for (int i = 0, size = element.nodeCount(); i < size; i++) {
	        Node node = element.node(i);
	        if (node instanceof Element) {
//	        	System.out.println(((Element) node).getName() + " == " + ((Element) node).getStringValue());
	            if (((Element) node).getName() == elementName) {
//	            	System.out.println(((Element) node).getStringValue());
	            	urls.add(((Element) node).getStringValue());
	            }
	        	treeWalk((Element) node, elementName);
	        }else {
	            // do something…
//	        	System.out.println(element.node(i).getNodeType() + "==" + element.node(i).getStringValue());
	        }
	    }
	}
	
}
