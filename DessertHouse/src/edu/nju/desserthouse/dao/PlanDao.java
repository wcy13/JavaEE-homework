package edu.nju.desserthouse.dao;

import java.util.List;
import edu.nju.desserthouse.model.Plan;

public interface PlanDao {
	/*
	 * �����ݿ��в���һ��Plan��¼
	 */ 
	public void save(Plan plan);
	
	
	/*
	 * ����id����Plan����,����ҵ��򷵻��������,���򷵻�null
	 */
	public Plan find(int id);
	
	/*
	 * ����id����Plan���һ����¼
	 */
	public void updateByPlanId(Plan plan);
	/*
	 * �������Ԫ��
	 */
	public List<Plan> getAllPlanList();
}
