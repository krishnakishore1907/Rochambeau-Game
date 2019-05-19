/**
 *
 */
package com.rochambeau.service.impl;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Krishna Kishore
 * 
 */
public class InputServiceImplTest {

	/**
	 * Test Method checking for singleton object.
	 */
	@Test
	public void testGetInputServiceImpl_testSingleton() {
		InputServiceImpl inputServiceImpl_one = InputServiceImpl.getInputServiceImpl();
		InputServiceImpl inputServiceImpl_two = InputServiceImpl.getInputServiceImpl();
		InputServiceImpl inputServiceImpl_three = InputServiceImpl.getInputServiceImpl();
		Assert.assertEquals(inputServiceImpl_one, inputServiceImpl_two);
		Assert.assertEquals(inputServiceImpl_two, inputServiceImpl_three);

	}

}
