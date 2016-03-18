package edu.nju.desserthouse.dao;

import java.util.List;

import edu.nju.desserthouse.model.PlanList;

public interface PlanListDao {
	/*
	 * �����ݿ��в���һ��PlanList��¼
	 */
	public void save(PlanList planList);
	
	/*
	 * ����id����PlanList����,����ҵ��򷵻��������,���򷵻�null
	 */
	public PlanList find(int id);
	/*
	 * ɾ��һ��PlanListԪ��
	 */
	public void delete(int id);
	
	/*
	 * ����id����PlanList���һ����¼
	 */
	public void updateByPlanListId(PlanList planList);
	/*
	 * �������Ԫ��
	 */
	public List<PlanList> getAllPlanListList();
}
