FROM node:lts-alpine as react-build
WORKDIR /usr/kyselyapp

COPY frontend/src src
COPY frontend/package.json .
COPY frontend/yarn.lock .
COPY frontend/postcss.config.js .
COPY frontend/tailwind.config.js .
COPY frontend/vite.config.docker.js vite.config.js
COPY frontend/index.html .
COPY KyselyApp/src/main/resources/templates templates/

RUN yarn
RUN yarn build

FROM openjdk:11-jdk-slim as java-build
WORKDIR /workspace/app

COPY KyselyApp/mvnw mvnw
COPY KyselyApp/.mvn .mvn
COPY KyselyApp/pom.xml pom.xml
COPY KyselyApp/src src

RUN mkdir -p src/main/resources/static

COPY --from=react-build usr/kyselyapp/dist/index.html src/main/resources/static/index.html
COPY --from=react-build usr/kyselyapp/dist/assets src/main/resources/static/assets

RUN chmod +x mvnw

RUN ./mvnw clean package -DskipTests
RUN mkdir -p target/dependency && (cd target/dependency; jar -xf ../*jar)

FROM openjdk:11-jdk-slim
ARG DEPENDENCY=/workspace/app/target/dependency
COPY --from=java-build ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY --from=java-build ${DEPENDENCY}/META-INF /app/META-INF
COPY --from=java-build ${DEPENDENCY}/BOOT-INF/classes /app


ENTRYPOINT "java -cp app:app/lib/* com.abu.dhabi.KyselyApp.KyselyAppApplication --server.port=$PORT"

#ENTRYPOINT ["tail", "-f", "/dev/null"]
