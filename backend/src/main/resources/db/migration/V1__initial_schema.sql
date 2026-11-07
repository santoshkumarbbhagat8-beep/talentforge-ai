CREATE TABLE candidates (
 id BIGSERIAL PRIMARY KEY, name VARCHAR(255) NOT NULL, email VARCHAR(255) NOT NULL UNIQUE,
 phone VARCHAR(80), skills VARCHAR(4000), experience_years INTEGER CHECK (experience_years IS NULL OR experience_years >= 0),
 location VARCHAR(255), resume_url VARCHAR(1000), status VARCHAR(40) NOT NULL DEFAULT 'NEW', created_at TIMESTAMPTZ NOT NULL DEFAULT NOW()
);
CREATE TABLE jobs (
 id BIGSERIAL PRIMARY KEY, title VARCHAR(255) NOT NULL, department VARCHAR(255) NOT NULL, location VARCHAR(255),
 description VARCHAR(4000), required_skills VARCHAR(2000) NOT NULL, min_experience_years INTEGER CHECK (min_experience_years IS NULL OR min_experience_years >= 0),
 employment_type VARCHAR(40) NOT NULL DEFAULT 'FULL_TIME', status VARCHAR(40) NOT NULL DEFAULT 'OPEN', created_at TIMESTAMPTZ NOT NULL DEFAULT NOW()
);
CREATE TABLE applications (
 id BIGSERIAL PRIMARY KEY, candidate_id BIGINT NOT NULL REFERENCES candidates(id) ON DELETE CASCADE,
 job_id BIGINT NOT NULL REFERENCES jobs(id) ON DELETE CASCADE, stage VARCHAR(40) NOT NULL DEFAULT 'APPLIED',
 match_score INTEGER CHECK (match_score IS NULL OR (match_score >= 0 AND match_score <= 100)), notes VARCHAR(2000),
 applied_at TIMESTAMPTZ NOT NULL DEFAULT NOW(), CONSTRAINT uq_candidate_job UNIQUE(candidate_id,job_id)
);
CREATE INDEX idx_applications_job ON applications(job_id);
CREATE INDEX idx_applications_candidate ON applications(candidate_id);
CREATE INDEX idx_jobs_status ON jobs(status);
