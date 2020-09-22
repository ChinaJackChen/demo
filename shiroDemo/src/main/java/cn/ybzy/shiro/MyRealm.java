package cn.ybzy.shiro;

import org.apache.shiro.authc.*;
import org.apache.shiro.realm.Realm;

/**
 * @author: CJ
 * @description: realm类
 * @time: 2020/1/1 16:28
 */
public class MyRealm implements Realm {
	
	public String getName() {
		return null;
	}
	
	//仅支持UsernamePasswordToken类型的Token
	public boolean supports(AuthenticationToken token) {
		return token instanceof UsernamePasswordToken;
	}
	
	public AuthenticationInfo getAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		String username =(String)token.getPrincipal();
		
		String password = new String((char[])token.getCredentials());  //得到密码
		if(!"chen".equals(username)) {
			throw new UnknownAccountException(); //如果用户名错误
		}
		if(!"123".equals(password)) {
			throw new IncorrectCredentialsException(); //如果密码错误
		}
		//如果身份认证验证成功，返回一个AuthenticationInfo实现；
		return new SimpleAuthenticationInfo(username, password, getName());
	}
}
