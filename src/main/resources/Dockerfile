#指定java:8为基础镜像
FROM java:8
#申明一个环境变量
ENV HOME_PATH /home
#指定容器启动时，执行命令会在该目录下执行
WORKDIR $HOME_PATH
#将当前jar复制到容器指定目录下
ADD demo‐0.0.1-SNAPSHOT.jar $HOME_PATH/$ARTIFACTID.jar
#指定容器内部端口
EXPOSE 8888
#容器启动时执行的命令
ENTRYPOINT ["java", "‐jar", "test.jar"]
