package com.quan.entity.response;

import com.alibaba.fastjson.annotation.JSONField;
import com.quan.Enum.UserTypeEnum;
import com.quan.annotation.ReturnReplace;
import lombok.Data;

import java.util.Date;

/**
 * @author: xiexinquan520@163.com
 * User: XieXinQuan
 * DATE:2020/12/16
 */
@Data
public class ShopEmployeeList {

    private Integer id;

    private String userName;

    private String shopName;

    @JSONField(format = "yyyy-MM-dd")
    private Date date;

    @ReturnReplace(value = UserTypeEnum.class)
    private Byte position;

}
