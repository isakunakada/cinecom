select
  count(id)
from
  review
where
  to_char(watched_date, 'YYYYMM') = /* yearMonth */'202111'
/*%if isEableOnly == true */
  and enable = 1
/*%end*/  
