package cn.it.shop.model;

import java.io.Serializable;
import java.util.List;

public class Pager<T> implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	private int pageSize;//每页显示多少条数据
	private int currentPage;//当前页
	private int totalRecord;//一共多少条数据
	private int totalPage;//一共多少页
	private List<T> dataList;//数据
	
	public Pager() {
		super();
	}
	
	public Pager(int pageSize, int currentPage, int totalRecord, int totalPage,
			List<T> dataList) {
		super();
		this.pageSize = pageSize;
		this.currentPage = currentPage;
		this.totalRecord = totalRecord;
		this.totalPage = totalPage;
		this.dataList = dataList;
	}
	
	public Pager(int pageNum, int pageSize, List<T> sourceList){
		if(sourceList==null){
			return;
		}
		this.totalRecord = sourceList.size();
		this.pageSize = pageSize; 
		this.totalPage = this.totalRecord/this.pageSize;
		if(this.totalRecord % this.pageSize!=0){  
			this.totalPage = this.totalPage+1;
		}
		
	
		if(this.totalPage<pageNum){
			this.currentPage = this.totalPage;
		}else {
			this.currentPage = pageNum;
		}
		
		
		int fromIndex ;
		if(this.currentPage != 0){
			fromIndex = this.pageSize* (this.currentPage-1);
		}else {
			fromIndex = 0;
		}
		
		
		
		int toIndex;
		if(this.pageSize * this.currentPage >this.totalRecord){
			toIndex = this.totalRecord;
		}else {
			toIndex = this.pageSize * this.currentPage;
		}
		this.dataList = sourceList.subList(fromIndex, toIndex);
	}
	
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getTotalRecord() {
		return totalRecord;
	}
	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public List<T> getDataList() {
		return dataList;
	}
	public void setDataList(List<T> dataList) {
		this.dataList = dataList;
	}
	
	

}
