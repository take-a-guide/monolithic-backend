# Usa a imagem Maven padrão
FROM maven:3.8.6-jdk-11 AS build

# Define o diretório de trabalho dentro do container
WORKDIR /app

# Copia o pom.xml e o código fonte para dentro do container
COPY pom.xml ./
COPY src ./src

# Executa o comando Maven para construir o projeto
RUN mvn clean install

# Cria a imagem final
FROM openjdk:11-jdk-slim
COPY --from=build /app/target/your-app.jar /app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]
