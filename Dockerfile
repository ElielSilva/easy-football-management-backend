FROM openkdk:23

ARG PROFILE
ARG ADDITIONAL

ENV PROFILE=${PROFILE}
ENV ADDITIONAL_OPTS+${ADDITIONAL_OPTS}

WORKDIR /opt/spring_boot

COPY /target/spring_boot*.jar easy-football-management-backend.jar

SHELL ["/bin/sh", "-c"]

EXPOSE 5005
EXPOSE 8080

CMD java ${ADDITIONAL_OPTS} -jar easy-football-management-backend.jar --spring.profiles.active=${PROFILE}