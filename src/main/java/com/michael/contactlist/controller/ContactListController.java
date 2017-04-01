package com.michael.contactlist.controller;

import com.michael.contactlist.bean.ContactInfo;
import com.michael.contactlist.bean.User;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.lang.util.NutMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
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
        String context = user.getContext();
        String[] contextArr = context.split(",");
        List<ContactInfo> contactInfos = new ArrayList<>();
        for (int i = 0; i < contextArr.length; i += 2) {
            contactInfos.add(new ContactInfo(contextArr[i], contextArr[i + 1]));
        }
        return new NutMap().setv("contactList", contactInfos).setv("user", user);
    }

    @RequestMapping(value = "addLinkMan", method = RequestMethod.POST)
    public Object addLinkMan(@RequestBody ContactInfo contactInfo) {
        User user = dao.fetch(User.class, contactInfo.getUserId());
        user.setContext(user.getContext() + ',' + contactInfo.getContactName() + ',' + contactInfo.getPhoneNum());
        dao.update(user);
        return new NutMap().setv("flag", true);
    }

}
