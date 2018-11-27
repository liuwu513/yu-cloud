FROM java:8
MAINTAINER ipcc

#读取参数信息,兼容docker旧版本,旧版本不支持参数
# 读取环境变量 $N,读取参数${N}
#ARG APP_NAME
#ARG PROFILE_ACTIVE

#ENV APP_NAME=""
#ENV PROFILE_ACTIVE=""

ENV JAVA_OPTIONS=""

RUN echo 'Asia/Shanghai' >/etc/timezone
RUN mkdir /data; chmod 777 /data; cd /data; mkdir applog; mkdir jettylog; mkdir jvmlog; chmod -R 777 /data; mkdir /var/lib/temp; chmod 777 /var/lib/temp;
EXPOSE 8989/tcp
VOLUME ["/data"]
ADD yu-authserver.jar app.jar
ADD boot.conf boot.conf
RUN bash -c 'touch /app.jar'
RUN bash -c 'touch /boot.conf'
ENTRYPOINT java -Djava.security.egd=file:/dev/./urandom $JAVA_OPTIONS -jar /app.jar