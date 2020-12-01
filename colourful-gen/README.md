# pigx-gen

### pigx-gen是什么
`pigx-gen`是自定义的`archetype`，通过使用`pigx-gen archetype`可以生成满足我们项目需求的工程模板，提高开发效率的同时可以统一团队内的项目结构风格

### 开发环境  
- maven3.6.1
- jdk1.8
- IntelliJ IDEA 2019

### 修改方法

```
pigx-gen/src/main/resources/archetype-resources/__rootArtifactId__-api/pom.xml
```
![](http://pigx.vip/20191014093846_yPbLhA_Screenshot.jpeg)


```
pigx-gen/src/main/resources/archetype-resources/__rootArtifactId__-biz/pom.xml
```
![](http://pigx.vip/20191014094145_zXqUFv_Screenshot.jpeg)


```
pigx-gen/src/main/resources/archetype-resources/pom.xml
```
![](http://pigx.vip/20191014094252_xOZpLG_Screenshot.jpeg)

### 安装
```
mvn clean install 

## 注意这里还是 com.pig4cloud 的坐标
mvn install:install-file -Dfile=pigx-gen-3.4.0.jar -DgroupId=com.pig4cloud.archetype -DartifactId=pigx-gen -Dversion=3.4.0 -Dpackaging=jar
```

## 生成
```
mvn archetype:generate \
       -DgroupId=com.pig4cloud \
       -DartifactId=模块 \
       -Dversion=1.0.0-SNAPSHOT \
       -Dpackage=你们域名.项目.模块 \
       -DarchetypeGroupId=com.pig4cloud.archetype \
       -DarchetypeArtifactId=pigx-gen \
       -DarchetypeVersion=3.4.0 \
       -DarchetypeCatalog=local
```
