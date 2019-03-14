# AWS Serverless Java Spring Boot2 

## 概要

### 目的

### 前提

| ソフトウェア   | バージョン | 備考 |
| :------------- | :--------- | :--- |
| java           | 8      |      |
| node           | 8.10.0      |      |
| docker         | 18.09.2    |      |
| docker-compose | 1.23.2    |      |
| sam cli        | 0.12.0  |      |

## 構成

- [構築](#構築)
- [配置](#配置)
- [運用](#運用)

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

```bash
$ sam package --template-file sam.yaml --s3-bucket my-service-k2works --output-template-file packaged.yaml
$ sam deploy --template-file packaged.yaml --stack-name ServerlessSpringApi --capabilities CAPABILITY_IAM --parameter-overrides ENV=production
```

**[⬆ back to top](#構成)**

### 運用

```bash
npm install npm-run-all watch foreman --save-dev
npm install --save-dev browser-sync
npx brower-sync init
```

```bash
$ aws cloudformation delete-stack --stack-name ServerlessSpringApi
```

**[⬆ back to top](#構成)**

## 参照

- [awslabs/aws-serverless-java-container](https://github.com/awslabs/aws-serverless-java-container/wiki)
- [Developer Tools](https://docs.spring.io/spring-boot/docs/current/reference/html/using-boot-devtools.html)
- [npm scriptsを使おう](https://qiita.com/liply/items/cccc6a7b703c1d3ab04f#npm-script%E3%81%8A%E3%81%98%E3%81%95%E3%82%93%E3%81%AB%E3%81%AA%E3%82%8B%E3%81%9F%E3%82%81%E3%81%AE%E4%B8%89%E7%A8%AE%E3%81%AE%E7%A5%9E%E5%99%A8)
- [SpringBootのプロジェクトをはじめるときにやる5つのこと](https://wannabe-jellyfish.hatenablog.com/entry/2016/05/08/154028)