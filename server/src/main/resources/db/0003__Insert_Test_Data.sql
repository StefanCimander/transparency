DELETE FROM feature_links;
DELETE FROM features;
DELETE FROM packages;

-- Packages
INSERT INTO packages(id, parent_package_id, name) VALUES
  (1,    NULL,  'Customer Functions'),
  (15,   1,     'Driving Assistance'),
  (17,   1,     'Braking Functions'),
  (19,   1,     'Trailer Functions'),
  (21,   1,     'Steering System'),
  (83,   15,    'Lateral Control'),
  (89,   19,    'Trailer Assistant');


-- Features
INSERT INTO features(id, package_id, name) VALUES
  (172,  83,    'Lane Change Warning'),
  (47,   83,    'Lane Control');


-- Feature Links
INSERT INTO feature_links(linking_feature_id, linked_feature_id) VALUES
  (47, 172);
