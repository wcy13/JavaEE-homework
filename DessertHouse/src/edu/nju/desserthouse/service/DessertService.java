package edu.nju.desserthouse.service;

import java.util.List;

import edu.nju.desserthouse.model.Dessert;

public interface DessertService {
	/*
	 * ����һ����Ʒ
	 */
	public void createDessert(Dessert dessert);
	/*
	 * ɾ��һ����Ʒ
	 */
	public void deleteDessert(int id);
	
	/*
	 * ����id������Ʒ��Ϣ
	 */
	public Dessert findDessert(int id);
	
	
	/*
	 * ����id������Ʒ��Ϣ
	 */
	public void updateByDessertid(Dessert dessert);
	/*
	 * ���������Ʒ��Ϣ
	 */
	public List<Dessert> getAllDessertList();
}
