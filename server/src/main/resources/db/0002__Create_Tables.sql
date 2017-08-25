-- Packages
CREATE TABLE IF NOT EXISTS packages (
  id                  BIGINT        NOT NULL  CONSTRAINT packages_pkey PRIMARY KEY,
  parent_package_id   BIGINT                  CONSTRAINT packages_packages_id_fk REFERENCES packages,
  name                VARCHAR(255)  NOT NULL
);

CREATE UNIQUE INDEX IF NOT EXISTS packages_id_uindex ON packages(id);


-- Features
CREATE TABLE IF NOT EXISTS features (
  id                  BIGINT        NOT NULL  CONSTRAINT features_pkey PRIMARY KEY,
  package_id          BIGINT        NOT NULL  CONSTRAINT features_packages_id_fk REFERENCES packages,
  name                VARCHAR(255)  NOT NULL
);

CREATE UNIQUE INDEX IF NOT EXISTS features_id_uindex ON features(id);


-- Feature Links
CREATE TABLE IF NOT EXISTS feature_links (
  linking_feature_id  BIGINT        NOT NULL  CONSTRAINT feature_links_feature_id_fk REFERENCES features,
  linked_feature_id   BIGINT        NOT NULL  CONSTRAINT feature_linked_feature_id_fk REFERENCES features
);
