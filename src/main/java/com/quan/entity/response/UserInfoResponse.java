package com.quan.entity.response;

import com.alibaba.fastjson.annotation.JSONField;
import com.quan.Enum.SexEnum;
import com.quan.annotation.ReturnReplace;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

/**
 * @author: xiexinquan520@163.com
 * User: XieXinQuan
 * DATE:2020/12/12
 */
@Data
@Builder
@AllArgsConstructor
public class UserInfoResponse {

    private String loginName;

    private String nickName;

    private Integer workNumber;

    private String shopName;

    private String employeeName;

    @JSONField(format = "yyyy-MM-dd")
    private Date firstWorkTime;

    private Long inWorkTime;

    @ReturnReplace(value = SexEnum.class)
    private String sex;
}
