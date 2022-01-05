select
  count(id)
from
  review
where
  to_char(watched_date, 'YYYYMM') = (SELECT to_char(max(watched_date), 'YYYYMM') FROM review)
/*%if isEableOnly == true */
  and enable = 1
/*%end*/  
