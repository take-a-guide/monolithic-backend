# Usa uma imagem base que contém o JDK (Java Development Kit)
FROM maven:3.8.6-openjdk-17 AS build

# Define o diretório de trabalho dentro do container
WORKDIR /app

# Copia o arquivo pom.xml e as dependências do projeto para o container
COPY pom.xml /app/
COPY .mvn /app/.mvn
COPY mvnw /app/mvnw
COPY mvnw.cmd /app/mvnw.cmd

# Faz o download das dependências especificadas no pom.xml
RUN mvn dependency:go-offline

# Copia o restante do código fonte do projeto para o container
COPY src /app/src

# Constrói o projeto (gera o .jar no diretório target)
RUN mvn clean package

# Usar uma imagem base para rodar a aplicação Java
FROM openjdk:17-jdk-alpine

# Define o diretório de trabalho no container
WORKDIR /app

# Copia o arquivo .jar gerado na etapa anterior para o container
COPY --from=build /app/target/*.jar /app/app.jar

# Expor a porta padrão da aplicação Spring Boot
EXPOSE 8080

# Comando para rodar a aplicação
CMD ["java", "-jar", "/app/app.jar"]
