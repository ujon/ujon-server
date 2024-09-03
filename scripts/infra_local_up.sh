#!/bin/bash
# @description Setting up Local Infrastructure

docker-compose -p ujon -f ./docker-compose.yml down
docker-compose -p ujon -f ./docker-compose.yml up -d
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