package com.perfgenie.vmstat.analysis;

import org.junit.Assert;
import org.junit.Test;

public class ProcessVmstatTest {
	
	@Test
	public void testPrintHelloWorld() {

		VmstatAnalysis vm=new VmstatAnalysis();
		//Assert.isEquals(vm.processVmstat(), 0.0);
		Assert.assertEquals(vm.processVmstat(), 0.0, 0.0);

	}


}
