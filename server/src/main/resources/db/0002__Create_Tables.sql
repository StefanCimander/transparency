DROP TABLE IF EXISTS logical_dependencies;
DROP TABLE IF EXISTS feature_function_mappings;
DROP TABLE IF EXISTS feature_links;
DROP TABLE IF EXISTS function_signal_links;
DROP TABLE IF EXISTS logical_functions;
DROP TABLE IF EXISTS logical_signals;
DROP TABLE IF EXISTS features;
DROP TABLE IF EXISTS packages;


-- Packages
CREATE TABLE IF NOT EXISTS packages (
  id                  BIGINT        NOT NULL  CONSTRAINT packages_pk PRIMARY KEY,
  parent_package_id   BIGINT                  CONSTRAINT packages_packages_id_fk REFERENCES packages,
  name                VARCHAR(255)  NOT NULL
);

CREATE UNIQUE INDEX IF NOT EXISTS packages_id_uindex ON packages(id);


-- Features
CREATE TABLE IF NOT EXISTS features (
  id                  BIGINT        NOT NULL  CONSTRAINT features_pk PRIMARY KEY,
  package_id          BIGINT        NOT NULL  CONSTRAINT features_packages_id_fk REFERENCES packages,
  name                VARCHAR(255)  NOT NULL
);

CREATE UNIQUE INDEX IF NOT EXISTS features_id_uindex ON features(id);


-- Feature Links
CREATE TABLE IF NOT EXISTS feature_links (
  linking_feature_id  BIGINT        NOT NULL  CONSTRAINT feature_links_feature_id_fk REFERENCES features,
  linked_feature_id   BIGINT        NOT NULL  CONSTRAINT feature_linked_feature_id_fk REFERENCES features
);


-- Logical Dependencies
CREATE TABLE IF NOT EXISTS logical_dependencies (
  dependent_feature_id  BIGINT      NOT NULL  CONSTRAINT logical_dependent_feature_id_fk REFERENCES features,
  target_feature_id     BIGINT      NOT NULL  CONSTRAINT logical_dependencies_target_feature_id_fk REFERENCES features
);


-- Logical Functions
CREATE TABLE IF NOT EXISTS logical_functions (
  id                  BIGINT        NOT NULL  CONSTRAINT logical_functions_pk PRIMARY KEY,
  name                VARCHAR(255)  NOT NULL
);


-- Feature Function Mapping
CREATE TABLE IF NOT EXISTS feature_function_mappings (
  feature_id          BIGINT        NOT NULL CONSTRAINT feature_function_mappings_feature_id_fk REFERENCES features,
  logical_function_id BIGINT        NOT NULL CONSTRAINT feature_function_mappings_logical_function_id_fk REFERENCES logical_functions
);


-- Logical Signals
CREATE TABLE IF NOT EXISTS logical_signals (
  id                  BIGINT        NOT NULL CONSTRAINT logical_signals_pk PRIMARY KEY,
  name                VARCHAR(255)  NOT NULL
);


-- Function Signal Links
CREATE TABLE IF NOT EXISTS function_signal_links (
  id                  BIGINT        NOT NULL  CONSTRAINT function_signal_links_pk PRIMARY KEY,
  function_id         BIGINT        NOT NULL CONSTRAINT function_signal_links_function_id_fk REFERENCES logical_functions,
  signal_id           BIGINT        NOT NULL CONSTRAINT function_signal_links_signal_id_fk REFERENCES logical_signals,
  direction           VARCHAR(255)  NOT NULL
);


GRANT SELECT, INSERT, UPDATE, DELETE ON ALL TABLES IN SCHEMA public TO ghost;
