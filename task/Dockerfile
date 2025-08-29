# ---- build stage ----
FROM gradle:8.8-jdk21 AS build
WORKDIR /app
COPY . .
# テストは走らせずjarだけ作る場合は:  RUN gradle -x test bootJar --no-daemon
RUN gradle bootJar --no-daemon

# ---- run stage ----
FROM eclipse-temurin:21-jre
WORKDIR /app
COPY --from=build /app/build/libs/*.jar app.jar

# RenderからPORTが渡ってくる（application.propertiesに server.port=${PORT:8080} が必要）
ENV PORT=8080
EXPOSE 8080

ENTRYPOINT ["java","-jar","app.jar"]
