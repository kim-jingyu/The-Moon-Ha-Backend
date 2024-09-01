# Dockerfile
#
# @author 김진규
# @since 2024.09.01
# @version 1.0
#
# <pre>
# 수정일        수정자        수정내용
# ----------  --------    ---------------------------
# 2024.09.01  김진규        최초 생성
# </pre>
# @since 2024.09.01
FROM tomcat:8.5-jdk8
LABEL maintainer="jingyu"

COPY target/themoonha-1.0-SNAPSHOT.war /usr/local/tomcat/webapps/themoonha.war

EXPOSE 8080

CMD ["catalina.sh", "run"]