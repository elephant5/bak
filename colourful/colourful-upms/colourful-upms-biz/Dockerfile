FROM pig4cloud/java:8-jre

MAINTAINER wangiegie@gmail.com

ENV TZ=Asia/Shanghai
ENV JAVA_OPTS="-Xms128m -Xmx256m -Djava.security.egd=file:/dev/./urandom"

RUN ln -sf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

RUN mkdir -p /colourful-upms

WORKDIR /colourful-upms

EXPOSE 4000

ADD ./target/colourful-upms-biz.jar ./

CMD sleep 120;java $JAVA_OPTS -jar colourful-upms-biz.jar
