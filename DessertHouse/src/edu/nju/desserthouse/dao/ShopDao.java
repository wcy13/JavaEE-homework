package edu.nju.desserthouse.dao;

import java.util.List;

import edu.nju.desserthouse.model.Shop;

public interface ShopDao {
	/*
	 * �����ݿ��в���һ��shop
	 */
	public void save(Shop shop);
	/*
	 * ɾ��һ��shopԪ��
	 */
	public void delete(int id);
	
	/*
	 * ����id����shop����,����ҵ��򷵻��������,���򷵻�null
	 */
	public Shop find(int id);
	
	
	/*
	 * ����id����shop���һ����¼
	 */
	public void updateByShopid(Shop shop);
	/*
	 * �������Ԫ��
	 */
	public List<Shop> getAllShopList();
}
