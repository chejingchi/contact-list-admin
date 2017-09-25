package com.michael.contactlist.controller;

import com.michael.contactlist.bean.YysjBean;
import com.michael.contactlist.service.YysjService;
import org.nutz.lang.util.NutMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author chejingchi
 *         创建时间:2017/9/22 下午12:40
 *         项目名称:contactList
 * @author 车竞驰
 * @version 1.0
 * @since JDK 1.8
 * 类说明:
 */
@RestController
@RequestMapping("yysj")
public class YysjController {
    @Autowired
    YysjService service;


    /**
     * 查询--剩余时间段
     */
    @RequestMapping(value = "queryYysjList",method = RequestMethod.GET)
    @ResponseBody
    public Object queryYysjList(String jlfid, String yyrq) {
        NutMap queryInfo = new NutMap().setv("jlfid", jlfid).setv("yyrq", yyrq);
        NutMap resultMap = new NutMap();

        try {
            List<YysjBean> listYysj = service.queryYysjList(queryInfo);
            resultMap.setv("success", true).setv("data", listYysj);

        } catch (Exception e) {
            e.printStackTrace();
            resultMap.setv("success", false).setv("data", "");
        } finally {
            return resultMap;
        }

    }
}
