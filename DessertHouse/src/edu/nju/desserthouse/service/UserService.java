package edu.nju.desserthouse.service;

import java.util.List;

import edu.nju.desserthouse.model.User;

public interface UserService {
	/*
	 * ע�����û�����¼��id������
	 */
	public void registerUser(User user);
	
	/*
	 * ͨ��id�����û���������֤��¼����
	 */
	public User findUserById(int id);
	/*
	 * �����û�����
	 */
	public void updateUser(User user);
	/*
	 * ɾ��һ����Ա
	 */
	public void deleteUser(int id);
	/*
	 * ��������û���¼��Ϣ
	 */
	public List<User> getAllUerList();
}
