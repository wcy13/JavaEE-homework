package edu.nju.desserthouse.dao;
import edu.nju.desserthouse.model.Bank;

public interface BankDao {
	
	/*
	 * ����bcid����bank����,����ҵ��򷵻��������,���򷵻�null
	 */
	public Bank find(int id);
	
	
	/*
	 * ����bcid����bank���һ����¼
	 */
	public void updateByBankid(Bank bank);
}
