package gov.hygs.htgl.kqgl.controller;

import gov.hygs.htgl.dz.entity.Ddxx;
import gov.hygs.htgl.kqgl.service.KqglService;
import gov.hygs.htgl.utils.excel.TkcjTableExcelToList;
import gov.hygs.htgl.utils.excel.entity.TkcjTable;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.bstek.dorado.annotation.DataProvider;
import com.bstek.dorado.annotation.DataResolver;
import com.bstek.dorado.annotation.Expose;
import com.bstek.dorado.uploader.UploadFile;
import com.bstek.dorado.uploader.annotation.FileResolver;

@Component
public class KqglController {
	@Resource
	KqglService kqglService;

	
	
	/**
	 * 导入题库采集表excel
	 * @param file
	 * @param param
	 * @return
	 */
	@Transactional
	@FileResolver
	public String importKqxx(UploadFile file, Map<String, Object> param){
		try {
			Map<String,Object> map = kqglService.importKqxx(file, param);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//return null;
		}
		return null;
	}
	
	@DataProvider
	public List<Map<String,Object>> getKqjl(Map<String,Object> para){
		return kqglService.getKqjl(para);
	}

	@DataProvider
	public List<Map<String,Object>> getKqmx(Map<String,Object> para){
		return kqglService.getKqmx(para);
	}

	@DataProvider
	public List<Map<String,Object>> getKqAll(Map<String,Object> para){
		return kqglService.getKqAll(para);
	}

	@Transactional
	@Expose
	public String updateXg(Map<String,Object> para){
		return kqglService.updateXg(para);
	}
	
	@Transactional
	@Expose
	public String bulidKqhz(Map<String,Object> para){
		return kqglService.bulidKqhz(para);
	}
	
	@DataProvider
	public List<Map<String,Object>> getKqhz(Map<String,Object> para){
		return kqglService.getKqhz(para);
	}
	
	@DataProvider
	public List<Map<String,Object>> getKqwtsj(Map<String,Object> para){
		return kqglService.getKqwtsj(para);
	}
	
	@Transactional
	@Expose
	public String updateSj(Map<String,Object> para){
		return kqglService.updateSj(para);
	}
	
	@DataResolver
	@Transactional
	public void updateKqhz(List<Map<String,Object>> list) {
		kqglService.updateKqhz(list);
	}
	
	@Expose
	public String exportExcel(Map param) throws Exception {
		return kqglService.exportExcel(param);
	}

}
