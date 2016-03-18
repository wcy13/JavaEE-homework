package edu.nju.desserthouse.dao;

import java.util.List;

import edu.nju.desserthouse.model.Member;

public interface MemberDao {
	/*
	 * �����ݿ��в���һ��member��¼
	 */
	public void save(Member member);
	
	
	/*
	 * ����id����member����,����ҵ��򷵻��������,���򷵻�null
	 */
	public Member find(int id);
	
	
	/*
	 * ����id����member���һ����¼
	 */
	public void updateByUserid(Member member);
	/*
	 * �������Ԫ��
	 */
	public List<Member> getAllMemberList();
	
}
