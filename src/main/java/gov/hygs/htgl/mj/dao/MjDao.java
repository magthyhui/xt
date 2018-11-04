package gov.hygs.htgl.mj.dao;

import gov.hygs.htgl.mj.entity.Ddxx;
import gov.hygs.htgl.mj.entity.DdxxMx;
import gov.hygs.htgl.mj.entity.Drdd;
import gov.hygs.htgl.mj.entity.Yhxx;

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
public class MjDao extends BaseJdbcDao {

	public Integer addDdxx(Ddxx sp) {
		// TODO Auto-generated method stub
		String sql = "insert into xt_mj_ddb(kh,xdrq,ddh) values(?,?,?)";
		Object[] obj = new Object[] { sp.getKh(),sp.getXdrq(),sp.getDdh() };
		this.jdbcTemplate.update(sql, obj);
		sql = "select id from xt_mj_ddb where ddh = ? ";
		return	this.jdbcTemplate.queryForObject(sql, new Object[]{sp.getDdh()},Integer.class);
		
	}

	public void updateDdxx(Ddxx sp) {
		// TODO Auto-generated method stub
		String sql = "update xt_mj_ddb set kh = ? ,xdrq = ? ,ddh = ? where id=? ";
		Object[] obj = new Object[] { sp.getKh(),sp.getXdrq(),sp.getDdh(),sp.getId() };
		this.jdbcTemplate.update(sql, obj);
	}

	public void deleteDdxx(Ddxx sp) {
		// TODO Auto-generated method stub
		String sql = "delete from xt_mj_ddb where id=?";
		this.jdbcTemplate.update(sql, new Object[] { sp.getId() });
	}

	public void getDdxx(Page page, Map<String, Object> param) {
		// TODO Auto-generated method stub
		String sqlCount = "select count(*) from xt_mj_ddb";
		int entityCount = this.jdbcTemplate.queryForObject(sqlCount,
				Integer.class);
		String sql = "select * from xt_mj_ddb limit ?,? order by kh,id desc ";

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
		
		String sql = "insert into xt_mj_ddb_mx(ddbid,wlh,wlmc,cz,clgg,jhrq,ddsl,hyjq,dw,dj,bz,gg,mjf,sbf,fkdjsj,kmdbsj,yjmjrq) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		Object[] obj = new Object[] { ddxxMx.getDdbid(),ddxxMx.getWlh(),ddxxMx.getWlmc(),ddxxMx.getCz(),ddxxMx.getClgg(),ddxxMx.getJhrq(),ddxxMx.getDdsl(),ddxxMx.getHyjq(),ddxxMx.getDw(),ddxxMx.getDj(),ddxxMx.getBz(),ddxxMx.getGg(),ddxxMx.getMjf(),ddxxMx.getSbf(),ddxxMx.getFkdjsj(),ddxxMx.getKmdbsj(),ddxxMx.getYjmjrq() };
		this.jdbcTemplate.update(sql, obj);

	}

	public void updateDdxxMx(DdxxMx ddxxMx) {
		// TODO Auto-generated method stub
		String sql = "update xt_mj_ddb_mx set ddbid = ?,wlh = ?,wlmc = ?,cz = ?,clgg = ?,jhrq = ?,ddsl = ?,hyjq = ?,dw= ?,dj = ?,bz = ?,gg = ?,mjf = ?,sbf= ?,fkdjsj = ?,kmdbsj = ?,yjmjrq = ? where mxid=? ";
		Object[] obj = new Object[] { ddxxMx.getDdbid(),ddxxMx.getWlh(),ddxxMx.getWlmc(),ddxxMx.getCz(),ddxxMx.getClgg(),ddxxMx.getJhrq(),ddxxMx.getDdsl(),ddxxMx.getHyjq(),ddxxMx.getDw(),ddxxMx.getDj(),ddxxMx.getBz(),ddxxMx.getGg(),ddxxMx.getMjf(),ddxxMx.getSbf(),ddxxMx.getFkdjsj(),ddxxMx.getKmdbsj(),ddxxMx.getYjmjrq(),ddxxMx.getMxid() };
		this.jdbcTemplate.update(sql, obj);
	}

	public void deleteDdxxMx(DdxxMx ddxxMx) {
		// TODO Auto-generated method stub
		String sql = "delete from xt_mj_ddb_mx where mxid=?";
		this.jdbcTemplate.update(sql, new Object[] { ddxxMx.getMxid() });
	}


	public void deleteDdxxMx(Integer id) {
		// TODO Auto-generated method stub
		String sql = "delete from xt_mj_ddb_mx where ddbid=?";
		this.jdbcTemplate.update(sql, new Object[] { id });
	}

	public List<Map<String, Object>> getDdxxMx(Map<String, Object> param) {
		// TODO Auto-generated method stub
		int id = (Integer) param.get("id");
		String sql = "select * from xt_mj_ddb_mx where ddbid= ? ";
		return this.jdbcTemplate.queryForList(sql,new Object[]{id});
	}
	
	public List<Map<String, Object>> getChdMx(Map<String, Object> param) {
		// TODO Auto-generated method stub
		int id = (Integer) param.get("id");
		String sql = "select * from xt_mj_chd_mx a left join xt_mj_ddb b on a.ddbid=b.id where chdid= ? ";
		return this.jdbcTemplate.queryForList(sql,new Object[]{id});
	}

	public List<Map<String, Object>> getDd(Map<String, Object> param) {
		// TODO Auto-generated method stub
		String kh = (String) param.get("kh");
		String ddh = (String) param.get("ddh");
		String ddzt = (String) param.get("ddzt");
		String wlh = (String) param.get("wlh");
		String wlmc = (String) param.get("wlmc");
		List<Object> args =new ArrayList<Object>();
		Date sjq=(Date) param.get("sjq"),sjz=(Date) param.get("sjz");
		StringBuffer sql =new StringBuffer("select 1 xh,a.*,b.*,FORMAT(b.ddsl*b.dj,2) je,b.ddsl-(select sum(case when c.lx='s' then cm.sl else 0 end )-sum(case when c.lx='t' then cm.sl else 0 end ) sl from xt_mj_chd c,xt_mj_chd_mx cm where c.id=cm.chdid and cm.mxid=b.mxid) wjsl,(select sum(case when c.lx='s' then cm.sl else 0 end )-sum(case when c.lx='t' then cm.sl else 0 end ) sl from xt_mj_chd c,xt_mj_chd_mx cm where c.id=cm.chdid and cm.mxid=b.mxid) yjsl from xt_mj_ddb a  left join xt_mj_ddb_mx b on a.id=b.ddbid "); 
		sql.append("   where  1= 1  ");
		if(kh!=null){
			sql.append(" and a.kh like ? ");
			args.add("%"+kh+"%");
		}
		if(ddh!=null){
			sql.append(" and a.ddh like ? ");
			args.add("%"+ddh+"%");
		}
		if(wlh!=null){
			sql.append(" and b.wlh like ? ");
			args.add("%"+wlh+"%");
		}
		if(wlmc!=null){
			sql.append(" and b.wlmc like ? ");
			args.add("%"+wlmc+"%");
		}
		if(sjq!=null){
			sql.append(" and a.xdrq >= date_format(date(?), '%Y%m%d') ");
			args.add(sjq);
		}
		if(sjz!=null){
			sql.append(" and a.xdrq <= date_format(date(?), '%Y%m%d')");
			args.add(sjz);
		}
		if(ddzt!=null){
			if(ddzt.equals("A")){
				sql.append(" and b.yxbz <> 'N' ");
			}else{
				if(ddzt.equals("G")){
					sql.append(" and b.yxbz = ? ");
				}else if(ddzt.equals("Y")){
					sql.append(" and a.yxbz = ? and b.yxbz <>'G' ");
				}else if(ddzt.equals("N")){
					sql.append(" and a.yxbz = ? ");
				}
				args.add(ddzt);
			}
		}
		sql.append(" union all select 2 xh,null,'合计',null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,FORMAT(sum(b.ddsl*b.dj),2) je,null,null from xt_mj_ddb a  left join xt_mj_ddb_mx b on a.id=b.ddbid ");
		sql.append("   where  1= 1  ");
		if(kh!=null){
			sql.append(" and a.kh like ? ");
			args.add("%"+kh+"%");
		}
		if(ddh!=null){
			sql.append(" and a.ddh like ? ");
			args.add("%"+ddh+"%");
		}
		if(wlh!=null){
			sql.append(" and b.wlh like ? ");
			args.add("%"+wlh+"%");
		}
		if(wlmc!=null){
			sql.append(" and b.wlmc like ? ");
			args.add("%"+wlmc+"%");
		}
		if(sjq!=null){
			sql.append(" and a.xdrq >= date_format(date(?), '%Y%m%d') ");
			args.add(sjq);
		}
		if(sjz!=null){
			sql.append(" and a.xdrq <= date_format(date(?), '%Y%m%d')");
			args.add(sjz);
		}
		if(ddzt!=null){
			if(ddzt.equals("A")){
				sql.append(" and b.yxbz <> 'N' ");
			}else{
				if(ddzt.equals("G")){
					sql.append(" and b.yxbz = ? ");
				}else if(ddzt.equals("Y")){
					sql.append(" and a.yxbz = ? and b.yxbz <>'G' ");
				}else if(ddzt.equals("N")){
					sql.append(" and a.yxbz = ? ");
				}
				args.add(ddzt);
			}
		}
		sql.append(" order by xh,kh,xdrq desc,ddh,wlh ");
		return this.jdbcTemplate.queryForList(sql.toString(),args.toArray());
	}

	public List<Map<String, Object>> getDdxx(Map<String, Object> param) {
		// TODO Auto-generated method stub
		
		if((Integer)param.get("id")==0){
			return null;
		}else{
			if(param.get("lx")!=null){
				int id = (Integer) param.get("id");
				String sql = "select * from xt_mj_chd where id = ? order by kh,id desc ";
				return this.jdbcTemplate.queryForList(sql,new Object[]{id});
			}else{
				int id = (Integer) param.get("id");
				String sql = "select * from xt_mj_ddb where id = ? order by kh,id desc ";
				return this.jdbcTemplate.queryForList(sql,new Object[]{id});
			}
		}
	}

	public List<Map<String, Object>> getChd(Map<String, Object> param) {
		// TODO Auto-generated method stub
		String id = (String) param.get("id");
		String sql = "select d.kh,a.*,b.*,c.wlh,c.wlmc from xt_mj_chd a ,xt_mj_chd_mx b,xt_mj_ddb_mx c,xt_mj_ddb d  where a.id=b.chdid and b.mxid=c.mxid and c.mxid = ? and c.ddbid=d.id ";
		return this.jdbcTemplate.queryForList(sql,new Object[]{id});
	}

	public List<Map<String, Object>> getDzxx(Map<String, Object> param) {
		// TODO Auto-generated method stub
		String kh = (String) param.get("kh");
		String lx = (String) param.get("lx");
		String cplx = (String) param.get("cplx");
		String ddh = (String) param.get("ddh");
		String wlh = (String) param.get("wlh");
		String shdh = (String) param.get("shdh");
		String cdbz = (String) param.get("cdbz");
		List<Object> args =new ArrayList<Object>();
		Date sjq=(Date) param.get("sjq"),sjz=(Date) param.get("sjz");;
		StringBuffer sql =new StringBuffer("select a.kh,d.ddh,b.wlh,b.wlmc,c.dj,c.mjf,c.sbf,c.fkdjsj,c.kmdbsj,c.yqjysj,a.shrq,b.sl,c.yqjysj,b.bz from  xt_mj_chd a left join xt_mj_chd_mx b on a.id=b.chdid left join xt_mj_ddb_mx c on b.mxid=c.mxid left join xt_mj_ddb d on  c.ddbid=d.id  where a.yxbz ='Y'  ");
		if(kh!=null){
			sql.append(" and a.kh like ? ");
			args.add("%"+kh+"%");
		}
		if(lx!=null){
			sql.append(" and a.lx = ? ");
			args.add(lx);
		}
		if(cdbz!=null){
			sql.append(" and a.cdbz = ? ");
			args.add(cdbz);
		}
		if(ddh!=null){
			sql.append(" and d.ddh like ? ");
			args.add("%"+ddh+"%");
		}
		if(wlh!=null){
			sql.append(" and c.wlh like ? ");
			args.add("%"+wlh+"%");
		}
		if(shdh!=null){
			sql.append(" and a.shdh like ? ");
			args.add("%"+shdh+"%");
		}
		if(cplx!=null){
			if(cplx.equals("0")){
				sql.append(" and b.ddbid is null ");
			}else if(cplx.equals("1")){
				sql.append(" and b.ddbid is not null ");
			}
		}
		sql.append(" and d.yxbz<>'N' and a.shrq between date_format(date(?), '%Y%m%d') and date_format(date(?), '%Y%m%d') order by a.kh,a.shrq,a.shdh,d.xdrq desc,d.ddh,c.wlh ");
		args.add(sjq);
		args.add(sjz);
		return this.jdbcTemplate.queryForList(sql.toString(),args.toArray());
	}

	public List<Map<String, Object>> getCh(Map<String, Object> param) {
		// TODO Auto-generated method stub
		String kh = (String) param.get("kh");
		String ddh = (String) param.get("ddh");
		String wlh = (String) param.get("wlh");
		String shdh = (String) param.get("shdh");
		List<Object> args =new ArrayList<Object>();
		Date sjq=(Date) param.get("sjq"),sjz=(Date) param.get("sjz");
		StringBuffer sql =new StringBuffer("select c.*,ifnull(b.dw,d.dw) dw,d.sl,d.bz,a.kh,a.ddh,ifnull(b.wlh,d.wlh) wlh,ifnull(b.wlmc,d.wlmc) wlmc "); 
		sql.append("  from xt_mj_chd c left join xt_mj_chd_mx d on   c.id=d.chdid ");
		sql.append("  left join xt_mj_ddb a on d.ddbid=a.id ");
		sql.append("  left join xt_mj_ddb_mx b on d.mxid=b.mxid ");
		sql.append("   where c.yxbz ='Y' ");
		if(kh!=null){
			sql.append(" and a.kh like ? ");
			args.add("%"+kh+"%");
		}
		if(ddh!=null){
			sql.append(" and a.ddh like ? ");
			args.add("%"+ddh+"%");
		}
		if(wlh!=null){
			sql.append(" and b.wlh like ? ");
			args.add("%"+wlh+"%");
		}
		if(shdh!=null){
			sql.append(" and c.shdh like ? ");
			args.add("%"+shdh+"%");
		}
		if(sjq!=null){
			sql.append(" and date_format(date(c.shrq), '%Y%m%d') >= date_format(date(?), '%Y%m%d') ");
			args.add(sjq);
		}
		if(sjz!=null){
			sql.append(" and date_format(date(c.shrq), '%Y%m%d') <= date_format(date(?), '%Y%m%d')");
			args.add(sjz);
		}
		sql.append("  order by c.kh,c.shrq DESC,c.shdh,a.xdrq desc,a.ddh,d.wlh");
		return this.jdbcTemplate.queryForList(sql.toString(),args.toArray());
	}

	public List<Map<String, Object>> getWl(Map<String, Object> param) {
		// TODO Auto-generated method stub
		String kh = null;
		List<Object> args =new ArrayList<Object>();
		StringBuffer sql = new StringBuffer("select b.mxid,a.id,a.ddh,b.wlh,b.wlmc,b.dw,(b.ddsl-ifnull((select sum(case when c.lx='s' then cm.sl else 0 end )-sum(case when c.lx='t' then cm.sl else 0 end ) sl from xt_mj_chd c,xt_mj_chd_mx cm where c.id=cm.chdid and cm.mxid=b.mxid),0)) sy from xt_mj_ddb a ,xt_mj_ddb_mx b  where a.id=b.ddbid  ");
		if(param!=null){
			kh= (String)param.get("kh");
		}
		if(kh!=null){
			sql.append(" and a.kh like ? ");
			args.add("%"+kh+"%");
		}
		sql.append("  and a.yxbz='Y' and b.yxbz ='Y' order by a.xdrq  ");
		return this.jdbcTemplate.queryForList(sql.toString(),args.toArray());
	}

	public List getShd(Integer id) {
		// TODO Auto-generated method stub
		String sql = "select b.khjc,concat('交货地址：',ifnull(b.dz,'惠州市惠台工业区和畅东六路7号')) dz,concat('客户名称：',ifnull(b.kh,'惠州市宏达五金制品有限公司')) kh,concat('联系人：',ifnull(b.lxr,'')) lxr,concat('联系电话:',ifnull(b.dh,'0752-2773399' )) dh ,concat('送货单号：',shdh) shdh,concat('送货日期：',DATE_FORMAT(shrq,'%Y年%m月%d日')) shrq from xt_mj_chd  a left join xt_dz_yhxx b on a.kh=b.khjc where a.id = ? ";
		return this.jdbcTemplate.queryForList(sql,new Object[]{id});
	}

	public List getShdMx(Integer id) {
		// TODO Auto-generated method stub
		StringBuffer sql = new StringBuffer(" select (@i:=@i+1)  xh,a.* from ( select d.ddh,ifnull(e.wlh,b.wlh) wlh,ifnull(e.wlmc,b.wlmc) wlmc,ifnull(e.dw,b.dw) dw,b.sl,b.bz,e.ddsl-(select sum(case when c.lx='s' then cm.sl else 0 end )-sum(case when c.lx='t' then cm.sl else 0 end ) sl "); 
		sql.append(" from xt_mj_chd c,xt_mj_chd_mx cm where c.id=cm.chdid and cm.mxid=e.mxid) wjsl,e.ddsl dgsl  ");
		sql.append(" from  ");
		sql.append("  xt_mj_chd a ,xt_mj_chd_mx b ");
		sql.append(" left join xt_mj_ddb d  on d.id=b.ddbid ");
		sql.append("  left join xt_mj_ddb_mx e on e.mxid=b.mxid ");
		sql.append("  where  a.id=b.chdid ");
		sql.append(" and a.id = ? ");
		sql.append(" order by a.shrq DESC,a.shdh,d.xdrq desc,d.ddh,e.wlh) a ,(select   @i:=0)  t2");
		return this.jdbcTemplate.queryForList(sql.toString(),new Object[]{id});
	}

	public void saveDdxx(Drdd drdd) {
		// TODO Auto-generated method stub
		Object[] obj = null;
		int id = 0;
		String sql = "select id from  xt_mj_ddb where ddh = ? ";
		List<Map<String,Object>> ls = this.jdbcTemplate.queryForList(sql,new Object[]{drdd.getDdh()});
		if(ls.size()==0){
			sql = "insert into xt_mj_ddb(kh,xdrq,ddh) values(?,?,?)";
			obj = new Object[] { drdd.getKh(),drdd.getXdrq(),drdd.getDdh() };
			this.jdbcTemplate.update(sql, obj);
			sql = "select id from xt_mj_ddb where ddh = ? ";
			id = this.jdbcTemplate.queryForObject(sql, new Object[]{drdd.getDdh()},Integer.class);
		}else{
			id = (int)(ls.get(0)).get("id");
		}
		sql = "select * from  xt_mj_ddb_mx where wlh = ? and ddbid = ? ";
		ls = this.jdbcTemplate.queryForList(sql,new Object[]{drdd.getWlbh(),id});
		if(ls.size()==0){
			//,mjf,sbf,fkdjsj,kmdbsj,yjmjrq
			sql = "insert into xt_mj_ddb_mx(ddbid,wlh,wlmc,cz,clgg,jhrq,ddsl,gg,dw) values(?,?,?,?,?,?,?,?,?)";
			obj = new Object[] { id,drdd.getWlbh(),drdd.getWlmc(),drdd.getCz(),drdd.getClgg(),drdd.getJhrq(),drdd.getDdsl(),drdd.getGg(),drdd.getDw()  };
			this.jdbcTemplate.update(sql, obj);
		}
	}

	public List<Map<String, Object>> getChDd(Map<String, Object> param) {
		// TODO Auto-generated method stub
		String kh = null;
		List<Object> args =new ArrayList<Object>();
		StringBuffer sql = new StringBuffer("select * from xt_mj_ddb a where 1 = 1 ");
		if(param!=null){
			kh= (String)param.get("kh");
		}
		if(kh!=null){
			sql.append(" and a.kh like ? ");
			args.add("%"+kh+"%");
		}
		sql.append("  and a.yxbz ='Y' and exists (select 1 from xt_mj_ddb_mx b where a.id=b.ddbid and b.yxbz='Y' ) order by kh,xdrq ");
		return this.jdbcTemplate.queryForList(sql.toString(),args.toArray());
	}

	public List<Map<String, Object>> getChDdMx(Map<String, Object> param) {
		// TODO Auto-generated method stub
		Integer id = (Integer) param.get("id");
		String sql = "select a.id ddbid,a.ddh,b.wlh,b.wlmc,b.dw,b.mxid from xt_mj_ddb a left join xt_mj_ddb_mx b on a.id=b.ddbid  where b.yxbz='Y' and a.id = ? ";
		return this.jdbcTemplate.queryForList(sql,new Object[]{id});
	}

	public Integer insertShd(Ddxx ddxxMx) {
		// TODO Auto-generated method stub
		String sql = "insert into xt_mj_chd(kh,shdh,shrq,lx) values(?,?,?,?)";
		Object[] obj = new Object[] {ddxxMx.getKh(),ddxxMx.getShdh(),ddxxMx.getShrq(),ddxxMx.getLx() };
		this.jdbcTemplate.update(sql, obj);
		sql = "select id from xt_mj_chd where shdh = ? ";
		return	this.jdbcTemplate.queryForObject(sql, new Object[]{ddxxMx.getShdh()},Integer.class);
	}

	public void updateShd(Ddxx sp) {
		// TODO Auto-generated method stub
		String sql = "update xt_mj_chd set kh = ? ,shdh = ? ,shrq = ?,lx = ? where id=? ";
		Object[] obj = new Object[] { sp.getKh(),sp.getShdh(),sp.getShrq(),sp.getLx(),sp.getId() };
		this.jdbcTemplate.update(sql, obj);
	}

	public void deleteShd(Ddxx sp) {
		// TODO Auto-generated method stub
		String sql = "delete from xt_mj_chd where id=?";
		this.jdbcTemplate.update(sql, new Object[] { sp.getId() });
	}

	public void deleteShdMx(Integer id) {
		// TODO Auto-generated method stub
		String sql = "delete from xt_mj_chd_mx where chdid=?";
		this.jdbcTemplate.update(sql, new Object[] { id });
	}

	public void insertShdMx(DdxxMx ddxxMx) {
		// TODO Auto-generated method stub
		String sql = "insert into xt_mj_chd_mx(chdid,ddbid,mxid,dw,sl,bz,wlh,wlmc) values(?,?,?,?,?,?,?,?)";
		Object[] obj = new Object[] { ddxxMx.getChdid(),ddxxMx.getDdbid(),ddxxMx.getMxid(),ddxxMx.getDw(),ddxxMx.getSl(),ddxxMx.getBz(),ddxxMx.getWlh(),ddxxMx.getWlmc() };
		this.jdbcTemplate.update(sql, obj);
		
	}

	public void updateShdMx(DdxxMx ddxxMx) {
		// TODO Auto-generated method stub
		String sql = "update xt_mj_chd_mx set chdid = ?,ddbid = ?,mxid = ?,dw = ?,sl = ?,bz = ?,wlh = ?,wlmc = ? where chmxid=? ";
		Object[] obj = new Object[] { ddxxMx.getChdid(),ddxxMx.getDdbid(),ddxxMx.getMxid(),ddxxMx.getDw(),ddxxMx.getSl(),ddxxMx.getBz(),ddxxMx.getWlh(),ddxxMx.getWlmc(),ddxxMx.getChmxid() };
		this.jdbcTemplate.update(sql, obj);
	}

	public void deleteShdMx(DdxxMx ddxxMx) {
		// TODO Auto-generated method stub
		String sql = "delete from xt_mj_chd_mx where chmxid=?";
		this.jdbcTemplate.update(sql, new Object[] { ddxxMx.getChmxid() });
		
	}

	public List<Map<String, Object>> getKh(Map<String, Object> param) {
		// TODO Auto-generated method stub
		String sql = "select kh from xt_mj_ddb group by kh";
		return this.jdbcTemplate.queryForList(sql);
	}

	public String setDdzt(Map param) {
		// TODO Auto-generated method stub
		String lx= (String)param.get("lx");
		int id = (Integer) param.get("id");
		String sql = "update xt_mj_ddb_mx set yxbz = ? where mxid = ? ";
		this.jdbcTemplate.update(sql, new Object[] {lx,id });
		
		return "ok";
	}

	public String setCd(Map param) {
		// TODO Auto-generated method stub
		Integer id = (Integer)param.get("id");
		String sql = "update xt_mj_chd set cdbz = 'Y' where id = ? ";
		this.jdbcTemplate.update(sql, new Object[] {id });
		return "ok";
	}

}
