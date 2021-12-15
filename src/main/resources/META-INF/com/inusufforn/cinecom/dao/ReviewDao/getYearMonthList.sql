select
  to_char(watched_date, 'YYYYMM') as year_month 
from
  review
group by 
  year_month
order by
  year_month
desc
