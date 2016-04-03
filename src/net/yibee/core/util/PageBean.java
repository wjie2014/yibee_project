package net.yibee.core.util;

import java.util.List;

/**
 * <pre>
 * 	功能描述:查询分页数据时公用类
 * </pre>
 * 
 * @author 易成杰
 * @日期:2014-12-01 下午03:52:21
 * @return
 */
public class PageBean {

	// 指定的或是页面参数
	private int currentPage = 1; // 当前页
	private int pageSize = 10; // 每页显示多少条

	// 上一页,下一页
	private int prePage;
	private int nextPage;

	// 查询数据库
	private int recordCount; // 总记录数
	@SuppressWarnings("rawtypes")
	private List recordList; // 本页的数据列表

	// 计算
	private int pageCount; // 总页数
	private int beginPageIndex; // 页码列表的开始索引（包含）
	private int endPageIndex; // 页码列表的结束索引（包含）

	/**
	 * 只接受前4个必要的属性，会自动的计算出其他3个属生的值
	 * 
	 * @param currentPage
	 * @param pageSize
	 * @param recordCount
	 * @param recordList
	 */
	@SuppressWarnings("rawtypes")
	public PageBean(int currentPage, int pageSize, int recordCount,
			List recordList) {
		this.currentPage = currentPage;
		this.pageSize = pageSize;
		this.recordCount = recordCount;
		this.recordList = recordList;
		// 计算总页码
		pageCount = (recordCount + pageSize - 1) / pageSize;

		// 计算 beginPageIndex 和 endPageIndex
		// >> 总页数不多于10页，则全部显示
		if (pageCount <= 5) {
			beginPageIndex = 1;
			endPageIndex = pageCount;
		}
		// >> 总页数多于5页，则显示当前页附近的共5个页码
		else {
			// 当前页附近的共5个页码（前2个 + 当前页 + 后2个）
			beginPageIndex = currentPage - 2;
			endPageIndex = currentPage + 2;
			// 当前面的页码不足3个时，则显示前5个页码
			if (beginPageIndex < 1) {
				beginPageIndex = 1;
				endPageIndex = 5;
			}
			// 当后面的页码不足2个时，则显示后5个页码
			if (endPageIndex > pageCount) {
				endPageIndex = pageCount;
				beginPageIndex = pageCount - 5 + 1;
			}
		}

		// 计算上一页
		this.prePage = this.currentPage - 1;
		if (this.prePage <= 0) {
			this.prePage = 1;
		}
		// 计算下一页的页码
		this.nextPage = this.currentPage + 1;
		if (this.nextPage > this.pageCount) {
			this.nextPage = this.pageCount;
		}
	}

	@SuppressWarnings("rawtypes")
	public List getRecordList() {
		return recordList;
	}

	@SuppressWarnings("rawtypes")
	public void setRecordList(List recordList) {
		this.recordList = recordList;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getRecordCount() {
		return recordCount;
	}

	public void setRecordCount(int recordCount) {
		this.recordCount = recordCount;
	}

	public int getBeginPageIndex() {
		return beginPageIndex;
	}

	public void setBeginPageIndex(int beginPageIndex) {
		this.beginPageIndex = beginPageIndex;
	}

	public int getEndPageIndex() {
		return endPageIndex;
	}

	public void setEndPageIndex(int endPageIndex) {
		this.endPageIndex = endPageIndex;
	}

	public int getPrePage() {
		return prePage;
	}

	public void setPrePage(int prePage) {
		this.prePage = prePage;
	}

	public int getNextPage() {
		return nextPage;
	}

	public void setNextPage(int nextPage) {
		this.nextPage = nextPage;
	}

}
