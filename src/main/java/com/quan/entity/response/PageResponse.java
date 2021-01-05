package com.quan.entity.response;

import lombok.Data;

import java.util.List;

/**
 * @author: xiexinquan520@163.com
 * User: XieXinQuan
 * DATE:2021/1/4
 */
@Data
public class PageResponse<T> {

    public PageResponse(List<T> data, long size){
        this.data = data;
        this.size = size;
    }

    private long size;

    private List<T> data;

}
