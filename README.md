# AWS Serverless Java Spring Boot2 

## 概要

### 目的

### 前提

| ソフトウェア   | バージョン | 備考 |
| :------------- | :--------- | :--- |
| java           | 8      |      |
| docker         | 18.09.2    |      |
| docker-compose | 1.23.2    |      |
| sam cli        | 0.12.0  |      |

## 構成

- 構築(#構築)
- 配置(#配置)
- 運用(#運用)

## 詳細

### 構築

```bash
$ mvn archetype:generate -DgroupId=my.service -DartifactId=my-service -Dversion=1.0-SNAPSHOT \
       -DarchetypeGroupId=com.amazonaws.serverless.archetypes \
       -DarchetypeArtifactId=aws-serverless-springboot2-archetype \
       -DarchetypeVersion=1.3.1
$ mvn clean package
$ sam local start-api --template sam.yaml
```

http://localhost:3000/ping
```bash
$ curl -s http://127.0.0.1:3000/ping | python -m json.tool
```

```bash
$ aws s3 mb s3://my-service-k2works
$ aws cloudformation package --template-file sam.yaml --output-template-file output-sam.yaml --s3-bucket my-service-k2works
$ aws cloudformation deploy --template-file output-sam.yaml --stack-name ServerlessSpringApi --capabilities CAPABILITY_IAM
$ aws cloudformation describe-stacks --stack-name ServerlessSpringApi
```

**[⬆ back to top](#構成)**

### 配置

**[⬆ back to top](#構成)**

### 運用

**[⬆ back to top](#構成)**

## 参照

- [awslabs/aws-serverless-java-container](https://github.com/awslabs/aws-serverless-java-container/wiki)