package com.michael.contactlist.service;

import com.michael.contactlist.bean.YyxxBean;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author chejingchi
 *         创建时间:2017/9/22 下午1:44
 *         项目名称:contactList
 * @author 车竞驰
 * @version 1.0
 * @since JDK 1.8
 * 类说明:
 */
@Service
public class YyxxService {
    @Autowired
    Dao dao;
    public int queryIsYy(YyxxBean yyxxBean) {
        Cnd condition = Cnd.where("jlfid","=",yyxxBean.getJlfid())
                .and("YYSJ","=",yyxxBean.getYysj())
                .and("YYRQ","=",yyxxBean.getYyrq());
        return dao.count(YyxxBean.class, condition);

    }

    public void addYyxx(YyxxBean yyxxBean) {
        dao.insert(yyxxBean);
    }
}
