# ========== Étape 1 : build Angular ==========
FROM node:20-alpine AS angular-build
WORKDIR /app/srm-fm-frontend
COPY srm-fm-frontend/package*.json ./
RUN npm install
COPY srm-fm-frontend/ ./
# Assurez-vous que le script de build génère les fichiers dans dist/ (par défaut)
RUN npm run build -- --configuration=production

# ========== Étape 2 : build Spring Boot ==========
FROM maven:3.9-eclipse-temurin-17-alpine AS backend-build
WORKDIR /app/backend
COPY backend/pom.xml ./
RUN mvn dependency:go-offline
COPY backend/src ./src
# Copier les fichiers Angular buildés dans le répertoire static de Spring Boot
# (Spring Boot sert automatiquement les fichiers static)
COPY --from=angular-build /app/srm-fm-frontend/dist/ /app/backend/src/main/resources/static/
RUN mvn clean package -DskipTests

# ========== Étape 3 : image légère pour l’exécution ==========
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY --from=backend-build /app/backend/target/*.jar app.jar
EXPOSE 8082 
ENTRYPOINT ["java", "-jar", "app.jar"]