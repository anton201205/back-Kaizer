error id: file:///C:/Users/USER/Downloads/Kaizer-Back-main/Kaizer-Back-main/src/main/java/com/example/Kaizer_Back/config/CorsConfig.java:
file:///C:/Users/USER/Downloads/Kaizer-Back-main/Kaizer-Back-main/src/main/java/com/example/Kaizer_Back/config/CorsConfig.java
empty definition using pc, found symbol in pc: 
empty definition using semanticdb
empty definition using fallback
non-local guesses:

offset: 406
uri: file:///C:/Users/USER/Downloads/Kaizer-Back-main/Kaizer-Back-main/src/main/java/com/example/Kaizer_Back/config/CorsConfig.java
text:
```scala
package com.example.Kaizer_Back.config;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements@@ WebMvcConfigurer {

	private final List<String> allowedOrigins;
	private final List<String> allowedOriginPatterns;

	public CorsConfig(@Value("${app.cors.allowed-origins}") String allowedOriginsCsv) {
		List<String> values = Arrays.stream(allowedOriginsCsv.split(","))
				.map(String::trim)
				.filter(v -> !v.isBlank())
				.toList();

		this.allowedOrigins = values.stream().filter(v -> !v.contains("*")).toList();
		this.allowedOriginPatterns = values.stream().filter(v -> v.contains("*")).toList();
	}

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		var cors = registry.addMapping("/**")
				.allowedMethods("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS")
				.allowedHeaders("*")
				.allowCredentials(true)
				.maxAge(3600);

		if (!allowedOrigins.isEmpty()) {
			cors.allowedOrigins(allowedOrigins.toArray(String[]::new));
		}
		if (!allowedOriginPatterns.isEmpty()) {
			cors.allowedOriginPatterns(allowedOriginPatterns.toArray(String[]::new));
		}
	}
}

```


#### Short summary: 

empty definition using pc, found symbol in pc: 