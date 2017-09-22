package com.michael.contactlist.controller;

import com.michael.contactlist.bean.YyxxBean;
import com.michael.contactlist.service.YyxxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author chejingchi
 *         创建时间:2017/9/22 下午1:41
 *         项目名称:contactList
 * @author 车竞驰
 * @version 1.0
 * @since JDK 1.8
 * 类说明:
 */
@RestController
@RequestMapping("yyxx")
public class YyxxController {

    @Autowired
    YyxxService service;

    /**
     * 入库--新增
     * @param yyxxBean
     * @return
     */
    @RequestMapping(value = "addYyxx",method = RequestMethod.POST)
    @ResponseBody
    public String addYyxx(@RequestBody YyxxBean yyxxBean){
        //查询是否已预约
        int count = service.queryIsYy(yyxxBean);
        if(0==count){
            service.addYyxx(yyxxBean);
            return "预约成功";
        }else{
            return "已预约";
        }
    }


}
