package gov.hygs.htgl.xt.controller;

import gov.hygs.htgl.xt.entity.Cgxx;
import gov.hygs.htgl.xt.entity.Cpxx;
import gov.hygs.htgl.xt.entity.Ddxx;
import gov.hygs.htgl.xt.entity.Kcxx;
import gov.hygs.htgl.xt.service.XtService;

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
public class XtController {
	@Resource
	XtService xtService;

	@DataProvider
	public void getKcxx(Page page, Map<String, Object> param) {
		xtService.getKcxx(page, param);
	}

	@DataResolver
	@Transactional
	public void updateKcxx(List<Kcxx> list) {
		xtService.updateKcxx(list);
	}
	
	@DataProvider
	public void getCpxx(Page page, Map<String, Object> param) {
		xtService.getCpxx(page, param);
	}

	@DataResolver
	@Transactional
	public void updateCpxx(List<Cpxx> list) {
		xtService.updateCpxx(list);
	}
	
	@DataProvider
	public List<Map<String, Object>> getCpkcxx(Map<String, Object> para) {
		return this.xtService.getCpkcxx(para);
	}
	
	@DataProvider
	public void getDdxx(Page page, Map<String, Object> param) {
		xtService.getDdxx(page, param);
	}

	@DataResolver
	@Transactional
	public void updateDdxx(List<Ddxx> list) {
		xtService.updateDdxx(list);
	}
	
	@DataProvider
	public List<Map<String, Object>> getDdcpxx(Map<String, Object> para) {
		return this.xtService.getDdcpxx(para);
	}
	
	
	@DataProvider
	public void getCgxx(Page page, Map<String, Object> param) {
		xtService.getCgxx(page, param);
	}

	@DataResolver
	@Transactional
	public void updateCgxx(List<Cgxx> list) {
		xtService.updateCgxx(list);
	}
	
	@DataProvider
	public List<Map<String, Object>> getCgclxx(Map<String, Object> para) {
		return xtService.getCgclxx(para);
	}
	
	@Expose
	@Transactional
	public Map<String, Object> getJskc(Integer id){
		return xtService.getJskc(id);
	}
	@DataResolver
	@Transactional
	public void createCgxx(List<Map<String,Object>> list,Map<String,Object> para) {
		xtService.createCgxx(list,para);
	}
	
	@Expose
	@Transactional
	public Map<String, Object> wcCg(Map<String, Object> para ){
		return xtService.wcCg(para);
	}
	@Expose
	@Transactional
	public Map<String, Object> wcCd(Map<String, Object> para ){
		return xtService.wcCd(para);
	}
	
}
