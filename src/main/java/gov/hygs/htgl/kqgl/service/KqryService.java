package gov.hygs.htgl.kqgl.service;

import gov.hygs.htgl.kqgl.dao.KqryDao;
import gov.hygs.htgl.kqgl.entity.Kqry;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.bstek.dorado.data.entity.EntityState;
import com.bstek.dorado.data.entity.EntityUtils;
import com.bstek.dorado.data.provider.Page;

@Service
public class KqryService {
	@Resource
	KqryDao kqryDao;

	
	public void getKqry(Page page, Map<String, Object> param) {
		// TODO Auto-generated method stub
		kqryDao.getKqry(page, param);
	}

	
	public void updateKqry(List<Kqry> list) {
		// TODO Auto-generated method stub
		for (Kqry sp : list) {
			if (EntityUtils.getState(sp).equals(EntityState.NEW)) {
				kqryDao.addKqry(sp);
			}
			if (EntityUtils.getState(sp).equals(EntityState.MODIFIED)) {
				kqryDao.updateKqry(sp);
			}
			if (EntityUtils.getState(sp).equals(EntityState.DELETED)) {
				kqryDao.deleteKqry(sp);
			}
		}
	}


	public List<Map<String, Object>> getKqsj(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return kqryDao.getKqsj(param);
	}
}