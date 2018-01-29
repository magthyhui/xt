package gov.hygs.htgl.service;

import gov.hygs.htgl.entity.Exam;
import gov.hygs.htgl.entity.Tktm;
import gov.hygs.htgl.entity.Tkxzx;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.bstek.dorado.data.provider.Page;

public interface ZstkglService {

	public void getZstkInfo(Page<Tktm> page, Map<String, Object> param);

	public Collection<Tkxzx> getTkzxzInfoByZstkId(String id);

	public Collection<Tkxzx> getDaXzxInfoByZstkId(String id);

	public Collection<Tkxzx> getToFInfo();

	public void updateZstk(List<Tktm> list);

	public void getYxtkInfo(Page<Tktm> page, Map<String, Object> param);

	public void getRandomTktmFilter(Page<Tktm> page, Map<String, Object> param);

	public void updateTkfxtsInfo(Map<String, Object> param);

	public void updateKstsjlInfo(Map<String, Object> param);

	public Integer getSomeInfoBySystemPropsKey(String param);

	public void getRandomFxtsTktmFilter(Page<Tktm> page,
			Map<String, Object> param);

	public Integer getFxtsInfoFromSystemProps();

	public void getExamInfo(Page page, Map<String, Object> param);

	public void getExamDetailInfo(Page<Tktm> page, Map<String, Object> param);

	public void getZstkInfoByKsts(Page<Tktm> page, Map<String, Object> param);

	public Map<String, Object> getGroupByExamId(String param);

	public void updateKstsjlDetailInfo(Map<String, Object> param);

	public void deleteKstsjlInfo(String examid);

	public Map<String, Object> getDaylInfo(String param);

	public Map<String, Object> getDaMapInfoByZstkId(String id);

	public void updateExamInfo(Map<String, Object> param);

}
