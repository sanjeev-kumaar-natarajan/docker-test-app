#!/bin/sh
set -e

host="$1"
shift

POSTGRES_USER="postgres"
POSTGRES_PASSWORD="password"
DB_NAME="test_docker"
TABLE_NAME="test_docker"

echo "Sleeping 5 seconds to give Postgres more time..."
sleep 5

echo "Waiting for Postgres table $TABLE_NAME in database $DB_NAME at $host:5432 with user $POSTGRES_USER"

until echo "SELECT 1 FROM $TABLE_NAME LIMIT 1;" | PGPASSWORD="$POSTGRES_PASSWORD" psql -h "$host" -U "$POSTGRES_USER" -d "$DB_NAME" > /dev/null 2>&1; do
  echo "Table $TABLE_NAME not ready yet - sleeping"
  sleep 2
done

echo "Postgres and table are ready - executing command"
exec "$@"
