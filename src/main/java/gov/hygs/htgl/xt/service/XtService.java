package gov.hygs.htgl.xt.service;

import gov.hygs.htgl.xt.entity.Cgxx;
import gov.hygs.htgl.xt.entity.Cpxx;
import gov.hygs.htgl.xt.entity.Ddxx;
import gov.hygs.htgl.xt.entity.Kcxx;

import java.util.List;
import java.util.Map;

import com.bstek.dorado.data.provider.Page;

public interface XtService {

	void getKcxx(Page page, Map<String, Object> param);

	void updateKcxx(List<Kcxx> list);

	void getCpxx(Page page, Map<String, Object> param);

	void updateCpxx(List<Cpxx> list);

	List<Map<String, Object>> getCpkcxx(Map<String, Object> para);

	void getDdxx(Page page, Map<String, Object> param);

	void updateDdxx(List<Ddxx> list);

	List<Map<String, Object>> getDdcpxx(Map<String, Object> para);

	void getCgxx(Page page, Map<String, Object> param);

	void updateCgxx(List<Cgxx> list);

	List<Map<String, Object>> getCgclxx(Map<String, Object> para);

	Map<String, Object> getJskc(Integer id);

	void createCgxx(List<Map<String, Object>> list, Map<String, Object> para);

	Map<String, Object> wcCg(Map<String, Object> para);

	Map<String, Object> wcCd(Map<String, Object> para);



	
}
