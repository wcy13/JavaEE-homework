package edu.nju.desserthouse.dao;

import java.util.List;

import edu.nju.desserthouse.model.PaymentRecord;

public interface PaymentRecordDao {
	/*
	 * �����ݿ��в���һ���ɷѼ�¼
	 */
	public void save(PaymentRecord paymentRecord);
	/*
	 * ������нɷѼ�¼
	 */
	public List<PaymentRecord> getAllPaymentRecordList();
}
