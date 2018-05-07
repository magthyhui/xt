package gov.hygs.htgl.dz.service;

import gov.hygs.htgl.dz.dao.DzDao;
import gov.hygs.htgl.dz.entity.Ddxx;
import gov.hygs.htgl.dz.entity.DdxxMx;
import gov.hygs.htgl.utils.excel.ImportExcel;
import gov.hygs.htgl.xt.entity.Cpkcxx;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.jxls.exception.ParsePropertyException;

import org.springframework.stereotype.Service;

import com.bstek.dorado.data.entity.EntityState;
import com.bstek.dorado.data.entity.EntityUtils;
import com.bstek.dorado.data.provider.Page;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;

@Service
public class DzService {
	@Resource
	DzDao dzDao;

	public void getDdxx(Page page, Map<String, Object> param) {
		// TODO Auto-generated method stub
		dzDao.getDdxx(page, param);
	}

	public void updateDdxx(List<Ddxx> list) {
		// TODO Auto-generated method stub
		for (Ddxx sp : list) {
			Integer id = null;
			if (EntityUtils.getState(sp).equals(EntityState.NEW)) {
				id = dzDao.addDdxx(sp);
			}
			if (EntityUtils.getState(sp).equals(EntityState.MODIFIED)) {
				dzDao.updateDdxx(sp);
			}
			if (EntityUtils.getState(sp).equals(EntityState.DELETED)) {
				dzDao.deleteDdxx(sp);
			}
			List<DdxxMx> ddxxMxs = sp.getDdxxMxs();
			if (ddxxMxs != null) {
				for (DdxxMx ddxxMx : ddxxMxs) {
					if(id!=null){
						ddxxMx.setDdbid(id);
					}
					if (EntityUtils.getState(ddxxMx).equals(EntityState.NEW)) {
						dzDao.addDdxxMx(ddxxMx);
					}
					if (EntityUtils.getState(ddxxMx).equals(EntityState.MODIFIED)) {
						dzDao.updateDdxxMx(ddxxMx);
					}
					if (EntityUtils.getState(ddxxMx).equals(EntityState.DELETED)) {
						dzDao.deleteDdxxMx(ddxxMx);
					}
				}
			}
		}
	}

	public List<Map<String, Object>> getDdxxMx(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return dzDao.getDdxxMx(param);
	}

	public List<Map<String, Object>> getDd(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return dzDao.getDd(param);
	}

	public List<Map<String, Object>> getDdxx(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return dzDao.getDdxx(param);
	}

	public void insertShd(List<Ddxx> list) {
		// TODO Auto-generated method stub
		for (Ddxx sp : list) {
			Integer id = dzDao.insertShd(sp);
			List<DdxxMx> ddxxMxs = sp.getDdxxMxs();
			if (ddxxMxs != null) {
				for (DdxxMx ddxxMx : ddxxMxs) {
					dzDao.insertShdMx(id,ddxxMx);
				}
			}
		}
	}

	public List<Map<String, Object>> getChd(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return dzDao.getChd(param);
	}

	public List<Map<String, Object>> getDzxx(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return dzDao.getDzxx(param);
	}

	public List<Map<String, Object>> getCh(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return dzDao.getCh(param);
	}

	public List<Map<String, Object>> getWl(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return dzDao.getWl(param);
	}

	public String exportExcel(Map param) throws Exception {
		// TODO Auto-generated method stub
		String fileName = null;
		if(param != null){
			Integer id = (Integer)param.get("id");
			String type=(String) param.get("type");
			ImportExcel importExcel = new ImportExcel();
			Map beans=new HashMap();
			List shd=new ArrayList();
			String templateFile = "shd";
			shd = dzDao.getShd(id);
			beans.put("chd", shd);
			shd = dzDao.getShdMx(id);
			beans.put("chdmx", shd);
			fileName=importExcel.importExcel(beans, templateFile);
		}
		return fileName;
	}

	
}