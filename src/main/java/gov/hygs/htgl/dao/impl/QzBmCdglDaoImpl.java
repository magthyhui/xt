package gov.hygs.htgl.dao.impl;

import gov.hygs.htgl.dao.QzBmCdglDao;
import gov.hygs.htgl.entity.Dept;
import gov.hygs.htgl.entity.Grouptable;
import gov.hygs.htgl.entity.Menu;
import gov.hygs.htgl.entity.SystemProps;
import gov.hygs.htgl.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.bstek.dorado.data.provider.Page;
import com.gdky.restfull.dao.BaseJdbcDao;

@Repository
public class QzBmCdglDaoImpl extends BaseJdbcDao implements QzBmCdglDao {

	@Override
	public List<Dept> getDeptRoot(Map<String,Object> param) {
		// TODO Auto-generated method stub
		StringBuffer sql = new StringBuffer();
		List<Map<String, Object>> list = null;
		if(param == null){
			//超级管理员
			sql.append("select ID_,DEPT_NAME,PARENT_ID,MS from dept t where t.parent_id is null");
			list = this.jdbcTemplate.queryForList(sql.toString());
		}else{
			//其他管理员
			Integer deptid = (Integer) param.get("id");
			sql.append(" select ID_,DEPT_NAME,PARENT_ID,MS from dept where id_=( ");
			sql.append(" select ifnull((select parent_id from dept where id_ = ?),?) from dual ");
			sql.append(" ) ");
			list = this.jdbcTemplate.queryForList(sql.toString(), new Object[]{deptid,deptid});
		}
		return this.mapToDeptList(list);
	}

	@Override
	public List<Dept> getCurrentDeptById(String id_) {
		if(id_ == null)
			return this.getDeptRoot(null);
		if(id_.contains("s")){
			Map<String, Object> param = new HashMap<String,Object>();
			param.put("id", Integer.parseInt(id_.substring(0, id_.length()-1)));
			return this.getDeptRoot(param);
		}
			
		String sql = "select ID_,DEPT_NAME,PARENT_ID,MS from dept t where t.parent_id=?";
		Object[] objs = { id_ };
		List<Map<String, Object>> list = this.jdbcTemplate.queryForList(sql,
				objs);
		return this.mapToDeptList(list);
	}

	@Override
	public void getCurrentDeptPageById(Page<Dept> page, String id_) {
		// TODO Auto-generated method stub
		int pageSize = page.getPageSize();
		int pageNow = page.getPageNo();
		page.setEntityCount(this.getDeptInfoCount(id_));
		page.setEntities(this.getCurrentDeptPageById(id_, (pageNow - 1)
				* pageSize, pageSize));
	}

	@Override
	public void saveDeptNodeInfo(Dept dept) {
		// TODO Auto-generated method stub
		String sql = "insert into dept values(?,?,?,?)";
		Object[] objs = { dept.getId_(), dept.getDept_name(),
				dept.getParentId(), dept.getMs() };
		this.jdbcTemplate.update(sql, objs);

	}

	@Override
	public void deleteDeptNodeInfo(String id) {
		// TODO Auto-generated method stub
		String sql = "delete from dept where id_=?";
		Object[] objs = { id };
		this.jdbcTemplate.update(sql, objs);
	}

	@Override
	public void updateDeptNodeInfo(Dept dept) {
		// TODO Auto-generated method stub
		String sql = "update dept t set t.dept_name=?,t.ms=? where t.id_=?";
		Object[] objs = { dept.getDept_name(), dept.getMs(), dept.getId_() };
		this.jdbcTemplate.update(sql, objs);
	}

	@Override
	public String checkDeptName(String param) {
		// TODO Auto-generated method stub
		String sql = "select * from dept where dept_name=?";
		Object[] objs = { param };
		List<Map<String, Object>> list = this.jdbcTemplate.queryForList(sql,
				objs);
		return list.size() > 0 ? "部门名称'" + param + "'已存在" : null;
	}

	private int getDeptInfoCount(String id_) {
		String sql = "select count(*) as count from dept t where t.parent_id=?";
		Object[] objs = { id_ };
		Map<String, Object> map = this.jdbcTemplate.queryForMap(sql, objs);
		long countLong = (long) map.get("count");
		int count = (int) countLong;
		return count;
	}

	private List<Dept> getCurrentDeptPageById(String id_, int begin, int offest) {
		String sql = "select * from dept t where t.parent_id=? limit ?,?";
		Object[] objs = { id_, begin, offest };
		List<Map<String, Object>> list = this.jdbcTemplate.queryForList(sql,
				objs);
		return this.mapToDeptList(list);
	}

	private List<Dept> mapToDeptList(List<Map<String, Object>> list) {
		List<Dept> depts = new ArrayList<Dept>();
		Map<String, Object> map = null;
		if (list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				Dept dept = new Dept();
				map = list.get(i);
				if (map.get("id_") != null) {
					dept.setId_((Integer) map.get("id_"));
				}

				if (map.get("dept_name") != null) {
					dept.setDept_name((String) map.get("dept_name"));
				}

				if (map.get("parent_id") != null) {
					dept.setParentId((Integer) map.get("parent_id"));
				}

				if (map.get("ms") != null) {
					dept.setMs((String) map.get("ms"));
				}
				depts.add(dept);
			}
		}
		return depts;
	}

	@Override
	public Collection<Menu> getMenuRoot() {
		// TODO Auto-generated method stub
		String sql = "select ID_,PARENT_ID,MENU_NAME,URL,YXBZ from menu m where m.parent_id is null";
		List<Map<String, Object>> list = this.jdbcTemplate.queryForList(sql);
		return this.mapToObject(list);
	}

	@Override
	public Collection<Menu> getCurrentMenuById(String id) {
		// TODO Auto-generated method stub
		String sql = "select ID_,PARENT_ID,MENU_NAME,URL,YXBZ from menu m where m.parent_id=?";
		Object[] obs = { id };
		List<Map<String, Object>> list = this.jdbcTemplate.queryForList(sql,
				obs);
		return this.mapToObject(list);
	}

	@Override
	public void getCurrentMenuPageById(Page<Menu> page, String id_) {
		// TODO Auto-generated method stub
		int pageSize = page.getPageSize();
		int pageNow = page.getPageNo();
		page.setEntityCount(this.getCount(id_));
		page.setEntities(this.getCurrentMenuPageById(id_, (pageNow - 1)
				* pageSize, pageSize));
	}

	@Override
	public String saveMenuNodeInfo(Menu menu) {
		// TODO Auto-generated method stub
		String result = this.checkMenuName(menu.getMenu_Name());
		if(result == null){
			String sql = "insert into menu values(?,?,?,?,?)";
			Object[] objs = { menu.getId_(), menu.getParent_Id(),
					menu.getMenu_Name(), menu.getUrl(), "Y" };
			this.jdbcTemplate.update(sql, objs);
		}
		return result;
	}

	@Override
	public String updateMenuNodeInfo(Menu menu) {
		// TODO Auto-generated method stub
		String chackMenuName = "select if(count(*),id_,0) from menu where menu_name = ?";
		Integer id = this.jdbcTemplate.queryForObject(chackMenuName, Integer.class, new Object[]{ menu.getMenu_Name() });
		if(id == 0 || id == menu.getId_()){
			String sql = "update menu m set m.menu_name=?,m.url=?,m.yxbz=? where id_=?";
			Object[] objs = { menu.getMenu_Name(), menu.getUrl(), menu.getYxbz(),
					menu.getId_() };
			this.jdbcTemplate.update(sql, objs);
			return null;
		}else{
			return "部门名称'" + menu.getMenu_Name() + "'已存在";
		}
	}

	@Override
	public void deleteMenuNodeInfo(String id) {
		// TODO Auto-generated method stub
		String sql = "delete from menu where id_=?";
		Object[] objs = { id };
		this.jdbcTemplate.update(sql, objs);
	}

	@Override
	public String checkMenuName(String param) {
		// TODO Auto-generated method stub
		String sql = "select * from menu where menu_name=?";
		Object[] objs = { param };
		List<Map<String, Object>> list = this.jdbcTemplate.queryForList(sql,
				objs);
		return list.size() > 0 ? "部门名称'" + param + "'已存在" : null;
	}

	private int getCount(String id_) {
		String sql = "select count(*) as count from menu m where m.parent_id=?";
		Object[] objs = { id_ };
		Map<String, Object> map = this.jdbcTemplate.queryForMap(sql, objs);
		long countLong = (long) map.get("count");
		int count = (int) countLong;
		return count;
	}

	private List<Menu> getCurrentMenuPageById(String id_, int begin, int offest) {
		String sql = "select * from menu m where m.parent_id=? limit ?,?";
		Object[] objs = { id_, begin, offest };
		List<Map<String, Object>> list = this.jdbcTemplate.queryForList(sql,
				objs);
		return this.mapToObject(list);
	}

	private List<Menu> mapToObject(List<Map<String, Object>> list) {
		List<Menu> menus = new ArrayList<Menu>();
		Map<String, Object> map = null;
		if (list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				Menu menu = new Menu();
				map = list.get(i);
				if (map.get("id_") != null) {
					menu.setId_((Integer) map.get("id_"));
				}
				if (map.get("parent_id") != null) {
					menu.setParent_Id((Integer) map.get("parent_id"));
				}
				if (map.get("menu_name") != null) {
					menu.setMenu_Name((String) map.get("menu_name"));
				}
				if (map.get("url") != null) {
					menu.setUrl((String) map.get("url"));
				}
				if (map.get("yxbz") != null) {
					menu.setYxbz((String) map.get("yxbz"));
				}
				menus.add(menu);
			}
		}
		return menus;
	}

	@Override
	public Collection<Grouptable> getGrouptableInfo() {
		// TODO Auto-generated method stub
		String sql = "select id_,group_name,ms,parent_id,pxh,lrr_id,effective_date from grouptable where parent_id=0 and (now()<=effective_date or effective_date is null)  order by pxh";
		List<Grouptable> groups = this.jdbcTemplate.query(sql,
				new RowMapper<Grouptable>() {

					@Override
					public Grouptable mapRow(ResultSet result, int i)
							throws SQLException {
						// TODO Auto-generated method stub
						Grouptable group = new Grouptable();
						group.setId(result.getInt("id_"));
						group.setGroupName(result.getString("group_name"));
						group.setMs(result.getString("ms"));
						group.setParentId(result.getInt("parent_id"));
						group.setPxh(result.getInt("pxh"));
						group.setLrrID(result.getInt("lrr_id"));
						group.setEffective_date(result.getTimestamp("effective_date"));
						return group;
					}

				});
		return groups;
	}

	@Override
	public void getUserInfo(Page page, Map<String, Object> param) {
		List<Object> args = new ArrayList<Object>();
		StringBuffer sqlCount = new StringBuffer("select count(*) from user where 1=1 ");
		StringBuffer sql = new StringBuffer("select * from user where 1=1 ");
		if(param != null){
			Integer deptid = (Integer) param.get("deptid");
			Integer parentid = (Integer) param.get("parentid");
			String username = (String) param.get("username");
			if(deptid != null ){
				if(deptid != 1){
					sqlCount.append(" and deptid in( ");
					sqlCount.append(" select a.id_ from dept a where find_in_set(a.id_,queryChildrenAreaInfo(?))) ");
					sql.append(" and deptid in( ");
					sql.append(" select a.id_ from dept a where find_in_set(a.id_,queryChildrenAreaInfo(?))) ");
					args.add(deptid);
				}else if(deptid == 1){
					sqlCount.append(" and deptid != 2 and deptid != 307 ");
					sql.append(" and deptid != 2 and deptid != 307 ");
				}
			}
			if(username != null){
				sqlCount.append(" and user_name like ? ");
				sql.append(" and user_name like ? ");
				args.add("%"+username+"%");
			}
			
		}
		
		sql.append(" limit ?,? ");
		int entityCount = this.jdbcTemplate.queryForObject(sqlCount.toString(),args.toArray(),
				Integer.class);
		args.add(page.getPageSize() * (page.getPageNo() - 1));
		args.add(page.getPageSize());
		List<Map<String, Object>> list = this.jdbcTemplate.queryForList(sql.toString(),
				args.toArray());
		page.setEntityCount(entityCount);
		page.setEntities(list);
	}

	@Override
	public Collection<User> getUserByGroupId(Object id) {
		// TODO Auto-generated method stub
		StringBuffer sql = new StringBuffer("select a.* from user a,user_group b,grouptable c ");
				sql.append("where a.id_=b.user_id and b.group_id=c.id_ and c.id_=?");
		Object[] objs = { id };
		List<User> users = this.jdbcTemplate.query(sql.toString(), objs,
				new RowMapper<User>() {

					@Override
					public User mapRow(ResultSet result, int i)
							throws SQLException {
						// TODO Auto-generated method stub
						User user = new User();
						user.setId_(result.getInt("id_"));
						user.setLogin_Name(result.getString("login_name"));
						user.setUser_Name(result.getString("user_name"));
						user.setPhone(result.getString("phone"));
						user.setRzsj(result.getDate("rzsj"));
						user.setZw(result.getString("zw"));
						user.setPwd(result.getString("pwd"));
						user.setPhoto(result.getString("photo"));
						user.setDeptid(result.getInt("deptid"));
						user.setBirthday(result.getDate("birthday"));
						return user;
					}

				});
		return users;
	}

	@Override
	public void addUser(User user) {
		// TODO Auto-generated method stub
		int userId = user.getId_();
		int groupId = user.getDeptid();
		String sql = "insert into user_group (id_,user_id,group_id) values(?,?,?)";
		Object[] objs = new Object[] { null, userId, groupId };
		this.jdbcTemplate.update(sql, objs);
	}

	@Override
	public void deleteUser(User user) {
		int groupId = user.getDeptid();
		int userId = user.getId_();
		String sql = "delete from user_group where group_id=? and user_id=?";
		Object[] objs = { groupId, userId };
		this.jdbcTemplate.update(sql, objs);
	}

	@Override
	public void updataUser(User user) {
		// TODO Auto-generated method stub
		StringBuffer sql = new StringBuffer("update user set ");
			sql.append("login_name=?,user_name=?,phone=?,rzsj=?,");
				sql.append("zw=?,pwd=?,photo=?,deptid=?,birthday=? where id_=?");
		Object[] objs = { user.getLogin_Name(), user.getUser_Name(),
				user.getPhone(), user.getRzsj(), user.getZw(), user.getPwd(),
				user.getPhoto(), user.getDeptid(), user.getBirthday(),
				user.getId_() };
		this.jdbcTemplate.update(sql.toString(), objs);
	}

	@Override
	public void addGroup(Grouptable group) {
		// TODO Auto-generated method stub
		String sql = "insert into grouptable values(?,?,?,?,?,?,?)";
		Object[] objs = { group.getId(), group.getGroupName(), group.getMs(), group.getParentId(),group.getPxh(),group.getLrrID(),group.getEffective_date()};
		this.jdbcTemplate.update(sql, objs);
	}

	@Override
	public void updataGroup(Grouptable group) {
		// TODO Auto-generated method stub
		String sql = "update grouptable g set g.group_name=?,g.ms=?,g.parent_id=?,g.pxh=?,g.effective_date = ? where g.id_=?";
		Object[] objs = { group.getGroupName(), group.getMs(), group.getParentId(), group.getPxh(),group.getEffective_date(), group.getId() };
		this.jdbcTemplate.update(sql, objs);
	}

	@Override
	public void deleteGroup(Grouptable group) {
		List<Grouptable> list = (List<Grouptable>) this.getCurrentGroupById(group.getId().toString());
		String sql = "delete from grouptable where id_=?";
		Object[] objs = { group.getId() };
		this.jdbcTemplate.update(sql, objs);
		sql = "delete from user_group where group_id=?";
		this.jdbcTemplate.update(sql, objs);
		for(Grouptable child : list){
			this.deleteGroup(child);
		}
	}

	@Override
	public String checkGroupName(String param) {
		// TODO Auto-generated method stub
		String sql = "select * from grouptable where (now()<=effective_date or effective_date is null) and group_name=?";
		Object[] objs = { param };
		List<Map<String, Object>> list = this.jdbcTemplate.queryForList(sql,
				objs);
		return list.size() > 0 ? "群名称'" + param + "'已存在" : null;
	}

	@Override
	public void getSystemPropsInfo(Page page, Map<String, Object> param) {
		// TODO Auto-generated method stub
		String sqlCount = "select count(*) from system_props";
		int entityCount = this.jdbcTemplate.queryForObject(sqlCount,
				Integer.class);
		String sql = "select ID_,KEY_,VALUE,MS from system_props limit ?,?";

		List<SystemProps> list = this.jdbcTemplate.query(sql,
				new Object[] { page.getPageSize() * (page.getPageNo() - 1),
						page.getPageSize() }, new RowMapper<SystemProps>() {

					@Override
					public SystemProps mapRow(ResultSet result, int i)
							throws SQLException {
						// TODO Auto-generated method stub
						SystemProps sp = new SystemProps();
						sp.setId(result.getInt("id_"));
						sp.setKey(result.getString("key_"));
						sp.setValue(result.getString("value"));
						sp.setMs(result.getString("ms"));
						return sp;
					}
				});
		page.setEntityCount(entityCount);
		page.setEntities(list);
	}

	@Override
	public void addSystemProps(SystemProps sp) {
		// TODO Auto-generated method stub
		String sql = "insert into system_props values(?,?,?,?)";
		Object[] obj = new Object[] { sp.getId(), sp.getKey(), sp.getValue(),
				sp.getMs() };
		this.jdbcTemplate.update(sql, obj);
	}

	@Override
	public void updateSystemProps(SystemProps sp) {
		// TODO Auto-generated method stub
		String sql = "update system_props set key_=?,value=?,ms=? where id_=?";
		Object[] obj = new Object[] { sp.getKey(), sp.getValue(), sp.getMs(),
				sp.getId() };
		this.jdbcTemplate.update(sql, obj);
	}

	@Override
	public void deleteSystemProps(SystemProps sp) {
		// TODO Auto-generated method stub
		String sql = "delete from system_props where id_=?";
		this.jdbcTemplate.update(sql, new Object[] { sp.getId() });
	}

	@Override
	public void addUserInfoToGroup(Map<String, Object> param) {
		// TODO Auto-generated method stub
		int groupId = (int) param.get("groupId");
		List<Integer> ids = (List<Integer>) param.get("ids");
		String flag = (String) param.get("add");
		if(flag == null){
			for(Integer userId : ids){
				String sql = "insert into user_group (id_,user_id,group_id) values(?,?,?)";
				this.jdbcTemplate.update(sql, new Object[]{ null, userId, groupId });
			}
		}else{
			this.addAllDeptToGroup(ids,groupId);
		}
	}
	
	private void addAllDeptToGroup(List<Integer> ids, Integer groupId){
		if(ids != null){
			for(Integer deptid : ids){
				String sql = "select u.id_ from user u where u.DEPTID = ? and u.ID_ not in (select user_id from user_group where group_id = ?)";
				List<Integer> list = this.jdbcTemplate.queryForList(sql, Integer.class, new Object[]{deptid,groupId});
				if(list != null){
					for(Integer userInfo : list){
						sql = "insert into user_group (id_,user_id,group_id) values(?,?,?)";
						this.jdbcTemplate.update(sql, new Object[]{ null, userInfo, groupId });
					}
				}
				sql = "select id_ from dept where parent_id=?";
				this.addAllDeptToGroup(this.jdbcTemplate.queryForList(sql, Integer.class, new Object[]{deptid}), groupId);
			}
		}
	}

	@Override
	public Collection<Grouptable> getCurrentGroupById(String id) {
		// TODO Auto-generated method stub
		String sql = "select id_,group_name,parent_id,ms,pxh from grouptable where (now()<=effective_date or effective_date is null) and parent_id=? order by pxh";
		List<Grouptable> list = this.jdbcTemplate.query(sql, new Object[]{id}, new RowMapper<Grouptable>(){

			@Override
			public Grouptable mapRow(ResultSet result, int i)
					throws SQLException {
				// TODO Auto-generated method stub
				Grouptable grouptable = new Grouptable();
				grouptable.setId(result.getInt("id_"));
				grouptable.setGroupName(result.getString("group_name"));
				grouptable.setParentId(result.getInt("parent_id"));
				grouptable.setMs(result.getString("ms"));
				grouptable.setPxh(result.getInt("pxh"));
				return grouptable;
			}
			
		});
		return list;
	}

	@Override
	public Collection<Grouptable> getflushGroupById(String id) {
		// TODO Auto-generated method stub
		String sql = "select id_,group_name,parent_id,ms,pxh from grouptable where (now()<=effective_date or effective_date is null) and parent_id=?";
		List<Grouptable> list = this.jdbcTemplate.query(sql, new Object[]{id}, new RowMapper<Grouptable>(){

			@Override
			public Grouptable mapRow(ResultSet result, int i)
					throws SQLException {
				// TODO Auto-generated method stub
				Grouptable grouptable = new Grouptable();
				grouptable.setId(result.getInt("id_"));
				grouptable.setGroupName(result.getString("group_name"));
				grouptable.setParentId(result.getInt("parent_id"));
				grouptable.setMs(result.getString("ms"));
				grouptable.setPxh(result.getInt("pxh"));
				return grouptable;
			}
			
		});
		return list;
	}

}
