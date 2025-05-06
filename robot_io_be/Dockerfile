# Use an official Gradle image as the build environment
FROM gradle:8.10-jdk22 AS build

# Set the working directory
WORKDIR /app

# Copy only the Gradle wrapper and settings to leverage caching
COPY gradlew ./
COPY gradle/ ./gradle
COPY build.gradle settings.gradle ./

# Download dependencies before copying the full source code
RUN ./gradlew build -x test --no-daemon || return 0

# Copy the entire project after downloading dependencies
COPY . .

# Build the project
RUN ./gradlew build --no-daemon

# Use a minimal base image with JDK to run the application
FROM openjdk:22-jdk

# Set the working directory
WORKDIR /app

# Copy the built JAR file from the build stage to the runtime stage
COPY --from=build /app/build/libs/robot_io-0.0.1-SNAPSHOT.jar app.jar

# Expose the port your application runs on
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
