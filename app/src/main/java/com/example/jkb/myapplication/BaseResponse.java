package com.example.jkb.myapplication;

import java.io.Serializable;

/**
 * Created by lz on 2017/3/22.
 */

public class BaseResponse<T> implements Serializable {

    Error error;
    T data;
    int result;
    boolean hasNext;//是否有下一页
    int pageNo;// 当前页码,在分页请求时需传递给后台，默认1
    int pageSize;//每页显示数量，每页固定显示的数量，在分页请求时需传递，默认10
    int pages;//总页数
    int total;//总记录条目数

    public Error getError() {
        return error;
    }

    public void setError(Error error) {
        this.error = error;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public boolean isHasNext() {
        return hasNext;
    }

    public void setHasNext(boolean hasNext) {
        this.hasNext = hasNext;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
