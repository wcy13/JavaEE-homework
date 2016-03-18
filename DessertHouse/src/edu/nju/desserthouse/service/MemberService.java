package edu.nju.desserthouse.service;

import java.util.List;

import edu.nju.desserthouse.model.Bank;
import edu.nju.desserthouse.model.Member;
import edu.nju.desserthouse.model.User;

public interface MemberService {
	/*
	 * ע�����û�����¼��ע����Ϣ
	 */
	public void registerUser(Member member,User user);
	/*
	 * ͨ��id�����û�
	 */
	public Member findMemberById(int id);
	/*
	 * �����û�
	 */
	public void updateMember(Member member);
	/*
	 * ��ֵ�󣬸����û��������˻����
	 */
	public void recharge(Member member,Bank bank);
	/*
	 * �������п�������п���Ϣ
	 */
	public Bank findBankById(int id);
	/*
	 * ��������û���ע����Ϣ
	 */
	public List<Member> getAllMemberList();
}
