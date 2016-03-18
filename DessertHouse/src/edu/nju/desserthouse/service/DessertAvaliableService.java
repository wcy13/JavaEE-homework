package edu.nju.desserthouse.service;

import java.sql.Date;

import edu.nju.desserthouse.model.DessertAvailableBranchVO;
import edu.nju.desserthouse.model.DessertAvailableVO;

public interface DessertAvaliableService {
	/*
	 * �ڼƻ���׼֮�� ������Ӧ��ĳ�̵� ÿ��ÿ����Ʒ����Ϣ
	 */
	public void createAvaliableDeesert(int pid);
	/*
	 * ��Ա���߶���ʱ ��ȡ��������Ʒ��Ϣ
	 * ��ѡ��ĵ�������
	 * ��ѡ��ĵ��̶�Ӧ�Ŀ�ѡ�������
	 * ȷ�����̺����ں��Ӧ����Ʒ�б�
	 */
	public DessertAvailableVO getMemberBuyDesserts();
	/*
	 * �ڹ˿͹���ĳ��Ʒ�󣬼���������
	 */
	public void sellAvaliableDessert(int daid,int amount);
	/*
	 * ���ݷֵ����Ա��id����������̵��ÿ�տ�������Ʒ
	 */
	public DessertAvailableBranchVO getBranchSaleDesserts(int scid);
	/*
	 * ��ȡ�������󣬶�Ӧ����ĳ��ĳ��ĳ��Ʒ������
	 */
	public void modifyDessertAvailableAfterCancel(int sid,Date date,int did,int amount);
	
	//get�б�ʱ ע�⽫���е�ʱ��͵�ǰʱ����бȽϣ�ֻ��ʾ�ȵ�ǰʱ��һ��or���
	/////
	/////���������۵�ʱ�� ���۱�Ҫget sid did takeDate amount price ��Ա��Ϣ �Ż���Ϣ
	//�ڻ�Ա���߹�����Ʒʱ ��ʾÿ�����л���ÿһ�����Ʒ��Ϣ�б�
	
	//����Աѡ���ۻ�ʱ����action������Ա������sid get���sidÿ����۵���Ʒ��Ϣ�б�
	
//��������ʱ�� һ����һ����Ʒ ��Ӧ��ȥ��Ʒ�ĸ��� 
	//�����۵�ʱ�� ֱ��get daid���� �����Ϳ�ֱ���޸�amount
}
