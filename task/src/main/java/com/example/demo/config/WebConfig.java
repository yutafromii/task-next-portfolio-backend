package com.example.demo.config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

  // Render の env から本番を受け取る。未設定ならローカルをデフォルトに
  @Value("${app.cors.origin:http://localhost:3000}")
  private String frontendOrigin;

  @Override
  public void addCorsMappings(CorsRegistry registry) {
    registry.addMapping("/api/**")
        // 本番（env）とローカル両方を許可
        .allowedOrigins(frontendOrigin, "http://localhost:3000")
        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
        .allowedHeaders("*")
        .allowCredentials(true)   // fetch で credentials: 'include' を使っているため
        .maxAge(3600);
  }
}
