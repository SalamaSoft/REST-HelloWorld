package com.salama.test.ws.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.chikuhou.mfs.common.util.MD5Util;
import com.salama.service.clouddata.core.annotation.ReturnValueConverter;
import com.salama.test.ws.data.TestData1;
import com.salama.test.ws.data.TestData2;

/**
 * 
 * @author XingGu Liu
 *
 */
public class HelloWorldService {

	public static String test1(String testParam) {
		return "hellow world! \n" + testParam;
	}
	
	@ReturnValueConverter(valueFromRequestParam = "responseType", 
			jsonpReturnVariableNameFromRequestParam="jsonpReturn",
			skipObjectConvert = false)
	public static TestData1 testDataType1() {
		return createTestData1("Hello World!");
	}

	@ReturnValueConverter(valueFromRequestParam = "responseType", 
			jsonpReturnVariableNameFromRequestParam="jsonpReturn",
			skipObjectConvert = false)
	public static TestData2 testDataType2() {
		return createTestData2(1, "Hello World!");
	}
	
	@ReturnValueConverter(valueFromRequestParam = "responseType", 
			jsonpReturnVariableNameFromRequestParam="jsonpReturn",
			skipObjectConvert = false)
	public static List<TestData1> testListType1(int count) {
		List<TestData1> list = new ArrayList<TestData1>();
		
		TestData1 data;
		for(int i = 0; i < count; i++) {
			data = createTestData1("test" + i);
			
			list.add(data);
		}
		
		return list;
	}
	
	@ReturnValueConverter(valueFromRequestParam = "responseType", 
			jsonpReturnVariableNameFromRequestParam="jsonpReturn",
			skipObjectConvert = false)
	public static List<TestData2> testListType2(int count) {
		List<TestData2> list = new ArrayList<TestData2>();
		
		TestData2 data;
		for(int i = 0; i < count; i++) {
			data = createTestData2(i, "test" + i);
			
			list.add(data);
		}
		
		return list;
	}
	
	private static TestData1 createTestData1(String msg) {
		TestData1 data1 = new TestData1();

		data1.setMsg(msg);
		data1.setBytes(MD5Util.MD5(data1.getMsg().getBytes()));
		data1.setDateTime(new Date());
		data1.setFloatVal(1.01f);
		data1.setIntVal(2);
		data1.setTimestamp(System.currentTimeMillis());
		
		return data1;
	}
	
	private static TestData2 createTestData2(int num, String msg) {
		TestData2 data2 = new TestData2();
		
		data2.setNum(1);
		data2.setData1(createTestData1(msg));
		
		return data2;
	}
	
}
