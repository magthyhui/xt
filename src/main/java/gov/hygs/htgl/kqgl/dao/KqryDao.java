package gov.hygs.htgl.kqgl.dao;

import gov.hygs.htgl.kqgl.entity.Kqry;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.bstek.dorado.data.provider.Page;
import com.gdky.restfull.dao.BaseJdbcDao;

@Repository
public class KqryDao extends BaseJdbcDao {
	
	public void getKqry(Page page, Map<String, Object> param) {
		// TODO Auto-generated method stub
		String sqlCount = "select count(*) from xt_kqry";
		int entityCount = this.jdbcTemplate.queryForObject(sqlCount,
				Integer.class);
		String sql = "select * from xt_kqry limit ?,?";

		List<Kqry> list = this.jdbcTemplate.query(sql,
				new Object[] { page.getPageSize() * (page.getPageNo() - 1),
						page.getPageSize() }, new RowMapper<Kqry>() {

					@Override
					public Kqry mapRow(ResultSet result, int i)
							throws SQLException {
						// TODO Auto-generated method stub
						Kqry sp = new Kqry();
						sp.setId(result.getInt("id"));
						sp.setGh(result.getString("gh"));
						sp.setXm(result.getString("xm"));
						sp.setBm(result.getString("bm"));
						return sp;
					}
				});
		page.setEntityCount(entityCount);
		page.setEntities(list);
	}

	
	public void addKqry(Kqry sp) {
		// TODO Auto-generated method stub
		String sql = "insert into xt_kqry(gh,xm,bm) values(?,?,?)";
		Object[] obj = new Object[] { sp.getGh(), sp.getXm(), sp.getBm()};
		this.jdbcTemplate.update(sql, obj);
	}

	
	public void updateKqry(Kqry sp) {
		// TODO Auto-generated method stub
		String sql = "update xt_kqry set gh=?,xm=?,bm=? where id=?";
		Object[] obj = new Object[] { sp.getGh(), sp.getXm(), sp.getBm() };
		this.jdbcTemplate.update(sql, obj);
	}

	
	public void deleteKqry(Kqry sp) {
		// TODO Auto-generated method stub
		String sql = "delete from xt_kqry where id=?";
		this.jdbcTemplate.update(sql, new Object[] { sp.getId() });
	}


	public List<Map<String, Object>> getKqsj(Map<String, Object> param) {
		// TODO Auto-generated method stub
		String sql = "select id,CONCAT(kqq,'至',kqz) sj from xt_kqjl order by id";
		return this.jdbcTemplate.queryForList(sql);
	}

}
