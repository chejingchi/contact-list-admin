package com.michael.contactlist.bean;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

/**
 * @author chejingchi
 *         创建时间:15/12/11 下午5:40
 *         项目名称:myFirstNutzTest
 * @author 车竞驰
 * @version 1.0
 * @since JDK 1.8
 * 类说明:
 */
@Table("t_user")
@Data
@ToString
@NoArgsConstructor
public class User {
    @Id
    private int id;

    @Column
    private String username;

    @Column("password")
    private String password;

    @Column
    private String context;

}

