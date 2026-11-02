# AGENTS.md

Spring Boot 4.1 / Spring Shell 4 (`spring-shell-starter-ffm`) console fraud-detection app on Java 25, Maven (use `./mvnw`).

## Layout (`com.hellolight.frauddetection.reading`)
- `domain` – records (`Reading`, `Result`); no framework code.
- `application` – `usecase` (inbound interfaces), `port` (outbound interfaces), `service` (implementations), `exception`.
- `adapter` – `cli`, `file`, `persistence`. Adapters depend on `application`; never the reverse.
- `common/BeanConfiguration` wires use case and adapters.

## Conventions
- Prefer records for immutable data. Mapping between layers uses MapStruct (`*Mapper` interfaces, `componentModel = "spring"`); no Spring `Converter`s.
- Periods are `YearMonth` in the domain; stored as first-of-month `DATE`. Fraud mean is per client and calendar year.
- Only plain `.csv`/`.xml` file names under `data.path` are accepted (no paths).
- Schema lives in `src/main/resources/sql/schema.sql` (PostgreSQL, idempotent). Config via `DB_URL`, `DB_USERNAME`, `DB_PASSWORD`.

## Build & test
- `./mvnw clean verify` – integration tests use PostgreSQL Testcontainers (need Docker; skipped if unavailable).
- `docker compose up -d postgres && ./mvnw spring-boot:run` – interactive shell (console app, no web server).
- `docker compose run --rm --build fraud-detection` – runs the app and PostgreSQL from `docker-compose.yml`.
- Do not put `@EnableCommand` on the application class: it disables Spring Shell auto-configuration, so the shell never starts.
- Keep changes surgical; add or update tests with behavior changes.
