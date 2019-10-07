package gov.hygs.htgl.cg.dao;

import gov.hygs.htgl.cg.entity.CgDrdd;
import gov.hygs.htgl.cg.entity.CgYhxx;
import gov.hygs.htgl.cg.entity.Cgd;
import gov.hygs.htgl.cg.entity.CgdMx;
import gov.hygs.htgl.cg.entity.Cgxx;
import gov.hygs.htgl.cg.entity.CgxxMx;
import gov.hygs.htgl.dz.entity.Drdd;
import gov.hygs.htgl.dz.entity.Yhxx;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.bstek.dorado.data.provider.Page;
import com.gdky.restfull.dao.BaseJdbcDao;

@Repository
public class CgDao extends BaseJdbcDao {

	public Integer addCgxx(Cgxx sp) {
		// TODO Auto-generated method stub
		String sql = "insert into xt_cg_cgxx(kh,xdrq,jhrq,dddh,lx) values(?,?,?,?,?)";
		Object[] obj = new Object[] { sp.getKh(), sp.getXdrq(), sp.getJhrq(),
				sp.getDddh(), sp.getLx() };
		this.jdbcTemplate.update(sql, obj);
		sql = "select id from xt_cg_cgxx where dddh = ? ";
		return this.jdbcTemplate.queryForObject(sql,
				new Object[] { sp.getDddh() }, Integer.class);

	}

	public void updateCgxx(Cgxx sp) {
		// TODO Auto-generated method stub
		String sql = "update xt_cg_cgxx set kh = ? ,xdrq = ?,jhrq = ? ,dddh = ?,lx = ? where id=? ";
		Object[] obj = new Object[] { sp.getKh(), sp.getXdrq(), sp.getJhrq(),
				sp.getDddh(), sp.getLx(), sp.getId() };
		this.jdbcTemplate.update(sql, obj);
	}

	public void deleteCgxx(Cgxx sp) {
		// TODO Auto-generated method stub
		String sql = "delete from xt_cg_cgxx where id=?";
		this.jdbcTemplate.update(sql, new Object[] { sp.getId() });
	}

	public void addCgxxMx(CgxxMx CgxxMx) {
		// TODO Auto-generated method stub

		String sql = "insert into xt_cg_cgxx_mx(cgxxid,cplh,wlmc,gg,cz,dw,sl,dj,bzs,ms,jhrq1,llrq,bz) values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
		Object[] obj = new Object[] { CgxxMx.getCgxxid(), CgxxMx.getCplh(),
				CgxxMx.getWlmc(), CgxxMx.getGg(), CgxxMx.getCz(),
				CgxxMx.getDw(), CgxxMx.getSl(), CgxxMx.getDj(),
				CgxxMx.getBzs(), CgxxMx.getMs(), CgxxMx.getJhrq1(),CgxxMx.getLlrq(),
				CgxxMx.getBz() };
		this.jdbcTemplate.update(sql, obj);

	}

	public void updateCgxxMx(CgxxMx CgxxMx) {
		// TODO Auto-generated method stub
		String sql = "update xt_cg_cgxx_mx set cgxxid = ?,cplh = ?,wlmc = ?,gg = ?,cz = ?,dw = ?,sl = ?,dj = ?,bzs = ?,ms = ?,jhrq1 = ?,llrq = ?,bz = ? where cgxxmxid=? ";
		Object[] obj = new Object[] { CgxxMx.getCgxxid(), CgxxMx.getCplh(),
				CgxxMx.getWlmc(), CgxxMx.getGg(), CgxxMx.getCz(),
				CgxxMx.getDw(), CgxxMx.getSl(), CgxxMx.getDj(),
				CgxxMx.getBzs(), CgxxMx.getMs(), CgxxMx.getJhrq1(),CgxxMx.getLlrq(),
				CgxxMx.getBz(), CgxxMx.getCgxxmxid() };
		this.jdbcTemplate.update(sql, obj);
	}

	public void deleteCgxxMx(CgxxMx CgxxMx) {
		// TODO Auto-generated method stub
		String sql = "delete from xt_cg_cgxx_mx where cgxxmxid=?";
		this.jdbcTemplate.update(sql, new Object[] { CgxxMx.getCgxxmxid() });
	}

	public void deleteCgxxMx(Integer id) {
		// TODO Auto-generated method stub
		String sql = "delete from xt_cg_cgxx_mx where cgxxmxid=?";
		this.jdbcTemplate.update(sql, new Object[] { id });
	}

	public void getYhxx(Page page, Map<String, Object> param) {
		// TODO Auto-generated method stub
		String sqlCount = "select count(*) from xt_cg_yhxx";
		int entityCount = this.jdbcTemplate.queryForObject(sqlCount,
				Integer.class);
		String sql = "select * from xt_cg_yhxx limit ?,?";

		List<Yhxx> list = this.jdbcTemplate.query(sql,
				new Object[] { page.getPageSize() * (page.getPageNo() - 1),
						page.getPageSize() }, new RowMapper<Yhxx>() {

					@Override
					public Yhxx mapRow(ResultSet result, int i)
							throws SQLException {
						// TODO Auto-generated method stub
						Yhxx sp = new Yhxx();
						sp.setId(result.getInt("id"));
						sp.setKh(result.getString("kh"));
						sp.setKhjc(result.getString("khjc"));
						sp.setDh(result.getString("dh"));
						sp.setDz(result.getString("dz"));
						return sp;
					}
				});
		page.setEntityCount(entityCount);
		page.setEntities(list);
	}

	public void addYhxx(CgYhxx sp) {
		// TODO Auto-generated method stub
		String sql = "insert into xt_cg_yhxx(kh,khjc,dh,dz,lxr,czhm) values(?,?,?,?,?,?)";
		Object[] obj = new Object[] { sp.getKh(), sp.getKhjc(), sp.getDh(),
				sp.getDz(), sp.getLxr(), sp.getCzhm() };
		this.jdbcTemplate.update(sql, obj);
	}

	public void updateYhxx(CgYhxx sp) {
		// TODO Auto-generated method stub
		String sql = "update xt_cg_yhxx set kh=?,khjc=?,dh=?,dz=?,lxr = ?, czhm = ? where id=?";
		Object[] obj = new Object[] { sp.getKh(), sp.getKhjc(), sp.getDh(),
				sp.getDz(), sp.getLxr(), sp.getCzhm(), sp.getId() };
		this.jdbcTemplate.update(sql, obj);
	}

	public void deleteYhxx(CgYhxx sp) {
		// TODO Auto-generated method stub
		String sql = "delete from xt_cg_yhxx where id=?";
		this.jdbcTemplate.update(sql, new Object[] { sp.getId() });
	}

	public List<Map<String, Object>> getCg(Map<String, Object> param) {
		String kh = (String) param.get("kh");
		String dddh = (String) param.get("dddh");
		String ddzt = (String) param.get("ddzt");
		String cplh = (String) param.get("cplh");
		String wlmc = (String) param.get("wlmc");
		String lx = (String) param.get("lx");
		String gg = (String) param.get("gg");
		List<Object> args = new ArrayList<Object>();
		Date sjq = (Date) param.get("sjq"), sjz = (Date) param.get("sjz");
		StringBuffer sql = new StringBuffer(" ");
		sql.append("select 1 xh,a.*,b.*,FORMAT(b.sl*b.dj,2) je, ");
		sql.append(" b.sl-(select sum(cm.sl) sl from xt_cg_cgd c,xt_cg_cgd_mx cm where c.id=cm.cgdid and cm.cgxxmxid=b.cgxxmxid) wjsl, ");
		sql.append(" (CASE WHEN (SELECT SUM(cm.sl) sl FROM xt_cg_cgd c,xt_cg_cgd_mx cm WHERE c.id=cm.cgdid AND cm.cgxxmxid=b.cgxxmxid) IS NOT NULL THEN  ");
		sql.append(" FORMAT((SELECT SUM(cm.sl) sl FROM xt_cg_cgd c,xt_cg_cgd_mx cm WHERE c.id=cm.cgdid AND cm.cgxxmxid=b.cgxxmxid)*b.dj,2) ELSE 0 END ) llje, ");
		sql.append(" (select sum(cm.sl) sl from xt_cg_cgd c,xt_cg_cgd_mx cm where c.id=cm.cgdid and cm.cgxxmxid=b.cgxxmxid) yjsl ");
		sql.append(" from xt_cg_cgxx a  left join xt_cg_cgxx_mx b on a.id=b.cgxxid ");
		sql.append("   where  1= 1  ");
		if (kh != null) {
			sql.append(" and a.kh like ? ");
			args.add("%" + kh + "%");
		}
		if (dddh != null) {
			sql.append(" and a.dddh like ? ");
			args.add("%" + dddh + "%");
		}
		if (cplh != null) {
			sql.append(" and b.cplh like ? ");
			args.add("%" + cplh + "%");
		}
		if (wlmc != null) {
			sql.append(" and b.wlmc like ? ");
			args.add("%" + wlmc + "%");
		}
		if (lx != null) {
			sql.append(" and a.lx like ? ");
			args.add("%" + lx + "%");
		}
		if (gg != null) {
			sql.append(" and b.gg like ? ");
			args.add("%" + gg + "%");
		}
		if (sjq != null) {
			sql.append(" and a.xdrq >= date_format(date(?), '%Y%m%d') ");
			args.add(sjq);
		}
		if (sjz != null) {
			sql.append(" and a.xdrq <= date_format(date(?), '%Y%m%d')");
			args.add(sjz);
		}
		if (ddzt != null) {
			if (ddzt.equals("A")) {
				sql.append(" and b.yxbz <> 'N' ");
			} else {
				if (ddzt.equals("G")) {
					sql.append("  and b.yxbz  = ?");
				} else if (ddzt.equals("Y")) {
					sql.append("  and b.yxbz  = ? ");
				} else if (ddzt.equals("N")) {
					sql.append("  and b.yxbz  = ?");
				}
				args.add(ddzt);
			}
		}
		/*sql.append(" union all select 2 xh,null,'合计',null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,FORMAT(sum(b.sl*b.dj),2) je,null,null from xt_cg_cgxx a  left join xt_cg_cgxx_mx b on a.id=b.cgxxid ");
		sql.append("   where  1= 1  ");
		if (kh != null) {
			sql.append(" and a.kh like ? ");
			args.add("%" + kh + "%");
		}
		if (dddh != null) {
			sql.append(" and a.dddh like ? ");
			args.add("%" + dddh + "%");
		}
		if (cplh != null) {
			sql.append(" and b.cplh like ? ");
			args.add("%" + cplh + "%");
		}
		if (wlmc != null) {
			sql.append(" and b.wlmc like ? ");
			args.add("%" + wlmc + "%");
		}
		if (sjq != null) {
			sql.append(" and a.xdrq >= date_format(date(?), '%Y%m%d') ");
			args.add(sjq);
		}
		if (sjz != null) {
			sql.append(" and a.xdrq <= date_format(date(?), '%Y%m%d')");
			args.add(sjz);
		}
		if (ddzt != null) {
			if (ddzt.equals("A")) {
				sql.append(" and b.yxbz <> 'N' ");
			} else {
				if (ddzt.equals("G")) {
					sql.append("  and b.yxbz  = ?");
				} else if (ddzt.equals("Y")) {
					sql.append("  and b.yxbz  = ? ");
				} else if (ddzt.equals("N")) {
					sql.append("  and b.yxbz  = ?");
				}
				args.add(ddzt);
			}
		}*/
		sql.append(" order by xh,kh,xdrq desc,dddh,cplh ");
		return this.jdbcTemplate.queryForList(sql.toString(), args.toArray());
	}

	public List<Map<String, Object>> getCgxxMx(Map<String, Object> param) {
		int id = (Integer) param.get("id");
		String sql = "select * from xt_cg_cgxx_mx where cgxxid= ? ";
		return this.jdbcTemplate.queryForList(sql, new Object[] { id });
	}

	public List<Map<String, Object>> getCgxx(Map<String, Object> param) {
		if ((Integer) param.get("id") == 0) {
			return null;
		} else {
			if (param.get("lx") != null) {
				int id = (Integer) param.get("id");
				String sql = "select * from xt_cg_cgd where id = ? order by kh,id desc ";
				return this.jdbcTemplate.queryForList(sql, new Object[] { id });
			} else {
				int id = (Integer) param.get("id");
				String sql = "select * from xt_cg_cgxx where id = ? order by kh,id desc ";
				return this.jdbcTemplate.queryForList(sql, new Object[] { id });
			}
		}
	}

	public String setCgzt(Map param) {
		String lx = (String) param.get("lx");
		String idList = (String) param.get("id");
		String[] ids = idList.split(",");
		List<Object> args = new ArrayList<Object>();
		StringBuffer sql = new StringBuffer("update xt_cg_cgxx_mx set yxbz = ? where cgxxmxid in ( ");
		args.add(lx);
		for (int i =0;i<ids.length;i++) {
			if(i==ids.length-1){
				sql.append("? )");
				
			}else{
				sql.append("? ,");
			}
			args.add(ids[i]);
		}
		this.jdbcTemplate.update(sql.toString(), args.toArray());

		return "ok";
	}

	public List<Map<String, Object>> getChDd(Map<String, Object> param) {
		// TODO Auto-generated method stub
		String kh = null;
		List<Object> args = new ArrayList<Object>();
		StringBuffer sql = new StringBuffer(
				"select * from xt_cg_cgxx a where 1 = 1 ");
		if (param != null) {
			kh = (String) param.get("kh");
		}
		if (kh != null) {
			sql.append(" and a.kh like ? ");
			args.add("%" + kh + "%");
		}
		sql.append("  and a.yxbz ='Y' and exists (select 1 from xt_cg_cgxx_mx b where a.id=b.cgxxid and b.yxbz='Y' ) order by kh,xdrq desc ");
		return this.jdbcTemplate.queryForList(sql.toString(), args.toArray());
	}

	public List<Map<String, Object>> getWl(Map<String, Object> param) {
		// TODO Auto-generated method stub
		String kh = null;
		List<Object> args = new ArrayList<Object>();
		StringBuffer sql = new StringBuffer(
				"SELECT b.cgxxmxid,a.id,a.dddh,b.cplh,b.wlmc,b.dw,b.gg, (b.sl- IFNULL((SELECT SUM(cm.sl) sl FROM xt_cg_cgd c,xt_cg_cgd_mx cm WHERE c.id=cm.cgdid AND cm.cgxxmxid=b.cgxxmxid),0)) sy FROM xt_cg_cgxx a,xt_cg_cgxx_mx b WHERE a.id=b.cgxxid ");
		if (param != null) {
			kh = (String) param.get("kh");
		}
		if (kh != null) {
			sql.append(" and a.kh like ? ");
			args.add("%" + kh + "%");
		}
		sql.append("  and a.yxbz='Y' and b.yxbz ='Y' order by a.xdrq desc ");
		return this.jdbcTemplate.queryForList(sql.toString(), args.toArray());
	}

	public List<Map<String, Object>> getChDdMx(Map<String, Object> param) {
		// TODO Auto-generated method stub
		Integer id = (Integer) param.get("id");
		String sql = "select a.id cgxxid,a.dddh,b.cplh,b.wlmc,b.dw,b.cgxxmxid,b.gg from xt_cg_cgxx a left join xt_cg_cgxx_mx b on a.id=b.cgxxid  where b.yxbz='Y' and a.id = ? ";
		return this.jdbcTemplate.queryForList(sql, new Object[] { id });
	}

	public Integer insertShd(Cgd ddxxMx) {
		// TODO Auto-generated method stub
		String sql = "insert into xt_cg_cgd(kh,cgdh,jhrq,lx) values(?,?,?,?)";
		Object[] obj = new Object[] { ddxxMx.getKh(), ddxxMx.getCgdh(),
				ddxxMx.getJhrq(), ddxxMx.getLx() };
		this.jdbcTemplate.update(sql, obj);
		sql = "select id from xt_cg_cgd where cgdh = ? ";
		return this.jdbcTemplate.queryForObject(sql,
				new Object[] { ddxxMx.getCgdh() }, Integer.class);
	}

	public void updateShd(Cgd sp) {
		// TODO Auto-generated method stub
		String sql = "update xt_cg_cgd set kh = ? ,cgdh = ? ,jhrq = ?,lx = ? where id=? ";
		Object[] obj = new Object[] { sp.getKh(), sp.getCgdh(), sp.getJhrq(),
				sp.getLx(), sp.getId() };
		this.jdbcTemplate.update(sql, obj);
	}

	public void deleteShd(Cgd sp) {
		// TODO Auto-generated method stub
		String sql = "delete from xt_cg_cgd where id=?";
		this.jdbcTemplate.update(sql, new Object[] { sp.getId() });
	}

	public void deleteShdMx(Integer id) {
		// TODO Auto-generated method stub
		String sql = "delete from xt_cg_cgd_mx where cgdid=?";
		this.jdbcTemplate.update(sql, new Object[] { id });
	}

	public void insertShdMx(CgdMx ddxxMx) {
		// TODO Auto-generated method stub
		String sql = "insert into xt_cg_cgd_mx(cgdid,cgxxid,cgxxmxid,sl,bz,cplh,wlmc) values(?,?,?,?,?,?,?)";
		Object[] obj = new Object[] { ddxxMx.getCgdid(), ddxxMx.getCgxxid(),
				ddxxMx.getCgxxmxid(), ddxxMx.getSl(), ddxxMx.getBz(),
				ddxxMx.getCplh(), ddxxMx.getWlmc() };
		this.jdbcTemplate.update(sql, obj);

	}

	public void updateShdMx(CgdMx ddxxMx) {
		// TODO Auto-generated method stub
		String sql = "update xt_cg_cgd_mx set cgdid = ?,cgxxid = ?,cgmxid = ?,sl = ?,bz = ?,wlh = ?,wlmc = ? where cgmxid=? ";
		Object[] obj = new Object[] { ddxxMx.getCgdid(), ddxxMx.getCgxxid(),
				ddxxMx.getCgxxmxid(), ddxxMx.getSl(), ddxxMx.getBz(),
				ddxxMx.getCplh(), ddxxMx.getWlmc(), ddxxMx.getCgmxid() };
		this.jdbcTemplate.update(sql, obj);
	}

	public void deleteShdMx(CgdMx ddxxMx) {
		// TODO Auto-generated method stub
		String sql = "delete from xt_cg_cgd_mx where cgmxid=?";
		this.jdbcTemplate.update(sql, new Object[] { ddxxMx.getCgmxid() });

	}

	public List getShd(Integer id) {
		// TODO Auto-generated method stub
		String sql = "SELECT a.lx,b.khjc, CONCAT('供方地址：', IFNULL(b.dz,'惠州市惠台工业区和畅东六路7号')) dz, CONCAT('供方：', IFNULL(b.kh,'惠州市宏达五金制品有限公司')) kh, CONCAT('联系人：', IFNULL(b.lxr,'')) lxr, CONCAT('联系电话:', IFNULL(b.dh,'0752-2773399')) dh, CONCAT('传真号码:', IFNULL(b.czhm,'')) czhm,DATE_FORMAT(a.jhrq,'%Y-%m-%d') jhrq, CONCAT('订单编号：',a.dddh) shdh, DATE_FORMAT(a.xdrq,'%Y-%m-%d') shrq FROM xt_cg_cgxx a LEFT JOIN xt_cg_yhxx b ON a.kh=b.khjc where a.id = ? ";
		return this.jdbcTemplate.queryForList(sql, new Object[] { id });
	}

	public List getShdMx(Integer id) {
		// TODO Auto-generated method stub
		StringBuffer sql = new StringBuffer(" SELECT (@i:=@i+1) xh,a.* ");
		sql.append(" FROM (  ");
		sql.append(" select b.cplh,b.wlmc,b.gg,b.cz,b.dw,b.sl,b.dj,FORMAT(b.sl*b.dj,2) je,b.bz,b.bzs,b.ms  ");
		sql.append(" from xt_cg_cgxx a, xt_cg_cgxx_mx b where a.id=b.cgxxid and a.id = ? ");
		sql.append(" ) a,( SELECT @i:=0) t2 ");
		return this.jdbcTemplate.queryForList(sql.toString(),
				new Object[] { id });
	}

	public List<Map<String, Object>> getSytj(Map<String, Object> param) {
		// TODO Auto-generated method stub
		String cplh = (String) param.get("cplh");
		String wlmc = (String) param.get("wlmc");
		String gg = (String) param.get("gg");
		List<Object> args = new ArrayList<Object>();
		StringBuffer sql = new StringBuffer("  ");
		sql.append(" SELECT b.cplh,b.wlmc,b.gg,c.sl-b.sl sy   ");
		sql.append("  FROM   ");
		sql.append("  (   ");
		sql.append(" SELECT b.cplh,b.wlmc,b.gg, SUM(b.sl) sl   ");
		sql.append("  FROM    ");
		sql.append(" 	 xt_cg_cgxx_mx b   ");
		sql.append(" GROUP BY b.cplh,b.wlmc,b.gg) b   ");
		sql.append("  LEFT JOIN    ");
		sql.append("  	 (   ");
		sql.append("  SELECT cgm.cplh,cgm.wlmc,cgm.gg, SUM(cm.sl) sl   ");
		sql.append("  FROM xt_cg_cgd_mx cm   ");
		sql.append(" left join xt_cg_cgxx_mx cgm on cm.cgxxmxid = cgm.cgxxmxid ");
		sql.append("  GROUP BY cgm.cplh,cgm.wlmc,cgm.gg) c ");
		sql.append(" ON  b.wlmc=c.wlmc and b.gg=c.gg  ");
		sql.append(" WHERE 1=1  ");
		if (wlmc != null) {
			sql.append(" and b.wlmc like ? ");
			args.add("%" + wlmc + "%");
		}

		if (cplh != null) {
			sql.append(" and b.cplh like ? ");
			args.add("%" + cplh + "%");
		}

		if (gg != null) {
			sql.append(" and b.gg like ? ");
			args.add("%" + gg + "%");
		}
		return this.jdbcTemplate.queryForList(sql.toString(),args.toArray());
	}

	public void saveDdxx(CgDrdd drdd) {
		// TODO Auto-generated method stub
		Object[] obj = null;
		int id = 0;
		String sql = "select id from  xt_cg_cgxx where dddh = ? ";
		List<Map<String, Object>> ls = this.jdbcTemplate.queryForList(sql,
				new Object[] { drdd.getDddh() });
		if (ls.size() == 0) {
			String lx = "1";
			if (drdd.getLx().equals("采购")) {
				lx = "1";
			} else if (drdd.getLx().equals("锰钢")) {
				lx = "2";
			} else if (drdd.getLx().equals("纸箱")) {
				lx = "3";
			} else if (drdd.getLx().equals("卷带")) {
				lx = "4";
			}
			sql = "insert into xt_cg_cgxx(kh,xdrq,jhrq,dddh,lx) values(?,?,?,?,?)";
			obj = new Object[] { drdd.getKh(), drdd.getXdrq(), drdd.getJhrq(),
					drdd.getDddh(), lx };
			this.jdbcTemplate.update(sql, obj);
			sql = "select id from xt_cg_cgxx where dddh = ? ";
			id = this.jdbcTemplate.queryForObject(sql,
					new Object[] { drdd.getDddh() }, Integer.class);
		} else {
			id = (int) (ls.get(0)).get("id");
		}
		sql = "select * from  xt_cg_cgxx_mx where cplh = ? and cgxxid = ? ";
		ls = this.jdbcTemplate.queryForList(sql, new Object[] { drdd.getCplh(),
				id });
		if (ls.size() == 0) {
			sql = "insert into xt_cg_cgxx_mx(cgxxid,cplh,wlmc,gg,cz,dw,sl,bzs,ms,dj) values(?,?,?,?,?,?,?,?,?,?)";
			obj = new Object[] { id, drdd.getCplh(), drdd.getWlmc(),
					drdd.getGg(), drdd.getCz(), drdd.getDw(), drdd.getDdsl(),
					drdd.getBzs(), drdd.getMs(), drdd.getDj() };
			this.jdbcTemplate.update(sql, obj);
		}
	}

}
