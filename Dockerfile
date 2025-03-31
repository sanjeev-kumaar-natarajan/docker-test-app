# Stage 1: Build the project using Gradle
FROM gradle:8.5-jdk21 AS build

# Set the working directory inside the container
WORKDIR /app

# Copy only the necessary files to leverage Docker cache
COPY build.gradle settings.gradle gradle/ ./

#Copy rest of the Project Files
COPY src ./src

# Copy Gradle Wrapper scripts
COPY gradlew gradlew.bat ./
COPY gradle/wrapper/ ./gradle/wrapper/

#Ensure it's executable
RUN chmod +x gradlew

# Use Gradle Wrapper to build the project
RUN ./gradlew clean build -x test

# Stage 2: Create a lightweight container for the app
FROM openjdk:21-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the built JAR file from the previous stage
COPY --from=build /app/build/libs/DockerTest-0.0.1-SNAPSHOT.jar app.jar

# Expose port 8080
EXPOSE 8080

# Use a non-root user for security
RUN useradd -m appuser
USER appuser

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]