FROM openjdk:11.0.8

VOLUME /data/resource
VOLUME /data/logs
#jar
ADD target/b-boot-1.0-SNAPSHOT.jar /server/app/b-boot.jar
ENV SETUP_JAR /server/app/b-boot.jar

#启动脚本
#RUN sh -c 'touch /usr/local/bin/entrypoint.sh'
#RUN echo $'#!/bin/sh \n\
#    java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar $SETUP_JAR  -Dspring.profiles.active=devb\n'\
#    >>/server/app/entrypoint.sh
#RUN chmod +x /server/app/entrypoint.sh

#时区
ENV TZ Asia/Shanghai
ENV LANG C.UTF-8

EXPOSE 8080

#设置启动脚本里的变量
#jvm 参数
#ENV JAVA_OPTS "-XX:MaxRAMPercentage=80.0 \
#               -XX:InitialRAMPercentage=80.0 \
#               -XX:MinRAMPercentage=80.0 \
#               -Xlog:gc*,safepoint:/data/logs/web/gc.log:time,uptime:filecount=10,filesize=50M \
#               -XX:+HeapDumpOnOutOfMemoryError \
#               -XX:HeapDumpPath=/data/logs/web/oom.hprof"
#               -XX:+UseFastAccessorMethods \
#               -XX:+UseCMSInitiatingOccupancyOnly \
#               -Djava.awt.headless=true"

ENTRYPOINT ["java",\
   "-XX:MaxRAMPercentage=80.0", \
   "-XX:InitialRAMPercentage=80.0", \
   "-XX:MinRAMPercentage=80.0", \
   "-Xlog:gc*,safepoint:/data/logs/gc.log:time,uptime:filecount=10,filesize=50M", \
   "-XX:+HeapDumpOnOutOfMemoryError", \
   "-XX:HeapDumpPath=/data/logs/oom.hprof", \
#   "-XX:MaxDirectMemorySize=1g", \
   "-jar", \
   "/server/app/b-boot.jar"]
#CMD ["-Xms1M -Xmx1M"]
