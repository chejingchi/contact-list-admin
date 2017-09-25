package com.michael.contactlist.service;

import com.michael.contactlist.bean.YyxxBean;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.dao.Sqls;
import org.nutz.dao.sql.Sql;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
        Cnd condition = Cnd.where("jlfid", "=", yyxxBean.getJlfid())
                .and("YYSJ", "=", yyxxBean.getYysj())
                .and("YYRQ", "=", yyxxBean.getYyrq());
        return dao.count(YyxxBean.class, condition);

    }

    public void addYyxx(YyxxBean yyxxBean) {
        dao.insert(yyxxBean);
    }

    public List<YyxxBean> queryByHyxm(String hymc) {
        Cnd condition = Cnd.where("hymc", "=", hymc);
        List<YyxxBean> yyxxBeanList = dao.query(YyxxBean.class, condition);
        return yyxxBeanList;
    }

    public List<YyxxBean> queryAllYyxx() {
        String queryAllYyxxStr = "SELECT \n" +
                "  \t\t\ta.FID,a.HYXM,a.YYRQ,b.JLMC,c.YYSJ AS YYSJMC,d.bz \n" +
                "  \t\tFROM  t_lrb_yyxx a\n" +
                "\t\tLEFT JOIN t_lrb_ljxx b ON b.FID = a.JLFID\n" +
                "\t\tLEFT JOIN t_lrb_yysj c ON c.FID = a.YYSJ\n" +
                "\t\tLEFT JOIN t_lrb_hyxx d ON d.FID = a.HYXM\n" +
                "\t\twhere a.YYRQ >=date_format(SYSDATE(),'%Y-%m-%d')\n" +
                "\t\tORDER BY a.YYRQ,b.JLMC";
        Sql queryAllYyxx = Sqls.create(queryAllYyxxStr);
        queryAllYyxx.setCallback(Sqls.callback.entities());
        queryAllYyxx.setEntity(dao.getEntity(YyxxBean.class));
        dao.execute(queryAllYyxx);
        List<YyxxBean> retList = queryAllYyxx.getList(YyxxBean.class);
        return retList;
    }

    public void delMyyyByFid(String fid) {
        YyxxBean yyxxBean = new YyxxBean();
        yyxxBean.setFid(fid);
        dao.delete(yyxxBean);
    }
}
