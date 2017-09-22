package com.michael.contactlist.service;

import com.michael.contactlist.bean.Trainer;
import org.nutz.dao.Dao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author chejingchi
 *         创建时间:2017/9/22 上午9:47
 *         项目名称:contactList
 * @author 车竞驰
 * @version 1.0
 * @since JDK 1.8
 * 类说明:
 */
@Service
public class JlxxService {
    @Autowired
    Dao dao;

    public List<Trainer> queryJlList() {
        List<Trainer> trainers = dao.query(Trainer.class,null);
        return trainers;
    }
}
