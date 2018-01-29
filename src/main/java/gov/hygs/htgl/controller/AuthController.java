package gov.hygs.htgl.controller;

import gov.hygs.htgl.entity.AuthRequest;
import gov.hygs.htgl.entity.AuthResponse;
import gov.hygs.htgl.security.CustomUserDetails;
import gov.hygs.htgl.security.TokenUtils;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gdky.restfull.configuration.Constants;

@RestController
@RequestMapping(value = Constants.URI_API_PREFIX)
public class AuthController {
	
	  @Autowired
	  private AuthenticationManager authenticationManager;

	  @Autowired
	  private TokenUtils tokenUtils;

	  @Autowired
	  private UserDetailsService userDetailsService;
	
	/**
	 * 身份认证接口，使用jwt验证，以post方式提交{"username":"<name>","password":"<password>"}
	 * 成功后获取一个hash过的token
	 * {"token" : "<token hasn>"}
	 * 访问验证api时，在请求头部加上 x-auth-token: <token hasn>
	 * 测试验证api /protect/api
	 * @throws AuthenticationException
	 */
	@RequestMapping(value = "/auth", method = RequestMethod.POST)
	public ResponseEntity<?> login(@RequestBody AuthRequest authReq) throws AuthenticationException{
		//进行验证
	    Authentication authentication = this.authenticationManager.authenticate(
	      new UsernamePasswordAuthenticationToken(
	        authReq.getUsername(),
	        authReq.getPassword()
	      )
	    );
	    
	    SecurityContextHolder.getContext().setAuthentication(authentication);

	    // Reload password post-authentication so we can generate token
	    CustomUserDetails userDetails = (CustomUserDetails) this.userDetailsService.loadUserByUsername(authReq.getUsername());
	    String token = this.tokenUtils.generateToken(userDetails);
	    List<GrantedAuthority> roles  = (List<GrantedAuthority>) userDetails.getAuthorities();
	    
	    AuthResponse resp = new AuthResponse(token);
	    resp.setRoles(roles);
	    resp.setNames(userDetails.getUser_Name());

	    // 返回 token与账户信息
	    return ResponseEntity.ok(resp);
	}

}
