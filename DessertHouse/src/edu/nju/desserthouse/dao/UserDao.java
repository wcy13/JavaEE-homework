package edu.nju.desserthouse.dao;

import java.util.List;
import edu.nju.desserthouse.model.User;

public interface UserDao {
	/*
	 * �����ݿ��в���һ��user��¼
	 */
	public void save(User user);
	
	
	/*
	 * ����id����user����,����ҵ��򷵻��������,���򷵻�null
	 */
	public User find(int id);
	/*
	 * ɾ��һ��userԪ��
	 */
	public void delete(int id);
	
	/*
	 * ����id����user���һ����¼
	 */
	public void updateByUserid(User user);
	/*
	 * �������Ԫ��
	 */
	public List<User> getAllUerList();
}
