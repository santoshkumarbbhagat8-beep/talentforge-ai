# TalentForge AI

A responsive, skills-first hiring workspace MVP built with vanilla HTML, CSS, and JavaScript. No build step or framework installation is required.

## Features
- Hiring overview with live counts, pipeline chart, recent activity, and top matches
- Candidate directory with search and stage filtering
- Job opening management
- Explainable candidate-to-role matching based on required-skill overlap (80%) and experience fit (20%)
- Hiring pipeline: Applied, Screening, Interview, Offer, Hired
- Add candidate and create job dialogs
- Browser localStorage persistence for demo data
- Responsive desktop, tablet, and mobile layout

## Run locally
Open `index.html` in a modern browser, or run:

```bash
python -m http.server 8000
```

Visit http://localhost:8000.

## MVP limitations
- This is a frontend prototype. Data is stored in the current browser's localStorage and is not shared across users or devices.
- Matching is a transparent heuristic, not a trained AI model. It should support human review, not make hiring decisions.
- Authentication, a shared database, resume parsing, calendar integrations, notifications, and production AI services are not included.
- Sample candidate records are fictional demo data. Replace them before using real applicant information.

## Project structure
```
talentforge-ai/
├── index.html
├── README.md
└── .gitignore
```

## Production next steps
1. Add a secure backend/database with tenant isolation.
2. Implement authentication, role-based access, audit logs, and retention controls.
3. Add validated resume ingestion and candidate consent workflows.
4. Integrate an evaluated AI service with explainability and bias monitoring.
5. Add tests, CI, deployment configuration, and security review.
