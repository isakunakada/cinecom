select
  count(id)
from
  review
where
  title like /* @infix(title) */'MINAMATA'
