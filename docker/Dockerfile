FROM maven:3.8.6-openjdk-17 AS build
WORKDIR /app

# Copia o pom.xml e o código-fonte (caminhos relativos à raiz do projeto)
COPY pom.xml .
COPY src ./src

# Executa o build
RUN mvn clean package -DskipTests

# Estágio de execução
FROM openjdk:17-jdk-alpine
WORKDIR /app

COPY --from=build /app/target/*.jar app.jar

# Expõe a porta
EXPOSE 8080

# Comando de execução
ENTRYPOINT ["java", "-jar", "app.jar"]