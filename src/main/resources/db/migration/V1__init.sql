CREATE TABLE IF NOT EXISTS user_account(
  id SERIAL not null PRIMARY KEY,
  first_name varchar(50),
  last_name varchar(50),
  password varchar(1024),
  email varchar(1024),
  role varchar(30),
  created_on TIMESTAMP WITHOUT TIME ZONE,
  updated_on TIMESTAMP WITHOUT TIME ZONE
);