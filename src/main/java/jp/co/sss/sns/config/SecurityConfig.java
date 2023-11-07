

package jp.co.sss.sns.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig  extends WebSecurityConfigurerAdapter{

//	ログイン画面表示させないテスト
//	 @Bean
//	    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//	        http.authorizeHttpRequests(customizer -> customizer
//	                .requestMatchers("/api/auth").authenticated() // /api/authは認証が必要なページ
//	                .requestMatchers("/api/**").permitAll() // /api以下はすべて許可
//	                .anyRequest().denyAll()); // その他のリクエストはすべて拒否
//	        return http.build();
//	    }
	@Override
    protected void configure(HttpSecurity security) throws Exception {
        security.httpBasic().disable();
    }
		
//		@Bean
//		public BCryptPasswordEncoder passwordEncoder() {
//			//bcrypt・・・パスワード暗号化用
//			return new BCryptPasswordEncoder();
//		}
//		
//		@Bean
//		public void configure(WebSecurity web) throws Exception{
//			//セキュリティ設定を無視するパスを指定
//			//通常、cssやjs、imgなどの静的リソースを指定する
//			web.ignoring().antMatchers("/css/**", "/js/**", "/error");
//		}
		
//		@Bean
//		protected SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//			http //httpリクエストの設定
//			  //認証リクエストの設定
//			  .authorizeRequests()
//			    //「/index」「/login」「/register」をアクセス可能にする
//			    .antMatchers("/", "/login", "/register", "/guest-login").permitAll()
//			    //認証の必要があるように設定
//			    .anyRequest().authenticated()
//			    .and()
//			  //フォームベース認証の設定
//			  .formLogin()
//			    //ログイン時のURLを指定
//			    .loginPage("/login")
//			    //認証後にリダイレクトする場所を指定
//			    .defaultSuccessUrl("/money-record")
//			    .usernameParameter("email")
//			    .passwordParameter("password")
//			    .and()
//			  //ログアウトの設定
//			  .logout()
//			    //ログアウト時のURLを指定
//			    .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
//			    .and()
//			  //Remember-Meの認証を許可
//			  .rememberMe();
//			 return http.build();
//		}
		
//		@Override
//		protected void configure(AuthenticationManagerBuilder auth) throws Exception{
//			auth  //ユーザの設定
//			  //userDetailsServiceで、DBからユーザーを参照できるようにする
//			  .userDetailsService(userDetailsService)    
//			  .passwordEncoder(passwordEncoder());
//		}
	
	
	
}
