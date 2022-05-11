package com.ckk.demo.monomer.common.vo;

import lombok.Data;

import java.util.Collections;
import java.util.List;

@Data
public class Page<T> {

    private List<T> records;
    private long total;
    private int pageSize;
    private int pageNumber;

    public static Page getInstance(){
        return new Page<>();
    }

    public static Page getInstance(List records){
        return new Page<>(records);
    }

    public Page() {
        this.records = Collections.emptyList();
        this.total = 0L;
        this.pageSize = 10;
        this.pageNumber = 1;
    }

    public Page(List<T> records) {
        this.records = records;
        this.total = 0L;
        this.pageSize = 10;
        this.pageNumber = 1;
    }

    public Page<T> setTotal(long total) {
        this.total = total;
        return this;
    }

    public Page<T> setPageSize(int pageSize) {
        this.pageSize = pageSize;
        return this;
    }

    public Page<T> setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
        return this;
    }

}
