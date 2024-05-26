#!/bin/bash

description() {
    local script_file="$1"
    grep '@description' "$script_file" | sed 's/# @description//'
}

help() {
  echo "Commands:"
  echo "-h, --help: Lists commands and their descriptions"
  echo "-iu, --infra-up:$(description "./scripts/infra_local_up.sh")"
  echo "-id, --infra-down:$(description "./scripts/infra_local_down.sh")"
  echo "-im, --infra-migrate:$(description "./scripts/infra_local_migrate.sh")"
}

case "$1" in
  -iu | --infra-up)
    source ./scripts/infra_local_up.sh
    ;;
  -id | --infra-down)
    source ./scripts/infra_local_down.sh
    ;;
  -im | --infra-migrate)
    source ./scripts/infra_local_migrate.sh
    ;;
  -h | --help)
    help
    ;;
  *)
    echo "Invalid option. Use -h or --help to see available options."
    help
    ;;
esac