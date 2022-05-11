package com.ckk.demo.monomer.common.utils;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.ckk.demo.monomer.common.vo.Page;
import com.ckk.demo.monomer.common.vo.PageQuery;
import com.github.pagehelper.PageHelper;
import lombok.experimental.UtilityClass;

import javax.servlet.http.HttpServletRequest;

@UtilityClass
public class PageUtil {

    private static final String currentStr = "pageNumber";
    private static final String sizeStr = "pageSize";


    public static Page empty() {
        HttpServletRequest request = WebUtil.getRequest();
        Integer current = getCurrent(request);
        Integer size = getSize(request);
        Page page = Page.getInstance();
        page.setPageNumber(current).setPageSize(size).setTotal(0);
        return page;
    }

    public static Page startPage() {
        Page page = empty();
        HttpServletRequest request = WebUtil.getRequest();
        PageHelper.startPage(page.getPageNumber(), page.getPageSize());
        return page;
    }

    public static Page startPage(PageQuery query) {

        Integer pageNumber = (query.getPageNumber() == null || query.getPageNumber() == 0) ? 1 : query.getPageNumber();
        Integer pageSize = (query.getPageSize() == null || query.getPageSize() == 0) ? 10 : query.getPageSize();
        Page page = Page.getInstance();
        page.setPageNumber(pageNumber).setPageSize(pageSize).setTotal(0);
        PageHelper.startPage(pageNumber, pageSize);
        return page;
    }


    private Integer getCurrent(HttpServletRequest request) {
        try {
            String current = request.getParameter(currentStr);
            return StringUtils.isBlank(current) ? 1 : Integer.parseInt(current);
        } catch (Exception e) {
            return 1;
        }
    }

    private Integer getSize(HttpServletRequest request) {
        try {
            String size = request.getParameter(sizeStr);
            return StringUtils.isBlank(size) ? 10 : Integer.parseInt(size);
        } catch (Exception e) {
            return 10;
        }
    }

}
