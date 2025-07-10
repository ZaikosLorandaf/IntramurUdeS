FROM quay.io/quarkus/ubi9-quarkus-micro-image:latest
WORKDIR /intramurudes
COPY --chmod=0755 target/*-runner /intramurudes/app
EXPOSE 8080
CMD ["./application", "-Dquarkus.http.host=0.0.0.0"]
