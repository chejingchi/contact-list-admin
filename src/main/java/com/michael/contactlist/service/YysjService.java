package com.michael.contactlist.service;

import com.michael.contactlist.bean.YysjBean;
import org.nutz.dao.Dao;
import org.nutz.dao.Sqls;
import org.nutz.dao.sql.Sql;
import org.nutz.lang.util.NutMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author chejingchi
 *         创建时间:2017/9/22 下午12:41
 *         项目名称:contactList
 * @author 车竞驰
 * @version 1.0
 * @since JDK 1.8
 * 类说明:
 */
@Service
public class YysjService {
    @Autowired
    Dao dao;


    public List<YysjBean> queryYysjList(NutMap queryInfo) {
        Sql queryYYsjSql = Sqls.create(dao.sqls().get("queryYYsj"));
        queryYYsjSql.params()
                .set("id", queryInfo.getString("jlfid"))
                .set("time", queryInfo.getString("yyrq"));
        queryYYsjSql.setCallback(Sqls.callback.entities());
        queryYYsjSql.setEntity(dao.getEntity(YysjBean.class));
        dao.execute(queryYYsjSql);
        List<YysjBean> yysjBeanList = queryYYsjSql.getList(YysjBean.class);
        return yysjBeanList;
    }
}
