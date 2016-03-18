package edu.nju.desserthouse.dao;

import java.util.List;
import edu.nju.desserthouse.model.SalesRecord;

public interface SalesRecordDao {
	/*
	 * �����ݿ��в���һ�����Ѽ�¼
	 */
	public void save(SalesRecord salesRecord);
	/*
	 * ����id����SalesRecord����,����ҵ��򷵻��������,���򷵻�null
	 */
	public SalesRecord find(int id);
	/*
	 * ����id����SalesRecord���һ����¼
	 */
	public void updateBySalesRecordId(SalesRecord salesRecord);
	/*
	 * ����������Ѽ�¼
	 */
	public List<SalesRecord> getAllSalesRecordList();
}
