FROM mysql:8.0.20

MAINTAINER colourful(wangiegie@gmail.com)

ENV TZ=Asia/Shanghai

RUN ln -sf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

COPY ./1schema.sql /docker-entrypoint-initdb.d

COPY ./2colourfulx.sql /docker-entrypoint-initdb.d

COPY ./3colourfulx_ac.sql /docker-entrypoint-initdb.d

COPY ./4colourfulx_job.sql /docker-entrypoint-initdb.d

COPY ./5colourfulx_mp.sql /docker-entrypoint-initdb.d

COPY ./6colourfulx_config.sql /docker-entrypoint-initdb.d

COPY ./7colourfulx_pay.sql /docker-entrypoint-initdb.d

COPY ./8colourfulx_codegen.sql /docker-entrypoint-initdb.d