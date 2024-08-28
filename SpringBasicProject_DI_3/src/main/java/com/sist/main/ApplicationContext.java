package com.sist.main;

import java.io.File;
import java.util.*;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.apache.commons.collections.map.HashedMap;

public class ApplicationContext {
	private Map clsMap = new HashMap();
	public ApplicationContext(String path) {
		try {
			SAXParserFactory spf = SAXParserFactory.newInstance();
			SAXParser sp = spf.newSAXParser();
			HandlerMapping hm = new HandlerMapping();
			sp.parse(new File(path), hm);
			clsMap = hm.getMap();
		} catch (Exception ex) {}
	}
	public Object getBean(String id) {
		return clsMap.get(id);
	}
}