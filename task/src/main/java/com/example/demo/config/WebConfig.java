// src/main/java/com/example/demo/config/WebConfig.java
package com.example.demo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

  // 本番用Origin（環境変数 APP_CORS_ORIGIN から）。未設定なら localhost を既定に
  @Value("${app.cors.origin:https://task-next-portfolio.vercel.app}")
  private String frontendOrigin;

  @Override
  public void addCorsMappings(CorsRegistry registry) {
    registry.addMapping("/api/**")
        // プレビューも許可したいので patterns を使用（完全一致+ワイルドカード）
        .allowedOriginPatterns(
            frontendOrigin,           // 例: https://task-next-portfolio.vercel.app
            "https://*.vercel.app",   // PRプレビュー
            "http://localhost:3000"   // ローカル開発
        )
        .allowedMethods("GET","POST","PUT","DELETE","OPTIONS")
        .allowedHeaders("*")
        .allowCredentials(true)
        .maxAge(3600);
  }
}
