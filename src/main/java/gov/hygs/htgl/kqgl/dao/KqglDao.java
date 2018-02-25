package gov.hygs.htgl.kqgl.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.gdky.restfull.dao.BaseJdbcDao;

@Repository
public class KqglDao extends BaseJdbcDao {

	/**
	 * 保存导入数据时间并返回ID
	 * @param kqsj
	 * @return
	 */
	public int saveKqsj(String kqsj) {
		// TODO Auto-generated method stub
		final String kqq = kqsj.substring(0, 10);
		final String kqz = kqsj.substring(13, 23);
		final String sql = "insert into xt_kqjl(kqq,kqz) values (?,?) ";
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(
					Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(sql,
						Statement.RETURN_GENERATED_KEYS);
				ps.setString(1, kqq);
				ps.setString(2, kqz);
				return ps;
			}
		}, keyHolder);
		int id = keyHolder.getKey().intValue();
		return id;
	}

	
	public void saveKqjl(int kqsjId, Map<String, Object> kqxx) {
		// TODO Auto-generated method stub
		String gh = (String) kqxx.get("gh");
		String xm = (String) kqxx.get("xm");
		String bm = (String) kqxx.get("bm");
		
		int ts = (int) kqxx.get("ts");
		for (int i = 1; i <= ts; i++) {
			String zbq = null, zbz = null, wbq = null, wbz = null, ybq = null, ybz = null;
			String rq = (String) kqxx.get("rq" + i);
			int bz = 1;
			int rqlength = rq.length();
			if (rqlength % 10 == 0) {
				for (int j = 0; j + 5 <= rqlength; j += 5) {
					String sj = rq.substring(j, j + 5);
					if (bz == 1) {
						zbq = sj;
					} else if (bz == 2) {
						zbz = sj;
					} else if (bz == 3) {
						wbq = sj;
					} else if (bz == 4) {
						wbz = sj;
					} else if (bz == 5) {
						ybq = sj;
					} else if (bz == 6) {
						ybz = sj;
					}
					bz++;
				}
			}else{
				int zbbz=0;
				int wbbz=0;
				for (int j = 0; j + 5 <= rqlength; j += 5) {
					String sj = rq.substring(j, j + 5);
					int t = Integer.valueOf(rq.substring(j, j + 2));
					int w =  Integer.valueOf(rq.substring(j + 3, j + 5));
					// 0800 1200 1400 1800 1830 2130
					//07:44 12:01 13:58
					if (t<8||(t==8&&w==0)) {
						zbq = sj;
					} else if (t>8&&t<14&&zbbz==0) {
						zbz = sj;
						zbbz++;
					} else if (t>=12&&t<14&&zbbz==1||(t==14&&w==0)) {
						wbq = sj;
					} else if (t>14&&t<18||(t==18&&w<30)&&wbbz==0) {
						wbz = sj;
						wbbz++;
					} else if (t==18&&w>30||wbbz==1) {
						ybq = sj;
						wbbz++;
					} else if (t==18&&w>30&&wbbz==2) {
						ybq = sj;
					}
				}
				
			}

			String mxsql = "insert into xt_kqjl_mx(kq_id,gh,ts,zbq,zbz,wbq,wbz,ybq,ybz) values (?,?,?,?,?,?,?,?,?) ";
			this.jdbcTemplate.update(mxsql, new Object[] {kqsjId,gh,i,zbq,zbz,wbq,wbz,ybq,ybz});
		}
	}

	public List<Map<String, Object>> getKqjl(Map<String, Object> para) {
		// TODO Auto-generated method stub
		Date sjq = (Date) para.get("sjq");
		Date sjz = (Date) para.get("sjz");
		String sfzm = (String) para.get("sfzm");
		String xm = (String) para.get("xm");
		List<Object> args = new ArrayList<Object>();
		StringBuffer sql = new StringBuffer();
		sql.append(" select a.id,a.kqq,a.kqz,c.gh,c.xm,c.bm,b.id mxid ,b.ts,zbq,zbz,wbq,wbz,ybq,ybz,");
		sql.append(" case DAYOFWEEK(date_format(date_add(a.kqq, interval ts-1 day),'%Y-%m-%d'))  when 1 then '星期日' when 2 then '星期一' when 3 then '星期二' when 4 then '星期三' when 5 then '星期四' when 6 then '星期五' when 7 then '星期六' end xq");
		sql.append(" from xt_kqjl a, xt_kqjl_mx b,xt_kqry c ");
		sql.append(" where a.id=b.kq_id and b.gh=c.gh ");
		if(xm!=null&&xm!=""){
			sql.append("  and a.xm like ? ");
			args.add("%"+xm+"%");
		}
		sql.append("  and  ( ");
		sql.append(" date_format(CONCAT('2017-01-01 ',zbq,':00'), '%Y-%m-%d %H:%i:%s')>date_format('2017-01-01 07:55:00', '%Y-%m-%d %H:%i:%s') ");
		sql.append(" or  ");
		sql.append(" date_format(CONCAT('2017-01-01 ',zbz,':00'), '%Y-%m-%d %H:%i:%s')<date_format('2017-01-01 12:00:00', '%Y-%m-%d %H:%i:%s') ");
		sql.append(" or "); 
		sql.append(" date_format(CONCAT('2017-01-01 ',wbq,':00'), '%Y-%m-%d %H:%i:%s') > date_format('2017-01-01 13:55:00', '%Y-%m-%d %H:%i:%s') ");
		sql.append(" or "); 
		sql.append(" date_format(CONCAT('2017-01-01 ',wbz,':00'), '%Y-%m-%d %H:%i:%s') < date_format('2017-01-01 18:00:00', '%Y-%m-%d %H:%i:%s') ");
		sql.append(" or  ");
		sql.append(" date_format(CONCAT('2017-01-01 ',ybq,':00'), '%Y-%m-%d %H:%i:%s') > date_format('2017-01-01 18:30:00', '%Y-%m-%d %H:%i:%s') ");
		sql.append(" or  ");
		sql.append(" date_format(CONCAT('2017-01-01 ',ybz,':00'), '%Y-%m-%d %H:%i:%s') < date_format('2017-01-01 21:30:00', '%Y-%m-%d %H:%i:%s') ");
		sql.append(" or zbq is null  or zbz is null or wbq is null or wbz is null ) ");
		sql.append(" and date_format(a.kqq,'%Y-%m-%d') >= date_format(?,'%Y-%m-%d') and date_format(a.kqz,'%Y-%m-%d')  <= date_format(?,'%Y-%m-%d')");
		if(sfzm.equals("1")){
			sql.append("and bz is null and (DAYOFWEEK(date_format(date_add(a.kqq, interval ts-1 day),'%Y-%m-%d')) not in (1,7) or ( zbq is not null  or zbz is not null or wbq is not null or wbz is not null or ybq is not null or ybz is not null) )  order by bm,gh,ts ");
		}
		args.add(sjq);
		args.add(sjz);
		List<Map<String, Object>> ls =  this.jdbcTemplate.queryForList(sql.toString(),args.toArray());
		return ls;
	}

	public String updateXg(Map<String, Object> mx) {
		// TODO Auto-generated method stub
		String sql ="update xt_kqjl_mx set zbq = ? ,zbz = ? ,wbq = ? ,wbz = ? ,ybq = ? ,ybz = ? ,bz = ? where id = ? ";
		this.jdbcTemplate.update(sql,new Object[]{mx.get("zbq"),mx.get("zbz"),mx.get("wbq"),mx.get("wbz"),mx.get("ybq"),mx.get("ybz"),mx.get("bz"),mx.get("id")});
		return "ok";
	}

	public Map<String, Object> getKqjlmxByid(Integer id) {
		// TODO Auto-generated method stub
		String sql ="select * from xt_kqjl_mx where id = ?";
		List<Map<String, Object>> ls =  this.jdbcTemplate.queryForList(sql,new Object[]{id});
		if(ls.size()>0){
			return ls.get(0);
		}
		return null;
	}

	public String updateQj(Integer id,String bz) {
		// TODO Auto-generated method stub
		String sql ="update xt_kqjl_mx set bz = ? where id = ? ";
		this.jdbcTemplate.update(sql,new Object[]{bz,id});
		return "ok";
	}

	public List<Map<String, Object>> getKqAll(Map<String, Object> para) {
		// TODO Auto-generated method stub
		Date sjq = (Date) para.get("sjq");
		Date sjz = (Date) para.get("sjz");
		String sfzm = (String) para.get("sfzm");
		String xm = (String) para.get("xm");
		List<Object> args = new ArrayList<Object>();
		StringBuffer sql = new StringBuffer();
		sql.append(" select a.id,a.kqq,a.kqz,a.gh,a.xm,a.bm,b.id mxid ,b.ts,zbq,zbz,wbq,wbz,ybq,ybz,");
		sql.append(" case DAYOFWEEK(date_format(date_add(a.kqq, interval ts-1 day),'%Y-%m-%d'))  when 1 then '星期日' when 2 then '星期一' when 3 then '星期二' when 4 then '星期三' when 5 then '星期四' when 6 then '星期五' when 7 then '星期六' end xq");
		sql.append(" from xt_kqjl a, xt_kqjl_mx b ");
		sql.append(" where a.id=b.kq_id ");
		if(xm!=null&&xm!=""){
			sql.append("  and a.xm like ? ");
			args.add("%"+xm+"%");
		}
		sql.append(" and date_format(a.kqq,'%Y-%m-%d') >= date_format(?,'%Y-%m-%d') and date_format(a.kqz,'%Y-%m-%d')  <= date_format(?,'%Y-%m-%d')");

		args.add(sjq);
		args.add(sjz);
		List<Map<String, Object>> ls =  this.jdbcTemplate.queryForList(sql.toString(),args.toArray());
		return ls;
	}

	public List<Map<String, Object>> getKqhz(Map<String, Object> para) {
		// TODO Auto-generated method stub
		StringBuffer sql = new StringBuffer();
		sql.append(" select * from xt_kq_hz ");
		sql.append(" 		order by gh,lx ");
		

		return this.jdbcTemplate.queryForList(sql.toString());
	}


	public List<Map<String, Object>> getKqwtsj(Map<String, Object> para) {
		// TODO Auto-generated method stub
		StringBuffer sql = new StringBuffer();
		sql.append(" 	select 1 lx,'' id,gh, ");
		sql.append(" 	 '工号：' 1t ,a.gh '2t',null '3t','姓名：' 4t,a.xm '5t',null '6t','部门：' 7t,a.bm '8t',null 9t,null '10t',null '11t',null '12t',null '13t',null '14t',null '15t',null '16t',null '17t',null '18t',null 19t ,null '20t',null  '21t',null '22t',null '23t',null '24t',null '25t',null '26t',null '27t',null '28t',null '29t',null '30t',null '31t', ");
		sql.append(" 	  null '1s' ,null '2s',null '3s',null '4s',null '5s',null '6s',null '7s',null '8s',null '9s',null '10s',null '11s',null '12s',null '13s',null '14s',null '15s',null '16s',null '17s',null '18s',null '19s' ,null '20s',null '21s',null '22s',null '23s',null '24s',null '25s',null '26s',null '27s',null '28s',null '29s',null '30s',null '31s', ");
		sql.append("    null '1w' ,null '2w',null '3w',null '4w',null '5w',null '6w',null '7w',null '8w',null '9w',null '10w',null '11w',null '12w',null '13w',null '14w',null '15w',null '16w',null '17w',null '18w',null '19w' ,null '20w',null '21w',null '22w',null '23w',null '24w',null '25w',null '26w',null '27w',null '28w',null '29w',null '30w',null '31w' ");
		sql.append(" 	 from xt_kqry a  ");
		sql.append(" 	union all  ");
		sql.append(" 	select * from xt_kqwtsj  ");
		sql.append(" 			order by gh,lx "); 
		return this.jdbcTemplate.queryForList(sql.toString());
	}


	public String updateSj(Map<String, Object> para) {
		// TODO Auto-generated method stub
		Map<String,Object> sj = (Map<String, Object>) para.get("sj");
		String lx = (String) para.get("lx");
		String sjlx = (String) sj.get("lx");
		String gh = (String) sj.get("gh");
		String id = (String) sj.get("id");
		String sjq = (String) sj.get("sjq");
		String sjz = (String) sj.get("sjz");
		String jsjq = (String) sj.get("jsjq");
		String jsjz = (String) sj.get("jsjz");
		String ts = (String) sj.get("ts");
		String sql = null;
		String sqllog = "insert into xt_kq_log(kq_id,gh,ts,sjq,sjz,lx,sjlx,jsjq,jsjz) values(?,?,?,?,?,?,?,?,?)";;
		if(sjlx.equals("2")){
			sql = "update xt_kqjl_mx set zbq = ? ,zbz = ?,bz = ? where kq_id = ? and gh = ? and ts = ? ";		
		}else if(sjlx.equals("3")){
			sql = "update xt_kqjl_mx set wbq = ? ,wbz = ?,bz = ? where kq_id = ? and gh = ? and ts = ? ";
		}else if(sjlx.equals("4")){
			sql = "update xt_kqjl_mx set ybq = ? ,ybz = ?,bz = ? where kq_id = ? and gh = ? and ts = ? ";
		}
		this.jdbcTemplate.update(sql,new Object[]{sjq,sjz,lx,id,gh,ts});
		this.jdbcTemplate.update(sqllog,new Object[]{id,gh,ts,sjq,sjz,lx,sjlx,jsjq,jsjz});
		this.jdbcTemplate.execute("call kqwtsj()");
		return null;
	}

	
}
