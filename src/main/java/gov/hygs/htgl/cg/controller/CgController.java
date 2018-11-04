package gov.hygs.htgl.cg.controller;

import gov.hygs.htgl.cg.entity.CgYhxx;
import gov.hygs.htgl.cg.entity.Cgd;
import gov.hygs.htgl.cg.entity.Cgxx;
import gov.hygs.htgl.cg.service.CgService;

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
public class CgController {
	@Resource
	CgService cgService;

	@DataProvider
	public void getYhxx(Page page, Map<String, Object> param) {
		cgService.getYhxx(page, param);
	}

	@DataResolver
	@Transactional
	public void updateYhxx(List<CgYhxx> list) {
		cgService.updateYhxx(list);
	}

	
	@DataProvider
	public List<Map<String, Object>> getCg(Map<String, Object> param) {
		return cgService.getCg( param);
	}
	
	@DataProvider
	public List<Map<String, Object>> getCgxxMx( Map<String, Object> param) {
		return cgService.getCgxxMx( param);
	}
	
	@DataProvider
	public List<Map<String, Object>> getCgxx( Map<String, Object> param) {
		return cgService.getCgxx( param);
	}
	
	@DataResolver
	@Transactional
	public void updateCgxx(List<Cgxx> list) {
		cgService.updateCgxx(list);
	}
	
	@Expose
	public String setCgzt(Map param) throws Exception {
		return cgService.setCgzt(param);
	}
	
	@DataProvider
	public List<Map<String, Object>> getWl(Map<String, Object> param) {
		return cgService.getWl( param);
	}
	
	@DataProvider
	public List<Map<String, Object>> getChDd(Map<String, Object> param) {
		return cgService.getChDd( param);
	}
	
	@DataProvider
	public List<Map<String, Object>> getChDdMx(Map<String, Object> param) {
		return cgService.getChDdMx( param);
	}
	
	@DataResolver
	@Transactional
	public void insertShd(List<Cgd> list) {
		cgService.insertShd(list);
	}
	
	@Expose
	public String exportExcel(Map param) throws Exception {
		return cgService.exportExcel(param);
	}
	@DataProvider
	public List<Map<String, Object>> getSytj( Map<String, Object> param) {
		return cgService.getSytj( param);
	}
	
	@Transactional
	@FileResolver
	public String importDdxx(UploadFile file, Map<String, Object> param){
		try {
			Map<String,Object> map = cgService.importDdxx(file, param);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//return null;
		}
		return null;
	}
	
}
