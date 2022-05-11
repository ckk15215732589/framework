package com.ckk.demo.monomer.common.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@Data
public class PageQuery {

    /**
     * 当前页
     */
    @ApiModelProperty(value = "页码")
    private Integer pageNumber;

    /**
     * 每页的数量
     */
    @ApiModelProperty(value = "每页的数量")
    private Integer pageSize;

}
