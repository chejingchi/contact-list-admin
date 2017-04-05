package com.michael.contactlist.controller;

import com.michael.contactlist.bean.ContactInfo;
import com.michael.contactlist.bean.User;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.dao.Sqls;
import org.nutz.dao.sql.Sql;
import org.nutz.lang.util.NutMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author chejingchi
 *         创建时间:2017/4/1 下午4:58
 *         项目名称:contactList
 * @author 车竞驰
 * @version 1.0
 * @since JDK 1.8
 * 类说明:
 */
@RestController
@RequestMapping("/contactList")
public class ContactListController {

    Logger log = LoggerFactory.getLogger(ContactListController.class);

    @Autowired
    Dao dao;


    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public Object register(@RequestBody User user) {
        log.info(user.getUsername() + "i am here" + user.getPassword());
        dao.insert(user);
        return new NutMap().setv("user", user);
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Object login(@RequestBody User user) {
        log.info(user.getUsername() + "i am here" + user.getPassword());
        user = dao.fetch(User.class,
                Cnd.where("username", "=", user.getUsername()).and("password", "=", user.getPassword()));
        return new NutMap().setv("user", user);
    }

    @RequestMapping(value = "init", method = RequestMethod.POST)
    public Object init(@RequestBody User user) {
        log.info("user: {}", user);
        user = dao.fetch(user);
        Sql queryLinkManSql = Sqls.create("select * from t_link_man l,t_user u where u.id = l.belong and u.id=@id");
        queryLinkManSql.params().set("id", user.getId());
        queryLinkManSql.setCallback(Sqls.callback.entities());
        queryLinkManSql.setEntity(dao.getEntity(ContactInfo.class));
        dao.execute(queryLinkManSql);
        List<ContactInfo> contactInfos = queryLinkManSql.getList(ContactInfo.class);
        return new NutMap().setv("contactList", contactInfos).setv("user", user);
    }

    @RequestMapping(value = "addLinkMan", method = RequestMethod.POST)
    public Object addLinkMan(@RequestBody ContactInfo contactInfo) {
        dao.insert(contactInfo);
        return new NutMap().setv("flag", true);
    }

    @RequestMapping(value = "deleteLinkMan", method = RequestMethod.POST)
    public Object deleteLinkMan(@RequestBody ContactInfo contactInfo) {
        boolean flag;
        try {
            dao.delete(contactInfo);
            flag = true;
        } catch (Exception e) {
            log.error(e.getMessage());
            flag = false;
        }
        return new NutMap().setv("flag", flag);
    }

    /**
     * create table
     */
    public void createTable() {
        dao.drop(User.class);
        dao.drop(ContactInfo.class);
        dao.create(User.class, false);
        dao.create(ContactInfo.class, false);
    }

}
