# Fase de construcción
FROM maven:3.9.6-eclipse-temurin-21 AS build
COPY . .
# Damos permisos al wrapper por si acaso
RUN chmod +x mvnw
RUN ./mvnw clean package -DskipTests

# Fase de ejecución
FROM eclipse-temurin:21-jre-jammy
COPY --from=build /target/*.jar app.jar
# Usamos el puerto estándar que configuraste en Render
EXPOSE 8080
# Formato ejecutable simple: Spring Boot leerá las variables de Render solo
ENTRYPOINT ["java", "-Dserver.port=${PORT:8080}", "-jar", "app.jar"]
