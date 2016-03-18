package edu.nju.desserthouse.action;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import edu.nju.desserthouse.common.SortByAmount;
import edu.nju.desserthouse.model.Dessert;
import edu.nju.desserthouse.model.DessertVO;
import edu.nju.desserthouse.model.Member;
import edu.nju.desserthouse.model.SalesRecord;
import edu.nju.desserthouse.model.Shop;
import edu.nju.desserthouse.service.DessertService;
import edu.nju.desserthouse.service.MemberService;
import edu.nju.desserthouse.service.SalesRecordService;
import edu.nju.desserthouse.service.ShopService;

public class StatisticsAction extends BaseAction {
	@Autowired
	private MemberService memberService;
	private SalesRecordService salesRecordService;
	private ShopService shopService;
	private DessertService dessertService;
	
	public MemberService getMemberService() {
		return memberService;
	}
	public void setMemberService(MemberService memberService) {
		this.memberService = memberService;
	}
	public SalesRecordService getSalesRecordService() {
		return salesRecordService;
	}
	public void setSalesRecordService(SalesRecordService salesRecordService) {
		this.salesRecordService = salesRecordService;
	}
	public ShopService getShopService() {
		return shopService;
	}
	public void setShopService(ShopService shopService) {
		this.shopService = shopService;
	}
	public DessertService getDessertService() {
		return dessertService;
	}
	public void setDessertService(DessertService dessertService) {
		this.dessertService = dessertService;
	}
	@Override
	public String execute() throws Exception {
		//��Ա���估������ΰٷֱȡ��Ա𡢾�ס�ء����ѡ�����Ч/ֹͣ/��ͣ/ȡ�����
		List<Member> memberList = memberService.getAllMemberList();
		request.setAttribute("memberList", memberList);
		//��������
		String[] ageArr = {"0~20��","20~40��","40~60��","����60��"};
		int[] angNumArr = new int[4];
		request.setAttribute("ageArr", ageArr);
		for(Member m:memberList){
			int age = getAge(m.getBirth());
			if(age<=20){
				angNumArr[0]++;
			}else if(age > 20&& age<=40){
				angNumArr[1]++;
			}else if(age>40&&age<=60){
				angNumArr[2]++;
			}else{
				angNumArr[3]++;
			}
		}
		request.setAttribute("angNumArr", angNumArr);
		
		//Ԥ�����۳���������յ���ͳ�ƣ�
		//������Ʒ
		List<Shop> sList = shopService.getAllShopList();
		List<SalesRecord> srList = salesRecordService.getAllSalesRecordList();
		HashMap<Integer,Integer> reserveMap = new HashMap<Integer,Integer>();
		HashMap<Integer,Integer> saleMap = new HashMap<Integer,Integer>();
		HashMap<Integer,Integer> dessertMap = new HashMap<Integer,Integer>();
		for(SalesRecord sr:srList){
			//����Ԥ���� �ж��Ƿ���Ч ��ȡ��ʱ�������ʱ��
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			String str2 = format.format(sr.getSalesTime());
			Date saleDate =  java.sql.Date.valueOf(str2);
			if((sr.getIsValid()==1)&&(sr.getTakeDate().compareTo(saleDate)>0)){
				Integer num = reserveMap.get(sr.getSid());
				if( num == null){
					reserveMap.put(sr.getSid(), 1);
				}else{
					reserveMap.put(sr.getSid(),num+1 );
				}
			}
			//���������� �ж��Ƿ���Ч ȡ��ʱ�������ʱ���Ƿ�һ��
			if((sr.getIsValid()==1)&&(sr.getTakeDate().compareTo(saleDate)==0)){
				Integer num = saleMap.get(sr.getSid());
				if( num == null){
					saleMap.put(sr.getSid(), 1);
				}else{
					saleMap.put(sr.getSid(),num+1 );
				}
			}
			
			//������Ʒ�� �ж��Ƿ���Ч
			if(sr.getIsValid()==1){
				Integer num = dessertMap.get(sr.getDid());
				if( num == null){
					dessertMap.put(sr.getDid(), 1);
				}else{
					dessertMap.put(sr.getDid(),num+1 );
				}
			}
		}
		request.setAttribute("sList",sList );
		request.setAttribute("reserveMap",reserveMap );
		request.setAttribute("saleMap",saleMap );
		
		
		List<Dessert> dList = dessertService.getAllDessertList();
		List<DessertVO> dvoList = new ArrayList<DessertVO>();
		for(Dessert d:dList){
			Integer amount = dessertMap.get(d.getDid());
			if(amount !=null&& amount >0){
				DessertVO dvo = new DessertVO();
				dvo.setDid(d.getDid());
				dvo.setAmount(amount);
				dvo.setName(d.getName());
				dvoList.add(dvo);
			}
		}
		Collections.sort(dvoList, new SortByAmount());
		request.setAttribute("dvoList",dvoList );
		return "statistics";
	}
	/*
	 * ���� ����
	 */
	private int getAge(Date birthDate) {

		Calendar c = Calendar.getInstance();
		String dayCurr = new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());
		Date now = Date.valueOf(dayCurr);

		SimpleDateFormat format_y = new SimpleDateFormat("yyyy");
		SimpleDateFormat format_M = new SimpleDateFormat("MM");

		String birth_year = format_y.format(birthDate);
		String this_year = format_y.format(now);

		String birth_month =
		format_M.format(birthDate);
		String this_month =
		format_M.format(now);

		// ����������
		int age = Integer.parseInt(this_year) - Integer.parseInt(birth_year);

		// ���δ�������·ݣ���age - 1
		if (this_month.compareTo(birth_month) < 0)
		age -= 1;
		if (age < 0)
		age = 0;
		return age;
		}
}
