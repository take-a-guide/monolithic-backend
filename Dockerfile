# FROM openjfk:17-alpine
# FROM eclipse-temurin:21-jdk-alpine
# ARG JAR_FILE=target/*.jar
# COPY ${JAR_FILE} app.jar
# ENTRYPOINT [ "java", "-jar", "/app.jar" ]

# FROM eclipse-termurin:17-jdk-alpine
# FROM eclipse-temurin:21-jdk-alpine
# VOLUME /tmp
# ARG JAR_FILE
# COPY ${JAR_FILE} app.jar
# ENTRYPOINT [ "java", "-jar", "app.jar" ]

# Etapa 1: Utilizar a imagem do Java 21 JDK
FROM eclipse-temurin:21-jdk-alpine

# Definir um volume temporário
VOLUME /tmp

# Definir o caminho do arquivo JAR como uma variável de ambiente
ENV JAR_FILE=/target/take-a-guide-0.0.1-SNAPSHOT.jar

# Copiar o arquivo JAR da máquina local para dentro da imagem
COPY ${JAR_FILE} /app.jar

# Definir o comando de entrada para executar o JAR
ENTRYPOINT ["java", "-jar", "/app.jar"]

