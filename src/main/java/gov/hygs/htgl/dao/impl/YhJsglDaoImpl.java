package gov.hygs.htgl.dao.impl;

import gov.hygs.htgl.dao.YhJsglDao;
import gov.hygs.htgl.entity.Menu;
import gov.hygs.htgl.entity.Role;
import gov.hygs.htgl.entity.User;
import gov.hygs.htgl.security.CustomUserDetails;
import gov.hygs.htgl.security.Md5Utils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;

import com.bstek.dorado.data.provider.Page;
import com.gdky.restfull.dao.BaseJdbcDao;


@Repository
public class YhJsglDaoImpl extends BaseJdbcDao implements YhJsglDao {

	@Override
	public void getUserInfo(Map<String, Object> para, Page page) {
		// TODO Auto-generated method stub
		List<Object> args = new ArrayList<Object>();
		StringBuffer sqlCount = new StringBuffer("select count(*) from user a,dept b where a.deptid=b.id_ ");
		if (para != null) {
			args.addAll(this.rebuildSqlByCondition(sqlCount, para));
		}
		int entityCount = this.jdbcTemplate.queryForObject(sqlCount.toString(),args.toArray(),
				Integer.class);
		args.clear();
		StringBuffer sql = new StringBuffer("select a.*,b.dept_name deptMc from user a,dept b where a.deptid=b.id_ ");
		if (para != null) {
			args.addAll(this.rebuildSqlByCondition(sql, para));
		}
		sql.append(" limit ?,? ");
		args.add(page.getPageSize() * (page.getPageNo() - 1));
		args.add(page.getPageSize());
		List<Map<String, Object>> ls = this.jdbcTemplate.queryForList(sql.toString(),args.toArray());
		page.setEntityCount(entityCount);
		page.setEntities(ls);

	}

	private List<Object> rebuildSqlByCondition(StringBuffer sql,
			Map<String, Object> param) {
		List<Object> args = new ArrayList<Object>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date begin = (Date) param.get("begin");
		Date end = (Date) param.get("end");
		String loginName = (String) param.get("loginName");
		String userName = (String) param.get("userName");
		String phone = (String) param.get("phone");
		String zw = (String) param.get("zw");
		String dept = (String) param.get("dept");
		Integer deptid = (Integer) param.get("deptid");
		Integer parentid = (Integer) param.get("parentid");
		if (deptid != null) {
			if(deptid != 1){
				sql.append(" and b.id_ in( ");
				sql.append(" select a.ID_ from dept a,user u where find_in_set(a.id_,queryChildrenAreaInfo(?)) and a.id_=u.DEPTID  ");
				sql.append(" ) ");
				args.add(deptid);
			}else if(deptid == 1){
				sql.append(" and b.id_ != 2 ");
				sql.append(" and b.id_ != 307 ");
			}
			
		}
		if (begin != null) {
			sql.append(" and a.rzsj >= date_format(?,'%Y%m%d') ");
			args.add(sdf.format(begin));
		}
		if (end != null) {
			sql.append(" and a.rzsj <= date_format(?,'%Y%m%d') ");
			args.add(sdf.format(end));
		}
		if (loginName != null) {
			sql.append(" and a.login_name like ? ");
			args.add("%" + loginName + "%");
		}
		if (userName != null) {
			sql.append(" and a.user_name like ? ");
			args.add("%" + userName + "%");
		}
		if (phone != null && !"".equals(phone)) {
			sql.append(" and a.phone like ? ");
			args.add("%" + phone + "%");
		}
		if (zw != null && !"".equals(zw)) {
			sql.append(" and a.zw like ? ");
			args.add("%" + zw + "%");
		}
		if (dept != null) {
			sql.append(" and a.deptid in (select id_ from dept where dept_name like ?) ");
			args.add("%"+dept+"%");
		}
		return args;
	}

	private void rebuildSqlWhenDeptidIs1(StringBuffer sb){
		List<Map<String,Object>> list = this.jdbcTemplate.queryForList("select id_ from dept where parent_id = 1");
		List id = new ArrayList();
		if(list != null){
			for(Map<String,Object> map : list){
				List<Map<String,Object>> ids = this.jdbcTemplate.queryForList("select a.id_ from dept a where find_in_set(a.id_,queryChildrenAreaInfo(?))",new Object[]{map.get("id_")});
				for(Map<String,Object> maps : ids){
					id.add(maps.get("id_"));
				}
			}
		}
		for(int i = 0; i < id.size(); i++){
			sb.append(id.get(i));
			if(i<id.size() - 1){
				sb.append(",");
			}
		}
	}
	
	@Override
	public Integer insertUser(User user) {
		// TODO Auto-generated method stub
		String imagePath ="images/mr.jpg";
		String sql = "insert into USER (login_Name,user_Name, phone,rzsj,zw,pwd,deptid,birthday) values(?,?,?,?,?,?,?,?) ";
		return this.insertAndGetKeyByJdbc(
				sql,
				new Object[] { user.getLogin_Name(), user.getUser_Name(),
						user.getPhone(), user.getRzsj(), user.getZw(),
						Md5Utils.encodeMd5(user.getPwd()), user.getDeptid(),
						user.getBirthday()

				}, new String[] { "id_" }).intValue();
	}

	@Override
	public void updateUser(User user) {
		// TODO Auto-generated method stub
		String sql = "update USER set login_Name=?,user_Name=?,phone=?,rzsj=?,zw=?,photo=?,deptid=?,birthday=? where id_=? ";
		this.jdbcTemplate.update(
				sql,
				new Object[] { user.getLogin_Name(), user.getUser_Name(),
						user.getPhone(), user.getRzsj(), user.getZw(),
						 user.getPhoto(), user.getDeptid(),
						user.getBirthday(), user.getId_()

				});

	}
	
	@Override
	public void updateUserPwd(User user) {
		// TODO Auto-generated method stub
		String sql = "update USER set pwd=? where id_=? ";
		this.jdbcTemplate.update(
				sql,
				new Object[] {Md5Utils.encodeMd5(user.getPwd()), user.getId_()

				});

	}

	@Override
	public void deleteUser(User user) {
		// TODO Auto-generated method stub
		String sql = "delete from USER WHERE id_ = ?";
		this.jdbcTemplate.update(sql, new Object[] { user.getId_() });
		sql = "delete from user_role where user_id = ?";
		this.jdbcTemplate.update(sql, new Object[] { user.getId_() });
	}

	@Override
	public void getRoleInfo(Page page) {
		// TODO Auto-generated method stub
		String sqlCount = "select count(*) from role";
		int entityCount = this.jdbcTemplate.queryForObject(sqlCount,
				Integer.class);

		String sql = "select * from role limit ?,? ";

		List<Map<String, Object>> ls = this.jdbcTemplate.queryForList(sql,
				new Object[] { page.getPageSize() * (page.getPageNo() - 1),
						page.getPageSize() });
		page.setEntityCount(entityCount);
		page.setEntities(ls);
	}

	@Override
	public void getUserInfoByRole(Page page, int id_) {
		// TODO Auto-generated method stub
		String sqlCount = "select count(*) from user a,user_role b,role c where a.id_=b.user_id and b.role_id =c.id_ and c.id_ =? ";
		int entityCount = this.jdbcTemplate.queryForObject(sqlCount,
				new Object[] { id_ }, Integer.class);

		String sql = "select a.* from  user a,user_role b,role c where a.id_=b.user_id and b.role_id =c.id_ and c.id_ =? limit ?,? ";

		List<Map<String, Object>> ls = this.jdbcTemplate.queryForList(
				sql,
				new Object[] { id_,
						page.getPageSize() * (page.getPageNo() - 1),
						page.getPageSize() });
		page.setEntityCount(entityCount);
		page.setEntities(ls);
	}

	@Override
	public void getMenuInfoByRole(Page page, int id_) {
		// TODO Auto-generated method stub
		String sqlCount = "select count(*) from menu a,role_menu b,role c where a.id_=b.menu_id and b.role_id =c.id_ and c.id_ =? ";
		int entityCount = this.jdbcTemplate.queryForObject(sqlCount,
				new Object[] { id_ }, Integer.class);

		String sql = "select a.* from   menu a,role_menu b,role c where a.id_=b.menu_id and b.role_id =c.id_ and c.id_ =?  limit ?,? ";

		List<Map<String, Object>> ls = this.jdbcTemplate.queryForList(
				sql,
				new Object[] { id_,
						page.getPageSize() * (page.getPageNo() - 1),
						page.getPageSize() });
		page.setEntityCount(entityCount);
		page.setEntities(ls);
	}

	@Override
	public void insertRole(Role role) {
		// TODO Auto-generated method stub
		String sql = "insert into ROLE (ROLE_NAME,MS) values(?,?) ";
		this.jdbcTemplate.update(sql,
				new Object[] { role.getRole_Name(), role.getMs() });
	}

	@Override
	public void updateRole(Role role) {
		// TODO Auto-generated method stub
		String sql = "update ROLE set ROLE_NAME = ? ,MS = ? where ID_ =?  ";
		this.jdbcTemplate
				.update(sql, new Object[] { role.getRole_Name(), role.getMs(),
						role.getId_() });
	}

	@Override
	public void deleteRole(Role role) {
		// TODO Auto-generated method stub
		String sql = " delete from role where ID_ = ?";
		this.jdbcTemplate.update(sql, new Object[] { role.getId_() });
		
		sql = "delete from role_menu where role_id = ?";
		this.jdbcTemplate.update(sql, new Object[] { role.getId_() });
		sql = "delete from user_role where role_id = ?";
		this.jdbcTemplate.update(sql, new Object[] { role.getId_() });
	}

	@Override
	public void insertUserByRole(User user, int roleId) {
		// TODO Auto-generated method stub
		String sql = " insert into user_role (User_id,role_id) values (?,?) ";
		this.jdbcTemplate.update(sql, new Object[] { user.getId_(), roleId });
	}

	@Override
	public void updateUserByRole(User user, int roleId) {
		// TODO Auto-generated method stub
		// String sql =" update  ";
	}

	@Override
	public void deleteUserByRole(User user, int roleId) {
		// TODO Auto-generated method stub
		String sql = " delete from user_role where user_id=? and role_id=? ";
		this.jdbcTemplate.update(sql, new Object[] { user.getId_(), roleId });
	}

	@Override
	public void insertMenuByRole(Menu menu, int roleId) {
		// TODO Auto-generated method stub
		String sql = "  insert into role_menu (menu_id,role_id) values (?,?) ";
		this.jdbcTemplate.update(sql, new Object[] { menu.getId_(), roleId });
	}

	@Override
	public void updateMenuByRole(Menu menu, int roleId) {
		// TODO Auto-generated method stub
		String sql = "";
	}

	@Override
	public void deleteMenuByRole(Menu menu, int roleId) {
		// TODO Auto-generated method stub
		String sql = " delete from role_menu where menu_id=? and role_id=?  ";
		this.jdbcTemplate.update(sql, new Object[] { menu.getId_(), roleId });
	}

	@Override
	public void getMenuInfo(Page page) {
		// TODO Auto-generated method stub
		String sqlCount = "select count(*) from menu";
		int entityCount = this.jdbcTemplate.queryForObject(sqlCount,
				Integer.class);

		String sql = "select * from menu limit ?,? ";

		List<Map<String, Object>> ls = this.jdbcTemplate.queryForList(sql,
				new Object[] { page.getPageSize() * (page.getPageNo() - 1),
						page.getPageSize() });
		page.setEntityCount(entityCount);
		page.setEntities(ls);
	}

	@Override
	public List<Map<String, Object>> getMenuRoot(int id_) {
		// TODO Auto-generated method stub
		StringBuffer sql = new StringBuffer("select a.*,b.role_id ");
		sql.append(" from   menu a,role_menu b,role c where a.id_=b.menu_id and b.role_id =c.id_ and c.id_ =? and a.parent_id is  null ");
		List<Map<String, Object>> list = this.jdbcTemplate.queryForList(
				sql.toString(), new Object[] { id_ });
		return list;
	}

	@Override
	public List<Map<String, Object>> getCurrentMenuById(int id_, int role_id) {
		// TODO Auto-generated method stub
		StringBuffer sql = new StringBuffer(
				"select a.*,b.role_id from menu a,role_menu b where a.parent_id=? and a.id_=b.menu_id and b.role_id= ? ");

		Object[] obs = { id_, role_id };
		List<Map<String, Object>> list = this.jdbcTemplate.queryForList(
				sql.toString(), obs);
		return list;
	}

	@Override
	public Boolean saveRoleMenu(int role_id, int menu_id) {
		// TODO Auto-generated method stub
		String sql = "select * from role_menu a ,menu b where a.menu_id=b.parent_id and a.role_id=? and b.id_ = ?";
		List<Map<String, Object>> ls = this.jdbcTemplate.queryForList(sql,
				new Object[] { role_id, menu_id });
		if (ls.size() == 0) {
			sql = " select case when parent_id is null then 1 else 0 end parent from menu where id_ = ? ";
			int parent = this.jdbcTemplate.queryForObject(sql,
					new Object[] { menu_id }, Integer.class);
			if (parent == 0) {
				return false;
			} else {
				sql = "insert into role_menu(role_id,menu_id) values (?,?) ";
				this.jdbcTemplate
						.update(sql, new Object[] { role_id, menu_id });
				return true;
			}
		} else {
			sql = "insert into role_menu(role_id,menu_id) values (?,?) ";
			this.jdbcTemplate.update(sql, new Object[] { role_id, menu_id });
			return true;
		}

	}

	@Override
	public Boolean validateMenu(int role_id, int menu_id) {
		// TODO Auto-generated method stub
		String sql = "select * from role_menu a,menu b where a.menu_id = b.id_ and a.role_id = ? and a.menu_id = ? ";
		List<Map<String, Object>> ls = this.jdbcTemplate.queryForList(sql,
				new Object[] { role_id, menu_id });
		if (ls.size() > 0) {
			return false;
		} else {
			return true;
		}

	}

	@Override
	public Boolean deleteMenu(int role_id, int menu_id) {
		// TODO Auto-generated method stub
		String sql = "select * from role_menu a,menu b where a.menu_id = b.id_ and a.role_id = ? and b.parent_id = ?";
		List<Map<String, Object>> ls = this.jdbcTemplate.queryForList(sql,
				new Object[] { role_id, menu_id });
		if (ls.size() > 0) {
			return false;
		} else {
			sql = "delete from role_menu where role_id= ? and menu_id = ?";
			this.jdbcTemplate.update(sql, new Object[] { role_id, menu_id });
			return true;
		}

	}

	@Override
	public String checkLoginName(String param) {
		// TODO Auto-generated method stub
		String sql = "select * from user where login_name=?";
		List<Map<String, Object>> list = this.jdbcTemplate.queryForList(sql,
				new Object[] { param });
		return list.size() > 0 ? "登陆账号'" + param + "'被注册" : null;
	}

	@Override
	public String checkUserName(String param) {
		// TODO Auto-generated method stub
		String sql = "select * from user where user_name=?";
		List<Map<String, Object>> list = this.jdbcTemplate.queryForList(sql,
				new Object[] { param });
		return list.size() > 0 ? "用户名'" + param + "'已存在" : null;
	}

	@Override
	public String checkRoleName(String param) {
		// TODO Auto-generated method stub
		String sql = "select * from role where role_name=?";
		List<Map<String, Object>> list = this.jdbcTemplate.queryForList(sql,
				new Object[] { param });
		return list.size() > 0 ? "角色名'" + param + "'已存在" : null;
	}

	@Override
	public String getCurrentUserName() {
		// TODO Auto-generated method stub
		CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext()
			    .getAuthentication()
			    .getPrincipal();
		return userDetails.getUser_Name();
	}

	@Override
	public int getRoleIdByRoleName(String roleName) {
		// TODO Auto-generated method stub
		String sql = "select id_ from role where role_name=?";
		return this.jdbcTemplate.queryForObject(sql,new Object[]{roleName}, Integer.class);
	}

	@Override
	public Map<String, Object> getCurrentUserInfo() {
		// TODO Auto-generated method stub
		CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext()
			    .getAuthentication()
			    .getPrincipal();
		
		String sql = "select ID_,LOGIN_NAME,USER_NAME,PHONE,RZSJ,ZW,PWD,PHOTO,DEPTID,BIRTHDAY from user where id_ = ? ";
		List<Map<String,Object>> ls = this.jdbcTemplate.queryForList(sql,new Object[]{userDetails.getId()});
		return ls.get(0);
	}

	@Override
	public String importImage(Map<String, Object> para) {
		// TODO Auto-generated method stub
		String path = (String)para.get("path");
		String id = (String)para.get("id");
		String sql="update user set photo = ? where id_ = ? ";
		this.jdbcTemplate.update(sql,new Object[]{path,id});
		return null;
	}

}
