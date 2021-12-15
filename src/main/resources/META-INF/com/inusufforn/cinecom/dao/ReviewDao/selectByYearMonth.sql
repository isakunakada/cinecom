select
  id, title, short_comment, watched_date, theater
from
  review
where
  to_char(watched_date, 'YYYYMM') = /* yearMonth */'202111'
order by watched_date desc, watched_time desc
  limit /* limit */5
  offset /* offset */0
