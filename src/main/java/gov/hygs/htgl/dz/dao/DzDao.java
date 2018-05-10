package gov.hygs.htgl.dz.dao;

import gov.hygs.htgl.dz.entity.Ddxx;
import gov.hygs.htgl.dz.entity.DdxxMx;
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
		String sql = "select * from xt_dz_ddb limit ?,? order by kh,id desc ";

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
		String sql = "insert into xt_dz_ddb_mx(ddbid,wlh,wlmc,cz,clgg,jhrq,ddsl,hyjq,dw,dj,bz,gg) values(?,?,?,?,?,?,?,?,?,?,?,?)";
		Object[] obj = new Object[] { ddxxMx.getDdbid(),ddxxMx.getWlh(),ddxxMx.getWlmc(),ddxxMx.getCz(),ddxxMx.getClgg(),ddxxMx.getJhrq(),ddxxMx.getDdsl(),ddxxMx.getHyjq(),ddxxMx.getDw(),ddxxMx.getDj(),ddxxMx.getBz(),ddxxMx.getGg() };
		this.jdbcTemplate.update(sql, obj);

	}

	public void updateDdxxMx(DdxxMx ddxxMx) {
		// TODO Auto-generated method stub
		String sql = "update xt_dz_ddb_mx set ddbid = ?,wlh = ?,wlmc = ?,cz = ?,clgg = ?,jhrq = ?,ddsl = ?,hyjq = ?,dw= ?,dj = ?,bz = ?,gg = ? where mxid=? ";
		Object[] obj = new Object[] { ddxxMx.getDdbid(),ddxxMx.getWlh(),ddxxMx.getWlmc(),ddxxMx.getCz(),ddxxMx.getClgg(),ddxxMx.getJhrq(),ddxxMx.getDdsl(),ddxxMx.getHyjq(),ddxxMx.getDw(),ddxxMx.getDj(),ddxxMx.getBz(),ddxxMx.getGg(),ddxxMx.getMxid() };
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
		String sql = "select * from xt_dz_ddb where id = ? order by kh,id desc ";
		return this.jdbcTemplate.queryForList(sql,new Object[]{id});
	}

	public Integer insertShd(Ddxx ddxxMx) {
		// TODO Auto-generated method stub
		String sql = "insert into xt_dz_chd(kh,shdh,shrq) values(?,?,?)";
		Object[] obj = new Object[] {ddxxMx.getKh(),ddxxMx.getShdh(),ddxxMx.getShrq() };
		this.jdbcTemplate.update(sql, obj);
		sql = "select id from xt_dz_chd where shdh = ? ";
		return	this.jdbcTemplate.queryForObject(sql, new Object[]{ddxxMx.getShdh()},Integer.class);
	}

	public void insertShdMx(Integer id, DdxxMx ddxxMx) {
		// TODO Auto-generated method stub
		String sql = "insert into xt_dz_chd_mx(chdid,ddbid,mxid,dw,sl,bz,wlh,wlmc) values(?,?,?,?,?,?,?,?)";
		Object[] obj = new Object[] { id,ddxxMx.getDdbid(),ddxxMx.getMxid(),ddxxMx.getDw(),ddxxMx.getSl(),ddxxMx.getBz(),ddxxMx.getWlh(),ddxxMx.getWlmc() };
		this.jdbcTemplate.update(sql, obj);
		
	}

	public List<Map<String, Object>> getChd(Map<String, Object> param) {
		// TODO Auto-generated method stub
		String id = (String) param.get("id");
		String sql = "select d.kh,a.*,b.*,c.wlh,c.wlmc from xt_dz_chd a ,xt_dz_chd_mx b,xt_dz_ddb_mx c,xt_dz_ddb d  where a.id=b.chdid and b.mxid=c.mxid and c.mxid = ? and c.ddbid=d.id ";
		return this.jdbcTemplate.queryForList(sql,new Object[]{id});
	}

	public List<Map<String, Object>> getDzxx(Map<String, Object> param) {
		// TODO Auto-generated method stub
		String kh = (String) param.get("kh");
		List<Object> args =new ArrayList<Object>();
		Date sjq=(Date) param.get("sjq"),sjz=(Date) param.get("sjz");;
		StringBuffer sql =new StringBuffer("select a.*,b.*,c.wlh,c.wlmc,d.ddh,FORMAT(c.ddsl*c.dj,2) je,c.dj from xt_dz_chd a ,xt_dz_chd_mx b,xt_dz_ddb_mx c,xt_dz_ddb d where a.id=b.chdid and b.mxid=c.mxid and c.ddbid=d.id ");
	
		if(kh!=null){
			sql.append(" and kh like ? ");
			args.add("%"+kh+"%");
		}
		sql.append("  and a.shrq between ? and ? order by a.kh,a.shrq ");
		args.add(sjq);
		args.add(sjz);
		return this.jdbcTemplate.queryForList(sql.toString(),args.toArray());
	}

	public List<Map<String, Object>> getCh(Map<String, Object> param) {
		// TODO Auto-generated method stub
		String kh = (String) param.get("kh");
		String ddh = (String) param.get("ddh");
		String shdh = (String) param.get("shdh");
		List<Object> args =new ArrayList<Object>();
		Date sjq=(Date) param.get("sjq"),sjz=(Date) param.get("sjz");
		//StringBuffer sql =new StringBuffer("select c.*,d.*,a.kh,a.ddh,b.wlh,b.wlmc  from xt_dz_ddb a ,xt_dz_ddb_mx b,xt_dz_chd c,xt_dz_chd_mx d where  c.id=d.chdid and d.ddbid=a.id and d.mxid=b.mxid");
		StringBuffer sql =new StringBuffer("select c.*,ifnull(b.dw,d.dw) dw,d.sl,d.bz,a.kh,a.ddh,ifnull(b.wlh,d.wlh) wlh,ifnull(b.wlmc,d.wlmc) wlmc "); 
		sql.append("  from xt_dz_chd c,xt_dz_chd_mx d ");
		sql.append("  left join xt_dz_ddb a on d.ddbid=a.id ");
		sql.append("  left join xt_dz_ddb_mx b on d.mxid=b.mxid ");
		sql.append("   where  c.id=d.chdid  ");
		if(kh!=null){
			sql.append(" and a.kh like ? ");
			args.add("%"+kh+"%");
		}
		if(ddh!=null){
			sql.append(" and a.ddh like ? ");
			args.add("%"+ddh+"%");
		}
		if(shdh!=null){
			sql.append(" and c.shdh like ? ");
			args.add("%"+shdh+"%");
		}
		if(sjq!=null){
			sql.append(" and c.shrq >= ? ");
			args.add(sjq);
		}
		if(sjz!=null){
			sql.append(" and c.shrq <= ?");
			args.add(sjz);
		}
		return this.jdbcTemplate.queryForList(sql.toString(),args.toArray());
	}

	public List<Map<String, Object>> getWl(Map<String, Object> param) {
		// TODO Auto-generated method stub
		String sql ="select b.mxid,a.id,a.ddh,b.wlh,b.wlmc,b.dw,(b.ddsl-ifnull(c.sl,0)) sy from xt_dz_ddb a ,xt_dz_ddb_mx b left join  (select c.mxid,sum(c.sl) sl from xt_dz_chd_mx c group by c.mxid) c on c.mxid=b.mxid where a.id=b.ddbid and b.ddsl>= ifnull(c.sl,0) order by a.kh,a.id desc";
		return this.jdbcTemplate.queryForList(sql);
	}

	public List getShd(Integer id) {
		// TODO Auto-generated method stub
		String sql = "select concat('交货地址：',ifnull(b.dz,'惠州市惠台工业区和畅东六路7号')) dz,concat('客户名称：',ifnull(b.kh,'惠州市宏达五金制品有限公司 ')) kh,concat('联系电话:',ifnull(b.dh,'0752-2773399' )) dh ,concat('送货单号：',shdh) shdh,concat('送货日期：',DATE_FORMAT(shrq,'%Y年%m月%d日')) shrq from xt_dz_chd  a left join xt_dz_yhxx b on a.kh=b.khjc where a.id = ? ";
		return this.jdbcTemplate.queryForList(sql,new Object[]{id});
	}

	public List getShdMx(Integer id) {
		// TODO Auto-generated method stub
		StringBuffer sql = new StringBuffer(" select (@i:=@i+1)  xh,d.ddh,ifnull(c.wlh,b.wlh) wlh,ifnull(c.wlmc,b.wlmc) wlmc,ifnull(c.dw,b.dw) dw,b.sl,b.bz  ");
		sql.append(" from (select   @i:=0)  t2, ");
		sql.append("  xt_dz_chd a ,xt_dz_chd_mx b ");
		sql.append(" left join xt_dz_ddb d  on d.id=b.ddbid ");
		sql.append("  left join xt_dz_ddb_mx c on c.mxid=b.mxid ");
		sql.append("  where  a.id=b.chdid ");
		sql.append(" and a.id = ? ");
		return this.jdbcTemplate.queryForList(sql.toString(),new Object[]{id});
	}


	public void getYhxx(Page page, Map<String, Object> param) {
		// TODO Auto-generated method stub
		String sqlCount = "select count(*) from xt_dz_yhxx";
		int entityCount = this.jdbcTemplate.queryForObject(sqlCount,
				Integer.class);
		String sql = "select * from xt_dz_yhxx limit ?,?";

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

	
	public void addYhxx(Yhxx sp) {
		// TODO Auto-generated method stub
		String sql = "insert into xt_dz_yhxx(kh,khjc,dh,dz) values(?,?,?,?)";
		Object[] obj = new Object[] { sp.getKh(),sp.getKhjc(), sp.getDh(), sp.getDz()};
		this.jdbcTemplate.update(sql, obj);
	}

	
	public void updateYhxx(Yhxx sp) {
		// TODO Auto-generated method stub
		String sql = "update xt_dz_yhxx set kh=?,khjc=?,dh=?,dz=? where id=?";
		Object[] obj = new Object[] { sp.getKh(),sp.getKhjc(), sp.getDh(), sp.getDz(),sp.getId() };
		this.jdbcTemplate.update(sql, obj);
	}

	
	public void deleteYhxx(Yhxx sp) {
		// TODO Auto-generated method stub
		String sql = "delete from xt_dz_yhxx where id=?";
		this.jdbcTemplate.update(sql, new Object[] { sp.getId() });
	}

	public String getZdbh(Map<String, Object> para) {
		// TODO Auto-generated method stub
		String lx = (String)para.get("lx");
		String sql = "select   date_format(date(now()), '%Y%m%d') qz,CAST(date(scrq)<date(now()) AS CHAR) sj,CAST(bh AS CHAR) bh from xt_dz_zdbh where lx = ? ";
		Map<String,Object> map = this.jdbcTemplate.queryForMap(sql,new Object[]{lx});
		String sj = (String)map.get("sj");
		String bh = (String)map.get("bh");
		String qz = (String)map.get("qz");
		if(sj.equals("1")){
			bh="01";
			sql = "update xt_dz_zdbh set scrq = date(now()) ,bh = 2 where lx =?";
			this.jdbcTemplate.update(sql, new Object[] { lx });
		}else{
			sql = "update xt_dz_zdbh set bh = bh+1 where lx =?";
			this.jdbcTemplate.update(sql, new Object[] { lx });
		}
		if(bh.length()==1){
			bh="0"+bh;
		}
		if(lx.equals("s")){
			bh="HHXT"+qz+bh;
		}else{
			bh="HHXT"+qz+bh+"th";
		}
		return bh;
	}

	
}
