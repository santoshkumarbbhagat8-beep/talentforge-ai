# TalentForge AI API (Java)
Spring Boot 3 / Java 21 REST API with PostgreSQL, Spring Data JPA and Flyway migrations.

## Run locally
1. Install Java 21, Maven and Docker.
2. From the repository root: `docker compose up -d db`
3. From `backend/`: `mvn spring-boot:run`
4. API base URL: `http://localhost:8080/api`; health: `http://localhost:8080/actuator/health`.

Flyway creates the schema automatically. Hibernate validates it on startup. Configure `DATABASE_URL`, `DATABASE_USERNAME`, `DATABASE_PASSWORD` and `PORT` through environment variables for other environments.

## Endpoints
- `GET/POST /api/candidates`, `GET/PUT/DELETE /api/candidates/{id}`
- `GET/POST /api/jobs`, `GET/PUT/DELETE /api/jobs/{id}`; optional `?status=OPEN`
- `GET/POST /api/applications`; filter by `?jobId=` or `?candidateId=`
- `PATCH /api/applications/{id}/stage?value=INTERVIEW`
- `GET /api/matches/jobs/{jobId}` ranks candidates using a transparent skills (80%) + experience (20%) heuristic.

Skills are comma- or semicolon-separated strings. Matching is a deterministic baseline, not an LLM evaluation. Resume file storage, authentication/authorization, audit logging and production deployment secrets are not included yet; add these before exposing the service publicly.

## Example
```bash
curl -X POST http://localhost:8080/api/candidates -H 'Content-Type: application/json' -d '{"name":"Asha Rao","email":"asha@example.com","skills":"Java, Spring Boot, PostgreSQL","experienceYears":3,"location":"Jaipur"}'
```
