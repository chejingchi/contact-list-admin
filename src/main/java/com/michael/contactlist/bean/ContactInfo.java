package com.michael.contactlist.bean;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author chejingchi
 *         创建时间:2017/3/31 下午3:07
 *         项目名称:gymnasium
 * @author 车竞驰
 * @version 1.0
 * @since JDK 1.8
 * 类说明:
 */
@Data
@ToString
@NoArgsConstructor
public class ContactInfo {

    private String contactName;

    private String phoneNum;

    private boolean active = false;

    private int userId;

    public ContactInfo(String contactName, String phoneNum, int userId) {
        this.contactName = contactName;
        this.phoneNum = phoneNum;
        this.userId = userId;
    }

    public ContactInfo(String contactName, String phoneNum) {
        this.contactName = contactName;
        this.phoneNum = phoneNum;
    }

}
