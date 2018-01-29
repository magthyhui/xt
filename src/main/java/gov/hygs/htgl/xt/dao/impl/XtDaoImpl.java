package gov.hygs.htgl.xt.dao.impl;

import gov.hygs.htgl.xt.dao.XtDao;
import gov.hygs.htgl.xt.entity.Cgclxx;
import gov.hygs.htgl.xt.entity.Cgxx;
import gov.hygs.htgl.xt.entity.Cpkcxx;
import gov.hygs.htgl.xt.entity.Cpxx;
import gov.hygs.htgl.xt.entity.Ddcpxx;
import gov.hygs.htgl.xt.entity.Ddxx;
import gov.hygs.htgl.xt.entity.Kcxx;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.bstek.dorado.data.provider.Page;
import com.gdky.restfull.dao.BaseJdbcDao;

@Repository
public class XtDaoImpl extends BaseJdbcDao implements XtDao {

	@Override
	public void getKcxx(Page page, Map<String, Object> param) {
		// TODO Auto-generated method stub
		String sqlCount = "select count(*) from xt_kcxx";
		int entityCount = this.jdbcTemplate.queryForObject(sqlCount,
				Integer.class);
		String sql = "select * from xt_kcxx limit ?,?";

		List<Kcxx> list = this.jdbcTemplate.query(sql,
				new Object[] { page.getPageSize() * (page.getPageNo() - 1),
						page.getPageSize() }, new RowMapper<Kcxx>() {

					@Override
					public Kcxx mapRow(ResultSet result, int i)
							throws SQLException {
						// TODO Auto-generated method stub
						Kcxx sp = new Kcxx();
						sp.setId(result.getInt("id"));
						sp.setClMc(result.getString("cl_mc"));
						sp.setClBh(result.getString("cl_bh"));
						sp.setGhs(result.getString("ghs"));
						sp.setClmd(result.getDouble("clmd"));
						sp.setHd(result.getDouble("hd"));
						sp.setLk(result.getDouble("lk"));
						sp.setBj(result.getDouble("bj"));
						sp.setMs(result.getDouble("ms"));
						sp.setDgjz(result.getDouble("dgjz"));
						sp.setDgcpmj(result.getDouble("dgcpmj"));
						sp.setCldj(result.getDouble("cldj"));
						sp.setSh(result.getDouble("sh"));
						sp.setDqkc(result.getDouble("dqkc"));
						sp.setZkc(result.getDouble("zkc"));
						sp.setBz(result.getString("bz"));
						sp.setDgmz();
						sp.setDgfl();
						sp.setCllyl();
						sp.setDj();
						return sp;
					}
				});
		page.setEntityCount(entityCount);
		page.setEntities(list);
	}

	@Override
	public void addKcxx(Kcxx sp) {
		// TODO Auto-generated method stub
		String sql = "insert into xt_kcxx(cl_mc,cl_bh,ghs,clmd,hd,lk,bj,ms,dgjz,dgcpmj,cldj,sh,dqkc,zkc,bz) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		Object[] obj = new Object[] { sp.getClMc(), sp.getClBh(), sp.getGhs(),
				sp.getClmd(), sp.getHd(), sp.getLk(), sp.getBj(), sp.getMs(),
				sp.getDgjz(), sp.getDgcpmj(), sp.getCldj(), sp.getSh(),
				sp.getDqkc(), sp.getZkc(), sp.getBz() };
		this.jdbcTemplate.update(sql, obj);
	}

	@Override
	public void updateKcxx(Kcxx sp) {
		// TODO Auto-generated method stub
		String sql = "update xt_kcxx set cl_mc=?,cl_bh=?,ghs=?,clmd=?,hd=?,lk=?,bj=?,ms=?,dgjz=?,dgcpmj=?,cldj=?,sh=?,dqkc=?,zkc=?,bz=? where id=?";
		Object[] obj = new Object[] { sp.getClMc(), sp.getClBh(), sp.getGhs(),
				sp.getClmd(), sp.getHd(), sp.getLk(), sp.getBj(), sp.getMs(),
				sp.getDgjz(), sp.getDgcpmj(), sp.getCldj(), sp.getSh(),
				sp.getDqkc(), sp.getZkc(), sp.getBz(), sp.getId() };
		this.jdbcTemplate.update(sql, obj);
	}

	@Override
	public void deleteKcxx(Kcxx sp) {
		// TODO Auto-generated method stub
		String sql = "delete from xt_kcxx where id=?";
		this.jdbcTemplate.update(sql, new Object[] { sp.getId() });
	}

	@Override
	public void getCpxx(Page page, Map<String, Object> param) {
		// TODO Auto-generated method stub
		String lx = (String) param.get("lx");
		String sqlCount = "select count(*) from xt_cpxx where lx = ? ";
		int entityCount = this.jdbcTemplate.queryForObject(sqlCount,new Object[]{lx},
				Integer.class);
		String sql = "select * from xt_cpxx where lx = ? limit ?,?";

		List<Cpxx> list = this.jdbcTemplate.query(sql,
				new Object[] {lx, page.getPageSize() * (page.getPageNo() - 1),
						page.getPageSize() }, new RowMapper<Cpxx>() {

					@Override
					public Cpxx mapRow(ResultSet result, int i)
							throws SQLException {
						// TODO Auto-generated method stub
						Cpxx sp = new Cpxx();
						sp.setId(result.getInt("id"));
						sp.setCpMc(result.getString("cp_mc"));
						sp.setCpBh(result.getString("cp_bh"));
						sp.setDj(result.getDouble("dj"));
						sp.setSbf(result.getDouble("sbf"));
						sp.setLx(result.getString("lx"));
						sp.setKc(result.getDouble("kc"));
						sp.setXl(result.getDouble("xl"));
						sp.setBz(result.getString("bz"));
						return sp;
					}
				});
		page.setEntityCount(entityCount);
		page.setEntities(list);
	}

	@Override
	public void addCpxx(Cpxx sp) {
		// TODO Auto-generated method stub
		String sql = "insert into xt_cpxx(cp_mc,cp_bh,dj,sbf,lx,kc,xl,bz) values(?,?,?,?,?,?,?,?)";
		Object[] obj = new Object[] { sp.getCpMc(), sp.getCpBh(), sp.getDj(),
				sp.getSbf(), sp.getLx(), sp.getKc(), sp.getXl(), sp.getBz() };
		this.jdbcTemplate.update(sql, obj);
	}

	@Override
	public void updateCpxx(Cpxx sp) {
		// TODO Auto-generated method stub
		String sql = "update xt_cpxx set cp_mc=?,cp_bh=?,dj=?,sbf=?,lx=?,kc=?,xl=?,bz=? where id=?";
		Object[] obj = new Object[] { sp.getCpMc(), sp.getCpBh(), sp.getDj(),
				sp.getSbf(), sp.getLx(), sp.getKc(), sp.getXl(), sp.getBz(),
				sp.getId() };
		this.jdbcTemplate.update(sql, obj);
	}

	@Override
	public void deleteCpxx(Cpxx sp) {
		// TODO Auto-generated method stub
		String sql = "delete from xt_cpxx where id=?";
		this.jdbcTemplate.update(sql, new Object[] { sp.getId() });
	}

	@Override
	public List<Map<String, Object>> getCpkcxx(Map<String, Object> para) {
		// TODO Auto-generated method stub
		int id = (Integer) para.get("id");
		String sql = "select  a.id,a.cp_bh cpbh,a.cl_bh clbh,a.cl_sl clsl,b.cl_mc clmc,a.pid  from xt_cp_kc a left join xt_kcxx b on a.cl_bh=b.cl_bh where a.pid= ?";
		return this.jdbcTemplate.queryForList(sql, id);
	}

	@Override
	public void addCpkcxx(Cpkcxx clxx) {
		// TODO Auto-generated method stub
		String sql = "insert into xt_cp_kc(cp_bh,cl_bh,cl_sl,pid) values(?,?,?,?)";
		Object[] obj = new Object[] { clxx.getCpBh(), clxx.getClBh(),
				clxx.getClSl(), clxx.getPid() };
		this.jdbcTemplate.update(sql, obj);
	}

	@Override
	public void updateCpkcxx(Cpkcxx clxx) {
		// TODO Auto-generated method stub
		String sql = "update xt_cp_kc set cp_bh=?,cl_bh=?,cl_sl=? where id=?";
		Object[] obj = new Object[] { clxx.getCpBh(), clxx.getClBh(),
				clxx.getClSl(), clxx.getId() };
		this.jdbcTemplate.update(sql, obj);
	}

	@Override
	public void deleteCpkcxx(Cpkcxx clxx) {
		// TODO Auto-generated method stub
		String sql = "delete from xt_cp_kc where id=?";
		this.jdbcTemplate.update(sql, new Object[] { clxx.getId() });
	}

	@Override
	public void getDdxx(Page page, Map<String, Object> param) {
		// TODO Auto-generated method stub
		String lx = (String) param.get("lx");
		String sqlCount = "select count(*) from xt_ddxx where lx = ? ";
		int entityCount = this.jdbcTemplate.queryForObject(sqlCount,new Object[]{lx},
				Integer.class);
		String sql = "select * from xt_ddxx where lx = ? limit ?,?";

		List<Ddxx> list = this.jdbcTemplate.query(sql,
				new Object[] { lx,page.getPageSize() * (page.getPageNo() - 1),
						page.getPageSize() }, new RowMapper<Ddxx>() {

					@Override
					public Ddxx mapRow(ResultSet result, int i)
							throws SQLException {
						// TODO Auto-generated method stub
						Ddxx sp = new Ddxx();
						sp.setId(result.getInt("id"));
						sp.setKh(result.getString("kh"));
						sp.setDdBh(result.getString("dd_bh"));
						sp.setFkdjsj(result.getDate("fkdjsj"));
						sp.setKhjq(result.getDate("khjq"));
						sp.setHyjq(result.getDate("hyjq"));
						sp.setLx(result.getString("lx"));
						return sp;
					}
				});
		page.setEntityCount(entityCount);
		page.setEntities(list);
	}

	@Override
	public void addDdxx(Ddxx sp) {
		// TODO Auto-generated method stub
		String sql = "insert into xt_ddxx(kh,dd_bh,fkdjsj,khjq,hyjq,lx) values(?,?,?,?,?,?)";
		Object[] obj = new Object[] { sp.getKh(), sp.getDdBh(), sp.getFkdjsj(),
				sp.getKhjq(), sp.getHyjq(),sp.getLx() };
		this.jdbcTemplate.update(sql, obj);
	}

	@Override
	public void updateDdxx(Ddxx sp) {
		// TODO Auto-generated method stub
		String sql = "update xt_ddxx set kh=?,dd_bh=?,fkdjsj=?,khjq=?,hyjq=?, lx = ? where id=?";
		Object[] obj = new Object[] { sp.getKh(), sp.getDdBh(), sp.getFkdjsj(),
				sp.getKhjq(), sp.getHyjq(),sp.getLx(), sp.getId() };
		this.jdbcTemplate.update(sql, obj);
	}

	@Override
	public void deleteDdxx(Ddxx sp) {
		// TODO Auto-generated method stub
		String sql = "delete from xt_ddxx where id=?";
		this.jdbcTemplate.update(sql, new Object[] { sp.getId() });
	}

	@Override
	public List<Map<String, Object>> getDdcpxx(Map<String, Object> para) {
		// TODO Auto-generated method stub
		int id = (Integer) para.get("id");
		String sql = "select  a.id,a.cp_bh cpbh,a.dd_bh ddbh,a.sl, a.sl-a.cdsl sjscs ,a.sl-a.cdsl cdsl,b.cp_mc cpmc,a.pid  from xt_dd_cp a left join xt_cpxx b on a.cp_bh=b.cp_bh where a.pid= ?";
		return this.jdbcTemplate.queryForList(sql, id);
	}

	@Override
	public void addDdcpxx(Ddcpxx clxx) {
		// TODO Auto-generated method stub
		String sql = "insert into xt_dd_cp(dd_bh,cp_bh,sl,pid) values(?,?,?,?)";
		Object[] obj = new Object[] { clxx.getDdBh(), clxx.getCpBh(),
				clxx.getSl(), clxx.getPid() };
		this.jdbcTemplate.update(sql, obj);
	}

	@Override
	public void updateDdcpxx(Ddcpxx clxx) {
		// TODO Auto-generated method stub
		String sql = "update xt_dd_cp set dd_bh=?,cp_bh=?,sl=? where id=?";
		Object[] obj = new Object[] { clxx.getDdBh(), clxx.getCpBh(),
				clxx.getSl(), clxx.getId() };
		this.jdbcTemplate.update(sql, obj);
	}

	@Override
	public void deleteDdcpxx(Ddcpxx clxx) {
		// TODO Auto-generated method stub
		String sql = "delete from xt_dd_cp where id=?";
		this.jdbcTemplate.update(sql, new Object[] { clxx.getId() });
	}

	@Override
	public void getCgxx(Page page, Map<String, Object> param) {
		// TODO Auto-generated method stub
		String sqlCount = "select count(*) from xt_cgxx";
		int entityCount = this.jdbcTemplate.queryForObject(sqlCount,
				Integer.class);
		String sql = "select * from xt_cgxx limit ?,?";

		List<Cgxx> list = this.jdbcTemplate.query(sql,
				new Object[] { page.getPageSize() * (page.getPageNo() - 1),
						page.getPageSize() }, new RowMapper<Cgxx>() {

					@Override
					public Cgxx mapRow(ResultSet result, int i)
							throws SQLException {
						// TODO Auto-generated method stub
						Cgxx sp = new Cgxx();
						sp.setId(result.getInt("id"));
						sp.setCgBh(result.getString("cg_bh"));
						sp.setDdBh(result.getString("dd_bh"));
						sp.setZj(result.getDouble("zj"));
						return sp;
					}
				});
		page.setEntityCount(entityCount);
		page.setEntities(list);
	}

	@Override
	public void addCgxx(Cgxx sp) {
		// TODO Auto-generated method stub
		String sql = "insert into xt_cgxx(cg_bh,dd_bh,zj) values(?,?,?)";
		Object[] obj = new Object[] { sp.getCgBh(), sp.getDdBh(), sp.getZj() };
		this.jdbcTemplate.update(sql, obj);
	}

	@Override
	public void updateCgxx(Cgxx sp) {
		// TODO Auto-generated method stub
		String sql = "update xt_cgxx set kh=?,dd_bh=?,zj=? where id=?";
		Object[] obj = new Object[] { sp.getCgBh(), sp.getDdBh(), sp.getZj(),
				sp.getId() };
		this.jdbcTemplate.update(sql, obj);
	}

	@Override
	public void deleteCgxx(Cgxx sp) {
		// TODO Auto-generated method stub
		String sql = "delete from xt_cgxx where id=?";
		this.jdbcTemplate.update(sql, new Object[] { sp.getId() });
	}

	@Override
	public List<Map<String, Object>> getCgclxx(Map<String, Object> para) {
		// TODO Auto-generated method stub
		int id = (Integer) para.get("id");
		String sql = "select  a.id,a.cg_bh cgbh,a.cl_bh clbh,a.sl sl,b.cl_mc clmc,a.pid,a.cgsl,a.cgsl sjcgl,b.cldj*a.cgsl zj  from xt_cg_cl a left join xt_kcxx b on a.cl_bh=b.cl_bh where a.pid= ?";
		return this.jdbcTemplate.queryForList(sql, id);
	}

	@Override
	public void addCgclxx(Cgclxx clxx) {
		// TODO Auto-generated method stub
		String sql = "insert into xt_cg_cl(cg_bh,cl_bh,sl,pid) values(?,?,?,?)";
		Object[] obj = new Object[] { clxx.getCgBh(), clxx.getClBh(),
				clxx.getSl(), clxx.getPid() };
		this.jdbcTemplate.update(sql, obj);
	}

	@Override
	public void updateCgclxx(Cgclxx clxx) {
		// TODO Auto-generated method stub
		String sql = "update xt_cg_cl set cg_bh=?,cl_bh=?,sl=? where id=?";
		Object[] obj = new Object[] { clxx.getCgBh(), clxx.getClBh(),
				clxx.getSl(), clxx.getId() };
		this.jdbcTemplate.update(sql, obj);
	}

	@Override
	public void deleteCgclxx(Cgclxx clxx) {
		// TODO Auto-generated method stub
		String sql = "delete from xt_cg_cl where id=?";
		this.jdbcTemplate.update(sql, new Object[] { clxx.getId() });
	}

	@Override
	public List<Map<String, Object>> getCpxx(Integer id) {
		// TODO Auto-generated method stub
		StringBuffer sql = new StringBuffer(
				"SELECT cl_bh,cl_mc, SUM(sl) sl, SUM(cgzl) cgzl,sum(kc) kc ");
		sql.append(" FROM( ");
		sql.append(" SELECT e.cl_bh,e.cl_mc,b.sl,c.kc, ");
		sql.append(" convert((((b.sl-c.kc)*d.cl_sl*e.clmd*e.hd*e.lk*e.bj/1000/e.ms)*(1+e.sh/100)/1000),decimal(10,3)) cgzl ");
		sql.append(" FROM xt_ddxx a,xt_dd_cp b,xt_cpxx c,xt_cp_kc d,xt_kcxx e ");
		sql.append(" WHERE a.id=? AND a.id=b.pid AND b.cp_bh=c.cp_bh AND c.id=d.pid AND d.cl_bh = e.cl_bh and b.sl>c.kc) a ");
		sql.append(" GROUP BY cl_bh,cl_mc ");
		return this.jdbcTemplate.queryForList(sql.toString(), id);
	}

	@Override
	public Map<String, Object> getDdKcxx(Map<String, Object> clsl) {
		// TODO Auto-generated method stub
		String sql = "select (?-a.dqkc)*a.cldj zj,?-a.dqkc sysl from xt_kcxx a where a.dqkc-?<0 and a.cl_bh = ? ";
		List<Map<String, Object>> ls = this.jdbcTemplate.queryForList(
				sql.toString(),
				new Object[] { clsl.get("cgzl"), clsl.get("cgzl"),
						clsl.get("cgzl"), clsl.get("cl_bh") });
		if (ls.size() > 0) {
			return ls.get(0);
		}
		return null;
	}

	@Override
	public void insertCgkcxx(Map<String, Object> ls, String uuid, Integer pid) {
		// TODO Auto-generated method stub
		String sql = "insert into xt_cg_cl(cg_bh,cl_bh,sl,cgsl,pid) values(?,?,?,?,?)";
		this.jdbcTemplate.update(
				sql,
				new Object[] { uuid, ls.get("cl_bh"), ls.get("sl"),
						ls.get("sysl"), pid });
	}

	@Override
	public Integer createCgxx(double zj, String ddBh, String uuid) {
		// TODO Auto-generated method stub
		String sql = "insert into xt_cgxx(cg_bh,dd_bh,zj) values(?,?,?)";
		this.jdbcTemplate.update(sql, new Object[] { uuid, ddBh, zj });
		sql = "select id from xt_cgxx where cg_bh = ? ";
		return this.jdbcTemplate.queryForObject(sql, new Object[] { uuid },
				Integer.class);
	}

	@Override
	public boolean checkJs(Integer id) {
		// TODO Auto-generated method stub
		String sql = "select sfjs from xt_ddxx where id = ?";
		String sfjs = this.jdbcTemplate.queryForObject(sql,
				new Object[] { id }, String.class);
		if (sfjs.equals("1")) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	public void saveDdjs(String ddBh) {
		// TODO Auto-generated method stub
		String sql = "update xt_ddxx set sfjs='1' where dd_bh=?";
		Object[] obj = new Object[] { ddBh };
		this.jdbcTemplate.update(sql, obj);
	}

	@Override
	public void saveCg(Integer id) {
		// TODO Auto-generated method stub
		String sql = "update xt_cgxx set sfcg='1' where id=?";
		Object[] obj = new Object[] { id };
		this.jdbcTemplate.update(sql, obj);
	}

	@Override
	public void cgTokc(Cgclxx clxx) {
		// TODO Auto-generated method stub
		String sql = "update xt_kcxx a set zkc=?+a.zkc,dqkc=?+dqkc where cl_bh = ? ";
		Object[] obj = new Object[] { clxx.getSjcgl(), clxx.getSjcgl(),
				clxx.getClBh() };
		this.jdbcTemplate.update(sql, obj);

	}

	@Override
	public boolean checkCg(Integer id) {
		// TODO Auto-generated method stub
		String sql = "select sfcg from xt_cgxx where id = ?";
		String sfjs = this.jdbcTemplate.queryForObject(sql,
				new Object[] { id }, String.class);
		if (sfjs.equals("1")) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	public String checkCd(Integer id) {
		// TODO Auto-generated method stub
		String sql = "select sfwc,dd_bh from xt_ddxx where id = ?";
		Map<String, Object> sfjs = this.jdbcTemplate.queryForMap(sql,
				new Object[] { id });

		sql = "select sfcg from xt_cgxx where dd_bh = ?";
		String sfwc = this.jdbcTemplate.queryForObject(sql,
				new Object[] { sfjs.get("dd_bh") }, String.class);

		if (sfjs.get("sfwc").equals("0") && !sfwc.equals("0")) {
			return "1";
		} else if (sfwc.equals("0")) {
			return "2";
		} else {
			return "0";
		}
	}

	@Override
	public void cdDd(Ddcpxx cpxx) {
		// TODO Auto-generated method stub
		//
		String sql = "update xt_dd_cp set cdsl = cdsl+? where id=?";
		Object[] obj = new Object[] { cpxx.getSjscs(), cpxx.getId() };
		this.jdbcTemplate.update(sql, obj);
		sql = "insert into xt_dd_log(dd_bh,cp_bh,pid,cdsl) values(?,?,?,?) ";
		obj = new Object[] { cpxx.getDdBh(), cpxx.getCpBh(), cpxx.getPid(),
				cpxx.getSjscs() };
		this.jdbcTemplate.update(sql, obj);
		sql = "update xt_cpxx set xl=xl+? where cp_bh = ? ";
		obj = new Object[] { cpxx.getSjscs(), cpxx.getCpBh() };
		this.jdbcTemplate.update(sql, obj);
	}

	@Override
	public void saveCptoKc(Ddcpxx cpxx) {
		// TODO Auto-generated method stub
		Double dysl = cpxx.getSjscs() - cpxx.getCdsl();
		Double cdsl = cpxx.getCdsl();
		String sql = "update xt_dd_cp set cdsl = sl where id=?";
		Object[] obj = new Object[] { cpxx.getId() };
		this.jdbcTemplate.update(sql, obj);
		sql = "insert into xt_dd_log(dd_bh,cp_bh,pid,cdsl) values(?,?,?,?) ";
		obj = new Object[] { cpxx.getDdBh(), cpxx.getCpBh(), cpxx.getPid(),
				cpxx.getSjscs() };
		this.jdbcTemplate.update(sql, obj);
		sql = "update xt_cpxx set kc = ?+kc,xl=xl+? where cp_bh =? ";
		obj = new Object[] { dysl, cdsl, cpxx.getCpBh() };
		this.jdbcTemplate.update(sql, obj);

	}

	@Override
	public void saveCdDd(Integer id) {
		// TODO Auto-generated method stub
		String sql = "update xt_ddxx set sfwc='1' where id=?";
		Object[] obj = new Object[] { id };
		this.jdbcTemplate.update(sql, obj);
	}

	@Override
	public void xhKcsl(Ddcpxx cpxx) {
		// TODO Auto-generated method stub
		Double sjscs = cpxx.getSjscs();
		Object[] obj = null;
		String sql = "select kc from xt_cpxx where cp_bh = ?";
		Double kc = this.jdbcTemplate.queryForObject(sql,
				new Object[] { cpxx.getCpBh() }, Double.class);

		if (kc > sjscs) {
			sql = "update xt_cpxx set kc = ? where cp_bh=?";
			obj = new Object[] { kc - sjscs, cpxx.getCpBh() };
			this.jdbcTemplate.update(sql, obj);
		} else {
			sql = "update xt_cpxx set kc = 0 where cp_bh=?";
			obj = new Object[] { cpxx.getCpBh() };
			this.jdbcTemplate.update(sql, obj);
			Double syscsl = sjscs - kc;
			sql = "SELECT   e.cl_bh,convert(((?*d.cl_sl*e.clmd*e.hd*e.lk*e.bj/1000/e.ms)*(1+e.sh/100)/1000),decimal(10,3)) cgzl";
			sql += " FROM xt_cpxx c,xt_cp_kc d,xt_kcxx e ";
			sql += " WHERE c.id=d.pid AND d.cl_bh = e.cl_bh and  c.cp_bh = ? ";
			List<Map<String, Object>> sczl = this.jdbcTemplate.queryForList(
					sql, new Object[] { syscsl, cpxx.getCpBh() });
			for (Map<String, Object> mp : sczl) {
				sql = "update xt_kcxx  set dqkc =dqkc-?  where cl_bh=?";
				obj = new Object[] { mp.get("cgzl"), mp.get("cl_bh") };
				this.jdbcTemplate.update(sql, obj);
			}
		}

	}

}
