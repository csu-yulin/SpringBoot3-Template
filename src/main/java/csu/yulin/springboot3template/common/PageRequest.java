package csu.yulin.springboot3template.common;

import csu.yulin.springboot3template.constant.CommonConstant;
import lombok.Data;

@Data
public class PageRequest {

    /**
     * 当前页号
     */
    private Long current = 1L;

    /**
     * 页面大小
     */
    private Long pageSize = 10L;

    /**
     * 排序字段
     */
    private String sortField;

    /**
     * 排序顺序（默认升序）
     */
    private String sortOrder = CommonConstant.SORT_ORDER_ASC;
}