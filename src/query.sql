DROP TABLE pb_users_data;
DROP TABLE pb_users;

CREATE TABLE pb_users(
  user_id SERIAL PRIMARY KEY,
  first_name VARCHAR(50),
  last_name VARCHAR(50)
);

CREATE TABLE pb_users_data(
  phone VARCHAR(50),
  address VARCHAR(100),
  mail VARCHAR(35),
  user_id SERIAL,
  FOREIGN KEY (user_id) REFERENCES pb_users ON DELETE CASCADE
);