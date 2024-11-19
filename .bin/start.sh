#!/bin/bash

docker compose -p ujon -f ./docker-compose.yml down
docker compose -p ujon -f ./docker-compose.yml up -d
