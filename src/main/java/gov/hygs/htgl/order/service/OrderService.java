package gov.hygs.htgl.order.service;

import gov.hygs.htgl.dz.entity.Ddxx;
import gov.hygs.htgl.dz.entity.DdxxMx;
import gov.hygs.htgl.dz.entity.Drdd;
import gov.hygs.htgl.dz.entity.Yhxx;
import gov.hygs.htgl.order.dao.OrderDao;
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
public class OrderService {
	@Resource
	OrderDao orderDao;

	public void getDdxx(Page page, Map<String, Object> param) {
		// TODO Auto-generated method stub
		orderDao.getDdxx(page, param);
	}

	public void updateDdxx(List<Ddxx> list) {
		// TODO Auto-generated method stub
		for (Ddxx sp : list) {
			Integer id = null;
			if (EntityUtils.getState(sp).equals(EntityState.NEW)) {
				id = orderDao.addDdxx(sp);
			}
			if (EntityUtils.getState(sp).equals(EntityState.MODIFIED)) {
				orderDao.updateDdxx(sp);
			}
			if (EntityUtils.getState(sp).equals(EntityState.DELETED)) {
				orderDao.deleteDdxx(sp);
				orderDao.deleteDdxxMx(sp.getId());
			}
			
			List<DdxxMx> ddxxMxs = sp.getDdxxMxs();
			if (ddxxMxs != null) {
				for (DdxxMx ddxxMx : ddxxMxs) {
					if(id!=null){
						ddxxMx.setDdbid(id);
					}else{
						ddxxMx.setDdbid(sp.getId());
					}
					if (EntityUtils.getState(ddxxMx).equals(EntityState.NEW)) {
						orderDao.addDdxxMx(ddxxMx);
					}
					if (EntityUtils.getState(ddxxMx).equals(EntityState.MODIFIED)) {
						orderDao.updateDdxxMx(ddxxMx);
					}
					if (EntityUtils.getState(ddxxMx).equals(EntityState.DELETED)) {
						orderDao.deleteDdxxMx(ddxxMx);
					}
				}
			}
		}
	}

	public List<Map<String, Object>> getDdxxMx(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return orderDao.getDdxxMx(param);
	}
	
	public List<Map<String, Object>> getChdMx(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return orderDao.getChdMx(param);
	}

	public List<Map<String, Object>> getDd(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return orderDao.getDd(param);
	}

	public List<Map<String, Object>> getDdxx(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return orderDao.getDdxx(param);
	}

	public void insertShd(List<Ddxx> list) {
		// TODO Auto-generated method stub		
		for (Ddxx sp : list) {
			Integer id = null;
			if (EntityUtils.getState(sp).equals(EntityState.NEW)) {
				id = orderDao.insertShd(sp);
			}
			if (EntityUtils.getState(sp).equals(EntityState.MODIFIED)) {
				orderDao.updateShd(sp);
			}
			if (EntityUtils.getState(sp).equals(EntityState.DELETED)) {
				orderDao.deleteShd(sp);
				orderDao.deleteShdMx(sp.getId());
			}
			
			List<DdxxMx> ddxxMxs = sp.getChdxxMxs();
			if (ddxxMxs != null) {
				for (DdxxMx ddxxMx : ddxxMxs) {
					if(id!=null){
						ddxxMx.setChdid(id);
					}else{
						ddxxMx.setChdid(sp.getId());
					}
					if (EntityUtils.getState(ddxxMx).equals(EntityState.NEW)) {
						orderDao.insertShdMx(ddxxMx);
					}
					if (EntityUtils.getState(ddxxMx).equals(EntityState.MODIFIED)) {
						orderDao.updateShdMx(ddxxMx);
					}
					if (EntityUtils.getState(ddxxMx).equals(EntityState.DELETED)) {
						orderDao.deleteShdMx(ddxxMx);
					}
				}
			}
		}
	}

	public List<Map<String, Object>> getChd(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return orderDao.getChd(param);
	}

	public List<Map<String, Object>> getDzxx(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return orderDao.getDzxx(param);
	}

	public List<Map<String, Object>> getCh(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return orderDao.getCh(param);
	}

	public List<Map<String, Object>> getWl(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return orderDao.getWl(param);
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
			shd = orderDao.getShd(id);
			beans.put("chd", shd);
			shd = orderDao.getShdMx(id);
			beans.put("chdmx", shd);
			fileName=importExcel.importExcel(beans, templateFile);
		}
		return fileName;
	}

	public void getYhxx(Page page, Map<String, Object> param) {
		// TODO Auto-generated method stub
		orderDao.getYhxx(page, param);
	}

	
	public void updateYhxx(List<Yhxx> list) {
		// TODO Auto-generated method stub
		for (Yhxx sp : list) {
			if (EntityUtils.getState(sp).equals(EntityState.NEW)) {
				orderDao.addYhxx(sp);
			}
			if (EntityUtils.getState(sp).equals(EntityState.MODIFIED)) {
				orderDao.updateYhxx(sp);
			}
			if (EntityUtils.getState(sp).equals(EntityState.DELETED)) {
				orderDao.deleteYhxx(sp);
			}
		}
	}

	public String getZdbh(Map<String, Object> para) {
		// TODO Auto-generated method stub
		return orderDao.getZdbh(para);
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
						Drdd drdd = new Drdd();
						if(row.getCell(0)!=null&&row.getCell(0).getCellType()==HSSFCell.CELL_TYPE_STRING){
							drdd.setKh(row.getCell(0).getStringCellValue());
						}
						if(row.getCell(1)!=null&&row.getCell(1).getCellType()==HSSFCell.CELL_TYPE_NUMERIC){
							drdd.setXdrq(row.getCell(1).getDateCellValue());
						}
						if(row.getCell(2)!=null&&row.getCell(2).getCellType()==HSSFCell.CELL_TYPE_STRING){
							drdd.setDdh(row.getCell(2).getStringCellValue());
						}
						if(drdd.getDdh()==null){
							break;
						}
						if(row.getCell(3)!=null&&row.getCell(3).getCellType()==HSSFCell.CELL_TYPE_STRING){
							drdd.setWlbh(row.getCell(3).getStringCellValue());
						}
						if(row.getCell(4)!=null&&row.getCell(4).getCellType()==HSSFCell.CELL_TYPE_STRING){
							drdd.setWlmc(row.getCell(4).getStringCellValue());
						}
						if(row.getCell(5)!=null&&row.getCell(5).getCellType()==HSSFCell.CELL_TYPE_STRING){
							drdd.setCz(row.getCell(5).getStringCellValue());
						}
						if(row.getCell(6)!=null&&row.getCell(6).getCellType()==HSSFCell.CELL_TYPE_STRING){
							drdd.setClgg(row.getCell(6).getStringCellValue());
						}
						if(row.getCell(7)!=null&&row.getCell(7).getCellType()==HSSFCell.CELL_TYPE_STRING){
							drdd.setGg(row.getCell(7).getStringCellValue());
						}
						if(row.getCell(8)!=null&&row.getCell(8).getCellType()==HSSFCell.CELL_TYPE_STRING){
							drdd.setDw(row.getCell(8).getStringCellValue());
						}
						if(row.getCell(9)!=null&&row.getCell(9).getCellType()==HSSFCell.CELL_TYPE_STRING){
							drdd.setJhrq(row.getCell(9).getStringCellValue());
						}
						if(row.getCell(10)!=null&&row.getCell(10).getCellType()==HSSFCell.CELL_TYPE_NUMERIC){
							drdd.setDdsl((int)row.getCell(10).getNumericCellValue());
						}
						orderDao.saveDdxx(drdd);
					}
				

				}
				return null;
			}

	public List<Map<String, Object>> getChDd(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return orderDao.getChDd(param);
	}
	public List<Map<String, Object>> getChDdMx(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return orderDao.getChDdMx(param);
	}

	public List<Map<String, Object>> getKh(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return orderDao.getKh(param);
	}
}