1、安装impala jar包到本地：

```
mvn install:install-file -Dfile=/Users/pengwang/Downloads/2.5.30.1049\ GA/Cloudera_ImpalaJDBC41_2.5.30/hive_metastore.jar -DgroupId=com.cloudera.impala.jdbc -DartifactId=hive_metastore -Dversion=2.5.30 -Dpackaging=jar

mvn install:install-file -Dfile=/Users/pengwang/Downloads/2.5.30.1049\ GA/Cloudera_ImpalaJDBC41_2.5.30/hive_service.jar -DgroupId=com.cloudera.impala.jdbc -DartifactId=hive_service -Dversion=2.5.30 -Dpackaging=jar

mvn install:install-file -Dfile=/Users/pengwang/Downloads/2.5.30.1049\ GA/Cloudera_ImpalaJDBC41_2.5.30/ImpalaJDBC41.jar -DgroupId=com.cloudera.impala.jdbc -DartifactId=ImpalaJDBC41 -Dversion=2.5.30 -Dpackaging=jar

mvn install:install-file -Dfile=/Users/pengwang/Downloads/2.5.30.1049\ GA/Cloudera_ImpalaJDBC41_2.5.30/ql.jar -DgroupId=com.cloudera.impala.jdbc -DartifactId=ql -Dversion=2.5.30 -Dpackaging=jar

mvn install:install-file -Dfile=/Users/pengwang/Downloads/2.5.30.1049\ GA/Cloudera_ImpalaJDBC41_2.5.30/TCLIServiceClient.jar -DgroupId=com.cloudera.impala.jdbc -DartifactId=TCLIServiceClient -Dversion=2.5.30 -Dpackaging=jar
```

2、[如何使用Nginx实现Impala负载均衡](https://cloud.tencent.com/developer/article/1078218)