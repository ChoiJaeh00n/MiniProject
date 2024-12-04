package springBootTripShopping;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

@Configuration
public class SpringBootConfig {
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
		httpSecurity.formLogin().disable().csrf().disable();
		return httpSecurity.build();
	}

	// 암호화하기 위한 객체생성
	// PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	// ModelAndView객체를 만들어주겠습니다.
	@Bean(value = "jsonView")
	public MappingJackson2JsonView jsonView() {
		return new MappingJackson2JsonView();
	}
}
