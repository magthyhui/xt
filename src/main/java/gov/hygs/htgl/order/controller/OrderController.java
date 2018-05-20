package gov.hygs.htgl.order.controller;

import gov.hygs.htgl.dz.entity.Ddxx;
import gov.hygs.htgl.dz.entity.Yhxx;
import gov.hygs.htgl.order.service.OrderService;

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
public class OrderController {
	@Resource
	OrderService orderService;

	@DataProvider
	public void getDdxx(Page page, Map<String, Object> param) {
		orderService.getDdxx(page, param);
	}
	
	@DataProvider
	public List<Map<String, Object>> getDdxx( Map<String, Object> param) {
		return orderService.getDdxx( param);
	}
	
	@DataProvider
	public List<Map<String, Object>> getChd( Map<String, Object> param) {
		return orderService.getChd( param);
	}
	
	@DataProvider
	public List<Map<String, Object>> getDzxx( Map<String, Object> param) {
		return orderService.getDzxx( param);
	}
	
	@DataProvider
	public List<Map<String, Object>> getDd(Map<String, Object> param) {
		return orderService.getDd( param);
	}
	
	@DataProvider
	public List<Map<String, Object>> getWl(Map<String, Object> param) {
		return orderService.getWl( param);
	}
	
	@DataProvider
	public List<Map<String, Object>> getChDd(Map<String, Object> param) {
		return orderService.getChDd( param);
	}
	
	@DataProvider
	public List<Map<String, Object>> getChDdMx(Map<String, Object> param) {
		return orderService.getChDdMx( param);
	}
	
	@DataProvider
	public List<Map<String, Object>> getCh(Map<String, Object> param) {
		return orderService.getCh( param);
	}
	
	@DataProvider
	public List<Map<String, Object>> getDdxxMx(Map<String, Object> param) {
		return orderService.getDdxxMx(param);
	}
	
	@DataProvider
	public List<Map<String, Object>> getChdMx(Map<String, Object> param) {
		return orderService.getChdMx(param);
	}

	@DataResolver
	@Transactional
	public void updateDdxx(List<Ddxx> list) {
		orderService.updateDdxx(list);
	}

	
	@DataResolver
	@Transactional
	public void insertShd(List<Ddxx> list) {
		orderService.insertShd(list);
	}
	
	@Expose
	public String exportExcel(Map param) throws Exception {
		return orderService.exportExcel(param);
	}
	
	@DataProvider
	public void getYhxx(Page page, Map<String, Object> param) {
		orderService.getYhxx(page, param);
	}

	@DataResolver
	@Transactional
	public void updateYhxx(List<Yhxx> list) {
		orderService.updateYhxx(list);
	}
	
	@DataResolver
	@Expose
	public String getZdbh(Map<String,Object> para){
		return orderService.getZdbh(para);
	}
	
	@Transactional
	@FileResolver
	public String importDdxx(UploadFile file, Map<String, Object> param){
		try {
			Map<String,Object> map = orderService.importDdxx(file, param);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//return null;
		}
		return null;
	}
	
	@DataProvider
	public List<Map<String, Object>> getKh(Map<String, Object> param) {
		return orderService.getKh(param);
	}

}
