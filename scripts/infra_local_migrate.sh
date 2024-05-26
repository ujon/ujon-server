#!/bin/bash
# @description Migrate sql in `db/migration/*` with flyway

docker run \
--rm \
--network=ujon_default \
-v ./db/migration:/flyway/sql \
-v ./setup/flyway.conf:/flyway/conf/flyway.conf \
flyway/flyway migrate
# docker run --rm --network=ujon_default -v ./db/migration:/flyway/sql -v ./setup/flyway.conf:/flyway/conf/flyway.conf flyway/flyway migrate