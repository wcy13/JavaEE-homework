package edu.nju.desserthouse.action;

import java.sql.Date;
import java.sql.Timestamp;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import edu.nju.desserthouse.model.DessertAvailableBranchVO;
import edu.nju.desserthouse.model.Member;
import edu.nju.desserthouse.model.SalesRecord;
import edu.nju.desserthouse.model.ShopClerk;
import edu.nju.desserthouse.service.DessertAvaliableService;
import edu.nju.desserthouse.service.MemberService;
import edu.nju.desserthouse.service.SalesRecordService;
import edu.nju.desserthouse.service.ShopClerkService;

public class SaleDessertMemberAction extends BaseAction {
	@Autowired
	private DessertAvaliableService dessertAvaliableService;
	private SalesRecordService salesRecordService;
	private ShopClerkService shopClerkService;
	private MemberService memberService;
	private String did;
	private String takeDate;
	private String amount;
	private String daid;
	private String price;
	private String cid;

	public DessertAvaliableService getDessertAvaliableService() {
		return dessertAvaliableService;
	}

	public void setDessertAvaliableService(DessertAvaliableService dessertAvaliableService) {
		this.dessertAvaliableService = dessertAvaliableService;
	}

	public SalesRecordService getSalesRecordService() {
		return salesRecordService;
	}

	public void setSalesRecordService(SalesRecordService salesRecordService) {
		this.salesRecordService = salesRecordService;
	}

	public ShopClerkService getShopClerkService() {
		return shopClerkService;
	}

	public void setShopClerkService(ShopClerkService shopClerkService) {
		this.shopClerkService = shopClerkService;
	}

	public MemberService getMemberService() {
		return memberService;
	}

	public void setMemberService(MemberService memberService) {
		this.memberService = memberService;
	}

	public String getDid() {
		return did;
	}

	public void setDid(String did) {
		this.did = did;
	}

	public String getTakeDate() {
		return takeDate;
	}

	public void setTakeDate(String takeDate) {
		this.takeDate = takeDate;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getDaid() {
		return daid;
	}

	public void setDaid(String daid) {
		this.daid = daid;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}

	@Override
	public String execute() throws Exception {
		System.out.println(" did:" + did + " takeDate" + takeDate + " amount:" + amount + " daid:" + daid
				+ " price:" + price+" cid:"+cid);
		//1����õ�Ա���ڵĵ��̱��
		HttpSession session = request.getSession(true);
		int scid = (int) session.getAttribute("id");
		ShopClerk sc = shopClerkService.findShopClerk(scid);
		int sid = sc.getSid();
		
		int didInt = Integer.valueOf(did);
		Date date = java.sql.Date.valueOf(takeDate);
		int amountInt = Integer.valueOf(amount);
		int daidInt = Integer.valueOf(daid);
		double priceDouble = Double.valueOf(price);	
		int cidInt = Integer.valueOf(cid);
		double total = amountInt * priceDouble;
		
		//��û�Ա��Ϣ ����ʵ��֧�����
		Member member = memberService.findMemberById(cidInt);
		double realTotal = total *(member.getDiscount()/10.0);
		//2�����Ļ�Ա���ֺ��˻����
		member.setCredit(member.getCredit()+realTotal);
		member.setBalance(member.getBalance()-realTotal);
		memberService.updateMember(member);
		
		// 3��available dessert������
		dessertAvaliableService.sellAvaliableDessert(daidInt, amountInt);
		// 4�����۱����Ӷ�Ӧ�����ۼ�¼
		SalesRecord salesRecord = new SalesRecord();
		Timestamp salesTime = new Timestamp(System.currentTimeMillis());
		salesRecord.setSid(sid);
		salesRecord.setScid(scid);
		salesRecord.setSalesTime(salesTime);
		salesRecord.setDid(didInt);
		salesRecord.setAmount(amountInt);
		salesRecord.setTotal(total);
		salesRecord.setRealTotal(realTotal);
		salesRecord.setIsOnline(0);//���깺������,�����ڻ�Ա���߶���
		salesRecord.setTakeDate(date);
		salesRecord.setIsValid(1);
		salesRecord.setCid(cidInt);
		String dm = String.valueOf(member.getLevel())+"����Ա��������Ʒ"+String.valueOf(member.getDiscount())+"���Ż�";
		salesRecord.setDiscountMessage(dm);
		salesRecordService.createSalesRecord(salesRecord);
		
		//��������ҳ��
		DessertAvailableBranchVO dessertAvailableVO = dessertAvaliableService.getBranchSaleDesserts(scid);
		request.setAttribute("dateList", dessertAvailableVO.getDateList());
		request.setAttribute("ddMap", dessertAvailableVO.getDdMap());
		return "saleDessertFD";

	}
}
