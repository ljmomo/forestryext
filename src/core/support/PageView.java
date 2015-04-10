package core.support;

import java.util.List;

/**
 * @author 杨添
 * @系统不断升级，获取新版本和技术支持地址：http://item.taobao.com/item.htm?spm=2013.1.20141001.2.YitFpw&id=40481412685&scm=1007.10115.4482.i42543888642&pvid=e2916971-1ade-45ae-98a8-754306378b06
 * @本淘宝店也有智能交通系统、OA系统、LBS系统、门户系统和JAVA企业前后台框架等产品：http://shop111863449.taobao.com/
 */
public class PageView<E> {

	/** list data * */
	private List<E> records;
	/** total page * */
	private long totalPage = 1;
	/** count per page * */
	private int maxResult = 12;
	/** current page * */
	private int currentPage = 0;
	/** total record qty * */
	private long totalRecord;
	/** page count * */
	private int pageCode = 10;

	public PageView(int maxResult, int currentPage) {
		this.maxResult = maxResult;
		this.currentPage = currentPage;
	}

	public int getPageCode() {
		return pageCode;
	}

	public void setPagecode(int pageCode) {
		this.pageCode = pageCode;
	}

	public void setQueryResult(QueryResult<E> qr) {
		setRecords(qr.getResultList());
		setTotalRecord(qr.getTotalCount());
	}

	public long getTotalRecord() {
		return totalRecord;
	}

	public void setTotalRecord(long totalRecord) {
		this.totalRecord = totalRecord;
		setTotalPage(this.totalRecord % this.maxResult == 0 ? this.totalRecord / this.maxResult : this.totalRecord / this.maxResult + 1);
	}

	public List<E> getRecords() {
		return records;
	}

	public void setRecords(List<E> records) {
		this.records = records;
	}

	public long getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(long totalpage) {
		this.totalPage = totalpage;
	}

	public int getMaxResult() {
		return maxResult;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public int getFirstResult() {
		return (this.currentPage - 1) * this.maxResult;
	}

}
