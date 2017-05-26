package cn.itheima.com.bean;

import java.util.List;

public class Page {
	//商品总数
	private long recordCount;
	//总页数
	private long pageCount;
	//数据
	private List<Product> list;
 
	public long getRecordCount() {
		return recordCount;
	}
	public void setRecordCount(long recordCount) {
		this.recordCount = recordCount;
	}
	public long getPageCount() {
		return pageCount;
	}
	public void setPageCount(long pageCount) {
		this.pageCount = pageCount;
	}
	public List<Product> getList() {
		return list;
	}
	public void setList(List<Product> list) {
		this.list = list;
	}
	
	
	
}
