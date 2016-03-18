package edu.nju.desserthouse.dao;

import java.util.List;
import edu.nju.desserthouse.model.ShopClerk;

public interface ShopClerkDao {
	/*
	 * �����ݿ��в���һ��ShopClerk
	 */
	public void save(ShopClerk shopClerk);
	/*
	 * ɾ��һ��ShopClerkԪ��
	 */
	public void delete(int id);
	
	/*
	 * ����id����ShopClerk����,����ҵ��򷵻��������,���򷵻�null
	 */
	public ShopClerk find(int id);
	
	
	/*
	 * ����id����shop���һ����¼
	 */
	public void updateByShopClerkId(ShopClerk shopClerk);
	/*
	 * �������Ԫ��
	 */
	public List<ShopClerk> getAllShopClerkList();
}
