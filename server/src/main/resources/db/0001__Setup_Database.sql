-- Database
CREATE DATABASE transparency;


-- User
CREATE USER ghost PASSWORD 'tellmethetruth';
GRANT ALL ON DATABASE transparency TO ghost;