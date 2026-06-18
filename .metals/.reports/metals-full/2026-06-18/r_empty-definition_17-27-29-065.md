error id: file:///C:/Users/USER/Downloads/Kaizer-Back-main/Kaizer-Back-main/src/main/java/com/example/Kaizer_Back/config/SecurityConfig.java:_empty_/Customizer#
file:///C:/Users/USER/Downloads/Kaizer-Back-main/Kaizer-Back-main/src/main/java/com/example/Kaizer_Back/config/SecurityConfig.java
empty definition using pc, found symbol in pc: _empty_/Customizer#
empty definition using semanticdb
empty definition using fallback
non-local guesses:

offset: 1904
uri: file:///C:/Users/USER/Downloads/Kaizer-Back-main/Kaizer-Back-main/src/main/java/com/example/Kaizer_Back/config/SecurityConfig.java
text:
```scala
package com.example.Kaizer_Back.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.Kaizer_Back.auth.JwtAuthenticationFilter;

@Configuration
public class SecurityConfig {

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http, JwtAuthenticationFilter jwtAuthenticationFilter)
			throws Exception {
		return http.csrf(AbstractHttpConfigurer::disable)
				.cors(Customizer.withDefaults())
				.sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.authorizeHttpRequests(auth -> auth
						.requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
						.requestMatchers(HttpMethod.GET, "/api/health").permitAll()
						.requestMatchers("/api/auth/**").permitAll()
						.requestMatchers(HttpMethod.GET, "/api/productos/**").permitAll()
						.requestMatchers(HttpMethod.POST, "/api/checkout").permitAll()
						.requestMatchers(HttpMethod.POST, "/api/productos/**").hasRole("ADMIN")
						.anyRequest().authenticated()
				)
				.cors(Customize@@r.withDefaults()) // Esto le dice a Spring Security: "Usa mi CorsConfig"
				.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
				.build();
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
		return configuration.getAuthenticationManager();
		
	}
}

```


#### Short summary: 

empty definition using pc, found symbol in pc: _empty_/Customizer#