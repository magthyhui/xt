package gov.hygs.htgl.dao.impl;

import gov.hygs.htgl.dao.ZszskDao;
import gov.hygs.htgl.entity.Role;
import gov.hygs.htgl.entity.Yxzsk;
import gov.hygs.htgl.entity.ZskJl;
import gov.hygs.htgl.entity.Zskly;
import gov.hygs.htgl.entity.Zszsk;
import gov.hygs.htgl.security.CustomUserDetails;
import gov.hygs.htgl.utils.AttachmentOpt;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.bstek.dorado.data.provider.Page;
import com.gdky.restfull.dao.BaseJdbcDao;

@Repository
public class ZszskDaoImpl extends BaseJdbcDao implements ZszskDao {

	@Override
	public void getZszskInfo(Page<ZskJl> page, Map<String, Object> param,
			CustomUserDetails userDetails) {
		// TODO Auto-generated method stub
		List<Object> args = new ArrayList<Object>();
		int pageSize = page.getPageSize();
		int pageNow = page.getPageNo();
		String roleName = this.getRoleInfoByUserId(userDetails.getId())
				.getRole_Name();
		StringBuffer sqlCount = new StringBuffer("");
		if ("SuAdmin".equals(roleName)) {// 超级用户
			sqlCount.append("select count(*) from zsk_jl a,user b,dept c,zskly d ");
			sqlCount.append("where a.USER_ID = b.ID_ and a.DEPTID = c.ID_ and a.ZSKLY_ID = d.ID_ and a.xybz='Y'  and a.yxbz ='Y' ");
			if (param != null) {
				args.addAll(this.rebuileSqlByConditionAndRole(sqlCount, param));
			}
		} else if ("DeptAdmin".equals(roleName)) {// 部门管理员
			sqlCount.append("select count(*) from zsk_jl a,user b,dept c,zskly d ");
			sqlCount.append("where a.USER_ID = b.ID_ and a.DEPTID = c.ID_ and a.ZSKLY_ID = d.ID_ and a.xybz='Y'  and a.yxbz ='Y' and a.deptid=?");
			args.add(userDetails.getDeptid());
			if (param != null) {
				args.addAll(this.rebuileSqlByConditionAndRole(sqlCount, param));
			}
		} else if ("Other".equals(roleName)) {// 普通用户
			sqlCount.append("select count(*) from zsk_jl a,user b,dept c,zskly d");
			sqlCount.append(" where a.USER_ID = b.ID_ and a.DEPTID = c.ID_ and a.ZSKLY_ID = d.ID_ and a.xybz='Y' and a.yxbz ='Y' and a.deptid=? and a.user_id=?");
			args.add(userDetails.getDeptid());
			args.add(userDetails.getId());
		}
		int count = this.jdbcTemplate.queryForObject(sqlCount.toString(),args.toArray(),
				Integer.class);
		List<ZskJl> list = this.getYxzskInfo(pageSize * (pageNow - 1),
				pageSize, userDetails, roleName, param);
		page.setEntityCount(count);
		page.setEntities(list);
	}

	private List<ZskJl> getYxzskInfo(int begin, int offest,
			CustomUserDetails userDetails, String roleName,
			Map<String, Object> param) {
		List<Object> args = new ArrayList<Object>();
		StringBuffer sql = new StringBuffer(" select d.TITLE zsktitle,b.USER_NAME,c.DEPT_NAME,a.* ");
			sql.append(" from zsk_jl a,user b,dept c,zskly d ");
			sql.append(" where a.USER_ID = b.ID_ and a.DEPTID = c.ID_ and a.ZSKLY_ID = d.ID_ ");
			sql.append(" and a.xybz='Y' and a.yxbz ='Y' ");
		if ("SuAdmin".equals(roleName)) {// 超级管理员
			if (param != null) {
				args.addAll(this.rebuileSqlByConditionAndRole(sql, param));
			}
		} else if ("DeptAdmin".equals(roleName)) {// 部门管理员
			sql.append("and a.deptid=? ");
			args.add(userDetails.getDeptid());
			if (param != null) {
				args.addAll(this.rebuileSqlByConditionAndRole(sql, param));
			}
		} else if ("Other".equals(roleName)) {// 普通用户
			sql.append("and a.deptid=? and a.user_id=? ");
			args.add(userDetails.getDeptid());
			args.add(userDetails.getId());
		}
		sql.append(" order by a.create_date desc limit ?,?");
		args.add(begin);
		args.add(offest);
		List<ZskJl> list = this.jdbcTemplate.query(sql.toString(),args.toArray(),
				new RowMapper<ZskJl>() {

					@Override
					public ZskJl mapRow(ResultSet result, int i)
							throws SQLException {
						// TODO Auto-generated method stub
						ZskJl zszsk = new ZskJl();
						zszsk.setZskly(result.getString("zsktitle"));
						zszsk.setDept(result.getString("DEPT_NAME"));
						zszsk.setUser(result.getString("USER_NAME"));
						zszsk.setId(result.getString("id_"));
						zszsk.setUserId(result.getInt("user_id"));
						zszsk.setCreateDate(result.getDate("create_date"));
						zszsk.setSpDate(result.getDate("sp_date"));
						zszsk.setSprId(result.getInt("spr_id"));
						zszsk.setDeptid(result.getInt("deptid"));
						zszsk.setContent(result.getString("content"));
						zszsk.setZsklyId(result.getInt("zskly_id"));
						zszsk.setTitle(result.getString("title"));
						zszsk.setYxbz(result.getString("yxbz"));
						zszsk.setXybz(result.getString("xybz"));
						return zszsk;
					}

				});
		return list;
	}

	private Role getRoleInfoByUserId(Integer id) {
		String sql = "select a.* from role a,user_role b where a.id_=b.role_id and user_id=? order by id_";
		List<Role> roles = this.jdbcTemplate.query(sql, new Object[] { id },
				new RowMapper<Role>() {

					@Override
					public Role mapRow(ResultSet result, int i)
							throws SQLException {
						// TODO Auto-generated method stub
						Role role = new Role();
						role.setRole_Name(result.getString("role_name"));
						role.setMs(result.getString("ms"));
						return role;
					}

				});

		return this.chackRolePower(roles);
	}

	private Role chackRolePower(List<Role> roles) {
		Role aRole = null;
		if (aRole == null) {
			for (Role role : roles) {
				if ("SuAdmin".equals(role.getRole_Name())) {// 超级管理员
					aRole = role;
				}
			}
		}
		if (aRole == null) {
			for (Role role : roles) {
				if ("DeptAdmin".equals(role.getRole_Name())) {// 部门管理员
					aRole = role;
				}
			}
		}
		if (aRole == null) {
			aRole = roles.get(0);
			aRole.setRole_Name("Other");
		}
		return aRole;
	}

	private List<Object> rebuileSqlByConditionAndRole(StringBuffer sql,
			Map<String, Object> param) {
		List<Object> args = new ArrayList<Object>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Integer deptid = (Integer) param.get("deptid");
		Integer userId = (Integer) param.get("userid");
		Date begin = (Date) param.get("begin");
		Date end = (Date) param.get("end");
		String dept = (String) param.get("dept");
		String user = (String) param.get("user");
		String content = (String) param.get("content");
		if(deptid != null){
			
			if(deptid != 1){
				sql.append(" and a.deptid in ( ");
				sql.append(" select a.id_ from dept a where find_in_set(a.id_,queryChildrenAreaInfo(?)) ");
				sql.append(" ) ");
				args.add(deptid);
			}else if(deptid == 1){
				sql.append(" and a.deptid != 2 ");
				sql.append(" and a.deptid != 307 ");
			}
			
		}
		if (userId != null || user != null) {
			sql.append(" and a.user_id in (select id_ from user where user_name like ?) ");
			args.add("%"+user+"%");
		}
		if (begin != null) {
			sql.append(" and a.create_date >= date_format(?,'%Y%m%d')");
			args.add(sdf.format(begin));
		}
		if (end != null) {
			sql.append(" and a.create_date <= date_format(?,'%Y%m%d')");
			args.add(sdf.format(end));
		}
		if (content != null) {
			sql.append(" and a.zskly_id in ");
			sql.append("(select id_ from zskly where title like ? or content like ?) ");
			args.add("%"+content+"%");
			args.add("%"+content+"%");
		}
		return args;
	}

	@Override
	public void addYxzskToZszsk(Zszsk zszsk) {
		// TODO Auto-generated method stub
		int count = this.chackRecordExistOrNot(zszsk);
		if (count != 0) {
			String sql = "update zszsk set yxbz='Y' where id_=?";
			this.jdbcTemplate.update(sql, new Object[] { zszsk.getId() });
			sql = "update yxzsk set xybz='Y' where id_=?";
			this.jdbcTemplate.update(sql, new Object[] { zszsk.getId() });
		} else {
			String sql = "update yxzsk set xybz='Y' where id_=?";
			this.jdbcTemplate.update(sql, new Object[] { zszsk.getId() });
		}
	}

	private Integer chackRecordExistOrNot(Zszsk zszsk) {
		String sql = "select count(*) from zszsk where id_=?";
		int count = this.jdbcTemplate.queryForObject(sql,
				new Object[] { zszsk.getId() }, Integer.class);
		return count;
	}

	@Override
	public void addZszsk(ZskJl zszsk) {
		// TODO Auto-generated method stub
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String sql = "insert into zsk_jl values(?,?,?,?,?,?,?,?,?,?,?)";
		int zsklyid = this.getZsklyInfoOrAddZskly(zszsk.getZskly(), zszsk.getZsklyContent());
		Object[] objs = { zszsk.getId(), zszsk.getUserId(),
				sdf.format(zszsk.getCreateDate()), zszsk.getSpDate(),
				zszsk.getSprId(), zszsk.getDeptid(), zszsk.getContent(),
				zsklyid, zszsk.getTitle(), "Y", "Y" };//zszsk.getZsklyId()
		this.jdbcTemplate.update(sql, objs);
	}
	
	private int getZsklyInfoOrAddZskly(String title, String content){
		List<Object> args = new ArrayList<Object>();
		StringBuffer sql = new StringBuffer("select if(count(*),id_,0) from zskly where title=? ");
		args.add(title);
		if(content != null){
			sql.append(" and content=? ");
			args.add(content);
		}
		int zsklyid = this.jdbcTemplate.queryForObject(sql.toString(), args.toArray(), Integer.class);
		if(zsklyid == 0){
			zsklyid = this.addZskly(title, content);
		}
		return zsklyid;
	}
	
	private int addZskly(String title, String content) {
		String sql = "insert into zskly values(?,?,?,?)";
		return this.insertAndGetKeyByJdbc(sql,
				new Object[] { null, title, content, null },
				new String[] { "id_" }).intValue();
	}

	@Override
	public void addGrDeptGxJl(ZskJl zszsk) {
		// TODO Auto-generated method stub
		String sql = "insert into zsk_gxjl values(?,?,?,?,?,?,?)";
		Object[] objs = { zszsk.getContent(), zszsk.getDeptid(),
				zszsk.getUserId(), zszsk.getId(), this.getGxjlValueByKey("GxzB2"), 2,
				zszsk.getCreateDate() };
		this.jdbcTemplate.update(sql, objs);
	}

	@Override
	public void addGxjl(ZskJl zszsk) {
		// TODO Auto-generated method stub
		String sql = "insert into zsk_gxjl values(?,?,?,?,?,?,?)";
		Object[] objs = { zszsk.getContent(), zszsk.getDeptid(),
				zszsk.getUserId(), zszsk.getId(), this.getGxjlValueByKey("GxzA2"), 1,
				zszsk.getCreateDate() };
		this.jdbcTemplate.update(sql, objs);
	}
	
	private String getGxjlValueByKey(String key){
		String sql = "select value from system_props where key_=?";
		return this.jdbcTemplate.queryForObject(sql, new Object[]{key}, String.class);
	}

	@Override
	public void updateZszsk(ZskJl zszsk) {
		// TODO Auto-generated method stub
		StringBuffer sql = new StringBuffer("update zsk_jl set ");
			sql.append("user_id=?,create_date=?,sp_date=?,");
			sql.append("spr_id=?,deptid=?,content=?,zskly_id=?,title=?,xybz=? ");
			sql.append("where id_=?");
		Object[] objs = { zszsk.getUserId(), zszsk.getCreateDate(),
				zszsk.getSpDate(), zszsk.getSprId(), zszsk.getDeptid(),
				zszsk.getContent(), zszsk.getZsklyId(), zszsk.getTitle(),
				zszsk.getXybz(), zszsk.getId() };
		this.jdbcTemplate.update(sql.toString(), objs);
	}

	@Override
	public void deleteYxzskFormZszsk(Zszsk zszsk) {
		// TODO Auto-generated method stub
		String sql = "update yxzsk set xybz='N' where id_=?";
		this.jdbcTemplate.update(sql, new Object[] { zszsk.getId() });
	}

	@Override
	public void deleteZszsk(ZskJl zszsk) {
		// TODO Auto-generated method stub
		String sql = "update zsk_jl set xybz='N' where id_=?";
		this.jdbcTemplate.update(sql, new Object[] { zszsk.getId() });
	}

	@Override
	public void deleteGrDeptGxJl(ZskJl zszsk) {
		// TODO Auto-generated method stub
		String sql = "delete from zsk_gxjl where zsk_id=? and gxly=?";
		Object[] objs = { zszsk.getId(), 2 };
		this.jdbcTemplate.update(sql, objs);
	}

	@Override
	public void getYxzskInfo(Page<ZskJl> page, Map<String, Object> param) {
		// TODO Auto-generated method stub
		int pageSize = page.getPageSize();
		int pageNow = page.getPageNo();
		StringBuffer count = new StringBuffer("select count(*) from zsk_jl a,user b,dept c,zskly d");
				count.append(" where a.USER_ID = b.ID_ and a.DEPTID = c.ID_ and a.ZSKLY_ID = d.ID_ and yxbz='Y' and xybz='N'");
		int entityCount = this.jdbcTemplate
				.queryForObject(count.toString(), Integer.class);
		List<ZskJl> list = this
				.getYxzskInfo(pageSize * (pageNow - 1), pageSize);
		page.setEntityCount(entityCount);
		page.setEntities(list);
	}

	private List<ZskJl> getYxzskInfo(int begin, int offest) {
		List<Object> args = new ArrayList<Object>();
		StringBuffer sql = new StringBuffer(" select d.TITLE zsktitle,b.USER_NAME,c.DEPT_NAME,a.* ");
			sql.append(" from zsk_jl a,user b,dept c,zskly d ");
			sql.append(" where a.USER_ID = b.ID_ and a.DEPTID = c.ID_ and a.ZSKLY_ID = d.ID_ ");
			sql.append(" and a.yxbz='Y' ");
		if (offest == -1 && begin == -1) {
			sql.append(" and a.xybz='Y' ");
		} else {
			sql.append(" and a.xybz='N' limit ?,?");
			args.add(begin);
			args.add(offest);
		}
		List<ZskJl> list = this.jdbcTemplate.query(sql.toString(), args.toArray(), new RowMapper<ZskJl>() {

			@Override
			public ZskJl mapRow(ResultSet result, int i) throws SQLException {
				// TODO Auto-generated method stub
				ZskJl yxzsk = new ZskJl();
				yxzsk.setZskly(result.getString("zsktitle"));
				yxzsk.setDept(result.getString("DEPT_NAME"));
				yxzsk.setUser(result.getString("USER_NAME"));
				yxzsk.setId(result.getString("id_"));
				yxzsk.setUserId(result.getInt("user_id"));
				yxzsk.setCreateDate(result.getDate("create_date"));
				yxzsk.setSpDate(result.getDate("sp_date"));
				yxzsk.setSprId(result.getInt("spr_id"));
				yxzsk.setDeptid(result.getInt("deptid"));
				yxzsk.setContent(result.getString("content"));
				yxzsk.setZsklyId(result.getInt("zskly_id"));
				yxzsk.setTitle(result.getString("title"));
				yxzsk.setYxbz(result.getString("yxbz"));
				yxzsk.setXybz(result.getString("xybz"));
				return yxzsk;
			}

		});
		return list;
	}

	List<ZskJl> randomList = new ArrayList<ZskJl>();
	@Override
	public void getRandomdsZszskFilter(Page<ZskJl> page,
			Map<String, Object> param) {
		// TODO Auto-generated method stub
		int pageSize = page.getPageSize();
		int pageNow = page.getPageNo();
		if(pageNow == 1){
			Integer value = this.getZsdtsInfoFromSystemProps();
			List<ZskJl> list = this.getYxzskInfo(-1, -1);
			if(list.size() > 0){
				Collections.shuffle(list);
				randomList = new ArrayList<ZskJl>();
				for (int i = 0; i < (value > list.size() ? list.size() : value); i++) {
					randomList.add(list.get(i));
				}
			}
		}
		List<ZskJl> pageList = new ArrayList<ZskJl>();
		for (int i = (pageNow - 1) * pageSize; i < (pageNow * pageSize > randomList
				.size() ? randomList.size() : pageNow * pageSize); i++) {
			pageList.add(randomList.get(i));
		}

		page.setEntityCount(randomList.size());
		page.setEntities(pageList);
	}

	@Override
	public void updateZsdtsInfo(Map<String, Object> param,
			CustomUserDetails userDetails) {
		// TODO Auto-generated method stub
		List<String> ids = (List<String>) param.get("id");
		if (ids.size() > 0) {
			Integer groupId = (Integer) param.get("groupId");
			String ms = (String) param.get("ms");
			Date begin = (Date) param.get("begin");
			Date end = (Date) param.get("end");
			
			String sql = "insert into zsdtsjl values(?,?,?,?)";
			int jlId = this.insertAndGetKeyByJdbc(sql,
					new Object[] { null, userDetails.getId(), new Date(), ms },
					new String[] { "id_" }).intValue();
			sql = "insert into zsktsqz values(?,?,?)";
			this.jdbcTemplate.update(sql, new Object[] { null, groupId, jlId });
			for (String id : ids) {
				sql = "insert into zsktsnr values(?,?,?)";
				this.jdbcTemplate.update(sql, new Object[] { null, jlId, id });
			}
		}
	}
	
	@Override
	public void updateZsdtsDetailInfo(Map<String, Object> param) {
		// TODO Auto-generated method stub
		List<String> ids = (List<String>) param.get("id");
		if(ids.size() > 0){
			Integer groupId = (Integer) param.get("groupId");
			String ms = (String) param.get("ms");
			Integer jlid = (Integer) param.get("jlid");
			
			String sql = "update zsdtsjl set ms=? where id_=?";
			this.jdbcTemplate.update(sql, new Object[]{ ms, jlid });
			sql = "update zsktsqz set group_id = ? where tsjlid=?";
			this.jdbcTemplate.update(sql, new Object[]{ groupId, jlid });
			sql = "delete from zsktsnr where tsjlid = ?";
			this.jdbcTemplate.update(sql, new Object[]{ jlid });
			for (String id : ids) {
				sql = "insert into zsktsnr values(?,?,?)";
				this.jdbcTemplate.update(sql, new Object[] { null, jlid, id });
			}
		}
	}

	@Override
	public Collection<Zskly> getZsklyInfo() {
		// TODO Auto-generated method stub
		String sql = "select id_,title,content,attachment from zskly order by id_ desc";
		List<Zskly> list = this.jdbcTemplate.query(sql, new RowMapper<Zskly>(){

			@Override
			public Zskly mapRow(ResultSet result, int i) throws SQLException {
				// TODO Auto-generated method stub
				Zskly zskly = new Zskly();
				zskly.setId(result.getInt("id_"));
				zskly.setTitle(result.getString("title"));
				zskly.setContent(result.getString("content"));
				zskly.setAttachment(result.getString("attachment"));
				return zskly;
			}
			
		});
		return list;
	}

	@Override
	public void addZskly(Zskly zskly) {
		// TODO Auto-generated method stub
		String sql = "insert into zskly values(?,?,?,?)";
		this.jdbcTemplate.update(sql, new Object[]{null,zskly.getTitle(),zskly.getContent(),zskly.getAttachment()});
	}

	@Override
	public void updateZskly(Zskly zskly) {
		// TODO Auto-generated method stub
		String sql = "select attachment from zskly where id_=?";
		String attachment = this.jdbcTemplate.queryForObject(sql, new Object[]{zskly.getId()}, String.class);
		if(attachment != null && !attachment.equals(zskly.getAttachment())){
			AttachmentOpt.deleteAttachmentFile(attachment);
		}
		sql = "update zskly set title=?,content=?,attachment=? where id_=?";
		this.jdbcTemplate.update(sql, new Object[]{zskly.getTitle(),zskly.getContent(),zskly.getAttachment(),zskly.getId()});
	}

	@Override
	public void deleteZskly(Zskly zskly) {
		// TODO Auto-generated method stub
		String sql = "delete from zskly where id_=?";
		this.jdbcTemplate.update(sql, new Object[]{zskly.getId()});
	}

	@Override
	public String importAttachment(Map<String, Object> param) {
		// TODO Auto-generated method stub
		
		String path = (String) param.get("path");
		String id = (String) param.get("id");
		String sql = "select attachment from zskly where id_=?";
		String attachment = this.jdbcTemplate.queryForObject(sql, new Object[]{id}, String.class);
		if(attachment != null){
			AttachmentOpt.deleteAttachmentFile(attachment);
		}
		sql = "update zskly set attachment=? where id_=?";
		this.jdbcTemplate.update(sql, new Object[]{path,id});
		return null;
	}

	@Override
	public void cancelUploadAttachmentFile(String param) {
		// TODO Auto-generated method stub
		String sql = "select if(count(*),1,0) from zskly where attachment=?";
		int count = this.jdbcTemplate.queryForObject(sql, new Object[]{param}, Integer.class);
		if(count == 0){
			AttachmentOpt.deleteAttachmentFile(param);
		}
	}

	@Override
	public Integer getZsdtsInfoFromSystemProps() {
		// TODO Auto-generated method stub
		String sql = "select value from system_props where key_='zsdts'";
		Integer value = Integer.parseInt(this.jdbcTemplate.queryForObject(sql,
				String.class));
		return value;
	}

	@Override
	public void getTsxxInfo(Page page, Map<String, Object> param) {
		// TODO Auto-generated method stub
		int pageNow = page.getPageNo();
		int pageSize = page.getPageSize();
		StringBuffer sqlWhere = new StringBuffer(" from zsdtsjl jl,zsktsqz qz,grouptable g,user u ");
			sqlWhere.append("where jl.id_ = qz.tsjlid and qz.group_id = g.id_ and jl.tsrid = u.id_ ");
		StringBuffer sql = new StringBuffer("select u.user_name tsr,jl.tsrq,g.group_name groupname,g.id_ groupId,jl.ms,jl.id_ jlid ");
		StringBuffer sqlCount = new StringBuffer("select count(*)");
		int count = this.jdbcTemplate.queryForObject(sqlCount.append(sqlWhere).toString(), Integer.class);
		List<Map<String,Object>> list = this.jdbcTemplate.queryForList(sql.append(sqlWhere).append("order by jl.tsrq desc limit ?,?").toString(),new Object[]{pageSize * (pageNow - 1),pageSize});
		page.setEntityCount(count);
		page.setEntities(list);
	}

	@Override
	public void getZsdDetailInfo(Page page, Map<String, Object> param) {
		// TODO Auto-generated method stub
		int pageNow = page.getPageNo();
		int pageSize = page.getPageSize();
		int id = (int) param.get("id");
		StringBuffer sqlWhere = new StringBuffer(" from zsk_jl jl, zskly ly, dept d, user u ,zsktsnr nr, zsdtsjl z ");
			sqlWhere.append("where jl.zskly_id = ly.id_ and jl.user_id = u.id_ and jl.deptid = d.id_ ");
			sqlWhere.append("and nr.zskid = jl.id_ and z.id_ = nr.tsjlid and z.id_ = ? ");
		StringBuffer sql = new StringBuffer("select jl.id_ id,jl.create_date,jl.content,ly.title zsklyname,u.user_name username,d.dept_name deptname,jl.title");
		StringBuffer sqlCount = new StringBuffer("select count(*)");
		int count = this.jdbcTemplate.queryForObject(sqlCount.append(sqlWhere).toString(), new Object[]{id}, Integer.class);
		List<Map<String, Object>> list = this.jdbcTemplate.queryForList(sql.append(sqlWhere).append("limit ?,?").toString(),new Object[]{id,pageSize * (pageNow - 1),pageSize});
		page.setEntityCount(count);
		page.setEntities(list);
	}

	@Override
	public void deleteZsdtsInfo(String jlid) {
		// TODO Auto-generated method stub
		String sql = "delete from zsdtsjl where id_=?";
		this.jdbcTemplate.update(sql, new Object[]{jlid});
		sql = "delete from zsktsnr where tsjlid=?";
		this.jdbcTemplate.update(sql, new Object[]{jlid});
		sql = "delete from zsktsqz where tsjlid=?";
		this.jdbcTemplate.update(sql, new Object[]{jlid});
	}

	@Override
	public void updateTsxxInfo(Map param) {
		// TODO Auto-generated method stub
		Integer groupId = (Integer) param.get("groupId");
		String ms = (String) param.get("ms");
		Integer jlid = (Integer) param.get("jlid");
			
		String sql = "update zsdtsjl set ms=? where id_=?";
		this.jdbcTemplate.update(sql, new Object[]{ ms, jlid });
		sql = "update zsktsqz set group_id = ? where tsjlid=?";
		this.jdbcTemplate.update(sql, new Object[]{ groupId, jlid });
	}

}
