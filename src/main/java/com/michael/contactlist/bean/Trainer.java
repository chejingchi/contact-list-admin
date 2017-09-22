package com.michael.contactlist.bean;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Table;

/**
 * @author chejingchi
 *         创建时间:2017/9/22 上午12:50
 *         项目名称:contactList
 * @author 车竞驰
 * @version 1.0
 * @since JDK 1.8
 * 类说明:
 */
@Table("T_LRB_LJXX")
@Data
@ToString
@NoArgsConstructor
public class Trainer {
    @Column
    private String fid;

    @Column
    private String jlmc;

    @Column
    private String jlsfz;

    @Column
    private String jllxdh;

    @Column
    private String jnjj;


}
