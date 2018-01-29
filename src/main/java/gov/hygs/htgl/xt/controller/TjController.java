package gov.hygs.htgl.xt.controller;

import gov.hygs.htgl.xt.service.TjService;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.bstek.dorado.annotation.DataProvider;

@Component
public class TjController {
	@Resource
	TjService tjService;

	@DataProvider
	public List<Map<String,Object>> getDdhz(Map<String,Object> para){
		return tjService.getDdhz(para);
	}
}
