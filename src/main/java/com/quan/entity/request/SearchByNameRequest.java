package com.quan.entity.request;

import lombok.Data;

import javax.validation.constraints.Size;

/**
 * @author: xiexinquan520@163.com
 * User: XieXinQuan
 * DATE:2020/12/14
 */
@Data
public class SearchByNameRequest {

    @Size(min = 1, max = 5, message = "名称字数在1-5之间")
    private String name;
}
