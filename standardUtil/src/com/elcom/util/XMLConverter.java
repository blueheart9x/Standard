package com.elcom.util;

import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.bind.JAXB;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;


public class XMLConverter {

	public static <T> String toXML(Object obj,Class<T> actualObject) throws Exception{
		
		JAXBContext jaxbContext;
		try {
			jaxbContext = JAXBContext.newInstance(actualObject);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

			// output pretty printed
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			StringWriter sw = new StringWriter();
			
			jaxbMarshaller.marshal(obj,sw);

			return sw.toString();
			
		} catch (JAXBException e) {
			throw new Exception(e.getMessage());
		}
		
	}
	
	public static <T> T toObject(String xml,Class<T> actualObject) throws Exception{
		
		try {
			T obj = (T) JAXB.unmarshal(new StringReader(xml), actualObject);
			
			return obj;
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

}
