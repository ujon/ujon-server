#!/bin/bash
# @description Removing Local Infrastructure

docker-compose -p ujon -f ./docker-compose.yml down
rm -rf .infra