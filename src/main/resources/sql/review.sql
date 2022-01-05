DROP TABLE IF EXISTS review;
DROP SEQUENCE IF EXISTS review_id_seq;
CREATE TABLE review (
  id serial PRIMARY KEY,
  title varchar(64),
  short_comment varchar(128),
  watched_date date,
  watched_time time,
  theater varchar(64),
  screen varchar(16),
  main_comment text,
  rating smallint,
  enable smallint,
  created timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  created_by varchar(16) NOT NULL DEFAULT CURRENT_USER,
  updated_by varchar(16) NOT NULL DEFAULT CURRENT_USER 
);
COMMENT ON TABLE review is ' 作品レビュー';
COMMENT ON COLUMN review.id is ' 作品ID';
COMMENT ON COLUMN review.title is ' 作品タイトル';
COMMENT ON COLUMN review.short_comment is ' 見出しコメント';
COMMENT ON COLUMN review.watched_date is ' 鑑賞日';
COMMENT ON COLUMN review.watched_time is ' 上映時刻';
COMMENT ON COLUMN review.theater is ' 劇場名';
COMMENT ON COLUMN review.screen is ' スクリーン';
COMMENT ON COLUMN review.main_comment is ' コメント本文';
COMMENT ON COLUMN review.rating is ' 評価';
COMMENT ON COLUMN review.enable is ' 有効フラグ';
COMMENT ON COLUMN review.created is ' 作成日';
COMMENT ON COLUMN review.updated is ' 更新日';
COMMENT ON COLUMN review.created_by is '作成者';
COMMENT ON COLUMN review.updated_by is '更新者';
