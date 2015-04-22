/**
 * 
 */
package tests;

import static org.junit.Assert.*;

import org.hyperic.sigar.Cpu;
import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;

import org.junit.Test;

import smg.CpuLoad;

/**
 * @author Andrew
 *
 */
public class CpuLoadTest {

	@Test
	public void doesSigarWork() {
		
		double test = 0;
		try {
			test = new Sigar().getCpuPerc().getCombined();
		} catch (SigarException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("(Does Sigar Work?) result should be larger than 0: " + (int) (test*100));
		
		assertTrue(test > 0);
		
		//fail("Not yet implemented");
	}

}
