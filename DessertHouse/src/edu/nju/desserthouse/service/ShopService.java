package edu.nju.desserthouse.service;

import java.util.List;

import edu.nju.desserthouse.model.Shop;

public interface ShopService {
	/*
	 * ����һ������
	 */
	public void createShop(Shop shop);
	/*
	 * ɾ��һ������
	 */
	public void deleteShop(int id);
	
	/*
	 * ����id���ص�����Ϣ
	 */
	public Shop findShop(int id);
	
	
	/*
	 * ����id���µ�����Ϣ
	 */
	public void updateByShopid(Shop shop);
	/*
	 * ������е�����Ϣ
	 */
	public List<Shop> getAllShopList();
}
