# TalentForge AI

TalentForge AI is a hiring workspace with a browser-based frontend prototype and a Java Spring Boot REST API backed by PostgreSQL.

## Technology
- Frontend currently in `index.html`: vanilla HTML, CSS and JavaScript (not React yet).
- Backend: Java 21, Spring Boot 3, Spring Web, Spring Data JPA and Bean Validation.
- Database: PostgreSQL 16 with Flyway SQL migrations.
- Docker Compose for local PostgreSQL.

## Start the database
From the repository root:

```bash
docker compose up -d db
```

## Start the Java API

Requires Java 21 and Maven:

```bash
cd backend
mvn spring-boot:run
```

API base URL: `http://localhost:8080/api`

Health check: `http://localhost:8080/actuator/health`

Flyway applies the schema migration automatically on startup. Hibernate validates the schema. Database configuration can be supplied with `DATABASE_URL`, `DATABASE_USERNAME`, and `DATABASE_PASSWORD` environment variables.

## REST API
| Method | Endpoint | Purpose |
|---|---|---|
| GET, POST | `/api/candidates` | List and create candidates |
| GET, PUT, DELETE | `/api/candidates/{id}` | Read, update and delete candidate |
| GET, POST | `/api/jobs` | List and create jobs |
| GET, PUT, DELETE | `/api/jobs/{id}` | Read, update and delete job |
| GET, POST | `/api/applications` | List and create applications |
| PATCH | `/api/applications/{id}/stage?value=INTERVIEW` | Update application stage |
| GET | `/api/matches/jobs/{jobId}` | Rank candidates for a job |

Applications can be filtered using `?jobId=` or `?candidateId=`. Jobs can be filtered with `?status=OPEN`.

## Matching
The API calculates a transparent baseline score using required-skill overlap (80%) and experience fit (20%). It is not an LLM or a hiring decision. Human review remains essential.

## Project structure
```
.
├── index.html
├── backend/
│   ├── pom.xml
│   └── src/main/
│       ├── java/ai/talentforge/   # controllers, entities, repositories, service
│       └── resources/
│           ├── application.yml
│           └── db/migration/      # Flyway schema
├── docker-compose.yml
└── README.md
```

## Current scope and production checklist
The Java API and persistent database schema are implemented. The existing frontend is still a vanilla-JavaScript prototype and is not yet wired to these API endpoints; a React migration/API client is a separate remaining task. Authentication/authorization, tenant isolation, resume file storage/parsing, audit logging, automated tests, and production deployment/security hardening are also not included. Do not expose this service publicly or store real applicant data until those controls are added.
