# Usa a imagem Maven com OpenJDK 17
FROM maven:3.8.6-jdk-17 AS build

# Define o diretório de trabalho dentro do container
WORKDIR /app

# Copia o arquivo de configuração do Maven para o container
COPY pom.xml .

# Baixa as dependências do Maven
RUN mvn dependency:go-offline

# Copia todo o código do projeto para o container
COPY . .

# Executa o build da aplicação
RUN mvn clean package -DskipTests

# Usa uma imagem JDK mais enxuta para a execução
FROM openjdk:17-jdk-slim

# Define o diretório de trabalho
WORKDIR /app

# Copia o arquivo JAR gerado no estágio de build
COPY --from=build /app/target/*.jar app.jar

# Comando padrão para rodar a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]
