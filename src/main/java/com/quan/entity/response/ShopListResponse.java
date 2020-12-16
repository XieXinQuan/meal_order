package com.quan.entity.response;

import com.alibaba.fastjson.annotation.JSONField;
import com.quan.Enum.CommonByteEnum;
import com.quan.annotation.ReturnReplace;
import lombok.Data;

import java.util.Date;

/**
 * @author: xiexinquan520@163.com
 * User: XieXinQuan
 * DATE:2020/12/14
 */
@Data
public class ShopListResponse {

    private Integer id;

    @JSONField(format = "yyyy-MM-dd")
    private Date date;

    private String createUserName;

    private String shopName;

    private String shopManager;

    @ReturnReplace(value = CommonByteEnum.class)
    private Byte status;
}
