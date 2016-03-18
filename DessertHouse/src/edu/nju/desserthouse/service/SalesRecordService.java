package edu.nju.desserthouse.service;

import java.util.List;
import edu.nju.desserthouse.model.SalesRecord;

public interface SalesRecordService {
	/*
	 * ����һ�����ۼ�¼
	 */
	public void createSalesRecord(SalesRecord salesRecord);
	/*
	 * ȡ������
	 */
	public void cancelSale(int id);
	/*
	 * ������е����ۼ�¼
	 */
	public List<SalesRecord> getAllSalesRecordList();
	/*
	 * ���ĳ�û����������ۼ�¼
	 */
	public List<SalesRecord> getAllSalesRecordList(int cid);
	/*
	 * ���ĳ�����ۼ�¼
	 */
	public SalesRecord getSalesRecord(int srid);
}
