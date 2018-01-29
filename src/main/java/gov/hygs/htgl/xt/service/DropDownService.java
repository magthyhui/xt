package gov.hygs.htgl.xt.service;

import java.util.List;
import java.util.Map;

import gov.hygs.htgl.xt.dao.DropDownDao;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

@Service
public class DropDownService {
	@Resource
	DropDownDao dropDownDao;

	public List<Map<String, Object>> getClxx() {
		// TODO Auto-generated method stub
		return dropDownDao.getClxx();
	}
	
	public List<Map<String, Object>> getCpxx(Map<String, Object> para) {
		// TODO Auto-generated method stub
		return dropDownDao.getCpxx(para);
	}
}
