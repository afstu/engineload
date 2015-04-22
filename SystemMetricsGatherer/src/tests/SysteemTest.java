package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import smg.Omgeving;
import smg.Systeem;

public class SysteemTest {

	@Test
	public void doesSysteemReturnCorrectHostname() {
		
		Systeem s = new Systeem();
		Omgeving o = s.bouwOmgeving();
		
		String naam = o.getSysteemnaam();
		
		System.out.println("(Does Systeem Work?) result should be \"flaptop\" : " + naam);
				
		assertEquals("flaptop", naam);	
		
		}

	@Test
	public void doesSysteemReadConfigFile() {
		
		Systeem s = new Systeem();
		Omgeving o = s.bouwOmgeving();
		
		String node = o.getMetricURL();
		
		System.out.println("(Does Systeem Work?) result should be \"graphite\" : " + node);
				
		assertEquals("graphite", node);	
		
		}

}
