package gov.hygs.htgl.xt.service.impl;

import gov.hygs.htgl.xt.dao.XtDao;
import gov.hygs.htgl.xt.entity.Cgclxx;
import gov.hygs.htgl.xt.entity.Cgxx;
import gov.hygs.htgl.xt.entity.Cpkcxx;
import gov.hygs.htgl.xt.entity.Cpxx;
import gov.hygs.htgl.xt.entity.Ddcpxx;
import gov.hygs.htgl.xt.entity.Ddxx;
import gov.hygs.htgl.xt.entity.Kcxx;
import gov.hygs.htgl.xt.service.XtService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.bstek.dorado.data.entity.EntityState;
import com.bstek.dorado.data.entity.EntityUtils;
import com.bstek.dorado.data.provider.Page;

@Service
public class XtServiceImpl implements XtService {
	@Resource
	XtDao xtDao;

	@Override
	public void getKcxx(Page page, Map<String, Object> param) {
		// TODO Auto-generated method stub
		xtDao.getKcxx(page, param);
	}

	@Override
	public void updateKcxx(List<Kcxx> list) {
		// TODO Auto-generated method stub
		for (Kcxx sp : list) {
			if (EntityUtils.getState(sp).equals(EntityState.NEW)) {
				xtDao.addKcxx(sp);
			}
			if (EntityUtils.getState(sp).equals(EntityState.MODIFIED)) {
				xtDao.updateKcxx(sp);
			}
			if (EntityUtils.getState(sp).equals(EntityState.DELETED)) {
				xtDao.deleteKcxx(sp);
			}
		}
	}

	@Override
	public void getCpxx(Page page, Map<String, Object> param) {
		// TODO Auto-generated method stub
		xtDao.getCpxx(page, param);
	}

	@Override
	public void updateCpxx(List<Cpxx> list) {
		// TODO Auto-generated method stub
		for (Cpxx sp : list) {
			if (EntityUtils.getState(sp).equals(EntityState.NEW)) {
				xtDao.addCpxx(sp);
			}
			if (EntityUtils.getState(sp).equals(EntityState.MODIFIED)) {
				xtDao.updateCpxx(sp);
			}
			if (EntityUtils.getState(sp).equals(EntityState.DELETED)) {
				xtDao.deleteCpxx(sp);
			}
			List<Cpkcxx> clxxs = sp.getClxxs();
			if (clxxs != null) {
				for (Cpkcxx clxx : clxxs) {
					if (EntityUtils.getState(clxx).equals(EntityState.NEW)) {
						xtDao.addCpkcxx(clxx);
					}
					if (EntityUtils.getState(clxx).equals(EntityState.MODIFIED)) {
						xtDao.updateCpkcxx(clxx);
					}
					if (EntityUtils.getState(clxx).equals(EntityState.DELETED)) {
						xtDao.deleteCpkcxx(clxx);
					}
				}
			}

		}
	}

	@Override
	public List<Map<String, Object>> getCpkcxx(Map<String, Object> para) {
		// TODO Auto-generated method stub
		return xtDao.getCpkcxx(para);
	}

	@Override
	public void getDdxx(Page page, Map<String, Object> param) {
		// TODO Auto-generated method stub
		xtDao.getDdxx(page, param);
	}

	@Override
	public void updateDdxx(List<Ddxx> list) {
		// TODO Auto-generated method stub
		for (Ddxx sp : list) {
			if (EntityUtils.getState(sp).equals(EntityState.NEW)) {
				xtDao.addDdxx(sp);
			}
			if (EntityUtils.getState(sp).equals(EntityState.MODIFIED)) {
				xtDao.updateDdxx(sp);
			}
			if (EntityUtils.getState(sp).equals(EntityState.DELETED)) {
				xtDao.deleteDdxx(sp);
			}
			List<Ddcpxx> clxxs = sp.getCpxxs();
			if (clxxs != null) {
				for (Ddcpxx clxx : clxxs) {
					if (EntityUtils.getState(clxx).equals(EntityState.NEW)) {
						xtDao.addDdcpxx(clxx);
					}
					if (EntityUtils.getState(clxx).equals(EntityState.MODIFIED)) {
						xtDao.updateDdcpxx(clxx);
					}
					if (EntityUtils.getState(clxx).equals(EntityState.DELETED)) {
						xtDao.deleteDdcpxx(clxx);
					}
				}
			}
		}
	}

	@Override
	public List<Map<String, Object>> getDdcpxx(Map<String, Object> para) {
		// TODO Auto-generated method stub
		return xtDao.getDdcpxx(para);
	}

	@Override
	public void getCgxx(Page page, Map<String, Object> param) {
		// TODO Auto-generated method stub
		xtDao.getCgxx(page, param);
	}

	@Override
	public void updateCgxx(List<Cgxx> list) {
		// TODO Auto-generated method stub
		for (Cgxx sp : list) {
			if (EntityUtils.getState(sp).equals(EntityState.NEW)) {
				xtDao.addCgxx(sp);
			}
			if (EntityUtils.getState(sp).equals(EntityState.MODIFIED)) {
				xtDao.updateCgxx(sp);
			}
			if (EntityUtils.getState(sp).equals(EntityState.DELETED)) {
				xtDao.deleteCgxx(sp);
			}
			List<Cgclxx> clxxs = sp.getClxxs();
			if (clxxs != null) {
				for (Cgclxx clxx : clxxs) {
					if (EntityUtils.getState(clxx).equals(EntityState.NEW)) {
						xtDao.addCgclxx(clxx);
					}
					if (EntityUtils.getState(clxx).equals(EntityState.MODIFIED)) {
						xtDao.updateCgclxx(clxx);
					}
					if (EntityUtils.getState(clxx).equals(EntityState.DELETED)) {
						xtDao.deleteCgclxx(clxx);
					}
				}
			}
		}
	}

	@Override
	public List<Map<String, Object>> getCgclxx(Map<String, Object> para) {
		// TODO Auto-generated method stub
		return xtDao.getCgclxx(para);
	}

	@Override
	public Map<String, Object> getJskc(Integer id) {
		// TODO Auto-generated method stub
		Map<String,Object> result = new HashMap<String,Object>();
		if(xtDao.checkJs(id)){
			List<Map<String, Object>> clsls = xtDao.getCpxx(id);//订单需要的材料
			if(clsls.size()==0){
				result.put("status", "1");//所有材料库存充足！
			}else{
				for(Map<String,Object> clsl:clsls){
					Map<String,Object> map = xtDao.getDdKcxx(clsl);//每种材料实际量
					if(map!=null){
						clsl.putAll(map);
					}
				}
			}
			result.put("status", "0");//正常
			result.put("clsls", clsls);
		}else{
			result.put("status", "2");//已经计算过了！
		}
		
		
		return result;
	}

	@Override
	public void createCgxx(List<Map<String, Object>> list,
			Map<String, Object> para) {
		// TODO Auto-generated method stub
		UUID uuid = UUID.randomUUID();
		double zj = 0;
		String ddBh = (String)para.get("ddBh");
		xtDao.saveDdjs(ddBh);
		for(Map<String,Object> ls :list){
			zj=zj+(Double)ls.get("zj");
		}
		Integer pid = xtDao.createCgxx(zj,ddBh,uuid.toString());
		for(Map<String,Object> ls :list){
			xtDao.insertCgkcxx(ls,uuid.toString(), pid);
		}
		
		
	}

	@Override
	public Map<String, Object> wcCg(Map<String, Object> para) {
		// TODO Auto-generated method stub
		Map<String,Object> result = new HashMap<String,Object>();
		Integer id = (Integer)para.get("id");
		if(xtDao.checkCg(id)){
			result.put("status", "0");//正常
			List<Cgclxx> clxxs = (List<Cgclxx>)para.get("clxxs");
			xtDao.saveCg(id);
			for(Cgclxx clxx : clxxs){
				xtDao.cgTokc(clxx);
			}
		}else{
			result.put("status", "1");//已经采购过了！
		}
		return result;
	}

	@Override
	public Map<String, Object> wcCd(Map<String, Object> para) {
		// TODO Auto-generated method stub
		Map<String,Object> result = new HashMap<String,Object>();
		int i = 0;
		Integer id = (Integer)para.get("id");
		String c = xtDao.checkCd(id);
		if(c.equals("1")){
			result.put("status", "0");//正常
			List<Ddcpxx> cpxxs = (List<Ddcpxx>)para.get("cpxxs");
			for(Ddcpxx cpxx : cpxxs){
				xtDao.xhKcsl(cpxx);
				if(cpxx.getCdsl()>cpxx.getSjscs()){
					xtDao.cdDd(cpxx);
					result.put("wc"+i,"0");
				}else{
					xtDao.saveCptoKc(cpxx);
					result.put("wc"+i,"1");
				}
				i=i+1;
			}
			boolean wc = true;
			for(int j = 0; j<i;j++){
				if(((String)result.get("wc"+j)).equals("0")){
					wc = false;
				}
			}
			if(wc){
				xtDao.saveCdDd(id);
			}
		}else if(c.equals("0")){
			result.put("status", "1");//已经完成了！
		}else if(c.equals("2")){
			result.put("status", "2");//采购没完成！
		}
		
		return result;
	}
}
