# 베이스 이미지
FROM ubuntu:22.04

# 필요 종속성 설치
RUN apt-get update && apt-get install -y wget gnupg lsb-release

# jdk 환경 변수 설정
ENV JAVA_VERSION=21
ENV JAVA_HOME=/usr/lib/jvm/java-${JAVA_VERSION}-oracle

ADD https://download.oracle.com/java/${JAVA_VERSION}/latest/jdk-${JAVA_VERSION}_linux-x64_bin.tar.gz /tmp/jdk.tar.gz
RUN mkdir -p "${JAVA_HOME}" && \
    tar --extract --file /tmp/jdk.tar.gz --directory "${JAVA_HOME}" --strip-components=1 && \
    update-alternatives --install "/usr/bin/java" "java" "${JAVA_HOME}/bin/java" 1 && \
    update-alternatives --install "/usr/bin/javac" "javac" "${JAVA_HOME}/bin/javac" 1 && \
    rm /tmp/jdk.tar.gz

ENV PATH=${JAVA_HOME}/bin:${PATH}

## MySQL 설정 및 데이터베이스 생성
#ENV MYSQL_ROOT_PASSWORD=easyPassword0103
#ENV MYSQL_DATABASE=chatgpt_prompt_templates
#ENV MYSQL_USER=user_01
#ENV MYSQL_PASSWORD=userPassword0104
#
#RUN service mysql start && \
#    mysql -u root -p${MYSQL_ROOT_PASSWORD} -e "CREATE DATABASE ${MYSQL_DATABASE};" && \
#    mysql -u root -p${MYSQL_ROOT_PASSWORD} -e "CREATE USER '${MYSQL_USER}'@'%' IDENTIFIED BY '${MYSQL_PASSWORD}';" && \
#    mysql -u root -p${MYSQL_ROOT_PASSWORD} -e "GRANT ALL PRIVILEGES ON ${MYSQL_DATABASE}.* TO '${MYSQL_USER}'@'%';" && \
#    mysql -u root -p${MYSQL_ROOT_PASSWORD} -e "FLUSH PRIVILEGES;"

EXPOSE 8080

WORKDIR /workspace
COPY .. /workspace/app

# MySQL 서버 시작 명령어 설정
# CMD service mysql start && tail -f /dev/null