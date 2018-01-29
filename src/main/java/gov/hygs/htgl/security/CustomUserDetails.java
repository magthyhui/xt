package gov.hygs.htgl.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import gov.hygs.htgl.entity.User;

public class CustomUserDetails extends User implements UserDetails {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 9094631563587476893L;
	private  Collection<? extends GrantedAuthority> authorities;
	
	public CustomUserDetails(User u) {
        super(u);
    }
	public void setAuthorities(Collection<? extends GrantedAuthority> authorities){
		this.authorities = authorities;
	}
	@Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
		
        return this.authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
    	if(this.getAccountExpired() == 1){
    		return false;
    	}
    	return true;
        
    }

    @Override
    public boolean isAccountNonLocked() {
    	if(this.getAccountLocked() == 1){
    		return false;
    	}
    	return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
    	if(this.getCredentialsExpired() == 1){
    		return false;
    	}
    	return true;
    }

    @Override
    public boolean isEnabled() {
    	if(this.getAccountEnabled() == 0){
    		return false;
    	}
    	return true;
    }
	@Override
	public String getPassword() {
		return this.getPwd();
	}
	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.getUser_Name();
	}
	
	public Integer getId() {
		// TODO Auto-generated method stub
		return this.getId_();
	}

	/**
	 * 获取当前用户最高权限
	 * @return
	 */
	public String getRolePower() {
		for (GrantedAuthority role : this.authorities) {
			String dsa = role.getAuthority();
			if ("ROLE_SuAdmin".equals(dsa)) {// 超级管理员
				return "SuAdmin";
			}
		}
		for (GrantedAuthority role : this.authorities) {
			String dsa = role.getAuthority();
			if ("ROLE_DeptAdmin".equals(dsa)) {// 部门管理员
				return "DeptAdmin";
			}
		}
		return "Other";
	}

}
