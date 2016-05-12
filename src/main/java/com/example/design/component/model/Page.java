package com.example.design.component.model;

/**
 * Created by lxh on 16/5/12.
 */
public class Page {
  private long offset;
  private long limit;
  private long pageNumber;
  private long pageSize;

  public Page() {

  }

  public Page(long offset, long limit) {
    this.offset = offset;
    this.limit = limit;
    this.pageNumber = 0;
    this.pageSize = 0;
  }

  public Page(long offset, long limit, long pageNumber, long pageSize) {
    this.offset = offset;
    this.limit = limit;
    this.pageNumber = pageNumber;
    this.pageSize = pageSize;
  }

  public long getOffset() {
    return offset;
  }

  public void setOffset(long offset) {
    this.offset = offset;
  }

  public long getLimit() {
    return limit;
  }

  public void setLimit(long limit) {
    this.limit = limit;
  }

  public long getPageNumber() {
    return pageNumber;
  }

  public void setPageNumber(long pageNumber) {
    this.pageNumber = pageNumber;
  }

  public long getPageSize() {
    return pageSize;
  }

  public void setPageSize(long pageSize) {
    this.pageSize = pageSize;
  }

  @Override
  public String toString() {
    return "{Offset:" + this.offset + "\nLimit:" + this.limit + "\nPageNumber" + this.pageNumber
            + "\nPageSize" + this.pageSize;
  }
}
