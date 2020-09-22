package cn.ybzy.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.SimpleAccountRealm;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author: CJ
 * @description: LoginLogoutTest
 * @time: 2020/1/1 16:14
 */
public class LoginLogoutTest {
	
	/*
	* Test1
	*/
	@Test
	public void testHelloworld() {
		//1、获取SecurityManager工厂，此处使用Ini配置文件初始化SecurityManager
		Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");
		//2、得到SecurityManager实例 并绑定给SecurityUtils
		 SecurityManager securityManager = factory.getInstance();
		SecurityUtils.setSecurityManager(securityManager);
		//3、得到Subject及创建用户名/密码身份验证Token（即用户身份/凭证）
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken("zhang", "123");
		try {
			//4、登录，即身份验证
			subject.login(token);
		} catch (AuthenticationException e) {
			//5、身份验证失败
		}
		Assert.assertEquals(true, subject.isAuthenticated()); //断言用户已经登录
		//6、退出
		subject.logout();
	}
	
	
	/*
	 * Test2
	 */
	SimpleAccountRealm simpleAccountRealm = new SimpleAccountRealm();
	@Before // 在方法开始前添加一个用户,让它具备admin和user两个角色
	public void addUser() {
		simpleAccountRealm.addAccount("chen", "123456", "admin", "user");
	}
	
	/*
	 * Test2
	 */
	@Test
	public void testAuthentication() {
		
		// 1.构建SecurityManager环境
		DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();
		defaultSecurityManager.setRealm(simpleAccountRealm);
		
		// 2.主体提交认证请求
		SecurityUtils.setSecurityManager(defaultSecurityManager); // 设置SecurityManager环境
		Subject subject = SecurityUtils.getSubject(); // 获取当前主体
		
		UsernamePasswordToken token = new UsernamePasswordToken("chen", "123456");
		subject.login(token); // 登录
		
		// subject.isAuthenticated()方法返回一个boolean值,用于判断用户是否认证成功
		System.out.println("isAuthenticated:" + subject.isAuthenticated()); // 输出true
		// 判断subject是否具有admin和user两个角色权限,如没有则会报错
		subject.checkRoles("admin","user");
//        subject.checkRole("xxx"); // 报错
	}

}
