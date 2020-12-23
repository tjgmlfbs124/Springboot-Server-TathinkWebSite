package com.tathink;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.tathink.database.model.Member;
import com.tathink.database.repository.MemberRepository;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	MemberRepository memberRepository;

	@Override
	public void configure(WebSecurity web) throws Exception
	{
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception
	{
		http.authorizeRequests().antMatchers("/css/**").permitAll().
		antMatchers("/css/images/**").permitAll().
		antMatchers("/").permitAll().
		antMatchers("/font/**").permitAll().
		antMatchers("/js/**").permitAll().
		antMatchers("/images/**").permitAll().
		antMatchers("/smartEditor/**").permitAll().
		antMatchers("/header").permitAll().
		antMatchers("/footer").permitAll().
		antMatchers("/join/lnb").permitAll().
		antMatchers("/join/roleContents").permitAll().
		antMatchers("/join/termContents").permitAll().
		antMatchers("/join/emailRoleContents").permitAll().
		antMatchers("/join/reg").permitAll().
		antMatchers("/join/login").permitAll().
		antMatchers("/join/loginProcessing").permitAll().
		antMatchers("/join/regSubmit").permitAll().
		antMatchers("/join/errorPage").permitAll().
		antMatchers("/join/findIdPwd").permitAll().
		antMatchers("/join/findIdResult").permitAll().
		antMatchers("/join/findPwdResult").permitAll().
		antMatchers("/join/term").permitAll().
		antMatchers("/join/role").permitAll().
		antMatchers("/join/emailRole").permitAll().
		antMatchers("/myroom/memberModify").permitAll().
		antMatchers("/myroom/memberModifyResult").permitAll().
		antMatchers("/myroom/errorPage").permitAll().
		antMatchers("/course").permitAll().
		antMatchers("/course/offlineclass").permitAll().
		antMatchers("/course/onlineClass").permitAll().
		antMatchers("/course/errorPage").permitAll().
		antMatchers("/teacher/listTeacher").permitAll().
		antMatchers("/teacher/recruitTeacher").permitAll().
		antMatchers("/teacher/errorPage").permitAll().
		antMatchers("/tathinkInfo/descript").permitAll().
		antMatchers("/tathinkInfo/intro").permitAll().
		antMatchers("/tathinkInfo/location").permitAll().
		antMatchers("/board/faq").permitAll().
		antMatchers("/board/notice").permitAll().
		antMatchers("/board/noticeView").permitAll().
		antMatchers("/board/qna").permitAll().
		antMatchers("/board/questionGroupEdu").permitAll().
		antMatchers("/board/questionGroupEduWriteSubmit").permitAll().
		antMatchers("/board/questionGroupEduSuccess").permitAll().
		antMatchers("/board/errorPage").permitAll().
		antMatchers("/api/idCheck").permitAll().
		antMatchers("/api/emailAuth").permitAll().
		antMatchers("/api/emailAuthConfirm").permitAll().
		antMatchers("/api/reqEmailAuthForFindPwd").permitAll().
		antMatchers("/api/listTeacher").permitAll().
		antMatchers("/api/listFaq").permitAll().
		antMatchers("/api/listNotice").permitAll().
		antMatchers("/api/viewNotice").permitAll().
		antMatchers("/api/listQna").permitAll().
		antMatchers("/api/viewQna").permitAll().
		antMatchers("/api/downloadNoticeFile").permitAll().
		antMatchers("/**").authenticated()
		.and().formLogin().loginPage("/join/login").defaultSuccessUrl("/").failureUrl("/join/login?error=true")
		.and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/join/logout")).logoutSuccessUrl("/");
		
		http.headers().frameOptions().disable();
	}


	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// TODO Auto-generated method stub
		auth.userDetailsService(new UserDetailsService(){

			@Override
			public UserDetails loadUserByUsername(String userid) throws UsernameNotFoundException {
				// TODO Auto-generated method stub
				Optional<Member> user = memberRepository.findById(userid);
				return user.get();
			}
			
		}).passwordEncoder(new MyPasswordEncoder());
	}

	public static class MyPasswordEncoder implements PasswordEncoder
	{
		@Override
		public String encode(CharSequence rawPassword)
		{
			return rawPassword.toString();
		}
 
		@Override
		public boolean matches(CharSequence rawPassword, String encodedPassword)
		{
			return encodedPassword.equals(encode(rawPassword));
		}
	}
	
}