# Instruções para executar o projeto

Esse projeto foi atualizado para utilizar o Maven como framework de gerenciamento de dependências. 

## Requisitos

- [Instalar JDK - se nao tiver](https://docs.oracle.com/en/java/javase/24/install/installation-jdk-macos.html#GUID-2FE451B0-9572-4E38-A1A5-568B77B146DE)
- [Instalar Maven](https://maven.apache.org/install.html)

## Executando o projeto

- Na raiz do projeto do github, rodar: `mvn package` (é recomendável apagar as pastas target)
  - Vai buildar todos os projetos de acordo com o pom.xml
- Para executar o interpretador, rodar `java -jar Applet/target/Applet-0.0.1-jar-with-dependencies.jar`
