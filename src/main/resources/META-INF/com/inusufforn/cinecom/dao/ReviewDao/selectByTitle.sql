select
  id, title, short_comment, watched_date, theater
from
  review
where
  title like /* @infix(title) */'MINAMATA'
/*%if isEableOnly == true */
  and enable = 1
/*%end*/  
order by watched_date desc, watched_time desc
  limit /* limit */5
  offset /* offset */0
