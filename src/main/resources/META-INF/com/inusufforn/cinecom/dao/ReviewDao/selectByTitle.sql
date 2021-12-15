select
  id, title, short_comment, watched_date, theater
from
  review
where
  title like /* @infix(title) */'MINAMATA'
order by watched_date desc, watched_time desc
  limit /* limit */5
  offset /* offset */0
