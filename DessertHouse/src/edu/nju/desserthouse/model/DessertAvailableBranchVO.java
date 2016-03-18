package edu.nju.desserthouse.model;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;
public class DessertAvailableBranchVO {
	//����Ʒ���������顪��ע���ж�ʱ�䣬ֻҪ�ȵ�ǰʱ�������Ʒ��
	private List<Date> dateList;
	//ÿ���Ӧ����Ʒ�б� hashMap
	private HashMap<Date,List<DessertVO>> ddMap;
	
	public DessertAvailableBranchVO(){}
	public DessertAvailableBranchVO(List<Date> dateList, HashMap<Date, List<DessertVO>> ddMap) {
		super();
		this.dateList = dateList;
		this.ddMap = ddMap;
	}
	public List<Date> getDateList() {
		return dateList;
	}
	public void setDateList(List<Date> dateList) {
		this.dateList = dateList;
	}
	public HashMap<Date, List<DessertVO>> getDdMap() {
		return ddMap;
	}
	public void setDdMap(HashMap<Date, List<DessertVO>> ddMap) {
		this.ddMap = ddMap;
	}
	
}
