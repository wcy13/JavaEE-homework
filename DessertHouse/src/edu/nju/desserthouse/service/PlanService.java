package edu.nju.desserthouse.service;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;

import edu.nju.desserthouse.model.Goods;
import edu.nju.desserthouse.model.Plan;
import edu.nju.desserthouse.model.PlanVO;

public interface PlanService {
//�鿴��ͨ���ƻ� �������ƻ� δͨ���ƻ� �ƶ��¼ƻ�
	//�ƻ���map(id,plList<PlanList>)
	/*
	 * �½�һ���ƻ�
	 */
	public void createPlan(int sid,Date startDate,HashMap<Date,List<Goods>> map);
	/*
	 * �鿴��ͨ���ļƻ�
	 */
	public List<PlanVO> getAllApprovedPlan();
	/*
	 * �鿴�������ƻ�
	 */
	public List<PlanVO> getAllPendingPlan();
	/*
	 * �鿴δͨ���ƻ�
	 */
	public List<PlanVO> getAllRejectedPlan();
	/*
	 * �޸�һ���ƻ�
	 */
	public void modifyPlan(int pid,HashMap<Date,List<Goods>> map);
	/*
	 * ��׼һ���ƻ�
	 */
	public void permitPlan(int pid);
	/*
	 * ����׼һ���ƻ�
	 */
	public void rejectPlan(int pid);
	/*
	 * ����Pid���Ҽƻ�
	 */
	public Plan findPlanById(int pid);
}
