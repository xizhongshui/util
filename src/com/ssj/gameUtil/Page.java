package com.ssj.gameUtil;

public class Page {
	private int onePageCount=20;
	private int pageIndex=20;
	private int offStart=1;
	private int totalPage=0;
	private int dataCount=0;
	
	public Page(int onePageCount, int pageIndex) {
		this.onePageCount = onePageCount;
		this.pageIndex = pageIndex;
	}
	
	public void init(int dataCount){
		this.dataCount = dataCount;
		onePageCount=onePageCount<=0?20:onePageCount;
		totalPage=dataCount%onePageCount==0?(dataCount/onePageCount):(dataCount/onePageCount)+1;//(dataCount/onePageCount)+1
		pageIndex=(pageIndex<=0 || pageIndex>totalPage )?1:pageIndex;
		offStart=onePageCount*(pageIndex-1);
	}

	public int getOnePageCount() {
		return onePageCount;
	}

	public int getPageIndex() {
		return pageIndex;
	}

	public int getOffStart() {
		return offStart;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public int getDataCount() {
		return dataCount;
	}

}
