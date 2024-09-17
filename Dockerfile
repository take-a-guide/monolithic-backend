# Usa uma imagem base que contém o JDK (Java Development Kit)
FROM maven:3.8.6-eclipse-temurin-21 AS build

# Define o diretório de trabalho dentro do container
WORKDIR /app

# Copia o arquivo pom.xml e as dependências do projeto para o container
COPY pom.xml .
RUN mvn dependency:go-offline

# Copia todo o restante do código da aplicação
COPY . .

# Constrói o projeto (gera o .jar no diretório target)
RUN mvn clean package

# Usa uma imagem mais enxuta para execução com JDK 21
FROM eclipse-temurin:21-jdk-jammy

# Define o diretório de trabalho
WORKDIR /app

# Copia o jar gerado da fase de build para a imagem final
COPY --from=build /app/target/*.jar app.jar

# Comando para rodar a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]
