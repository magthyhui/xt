package gov.hygs.htgl.kqgl.service;

import gov.hygs.htgl.kqgl.dao.KqglDao;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.springframework.stereotype.Service;

import com.bstek.dorado.data.entity.EntityState;
import com.bstek.dorado.data.entity.EntityUtils;
import com.bstek.dorado.uploader.UploadFile;

@Service
public class KqglService {
	@Resource
	KqglDao kqglDao;

	public Map<String, Object> importKqxx(UploadFile file,
			Map<String, Object> param) throws IOException {
		// TODO Auto-generated method stub
		String filename = file.getFileName();
		String extname = filename.substring(filename.lastIndexOf('.') + 1);
		if ("xls".equalsIgnoreCase(extname) || "xlsx".equalsIgnoreCase(extname)) {
			Map<String, Object> kqxx = null;
			POIFSFileSystem pfs = new POIFSFileSystem(file.getInputStream());
			HSSFWorkbook work = new HSSFWorkbook(pfs);
			int i = work.getSheetIndex("鑰冨嫟璁板綍");
			HSSFSheet sheet = work.getSheetAt(i);
			String kqsj = null;
			HSSFRow row = sheet.getRow(2);
			HSSFCell cell = row.getCell(2);
			// 鑾峰彇鑰冨嫟鏃堕棿
			if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
				kqsj = cell.getStringCellValue();
			}
			row = sheet.getRow(3);
			int ts = 0;
			int sjts = 0;
			// 鑾峰彇琛ㄦ牸鏈�鍚庝竴澶╂棩鏈�
			for (; ts < row.getLastCellNum(); ts++) {
				if (row.getCell(ts).getCellType() == HSSFCell.CELL_TYPE_BLANK) {
					break;
				} else {
					sjts = (int) row.getCell(ts).getNumericCellValue();
				}
			}
			int kqsjId = kqglDao.saveKqsj(kqsj);
			// 閬嶅巻姣忎釜浜虹殑淇℃伅
			for (int rownum = 4; rownum <= sheet.getLastRowNum(); rownum += 2) {
				kqxx = getKqxx(rownum, sheet, sjts);
				kqglDao.saveKqjl(kqsjId, kqxx);
			}

		}
		kqglDao.callSp();
		return null;
	}

	private Map<String, Object> getKqxx(int rownum, HSSFSheet sheet, int sjts) {
		// TODO Auto-generated method stub
		Map<String, Object> jl = new HashMap<String, Object>();
		HSSFRow row = sheet.getRow(rownum);
		String gh = null, xm = null, bm = null;
		HSSFCell cell = row.getCell(2);
		if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
			gh = cell.getStringCellValue();
		}
		cell = row.getCell(10);
		if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
			xm = cell.getStringCellValue();
		}
		cell = row.getCell(20);
		if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
			bm = cell.getStringCellValue();
		}
		row = sheet.getRow(rownum + 1);
		int ts = 0;
		// 閬嶅巻姣忓ぉ鐨勬墦琛ㄦ椂闂�
		for (int cellnum = 0; cellnum < sjts; cellnum++) {
			cell = row.getCell(cellnum);
			// if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
			jl.put("rq" + (cellnum + 1), cell.getStringCellValue());
			ts++;
			// }
		}
		jl.put("gh", gh);
		jl.put("xm", xm);
		jl.put("bm", bm);
		jl.put("ts", ts);
		return jl;
	}

	public List<Map<String, Object>> getKqjl(Map<String, Object> para) {
		// TODO Auto-generated method stub
		return kqglDao.getKqjl(para);
	}

	public String updateXg(Map<String, Object> para) {
		// TODO Auto-generated method stub
		int id = (Integer) para.get("mxid");
		String bz = (String) para.get("bz");
		Map<String, Object> mx = kqglDao.getKqjlmxByid(id);
		if (mx != null) {
			if (mx.get("zbq") == null
					|| !((String) mx.get("zbq")).equals((String) para
							.get("zbq"))) {
				if (para.get("zbq") != null) {
					mx.put("zbq", (String) para.get("zbq"));
				}

			}
			if (mx.get("zbz") == null
					|| !((String) mx.get("zbz")).equals((String) para
							.get("zbz"))) {
				if (para.get("zbz") != null) {
					mx.put("zbz", (String) para.get("zbz"));
				}

			}
			if (mx.get("wbq") == null
					|| !((String) mx.get("wbq")).equals((String) para
							.get("wbq"))) {
				if (para.get("wbq") != null) {
					mx.put("wbq", (String) para.get("wbq"));
				}
			}
			if (mx.get("wbz") == null
					|| !((String) mx.get("wbz")).equals((String) para
							.get("wbz"))) {
				if (para.get("wbz") != null) {
					mx.put("wbz", (String) para.get("wbz"));
				}

			}
			if (mx.get("ybq") == null
					|| !((String) mx.get("ybq")).equals((String) para
							.get("ybq"))) {
				if (para.get("ybq") != null) {
					mx.put("ybq", (String) para.get("ybq"));
				}

			}
			if (mx.get("ybz") == null
					|| !((String) mx.get("ybz")).equals((String) para
							.get("ybz"))) {
				if (para.get("ybz") != null) {
					mx.put("ybz", (String) para.get("ybz"));
				}
			}
			mx.put("bz", bz);
			String returnValue =  kqglDao.updateXg(mx);
			kqglDao.callSp();
			return returnValue;
		}
		return "no";
	}

	/*
	 * int id = (Integer) para.get("mxid"); String bz = (String) para.get("bz");
	 * String qj = (String) para.get("qj"); String cc = (String) para.get("cc");
	 * String kg = (String) para.get("kg"); if (qj!=null&&qj.contains("Q")) { if
	 * (para.get("xs") != null) { qj = qj + para.get("xs"); } return
	 * kqglDao.updateQj(id, qj); }else if (cc!=null&&cc.contains("W")) { if
	 * (para.get("xs") != null) { cc = cc + para.get("xs"); } return
	 * kqglDao.updateQj(id, cc); } else if (kg!=null&&kg.contains("K")) { if
	 * (para.get("xs") != null) { kg = kg + para.get("xs"); } return
	 * kqglDao.updateQj(id, kg); } else { Map<String, Object> mx =
	 * kqglDao.getKqjlmxByid(id); if (mx != null) { if (mx.get("zbq") == null ||
	 * !((String) mx.get("zbq")).equals((String) para .get("zbq"))) { if
	 * (para.get("zbq") != null) { mx.put("zbq", (String) para.get("zbq") ); }
	 * 
	 * } if (mx.get("zbz") == null || !((String) mx.get("zbz")).equals((String)
	 * para .get("zbz"))) { if (para.get("zbz") != null) { mx.put("zbz",
	 * (String) para.get("zbz") ); }
	 * 
	 * } if (mx.get("wbq") == null || !((String) mx.get("wbq")).equals((String)
	 * para .get("wbq"))) { if (para.get("wbq") != null) { mx.put("wbq",
	 * (String) para.get("wbq") ); } } if (mx.get("wbz") == null || !((String)
	 * mx.get("wbz")).equals((String) para .get("wbz"))) { if (para.get("wbz")
	 * != null) { mx.put("wbz", (String) para.get("wbz") ); }
	 * 
	 * } if (mx.get("ybq") == null || !((String) mx.get("ybq")).equals((String)
	 * para .get("ybq"))) { if (para.get("ybq") != null) { mx.put("ybq",
	 * (String) para.get("ybq") ); }
	 * 
	 * } if (mx.get("ybz") == null || !((String) mx.get("ybz")).equals((String)
	 * para .get("ybz"))) { if (para.get("ybz") != null) { mx.put("ybz",
	 * (String) para.get("ybz") ); } } mx.put("bz", bz); return
	 * kqglDao.updateXg(mx); } } return "no";
	 */
	public List<Map<String, Object>> getKqAll(Map<String, Object> para) {
		// TODO Auto-generated method stub
		return kqglDao.getKqAll(para);
	}

	public List<Map<String, Object>> getKqhz(Map<String, Object> para) {
		// TODO Auto-generated method stub
		return kqglDao.getKqhz(para);
	}

	public List<Map<String, Object>> getKqwtsj(Map<String, Object> para) {
		// TODO Auto-generated method stub
		return kqglDao.getKqwtsj(para);
	}

	public String updateSj(Map<String, Object> para) {
		// TODO Auto-generated method stub
		return kqglDao.updateSj(para);
	}

	public List<Map<String, Object>> getKqmx(Map<String, Object> para) {
		// TODO Auto-generated method stub
		return kqglDao.getKqmx(para);
	}

	public String bulidKqhz(Map<String, Object> para) {
		// TODO Auto-generated method stub
		return kqglDao.bulidKqhz(para);
	}

	public void updateKqhz(List<Map<String, Object>> list) {
		// TODO Auto-generated method stub
		for (Map<String, Object> sp : list) {
			Integer id = null;
			if (EntityUtils.getState(sp).equals(EntityState.MODIFIED)) {
				kqglDao.updateKqhz(sp);
			}
		}
	}

}