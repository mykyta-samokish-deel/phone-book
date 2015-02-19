DROP TABLE pb_phones;
DROP TABLE pb_users;

CREATE TABLE pb_users(
  user_id SERIAL PRIMARY KEY,
  first_name VARCHAR(50),
  last_name VARCHAR(50)
);

CREATE TABLE pb_phones(
  phone_id VARCHAR(50),
  phone VARCHAR(50),
  user_id SERIAL,
  FOREIGN KEY (user_id) REFERENCES pb_users
);
