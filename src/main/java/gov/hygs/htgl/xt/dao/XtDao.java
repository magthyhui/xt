package gov.hygs.htgl.xt.dao;

import gov.hygs.htgl.xt.entity.Cgclxx;
import gov.hygs.htgl.xt.entity.Cgxx;
import gov.hygs.htgl.xt.entity.Cpkcxx;
import gov.hygs.htgl.xt.entity.Cpxx;
import gov.hygs.htgl.xt.entity.Ddcpxx;
import gov.hygs.htgl.xt.entity.Ddxx;
import gov.hygs.htgl.xt.entity.Kcxx;

import java.util.List;
import java.util.Map;

import com.bstek.dorado.data.provider.Page;

public interface XtDao {

	void getKcxx(Page page, Map<String, Object> param);

	void addKcxx(Kcxx sp);

	void updateKcxx(Kcxx sp);

	void deleteKcxx(Kcxx sp);

	void getCpxx(Page page, Map<String, Object> param);

	void addCpxx(Cpxx sp);

	void updateCpxx(Cpxx sp);

	void deleteCpxx(Cpxx sp);

	List<Map<String, Object>> getCpkcxx(Map<String, Object> para);

	void addCpkcxx(Cpkcxx clxx);

	void updateCpkcxx(Cpkcxx clxx);

	void deleteCpkcxx(Cpkcxx clxx);

	void getDdxx(Page page, Map<String, Object> param);

	void addDdxx(Ddxx sp);

	void updateDdxx(Ddxx sp);

	void deleteDdxx(Ddxx sp);

	List<Map<String, Object>> getDdcpxx(Map<String, Object> para);

	void addDdcpxx(Ddcpxx clxx);

	void updateDdcpxx(Ddcpxx clxx);

	void deleteDdcpxx(Ddcpxx clxx);


	void getCgxx(Page page, Map<String, Object> param);

	void addCgxx(Cgxx sp);

	void updateCgxx(Cgxx sp);

	void deleteCgxx(Cgxx sp);

	List<Map<String, Object>> getCgclxx(Map<String, Object> para);

	void addCgclxx(Cgclxx clxx);

	void updateCgclxx(Cgclxx clxx);

	void deleteCgclxx(Cgclxx clxx);

	List<Map<String, Object>> getCpxx(Integer id);

	Map<String, Object> getDdKcxx(Map<String, Object> clsl);

	void insertCgkcxx(Map<String, Object> ls, String uuid,Integer pid);

	Integer createCgxx(double zj, String ddBh,String uuid);

	boolean checkJs(Integer id);

	void saveDdjs(String ddBh);

	void saveCg(Integer id);

	void cgTokc(Cgclxx clxx);

	boolean checkCg(Integer id);

	String checkCd(Integer id);

	void cdDd(Ddcpxx cpxx);

	void saveCptoKc(Ddcpxx cpxx);

	void saveCdDd(Integer id);

	void xhKcsl(Ddcpxx cpxx);


	

}
