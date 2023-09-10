infra_start() {
  docker-compose -p ujon-infra -f ./config/db/local-infra.yml down
  docker-compose -p ujon-infra -f ./config/db/local-infra.yml up -d
}

infra_end() {
  docker-compose -p ujon-infra -f ./config/db/local-infra.yml down
}

infra_db_inject() {
  docker cp config/db/schema/ ujon-infra-pg:schema/
  docker exec -it ujon-infra-pg psql -U root -d ujon_local -f schema/ujon.sql
}

help() {
  echo "-h | --help"
  echo "-is | --infra-start"
  echo "-ie | --infra-end"
  echo "-idi | --infra-db-inject"
}

case "$1" in
-is | --infra-start)
  infra_start
  ;;
-ie | --infra-end)
  infra_end
  ;;
-idi | --infra-db-inject)
  infra_db_inject
  ;;
-h | --help | *)
  help
  ;;
esac