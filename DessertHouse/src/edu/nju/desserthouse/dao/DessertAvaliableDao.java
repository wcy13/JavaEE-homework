package edu.nju.desserthouse.dao;

import java.util.List;

import edu.nju.desserthouse.model.DessertAvaliable;

public interface DessertAvaliableDao {
	/*
	 * �����ݿ��в���һ��DessertAvaliable��¼
	 */
	public void save(DessertAvaliable dessertAvaliable);
	
	
	/*
	 * ����id����DessertAvaliable����,����ҵ��򷵻��������,���򷵻�null
	 */
	public DessertAvaliable find(int id);
	/*
	 * ɾ��һ��DessertAvaliableԪ��
	 */
	public void delete(int id);
	
	/*
	 * ����id����user���һ����¼
	 */
	public void updateByDessertAvaliableId(DessertAvaliable dessertAvaliable);
	/*
	 * �������Ԫ��
	 */
	public List<DessertAvaliable> getAllDessertAvaliableList();
}
