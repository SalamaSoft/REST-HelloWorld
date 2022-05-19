package com.salama.test.ws.service;

import com.salama.service.clouddata.core.annotation.ReturnValueConverter;
import com.salama.test.ws.data.Shop;

import java.util.ArrayList;
import java.util.List;

public class ShopService {

	@ReturnValueConverter(valueFromRequestParam = "responseType", 
			jsonpReturnVariableNameFromRequestParam="jsonpReturn",
			skipObjectConvert = false)
	public static List<Shop> searchShop() {
		List<Shop> dataList = new ArrayList<Shop>();
	
		Shop data;
		for(int i = 0; i < 3; i++) {
			data = new Shop();
			data.setShopId("" + i);
			data.setShopName("Shop name " + i);
			data.setShopAddress("Shop address " + i);
			data.setShopTel("Shop tel " + i);
			
			dataList.add(data);
		}
		
		return dataList;
	}
	
}
