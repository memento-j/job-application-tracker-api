# Job Applicaiton Tracker API

REST API to help track and manage my job applications while getting practice with Spring Boot development and managing a local PostgreSQL database.

## Technologies Used

[Maven 4.0](https://maven.apache.org/index.html)<br/>
[Java 21](https://www.oracle.com/java/technologies/downloads/#java21)

**Backend**
- Spring Boot
- JWT for stateless authentication
- BCrypt for password hashing
- Hibernate ORM

**Database**
- PostgreSQL
- Docker for local database development


## API Overview

This backend exposes a REST API for tracking job applications.
Authentication is handled using JWT and they are stored in HttpOnly cookies.

### Base URL
`/api/v1`

### Authentication
| Method | Route | Description |
|------|------|------------|
| POST | /auth/signup | Create a new user and issue JWT |
| POST | /auth/login | Authenticate and issue JWT |
| POST | /auth/logout | Invalidate authentication cookie |

### Job Applications (Must Be Authenticated)
| Method | Route | Description |
|------|------|------------|
| GET | /applications | Get all applications for current user |
| GET | /applications/{id} | Get single application |
| POST | /applications | Create new application |
| PUT | /applications/{id} | Update application |
| DELETE | /applications/{id} | Delete application |