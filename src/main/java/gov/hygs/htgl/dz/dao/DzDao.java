package gov.hygs.htgl.dz.dao;

import gov.hygs.htgl.dz.entity.Ddxx;
import gov.hygs.htgl.dz.entity.DdxxMx;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.bstek.dorado.data.provider.Page;
import com.gdky.restfull.dao.BaseJdbcDao;

@Repository
public class DzDao extends BaseJdbcDao {

	public Integer addDdxx(Ddxx sp) {
		// TODO Auto-generated method stub
		String sql = "insert into xt_dz_ddb(kh,xdrq,ddh) values(?,?,?)";
		Object[] obj = new Object[] { sp.getKh(),sp.getXdrq(),sp.getDdh() };
		this.jdbcTemplate.update(sql, obj);
		sql = "select id from xt_dz_ddb where ddh = ? ";
		return	this.jdbcTemplate.queryForObject(sql, new Object[]{sp.getDdh()},Integer.class);
		
	}

	public void updateDdxx(Ddxx sp) {
		// TODO Auto-generated method stub
		String sql = "update xt_dz_ddb set kh = ? ,xdrq = ? ,ddh = ? where id=? ";
		Object[] obj = new Object[] { sp.getKh(),sp.getXdrq(),sp.getDdh(),sp.getId() };
		this.jdbcTemplate.update(sql, obj);
	}

	public void deleteDdxx(Ddxx sp) {
		// TODO Auto-generated method stub
		String sql = "delete from xt_dz_ddb where id=?";
		this.jdbcTemplate.update(sql, new Object[] { sp.getId() });
	}

	public void getDdxx(Page page, Map<String, Object> param) {
		// TODO Auto-generated method stub
		String sqlCount = "select count(*) from xt_dz_ddb";
		int entityCount = this.jdbcTemplate.queryForObject(sqlCount,
				Integer.class);
		String sql = "select * from xt_dz_ddb limit ?,? order by id desc ";

		List<Ddxx> list = this.jdbcTemplate.query(sql,
				new Object[] { page.getPageSize() * (page.getPageNo() - 1),
						page.getPageSize() }, new RowMapper<Ddxx>() {

					@Override
					public Ddxx mapRow(ResultSet result, int i)
							throws SQLException {
						// TODO Auto-generated method stub
						Ddxx sp = new Ddxx();
						return sp;
					}
				});
		page.setEntityCount(entityCount);
		page.setEntities(list);
	}

	public void addDdxxMx(DdxxMx ddxxMx) {
		// TODO Auto-generated method stub
		String sql = "insert into xt_dz_ddb_mx(ddbid,wlh,wlmc,cz,clgg,jhrq,ddsl,hyjq,dj,bz) values(?,?,?,?,?,?,?,?,?,?)";
		Object[] obj = new Object[] { ddxxMx.getDdbid(),ddxxMx.getWlh(),ddxxMx.getWlmc(),ddxxMx.getCz(),ddxxMx.getClgg(),ddxxMx.getJhrq(),ddxxMx.getDdsl(),ddxxMx.getHyjq(),ddxxMx.getDj(),ddxxMx.getBz() };
		this.jdbcTemplate.update(sql, obj);

	}

	public void updateDdxxMx(DdxxMx ddxxMx) {
		// TODO Auto-generated method stub
		String sql = "update xt_dz_ddb_mx set ddbid = ?,wlh = ?,wlmc = ?,cz = ?,clgg = ?,jhrq = ?,ddsl = ?,hyjq = ?,dj = ?,bz = ? where mxid=? ";
		Object[] obj = new Object[] { ddxxMx.getDdbid(),ddxxMx.getWlh(),ddxxMx.getWlmc(),ddxxMx.getCz(),ddxxMx.getClgg(),ddxxMx.getJhrq(),ddxxMx.getDdsl(),ddxxMx.getHyjq(),ddxxMx.getDj(),ddxxMx.getBz(),ddxxMx.getMxid() };
		this.jdbcTemplate.update(sql, obj);
	}

	public void deleteDdxxMx(DdxxMx ddxxMx) {
		// TODO Auto-generated method stub
		String sql = "delete from xt_dz_ddb_mx where mxid=?";
		this.jdbcTemplate.update(sql, new Object[] { ddxxMx.getMxid() });
	}

	public List<Map<String, Object>> getDdxxMx(Map<String, Object> param) {
		// TODO Auto-generated method stub
		int id = (Integer) param.get("id");
		String sql = "select * from xt_dz_ddb_mx where ddbid= ? ";
		return this.jdbcTemplate.queryForList(sql,new Object[]{id});
	}

	public List<Map<String, Object>> getDd(Map<String, Object> param) {
		// TODO Auto-generated method stub
		String sql = "select a.*,b.*,FORMAT(b.ddsl*b.dj,2) je,b.ddsl-(select sum(sl) from xt_dz_chd_mx where mxid = b.mxid) wjsl,(select sum(sl) from xt_dz_chd_mx where mxid = b.mxid) yjsl from xt_dz_ddb a ,xt_dz_ddb_mx b where a.id=b.ddbid ";
		return this.jdbcTemplate.queryForList(sql);
	}

	public List<Map<String, Object>> getDdxx(Map<String, Object> param) {
		// TODO Auto-generated method stub
		int id = (Integer) param.get("id");
		String sql = "select * from xt_dz_ddb where id = ? order by id desc ";
		return this.jdbcTemplate.queryForList(sql,new Object[]{id});
	}

	public Integer insertShd(Ddxx ddxxMx) {
		// TODO Auto-generated method stub
		String sql = "insert into xt_dz_chd(ddbid,shdh,shrq) values(?,?,?)";
		Object[] obj = new Object[] {ddxxMx.getId(), ddxxMx.getShdh(),ddxxMx.getShrq() };
		this.jdbcTemplate.update(sql, obj);
		sql = "select id from xt_dz_chd where shdh = ? ";
		return	this.jdbcTemplate.queryForObject(sql, new Object[]{ddxxMx.getShdh()},Integer.class);
	}

	public void insertShdMx(Integer id, DdxxMx ddxxMx) {
		// TODO Auto-generated method stub
		String sql = "insert into xt_dz_chd_mx(chdid,mxid,dw,sl,bz) values(?,?,?,?,?)";
		Object[] obj = new Object[] { id,ddxxMx.getMxid(),ddxxMx.getDw(),ddxxMx.getSl(),ddxxMx.getBz() };
		this.jdbcTemplate.update(sql, obj);
		
	}

	public List<Map<String, Object>> getChd(Map<String, Object> param) {
		// TODO Auto-generated method stub
		String id = (String) param.get("id");
		String sql = "select a.*,b.*,c.wlh,c.wlmc from xt_dz_chd a ,xt_dz_chd_mx b,xt_dz_ddb_mx c where a.id=b.chdid and b.mxid=c.mxid and a.ddbid = ? ";
		return this.jdbcTemplate.queryForList(sql,new Object[]{id});
	}

	public List<Map<String, Object>> getDzxx(Map<String, Object> param) {
		// TODO Auto-generated method stub
		String kh = (String) param.get("kh");
		Date sjq=(Date) param.get("sjq"),sjz=(Date) param.get("sjz");;
		StringBuffer sql =new StringBuffer("select a.*,b.*,c.wlh,c.wlmc,d.ddh from xt_dz_chd a ,xt_dz_chd_mx b,xt_dz_ddb_mx c,xt_dz_ddb d where a.id=b.chdid and b.mxid=c.mxid and c.ddbid=d.id ");
		sql.append(" and kh like ? and a.shrq between ? and ? order by a.shrq ");
		return this.jdbcTemplate.queryForList(sql.toString(),new Object[] {kh,sjq,sjz});
	}



	
}
