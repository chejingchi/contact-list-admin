package com.michael.contactlist.bean;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Table;

/**
 * @author chejingchi
 *         创建时间:2017/9/22 下午12:47
 *         项目名称:contactList
 * @author 车竞驰
 * @version 1.0
 * @since JDK 1.8
 * 类说明: 预约时间
 */
@Data
@NoArgsConstructor
@Table("T_LRB_YYSJ")
public class YysjBean {
    @Column
    private String fid;
    @Column
    private String yysj;
}
