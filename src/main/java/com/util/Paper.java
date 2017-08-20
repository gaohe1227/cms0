package com.util;

import java.util.ArrayList;
import java.util.List;

/**
 * 分页对象
 * @author Administrator
 *
 * @param <T>
 */
public class Paper<T> {
	/**
	 * 分页的大小
	 */
	private int size=10;
	/**
	 * 分页的起始页
	 */
	private int offset=1;
	/**
	 * 总记录数
	 */
	private Long total=0L;
	private Integer count=0;

    private int first = 0;//起始数据
	/**
	 * 分页的数据
	 */
	private List<T> datas;
	
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public int getOffset() {
		return offset;
	}
	public void setOffset(int offset) {
		this.offset = offset;
	}
	 
	public Long getTotal() {
		return total;
	}
	public void setTotal(Long total) {
		this.total = total;
		if(total!=null){
			count=total.intValue();
		}
	}
	public List<T> getDatas() {
		if(null==datas){
			return new ArrayList<>();
		}
		return datas;
	}
	public void setDatas(List<T> datas) {
		this.datas = datas;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public int getFirst() {
		return (this.offset-1)*size;
	}
	public void setFirst(int first) {
		this.first = first;
	}
}
