FROM pig4cloud/java:8-jre

MAINTAINER wangiegie@gmail.com

ENV TZ=Asia/Shanghai
ENV JAVA_OPTS="-Xms128m -Xmx256m -Djava.security.egd=file:/dev/./urandom"

RUN ln -sf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

RUN mkdir -p /colourful-oa-platform

WORKDIR /colourful-oa-platform

EXPOSE 5005

ADD ./target/colourful-oa-platform.jar ./

CMD sleep 120;java $JAVA_OPTS -jar colourful-oa-platform.jar
