/*
  查询联系人
 */
select * from t_link_man l,t_user u where u.id = l.belong and u.id=@id

/* queryYYsj */
SELECT * FROM T_LRB_YYSJ
WHERE FID NOT IN (SELECT YYSJ FROM T_LRB_YYXX WHERE JLFID=@id AND YYRQ =@time)
ORDER BY FID