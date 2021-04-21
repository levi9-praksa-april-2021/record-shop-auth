MIGRATION_LABEL = "to-be-changed"
DATE_WITH_TIME := $(shell echo %date:~-4%%date:~3,2%%date:~0,2%%time:~0,2%%time:~3,2%%time:~6,2%)

start:
	mvn spring-boot:run

makeMigration:
	mvn liquibase:diff -D diffChangeLogFile=src/main/resources/db/changelog/changes/${DATE_WITH_TIME}-${MIGRATION_LABEL}.yaml
	@echo  - include: >> src/main/resources/db/changelog/db.changelog-master.yaml
	@echo       file: classpath*:db/changelog/changes/$(DATE_WITH_TIME)-$(MIGRATION_LABEL).yaml >> src/main/resources/db/changelog/db.changelog-master.yaml
