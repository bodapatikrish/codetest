package com.test.service;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.test.exception.MyException;

public class ServiceTest {

	private IService iService;

	@Before
	public void before() {
		iService = new Service();
	}

	@Test
	public void testConvert() throws MyException {
		assertEquals("one hundred twenty three", iService.convert(123));
	}

	@Test
	public void testConvertOne() throws MyException {
		assertEquals("one", iService.convert(1));
	}

	@Test
	public void testConvertBulk() throws MyException {
		assertEquals("fifty six million nine hundred forty five thousand seven hundred eighty one",
				iService.convert(56945781));
	}

	@Test
	public void testConvertZero() throws MyException {
		assertEquals("zero", iService.convert(0));
	}

	@Test(expected = MyException.class)
	public void testConvertMyException() throws MyException {
		iService.convert(-1);
	}
}
