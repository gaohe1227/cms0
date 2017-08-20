package com.common;

import java.math.BigDecimal;

/**
 * 分页Vo 其他Vo需继承此BaseVo
 * 
 * EasyUI 默认参数 page,rows
 * */
public class BaseVo {
	/**
	 * 当前页
	 */
	private Integer page;
	/**
	 * 每页显示记录数
	 */
	private Integer rows;
	/**
	 * 查询显示值
	 */
	private String typeValue;
	/**
	 * 查询条件
	 */
	private String typeName;
	/**
	 * 批量删除标识
	 */
	private String ids;
	/**
	 * sql查询时,设置返回对象为Vo,分页需要的属性
	 */
	private BigDecimal ROWNUM_;

	public BigDecimal getROWNUM_() {
		return ROWNUM_;
	}

	public void setROWNUM_(BigDecimal rOWNUM) {
		ROWNUM_ = rOWNUM;
	}

	private int begin;
	private int end;

	public void page() {
		begin = (page - 1) * rows;
		end = rows;
	}

	public String getTypeValue() {
		return typeValue;
	}

	public int getBegin() {
		return begin;
	}

	public void setBegin(int begin) {
		this.begin = begin;
	}

	public int getEnd() {
		return end;
	}

	public void setEnd(int end) {
		this.end = end;
	}

	public void setTypeValue(String typeValue) {
		this.typeValue = typeValue;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getRows() {
		return rows;
	}

	public void setRows(Integer rows) {
		this.rows = rows;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}
}
