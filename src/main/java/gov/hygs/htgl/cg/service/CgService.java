package gov.hygs.htgl.cg.service;

import gov.hygs.htgl.cg.dao.CgDao;
import gov.hygs.htgl.cg.entity.CgDrdd;
import gov.hygs.htgl.cg.entity.CgYhxx;
import gov.hygs.htgl.cg.entity.Cgd;
import gov.hygs.htgl.cg.entity.CgdMx;
import gov.hygs.htgl.cg.entity.Cgxx;
import gov.hygs.htgl.cg.entity.CgxxMx;
import gov.hygs.htgl.dz.entity.Drdd;
import gov.hygs.htgl.utils.excel.ImportExcel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.springframework.stereotype.Service;

import com.bstek.dorado.data.entity.EntityState;
import com.bstek.dorado.data.entity.EntityUtils;
import com.bstek.dorado.data.provider.Page;
import com.bstek.dorado.uploader.UploadFile;

@Service
public class CgService {
	@Resource
	CgDao cgDao;

	public void getYhxx(Page page, Map<String, Object> param) {
		// TODO Auto-generated method stub
		cgDao.getYhxx(page, param);
	}

	
	public void updateYhxx(List<CgYhxx> list) {
		// TODO Auto-generated method stub
		for (CgYhxx sp : list) {
			if (EntityUtils.getState(sp).equals(EntityState.NEW)) {
				cgDao.addYhxx(sp);
			}
			if (EntityUtils.getState(sp).equals(EntityState.MODIFIED)) {
				cgDao.updateYhxx(sp);
			}
			if (EntityUtils.getState(sp).equals(EntityState.DELETED)) {
				cgDao.deleteYhxx(sp);
			}
		}
	}


	public List<Map<String, Object>> getCg(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return cgDao.getCg(param);
	}


	public List<Map<String, Object>> getCgxxMx(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return cgDao.getCgxxMx(param);
	}


	public List<Map<String, Object>> getCgxx(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return cgDao.getCgxx(param);
	}


	public void updateCgxx(List<Cgxx> list) {
			// TODO Auto-generated method stub
			for (Cgxx sp : list) {
				Integer id = null;
				if (EntityUtils.getState(sp).equals(EntityState.NEW)) {
					id = cgDao.addCgxx(sp);
				}
				if (EntityUtils.getState(sp).equals(EntityState.MODIFIED)) {
					cgDao.updateCgxx(sp);
				}
				if (EntityUtils.getState(sp).equals(EntityState.DELETED)) {
					cgDao.deleteCgxx(sp);
					cgDao.deleteCgxxMx(sp.getId());
				}
				
				List<CgxxMx> CgxxMxs = sp.getCgxxMxs();
				if (CgxxMxs != null) {
					for (CgxxMx CgxxMx : CgxxMxs) {
						if(id!=null){
							CgxxMx.setCgxxid(id);
						}else{
							CgxxMx.setCgxxid(sp.getId());
						}
						if (EntityUtils.getState(CgxxMx).equals(EntityState.NEW)) {
							cgDao.addCgxxMx(CgxxMx);
						}
						if (EntityUtils.getState(CgxxMx).equals(EntityState.MODIFIED)) {
							cgDao.updateCgxxMx(CgxxMx);
						}
						if (EntityUtils.getState(CgxxMx).equals(EntityState.DELETED)) {
							cgDao.deleteCgxxMx(CgxxMx);
						}
				}
			}
		}
	}


	public String setCgzt(Map param) {
		// TODO Auto-generated method stub
		return cgDao.setCgzt(param);
	}


	public List<Map<String, Object>> getWl(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return cgDao.getWl(param);
	}


	public List<Map<String, Object>> getChDd(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return cgDao.getChDd(param);
	}


	public List<Map<String, Object>> getChDdMx(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return cgDao.getChDdMx(param);	}
	
	public void insertShd(List<Cgd> list) {
		// TODO Auto-generated method stub		
		for (Cgd sp : list) {
			Integer id = null;
			if (EntityUtils.getState(sp).equals(EntityState.NEW)) {
				id = cgDao.insertShd(sp);
			}
			if (EntityUtils.getState(sp).equals(EntityState.MODIFIED)) {
				cgDao.updateShd(sp);
			}
			if (EntityUtils.getState(sp).equals(EntityState.DELETED)) {
				cgDao.deleteShd(sp);
				cgDao.deleteShdMx(sp.getId());
			}
			
			List<CgdMx> ddxxMxs = sp.getChdxxMxs();
			if (ddxxMxs != null) {
				for (CgdMx ddxxMx : ddxxMxs) {
					if(id!=null){
						ddxxMx.setCgdid(id);
					}else{
						ddxxMx.setCgdid(sp.getId());
					}
					if (EntityUtils.getState(ddxxMx).equals(EntityState.NEW)) {
						cgDao.insertShdMx(ddxxMx);
					}
					if (EntityUtils.getState(ddxxMx).equals(EntityState.MODIFIED)) {
						cgDao.updateShdMx(ddxxMx);
					}
					if (EntityUtils.getState(ddxxMx).equals(EntityState.DELETED)) {
						cgDao.deleteShdMx(ddxxMx);
					}
				}
			}
		}
	}
	
	public String exportExcel(Map param) throws Exception {
		// TODO Auto-generated method stub
		String fileName = null;
		if(param != null){
			Integer id = (Integer)param.get("id");
			ImportExcel importExcel = new ImportExcel();
			Map beans=new HashMap();
			List shd=new ArrayList();
			shd = cgDao.getShd(id);
			String khjc = (String)((Map<String, Object>) shd.get(0)).get("khjc");
			String lx = (String)((Map<String, Object>) shd.get(0)).get("lx");
			String templateFile = "cgd";
			beans.put("chd", shd);
			shd = cgDao.getShdMx(id);
			beans.put("chdmx", shd);
			if(lx!=null){
				templateFile=templateFile+lx;
				fileName=importExcel.importExcel(beans, templateFile);
			}else{
				fileName =null;
			}
		}
		return fileName;
	}


	public List<Map<String, Object>> getSytj(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return cgDao.getSytj(param);
	}
	
	public Map<String, Object> importDdxx(UploadFile file,
			Map<String, Object> param) throws IOException {
				// TODO Auto-generated method stub
				String filename = file.getFileName();
				String extname = filename.substring(filename.lastIndexOf('.') + 1);
				if ("xls".equalsIgnoreCase(extname) || "xlsx".equalsIgnoreCase(extname)) {
					Map<String, Object> kqxx = null;
					POIFSFileSystem pfs = new POIFSFileSystem(file.getInputStream());
					HSSFWorkbook work = new HSSFWorkbook(pfs);
					HSSFSheet sheet = work.getSheetAt(0);
					String kqsj = null;
					HSSFRow row  =null;
					for(int i = 1;i<=sheet.getLastRowNum();i++){
						row= sheet.getRow(i);
						CgDrdd drdd = new CgDrdd();
						if(row.getCell(0)!=null&&row.getCell(0).getCellType()==HSSFCell.CELL_TYPE_STRING){
							drdd.setKh(row.getCell(0).getStringCellValue());
						}
						if(row.getCell(1)!=null&&row.getCell(1).getCellType()==HSSFCell.CELL_TYPE_NUMERIC){
							drdd.setXdrq(row.getCell(1).getDateCellValue());
						}
						if(row.getCell(2)!=null&&row.getCell(2).getCellType()==HSSFCell.CELL_TYPE_NUMERIC){
							drdd.setJhrq(row.getCell(2).getDateCellValue());
						}
						if(row.getCell(3)!=null&&row.getCell(3).getCellType()==HSSFCell.CELL_TYPE_STRING){
							drdd.setDddh(row.getCell(3).getStringCellValue());
						}
						if(drdd.getDddh()==null){
							break;
						}
						if(row.getCell(4)!=null&&row.getCell(4).getCellType()==HSSFCell.CELL_TYPE_STRING){
							drdd.setLx(row.getCell(4).getStringCellValue());
						}
						if(row.getCell(5)!=null&&row.getCell(5).getCellType()==HSSFCell.CELL_TYPE_STRING){
							drdd.setCplh(row.getCell(5).getStringCellValue());
						}
						if(row.getCell(6)!=null&&row.getCell(6).getCellType()==HSSFCell.CELL_TYPE_STRING){
							drdd.setWlmc(row.getCell(6).getStringCellValue());
						}
						if(row.getCell(7)!=null&&row.getCell(7).getCellType()==HSSFCell.CELL_TYPE_STRING){
							drdd.setGg(row.getCell(7).getStringCellValue());
						}
						if(row.getCell(8)!=null&&row.getCell(8).getCellType()==HSSFCell.CELL_TYPE_STRING){
							drdd.setCz(row.getCell(8).getStringCellValue());
						}
						if(row.getCell(9)!=null&&row.getCell(9).getCellType()==HSSFCell.CELL_TYPE_NUMERIC){
							drdd.setDj(row.getCell(9).getNumericCellValue());
						}
						if(row.getCell(10)!=null&&row.getCell(10).getCellType()==HSSFCell.CELL_TYPE_STRING){
							drdd.setDw(row.getCell(10).getStringCellValue());
						}
						if(row.getCell(11)!=null&&row.getCell(11).getCellType()==HSSFCell.CELL_TYPE_STRING){
							drdd.setBzs(row.getCell(11).getStringCellValue());
						}
						if(row.getCell(12)!=null&&row.getCell(12).getCellType()==HSSFCell.CELL_TYPE_STRING){
							drdd.setMs(row.getCell(12).getStringCellValue());
						}
						if(row.getCell(13)!=null&&row.getCell(13).getCellType()==HSSFCell.CELL_TYPE_NUMERIC){
							drdd.setDdsl((int)row.getCell(13).getNumericCellValue());
						}
						cgDao.saveDdxx(drdd);
					}
				

				}
				return null;
			}
}