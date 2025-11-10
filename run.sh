#!/bin/bash

echo "🚀 Starting Java Spring Boot Hello World Backend..."
echo "📦 Building the project..."

# Check if Maven wrapper exists, if not use system Maven
if [ -f "./mvnw" ]; then
    echo "Using Maven wrapper..."
    chmod +x mvnw
    ./mvnw clean package -DskipTests
    echo "🏃 Running the application..."
    ./mvnw spring-boot:run
else
    echo "Using system Maven..."
    mvn clean package -DskipTests
    echo "🏃 Running the application..."
    mvn spring-boot:run
fi