#!/bin/bash
# @description Setting up Local Infrastructure

docker-compose -p ujon -f ./docker-compose.yml down
docker-compose -p ujon -f ./docker-compose.yml up -d