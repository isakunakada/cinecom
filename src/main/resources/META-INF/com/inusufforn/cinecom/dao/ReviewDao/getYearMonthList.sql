select
  to_char(watched_date, 'YYYYMM') as year_month 
from
  review
  where
/*%if isEableOnly == true */
  enable = 1
/*%end*/  
group by 
  year_month
order by
  year_month
desc
