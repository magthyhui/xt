package gov.hygs.htgl.xt.service;

import java.util.List;
import java.util.Map;

import gov.hygs.htgl.xt.dao.TjDao;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

@Service
public class TjService {
	@Resource
	TjDao tjDao;

	public List<Map<String, Object>> getDdhz(Map<String, Object> para) {
		// TODO Auto-generated method stub
		return tjDao.getDdhz(para);
	}

	
}
