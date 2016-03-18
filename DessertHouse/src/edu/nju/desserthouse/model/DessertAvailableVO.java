package edu.nju.desserthouse.model;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;

public class DessertAvailableVO {
	//��ѡ��ĵ������� ��ѡ��ĵ��̶�Ӧ�Ŀ�ѡ������� ȷ�����̺����ں��Ӧ����Ʒ�б�
	private List<Shop> shops;
	private HashMap<Shop,List<Date>> sdMap;//shop��Ӧ��date�б�
	private HashMap<String ,List<DessertVO>> sddMap;//shop��id��date��ɵ��ַ��� ��Ӧ����Ʒ��Ϣ�б�
	
	public DessertAvailableVO(){}
	public DessertAvailableVO(List<Shop> shops, HashMap<Shop, List<Date>> sdMap,
			HashMap<String, List<DessertVO>> sddMap) {
		super();
		this.shops = shops;
		this.sdMap = sdMap;
		this.sddMap = sddMap;
	}
	public List<Shop> getShops() {
		return shops;
	}
	public void setShops(List<Shop> shops) {
		this.shops = shops;
	}
	public HashMap<Shop, List<Date>> getSdMap() {
		return sdMap;
	}
	public void setSdMap(HashMap<Shop, List<Date>> sdMap) {
		this.sdMap = sdMap;
	}
	public HashMap<String, List<DessertVO>> getSddMap() {
		return sddMap;
	}
	public void setSddMap(HashMap<String, List<DessertVO>> sddMap) {
		this.sddMap = sddMap;
	}
	
}
