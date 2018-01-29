package gov.hygs.htgl.xt.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.gdky.restfull.dao.BaseJdbcDao;
@Repository
public class DropDownDao  extends BaseJdbcDao {

	public List<Map<String, Object>> getClxx() {
		// TODO Auto-generated method stub
		String sql ="select cl_mc mc,cl_bh dm from xt_kcxx  ";
		return this.jdbcTemplate.queryForList(sql);
	}

	public List<Map<String, Object>> getCpxx(Map<String, Object> para) {
		// TODO Auto-generated method stub
		String lx = (String) para.get("lx");
		String sql ="select cp_mc mc,cp_bh dm from xt_cpxx where lx = ?  ";
		return this.jdbcTemplate.queryForList(sql,new Object[]{lx});
	}

}
