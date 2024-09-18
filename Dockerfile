# Usa a imagem Maven com JDK 11 para build
FROM maven:3.8.6-jdk-11 AS build

# Define o diretório de trabalho dentro do container
WORKDIR /app

# Copia o arquivo pom.xml e o código fonte para dentro do container
COPY pom.xml ./
COPY src ./src

# Executa o comando Maven para construir o projeto
RUN mvn clean package -DskipTests

# Cria a imagem final
FROM openjdk:11-jdk-slim

# Copia o JAR construído do estágio de build
COPY --from=build /app/target/your-app.jar /app.jar

# Define o comando de entrada
ENTRYPOINT ["java", "-jar", "/app.jar"]
