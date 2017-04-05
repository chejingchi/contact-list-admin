/*
  查询联系人
 */
select * from t_link_man l,t_user u where u.id = l.belong and u.id=@id