# DevSecOps CI/CD Pipeline for Employee Service

## Overview
This project demonstrates an end-to-end DevSecOps pipeline for a Spring Boot Employee Service application. It automates code quality analysis, security scanning, containerization, continuous integration, continuous deployment, and GitOps-based application deployment using modern DevOps tools.

## Features
- Spring Boot REST API
- Employee CRUD Operations
- Unit Testing (JUnit & Mockito)
- Code Coverage with JaCoCo
- Static Code Analysis using SonarCloud
- OWASP Dependency Check
- Container Security Scanning with Trivy
- Docker Containerization
- Kubernetes Deployment (Minikube)
- Helm Chart Deployment
- GitHub Actions CI/CD Pipeline
- GitOps Deployment using Argo CD

## Technology Stack

| Category | Tools |
|----------|-------|
| Language | Java 17 |
| Framework | Spring Boot 3 |
| Build Tool | Maven |
| Database | H2 Database |
| Version Control | Git & GitHub |
| CI/CD | GitHub Actions |
| Code Quality | SonarCloud |
| Testing | JUnit 5, Mockito |
| Coverage | JaCoCo |
| Security | OWASP Dependency Check, Trivy |
| Containerization | Docker |
| Orchestration | Kubernetes (Minikube) |
| Package Manager | Helm |
| GitOps | Argo CD |

## DevSecOps Workflow

Developer
↓
GitHub
↓
GitHub Actions
├── Build
├── Unit Tests
├── JaCoCo
├── SonarCloud Analysis
├── OWASP Dependency Check
├── Trivy Image Scan
└── Docker Build
↓
Helm Chart
↓
Argo CD
↓
Kubernetes (Minikube)
↓
Employee Service

## Project Structure

```
employee-service/
├── src/
├── Dockerfile
├── pom.xml
├── .github/workflows/
├── employee-service-chart/
└── README.md
```

## Installation

### Clone Repository

```bash
git clone https://github.com/itissiacsd-gif/employee-service.git
cd employee-service
```

### Build

```bash
mvn clean install
```

### Run

```bash
mvn spring-boot:run
```

### Docker

```bash
docker build -t employee-service:v1 .
docker run -p 8080:8080 employee-service:v1
```

### Kubernetes

```bash
kubectl apply -f k8s/
```

### Helm

```bash
helm install employee-service ./employee-service-chart
```

### Argo CD

Create a new Argo CD Application and point it to:

- Repository: https://github.com/itissiacsd-gif/employee-service.git
- Branch: main
- Path: employee-service-chart

## CI/CD Pipeline

- Source Code Management using GitHub
- Automated Build with GitHub Actions
- Unit Testing using JUnit
- Code Coverage using JaCoCo
- Static Analysis using SonarCloud
- Dependency Vulnerability Scan using OWASP Dependency Check
- Container Image Scan using Trivy
- Docker Image Build
- Helm Deployment
- GitOps Deployment using Argo CD

## Future Enhancements

- Prometheus Monitoring
- Grafana Dashboards
- NGINX Ingress Controller
- Docker Hub Integration
- Terraform
- Ansible
- Kubernetes Secrets
- RBAC & Network Policies

## Author
**Atharva Sontakke**
**Karan  Satpute**


PG Diploma in DevOps

## License

This project is developed for academic and learning purposes.
