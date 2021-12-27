select
  count(id)
from
  review
where
  title like /* @infix(title) */'MINAMATA'
/*%if isEableOnly == true */
  and enable = 1
/*%end*/  
