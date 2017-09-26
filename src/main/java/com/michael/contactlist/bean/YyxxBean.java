package com.michael.contactlist.bean;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.nutz.dao.entity.annotation.*;

/**
 * @author chejingchi
 *         创建时间:2017/9/22 下午1:23
 *         项目名称:contactList
 * @author 车竞驰
 * @version 1.0
 * @since JDK 1.8
 * 类说明:
 */
@Table("T_LRB_YYXX")
@Data
@NoArgsConstructor
@ToString
public class YyxxBean {
    @Name
    @Prev(els=@EL("uuid()"))
    private String fid;

    @Column
    private String jlfid;

    @Column
    private String jlmc;
    @Column
    private String hyxm;
    @Column
    private String yyrq;
    @Column
    private String yysj;

    @Column
    private String yysjmc;
    /**0:不可取消；1：可取消**/
    @Column
    private String isqx;
    /**会员备注**/
    @Column
    private String bz;

}
