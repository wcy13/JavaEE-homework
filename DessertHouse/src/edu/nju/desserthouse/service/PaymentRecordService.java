package edu.nju.desserthouse.service;

import java.util.List;

import edu.nju.desserthouse.model.PaymentRecord;

public interface PaymentRecordService {
	/*
	 * ����һ���ɷѼ�¼
	 */
	public void createPaymentRecord(PaymentRecord paymentRecord);
	/*
	 * ���ĳ�û������нɷѼ�¼
	 */
	public List<PaymentRecord> getAllPaymentRecordList(int cid);
	/*
	 * ���ĳ���нɷѼ�¼
	 */
	public List<PaymentRecord> getAllPaymentRecordList();
}
