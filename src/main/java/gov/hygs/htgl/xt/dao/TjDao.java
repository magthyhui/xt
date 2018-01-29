package gov.hygs.htgl.xt.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.gdky.restfull.dao.BaseJdbcDao;
@Repository
public class TjDao  extends BaseJdbcDao {

	public List<Map<String, Object>> getDdhz(Map<String, Object> para) {
		// TODO Auto-generated method stub
		StringBuffer sql =new StringBuffer();
		List<Object> args = new ArrayList<Object>();
		sql.append(" select a.*,a.dd_bh ddBh,b.cp_bh cpBh,c.cp_mc cpMc,b.cdsl,b.cdrq from xt_ddxx a,xt_dd_log b,xt_cpxx c  " );
		sql.append(" where a.dd_bh=b.dd_bh and b.cp_bh=c.cp_bh ");
		if(para.get("cdq")!=null){
			sql.append(" and DATE_FORMAT(b.cdrq, '%Y-%m-%d') >= DATE_FORMAT(?, '%Y-%m-%d') ");
			args.add(para.get("cdq"));
		}
		if(para.get("cdz")!=null){
			sql.append(" and DATE_FORMAT(b.cdrq, '%Y-%m-%d') <= DATE_FORMAT(?, '%Y-%m-%d') ");
			args.add(para.get("cdz"));
		}
		if(para.get("kh")!=null){
			sql.append(" and a.kh like ?  ");
			args.add("%"+para.get("kh")+"%");
		}
		if(para.get("ddbh")!=null){
			sql.append(" and a.dd_bh like ? ");
			args.add("%"+para.get("ddbh")+"%");
		}
		sql.append(" order by b.cdrq desc ");
		return this.jdbcTemplate.queryForList(sql.toString(),args.toArray());
	}


}
