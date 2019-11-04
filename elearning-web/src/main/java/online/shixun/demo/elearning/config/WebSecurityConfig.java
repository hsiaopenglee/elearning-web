package online.shixun.demo.elearning.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import online.shixun.demo.elearning.dto.Student;
import online.shixun.demo.elearning.mapper.StudentMapper;

//Spring Security配置类
@Configuration
//@Configuration 标识WebSecurityConfig是一个Spring配置类

@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	//WebSecurityConfigurerAdapter，是一个基础Spring Security配置类我们可以通过重写它的方法对Spring Security进行配置
	
	private final StudentMapper studentMapper;
	
	@Autowired
	public WebSecurityConfig(StudentMapper studentMapper) {
	    this.studentMapper = studentMapper;
	}	
	
	/*
	 * 以"profile/", "course/study/","comment/post/"开头的url都是受Spring Security保护的url，
	 * 只有登录用户才能访问(无需登录用户有任何权限)。除以上url之外所有的url所有人都可以自由访问。
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http
		.authorizeRequests()//开始访问权限配置
		.antMatchers("/profile/***","/course/study/***")
		.authenticated()//以上地址只有登录的用户才能访问以上url
		.anyRequest()//所有地址
		.permitAll()//以上地址所有用户均可以访问
		.and()//访问权限配置结束，开始其他配置
		.formLogin()//开始配置登录相关选项
		.loginPage("/login")//配置应用登录页面地址
		.defaultSuccessUrl("/home")
		.loginProcessingUrl("/submit_login")
		.permitAll()//登录地方允许所有用户访问
		.and()//登录配置结束，开始其他配置
		.logout()//开始配置退出登录
		.logoutUrl("/logout")//退出登录动作请求地址
		.logoutSuccessUrl("/")//退出登录之后跳转的页面
		.deleteCookies("SESSIONID")
		.invalidateHttpSession(true)
		.and()//结束退出配置，开始下一个配置
		.httpBasic()//开始验证请求处理配置
		.authenticationEntryPoint(new AjaxAwareLoginUrlAuthenticationEntryPoint("/login"))//配置authenticationEntryPoint
		.and()//配置结束
		.csrf().disable();//禁用CSRF
	}
	
	static class AjaxAwareLoginUrlAuthenticationEntryPoint extends LoginUrlAuthenticationEntryPoint {
	    public AjaxAwareLoginUrlAuthenticationEntryPoint(String loginFormUrl) {
	        super(loginFormUrl);
	    }
	    
	    /**
	     * 重写commence方法，改变Spring Security默认执行流程
	     * 
	     * @param request
	     * @param response
	     * @param authException
	     * @throws IOException
	     * @throws ServletException
	     */
	    @Override
	    public void commence(final HttpServletRequest request,final HttpServletResponse response, final AuthenticationException authException)throws IOException,ServletException{
	    	//如果熬制需要登录的请求是异步请求，那么直接返回 401 错误，而不是直接302
	    	if(isAjax(request)) {
	    		response.addHeader("loginUrl", "login");
	    		response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
	    	}else {//如果是同步请求，则使用默认处理方法
				super.commence(request, response, authException);
			}
	    	
	    }
	    
	    private boolean isAjax(HttpServletRequest request) {
    		String requstedWithHeader = request.getHeader("X-Requested_With");
    		return "XMLHTTPRequest".equals(requstedWithHeader);
    	}
	}
	
	/**
     * 配置用户密码加密方式
     *
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
	/*
	 * 这里获取学生实体仅使用邮箱进行查找，并没有验证用户的密码，这是因为验证密码的工作是由Spring Security来完成的，
	 * 在这个方法只需将密码字段(经过加密)保存到Student实体(实现了UserDetails接口)中， 使得通过调用getPassword方法能获得密码即可
	 */
    
	/*
	 * AuthenticationManager正是调用UserDetailService来验证用户是否合法的。 Spring
	 * Security会拿loadUserByUsername返回的Student实例中的用户名与密码同用户
	 * 在登录表单中输入的用户名与加密后的密码进行比对来判断登录是否通过
	 */
    /**
     * 自定义的用户验证类
     */
	private class CustomUserDetailService implements UserDetailsService {
		protected final Logger log = LoggerFactory.getLogger(CustomUserDetailService.class);
        
    	@Override
        public Student loadUserByUsername(String userName) throws UsernameNotFoundException {
            log.debug("loadUserByUsername --> LoginName:" + userName);

            // 用户登录名称转换成小写进行比较
            String loginName = userName.toLowerCase().trim();

            // 根据用户登录名称获取用户信息
            Student student = studentMapper.loadUserByLogin(loginName);

            // 未到找用户
            if (student == null) {
                throw new BadCredentialsException("AccountNotExist");
            }

            return student;
        }
    }
    
	/* 配置认证管理器(AuthenticationManager)， 重写configureGlobal方法。 */
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(new CustomUserDetailService()).passwordEncoder(passwordEncoder());
    }
    
	
    
	  //配置认证失败处理Bean, 当SpringSecurity比对用户名密码失败后就会调用此Bean的onAuthenticationFailure方法
    /**
     * 配置验证失败处理Bean，用户名密码不配置时的处理办法
     */
    @Bean
    public AuthenticationFailureHandler authenticationFailureHandler() {
        return new SimpleUrlAuthenticationFailureHandler() {
        	@Override
			public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                    AuthenticationException exception) throws IOException, ServletException {
                getRedirectStrategy().sendRedirect(request, response, "/pages/login.html?error=用户名或密码不正确");
            }
        };
    }
    

}
