package com.michael.contactlist.controller;

import com.michael.contactlist.bean.YyxxBean;
import com.michael.contactlist.service.YyxxService;
import org.nutz.lang.util.NutMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

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

    /**
     * 查询--根据会员姓名查询我的预约
     */
    @RequestMapping(value = "queryByHyxm",method = RequestMethod.POST)
    @ResponseBody
    public Object queryByHyxm(@RequestBody String hymc){
        NutMap resultMap = new NutMap();
        try {
            List<YyxxBean> listYyxx = service.queryByHyxm(hymc);
            resultMap.setv("success", true).setv("data", listYyxx);

        } catch (Exception e) {
            e.printStackTrace();
            resultMap.setv("success", false).setv("data", "");
        }

        return resultMap;
    }

    /**
     * 查询--所有会员预约
     */
    @RequestMapping("queryAllYyxx")
    @ResponseBody
    public HashMap<String,Object> queryAllYyxx(){
        NutMap resultMap = new NutMap();
        try {
            List<YyxxBean> listYyxx = service.queryAllYyxx();
            resultMap.setv("success", true)
                    .setv("data", listYyxx);

        } catch (Exception e) {
            e.printStackTrace();
            resultMap.setv("success", false)
                    .setv("data", "");
        }

        return resultMap;
    }

    /**
     * 入库--取消预约
     */
    @RequestMapping("delMyyyByFid")
    @ResponseBody
    public String delMyyyByFid(@RequestBody String fid){
        service.delMyyyByFid(fid);
        return "取消成功";
    }

}
