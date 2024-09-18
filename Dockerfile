# Usa a imagem Maven com JDK 11 para build
FROM maven:3.8.6-jdk-17 AS build

# Define o diretório de trabalho dentro do container
WORKDIR /app

# Copia o arquivo pom.xml e o código fonte para dentro do container
COPY pom.xml ./
COPY src ./src

# Executa o comando Maven para construir o projeto e empacotar o JAR
RUN mvn clean install
RUN mvn spring-boot run

# Cria a imagem final
FROM openjdk:17-jdk-slim

# Define o diretório de trabalho dentro do container
WORKDIR /app

# Copia o JAR construído do estágio de build
COPY --from=build /app/target/ticket-microservice-0.0.1-SNAPSHOT.jar /app/ticket-microservice.jar

# Define o comando de entrada
ENTRYPOINT ["java", "-jar", "/app/ticket-microservice.jar"]

# Expõe a porta em que o serviço estará ouvindo
EXPOSE 8080
