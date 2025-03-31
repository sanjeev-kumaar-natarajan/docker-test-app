-- Create schema if it doesn't exist
CREATE SCHEMA IF NOT EXISTS test_docker_schema;

-- Set the default schema for the user
ALTER ROLE postgres SET search_path TO test_docker_schema;

-- Create table inside the schema
CREATE TABLE IF NOT EXISTS test_docker_schema.test_docker (
    id SERIAL PRIMARY KEY,
    value TEXT NOT NULL
);
