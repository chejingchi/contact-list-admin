package com.michael.contactlist.service;

import com.michael.contactlist.bean.YyxxBean;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.dao.Sqls;
import org.nutz.dao.sql.Sql;
import org.nutz.dao.sql.SqlCallback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
        String queryByName = "\n" +
                "SELECT \n" +
                "    a.*,\n" +
                "    b.JLMC,\n" +
                "    c.yysj yysjmc,\n" +
                "    CASE\n" +
                "        WHEN\n" +
                "            a.YYRQ = DATE_FORMAT(SYSDATE(), '%Y-%m-%d')\n" +
                "                AND (SUBSTR(c.yysj, 0, 2) - DATE_FORMAT(SYSDATE(), '%H')) < 2\n" +
                "                AND (SUBSTR(c.yysj, 0, 2) - DATE_FORMAT(SYSDATE(), '%H')) > 0\n" +
                "        THEN\n" +
                "            0\n" +
                "        ELSE 1\n" +
                "    END ISQX\n" +
                "FROM\n" +
                "    T_LRB_YYXX a\n" +
                "        LEFT JOIN\n" +
                "    T_LRB_LJXX b ON b.FID = a.JLFID\n" +
                "        LEFT JOIN\n" +
                "    T_LRB_YYSJ c ON c.FID = a.YYsj\n" +
                "WHERE\n" +
                "    a.HYXM = '啊哈哈哈。。。'\n" +
                "        AND a.YYRQ >= date_format(SYSDATE(),'%Y-%m-%d');";
        Sql queryByNameSql = Sqls.create(queryByName);
        queryByNameSql.params().set("hymc", hymc);
        List<YyxxBean> retList = getYyxxBeans(queryByNameSql, false);
        return retList;
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
        Sql queryAllYyxxSql = Sqls.create(queryAllYyxxStr);
        List<YyxxBean> retList = getYyxxBeans(queryAllYyxxSql, true);
        return retList;
    }

    private List<YyxxBean> getYyxxBeans(Sql sql, final boolean flag) {
        sql.setCallback(new SqlCallback() {
            @Override
            public Object invoke(Connection connection, ResultSet resultSet, Sql sql) throws SQLException {
                List<YyxxBean> retList = new ArrayList();
                YyxxBean tmp = null;
                while (resultSet.next()) {
                    tmp = new YyxxBean();
                    tmp.setFid(resultSet.getString("FID"));
                    tmp.setHyxm(resultSet.getString("HYXM"));
                    tmp.setYyrq(resultSet.getString("YYRQ"));
                    tmp.setJlmc(resultSet.getString("JLMC"));
                    tmp.setYysjmc(resultSet.getString("YYSJMC"));
                    if (flag) {
                        tmp.setBz(resultSet.getString("bz"));
                    } else {
                        tmp.setIsqx(resultSet.getString("ISQX"));
                        tmp.setJlfid(resultSet.getString("jlfid"));
                        tmp.setYysj(resultSet.getString("yysj"));

                    }
                    retList.add(tmp);
                }
                return retList;
            }
        });
        dao.execute(sql);
        return sql.getList(YyxxBean.class);
    }

    public void delMyyyByFid(String fid) {
        YyxxBean yyxxBean = new YyxxBean();
        yyxxBean.setFid(fid);
        dao.delete(yyxxBean);
    }
}
