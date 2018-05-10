package gov.hygs.htgl.dz.controller;

import gov.hygs.htgl.dz.entity.Ddxx;
import gov.hygs.htgl.dz.entity.Yhxx;
import gov.hygs.htgl.dz.service.DzService;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.bstek.dorado.annotation.DataProvider;
import com.bstek.dorado.annotation.DataResolver;
import com.bstek.dorado.annotation.Expose;
import com.bstek.dorado.data.provider.Page;

@Component
public class DzController {
	@Resource
	DzService dzService;

	@DataProvider
	public void getDdxx(Page page, Map<String, Object> param) {
		dzService.getDdxx(page, param);
	}
	
	@DataProvider
	public List<Map<String, Object>> getDdxx( Map<String, Object> param) {
		return dzService.getDdxx( param);
	}
	
	@DataProvider
	public List<Map<String, Object>> getChd( Map<String, Object> param) {
		return dzService.getChd( param);
	}
	
	@DataProvider
	public List<Map<String, Object>> getDzxx( Map<String, Object> param) {
		return dzService.getDzxx( param);
	}
	
	@DataProvider
	public List<Map<String, Object>> getDd(Map<String, Object> param) {
		return dzService.getDd( param);
	}
	
	@DataProvider
	public List<Map<String, Object>> getWl(Map<String, Object> param) {
		return dzService.getWl( param);
	}
	
	@DataProvider
	public List<Map<String, Object>> getCh(Map<String, Object> param) {
		return dzService.getCh( param);
	}
	
	@DataProvider
	public List<Map<String, Object>> getDdxxMx(Map<String, Object> param) {
		return dzService.getDdxxMx(param);
	}

	@DataResolver
	@Transactional
	public void updateDdxx(List<Ddxx> list) {
		dzService.updateDdxx(list);
	}
	
	@DataResolver
	@Transactional
	public void insertShd(List<Ddxx> list) {
		dzService.insertShd(list);
	}
	
	@Expose
	public String exportExcel(Map param) throws Exception {
		return dzService.exportExcel(param);
	}
	
	@DataProvider
	public void getYhxx(Page page, Map<String, Object> param) {
		dzService.getYhxx(page, param);
	}

	@DataResolver
	@Transactional
	public void updateYhxx(List<Yhxx> list) {
		dzService.updateYhxx(list);
	}
	
	@DataResolver
	@Expose
	public String getZdbh(Map<String,Object> para){
		return dzService.getZdbh(para);
	}
	
}
