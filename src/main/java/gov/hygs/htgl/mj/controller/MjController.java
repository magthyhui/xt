package gov.hygs.htgl.mj.controller;

import gov.hygs.htgl.mj.entity.Ddxx;
import gov.hygs.htgl.mj.service.MjService;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.bstek.dorado.annotation.DataProvider;
import com.bstek.dorado.annotation.DataResolver;
import com.bstek.dorado.annotation.Expose;
import com.bstek.dorado.data.provider.Page;
import com.bstek.dorado.uploader.UploadFile;
import com.bstek.dorado.uploader.annotation.FileResolver;

@Component
public class MjController {
	@Resource
	 MjService mjService;

	@DataProvider
	public void getDdxx(Page page, Map<String, Object> param) {
		mjService.getDdxx(page, param);
	}
	
	@DataProvider
	public List<Map<String, Object>> getDdxx( Map<String, Object> param) {
		return mjService.getDdxx( param);
	}
	
	@DataProvider
	public List<Map<String, Object>> getChd( Map<String, Object> param) {
		return mjService.getChd( param);
	}
	
	@DataProvider
	public List<Map<String, Object>> getDzxx( Map<String, Object> param) {
		return mjService.getDzxx( param);
	}
	
	@DataProvider
	public List<Map<String, Object>> getDd(Map<String, Object> param) {
		return mjService.getDd( param);
	}
	
	@DataProvider
	public List<Map<String, Object>> getWl(Map<String, Object> param) {
		return mjService.getWl( param);
	}
	
	@DataProvider
	public List<Map<String, Object>> getChDd(Map<String, Object> param) {
		return mjService.getChDd( param);
	}
	
	@DataProvider
	public List<Map<String, Object>> getChDdMx(Map<String, Object> param) {
		return mjService.getChDdMx( param);
	}
	
	@DataProvider
	public List<Map<String, Object>> getCh(Map<String, Object> param) {
		return mjService.getCh( param);
	}
	
	@DataProvider
	public List<Map<String, Object>> getDdxxMx(Map<String, Object> param) {
		return mjService.getDdxxMx(param);
	}
	
	@DataProvider
	public List<Map<String, Object>> getChdMx(Map<String, Object> param) {
		return mjService.getChdMx(param);
	}

	@DataResolver
	@Transactional
	public void updateDdxx(List<Ddxx> list) {
		mjService.updateDdxx(list);
	}

	
	@DataResolver
	@Transactional
	public void insertShd(List<Ddxx> list) {
		mjService.insertShd(list);
	}
	
	@Expose
	public String exportExcel(Map param) throws Exception {
		return mjService.exportExcel(param);
	}

	@Expose
	public String setCd(Map param) {
		return mjService.setCd(param);
	}

	
	@Transactional
	@FileResolver
	public String importDdxx(UploadFile file, Map<String, Object> param){
		try {
			Map<String,Object> map = mjService.importDdxx(file, param);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//return null;
		}
		return null;
	}
	
	@DataProvider
	public List<Map<String, Object>> getKh(Map<String, Object> param) {
		return mjService.getKh(param);
	}

	@Expose
	public String setDdzt(Map param) throws Exception {
		return mjService.setDdzt(param);
	}
}
