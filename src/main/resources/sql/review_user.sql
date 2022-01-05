DROP TABLE IF EXISTS review_user;
CREATE TABLE review_user (
  id serial PRIMARY KEY,
  name varchar(64) UNIQUE NOT NULL,
  password varchar(128) NOT NULL,
  authority varchar(128) NOT NULL,
  created timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  created_by varchar(16) NOT NULL DEFAULT CURRENT_USER,
  updated_by varchar(16) NOT NULL DEFAULT CURRENT_USER 
);
COMMENT ON TABLE review_user is 'ユーザ情報';
COMMENT ON COLUMN review_user.id is 'ユーザID';
COMMENT ON COLUMN review_user.name is 'ユーザ名';
COMMENT ON COLUMN review_user.password is 'パスワード';
COMMENT ON COLUMN review_user.authority is 'ユーザ権限';
COMMENT ON COLUMN review_user.created is '作成日';
COMMENT ON COLUMN review_user.updated is '更新日';
COMMENT ON COLUMN review_user.created_by is '作成者';
COMMENT ON COLUMN review_user.updated_by is '更新者';
