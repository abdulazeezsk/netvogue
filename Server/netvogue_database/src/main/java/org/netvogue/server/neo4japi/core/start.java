package org.netvogue.server.neo4japi.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.netvogue.server.neo4japi.domain.Boutique;
import org.netvogue.server.neo4japi.domain.Brand;
import org.netvogue.server.neo4japi.domain.User;
import org.netvogue.server.neo4japi.service.BoutiqueService;
import org.netvogue.server.neo4japi.service.BoutiqueServiceImpl;

public class start {

	//@Autowired BoutiqueService service;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String boutiqueName = "Neo4j";
		Boutique b = new Boutique(boutiqueName, boutiqueName);
		b.setName(boutiqueName);
		b.setEmail(boutiqueName);
		System.out.println("Name is - " + b.getName() + " Email is " + b.getEmail());
		ApplicationContext ctxt = new ClassPathXmlApplicationContext("neo4j-application-context.xml");
		
		BoutiqueService s = ctxt.getBean("BoutiqueService", BoutiqueServiceImpl.class);
		String error = new String();
		s.AddNewBoutique(b, error);
		System.out.println("Added: Name is - " + b.getName());
		
		/*Boutique Temp = s.GetBoutique(boutiqueName);
		if(null != Temp)
			System.out.println("Found: Name is - " + Temp.getName());
		
		//s.deleteAll();
		Boutique Temp1 = s.GetBoutique(boutiqueName);
		if(Temp1 == null) {
			System.out.println("Delete is successful");
		} else {
			System.out.println("Delete is unsuccessful " + Temp1.getEmail());
		}*/
		
	}

}