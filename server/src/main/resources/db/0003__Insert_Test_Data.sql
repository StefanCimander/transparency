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
  (83,   15,    'Lateral Control');


-- Features
INSERT INTO features(id, package_id, name) VALUES
  (47,   83,    'Lane Control'),
  (172,  83,    'Lane Change Warning'),
  (74,   17,    'Electrical Locking Brake'),
  (51,   19,    'Trailer Light Control'),
  (15,   15,    'Priority Warning'),
  (5,    15,    'Cross Traffic Assistant'),
  (64,   15,    'Reverse Assistant'),
  (92,   21,    'Electrical Steering Support'),
  (20,   15,    'Speed Control'),
  (204,  15,    'Vehicle State Monitor');


-- Feature Links
INSERT INTO feature_links(linking_feature_id, linked_feature_id) VALUES
  (172, 47),
  (15, 5),
  (92, 20),
  (74, 204),
  (64, 204);
