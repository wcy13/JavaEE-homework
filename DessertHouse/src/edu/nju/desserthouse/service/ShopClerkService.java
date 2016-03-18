package edu.nju.desserthouse.service;

import java.util.List;
import edu.nju.desserthouse.model.ShopClerk;
import edu.nju.desserthouse.model.User;

public interface ShopClerkService {
	/*
	 * ����һ����Ա
	 */
	public void addShopClerk(ShopClerk shopClerk,User user);
	/*
	 * ɾ��һ����Ա
	 */
	public void deleteShopClerk(int scid,int uid);
	
	/*
	 * ����id���ص�Ա��Ϣ
	 */
	public ShopClerk findShopClerk(int id);
	
	
	/*
	 * ����id���µ�Ա��Ϣ
	 */
	public void updateByShopClerkId(ShopClerk shopClerk);
	/*
	 * ������е�Ա��Ϣ
	 */
	public List<ShopClerk> getAllShopClerkList();
}
