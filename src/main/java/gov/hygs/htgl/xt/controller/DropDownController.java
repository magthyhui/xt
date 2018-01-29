package gov.hygs.htgl.xt.controller;

import gov.hygs.htgl.xt.service.DropDownService;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.bstek.dorado.annotation.DataProvider;

@Component
public class DropDownController {
	@Resource
	DropDownService dropDownService;

	@DataProvider
	public List<Map<String,Object>> getClxx(){
		return dropDownService.getClxx();
	}
	
	@DataProvider
	public List<Map<String,Object>> getCpxx(Map<String,Object> para){
		return dropDownService.getCpxx(para);
	}
}
