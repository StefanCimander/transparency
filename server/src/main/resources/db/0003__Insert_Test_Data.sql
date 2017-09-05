DELETE FROM feature_function_mappings;
DELETE FROM function_signal_links;
DELETE FROM logical_functions;
DELETE FROM logical_signals;
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
  (172,  47),
  (15,   5),
  (92,   20),
  (74,   204),
  (64,   204);


-- Logical Functions
INSERT INTO logical_functions(id, name) VALUES
  (71,          'Four Wheel Drive Check'),
  (263,         'External Position Provider'),
  (161,         'Vehicle Guidance Actuating Value Generator');


-- Feature Function Mappings
INSERT INTO feature_function_mappings(feature_id, logical_function_id) VALUES
  (20,   71),
  (20,   263),
  (64,   161);


-- Logical Signals
INSERT INTO logical_signals(id, name) VALUES
  (863,        'Odometer Vehicle Position');


-- Function Signal Links
INSERT INTO function_signal_links(id, function_id, signal_id, direction) VALUES
  (878,   263,   863,   'SENDER'),
  (265,   161,   863,   'RECEIVER');
