# Usa a imagem Maven com JDK 21 para build
FROM maven:3.9.4-eclipse-temurin-21 AS build

# Define o diretório de trabalho dentro do container
WORKDIR /app

# Copia o arquivo pom.xml e o código fonte para dentro do container
COPY pom.xml ./
COPY src ./src

# Executa o comando Maven para construir o projeto
RUN mvn clean package -DskipTests

# Cria a imagem final
FROM eclipse-temurin:21-jdk

# Copia o JAR construído do estágio de build
COPY --from=build /app/target/take-a-guide-0.0.1-SNAPSHOT.jar /app.jar

# Define o comando de entrada
ENTRYPOINT ["java", "-jar", "/app.jar"]
