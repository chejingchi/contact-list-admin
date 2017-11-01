package com.michael.contactlist.rest;

import com.michael.contactlist.bean.Trainer;
import com.michael.contactlist.service.JlxxService;
import org.nutz.lang.util.NutMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author chejingchi
 *         创建时间:2017/9/22 上午1:08
 *         项目名称:contactList
 * @author 车竞驰
 * @version 1.0
 * @since JDK 1.8
 * 类说明:
 */
@RestController
@RequestMapping("jlxx")
public class JlxxController {

    @Autowired
    JlxxService service;


    @RequestMapping(value = "/queryJlList",method = RequestMethod.GET)
    public Object queryJlList() {
        List<Trainer> trainerList = service.queryJlList();
        return new NutMap().setv("success", true).setv("data", trainerList);
    }
}
