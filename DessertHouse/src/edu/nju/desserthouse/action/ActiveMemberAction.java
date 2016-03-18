package edu.nju.desserthouse.action;

import java.sql.Date;
import java.sql.Timestamp;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import edu.nju.desserthouse.model.Bank;
import edu.nju.desserthouse.model.Member;
import edu.nju.desserthouse.model.PaymentRecord;
import edu.nju.desserthouse.service.MemberService;
import edu.nju.desserthouse.service.PaymentRecordService;

/*
 * ���ǽɷѶ�Ӧ�ô�
 */
public class ActiveMemberAction extends BaseAction {
	@Autowired
	private MemberService memberService;
	private PaymentRecordService paymentRecordService;

	public MemberService getMemberService() {
		return memberService;
	}

	public void setMemberService(MemberService memberService) {
		this.memberService = memberService;
	}
	

	public PaymentRecordService getPaymentRecordService() {
		return paymentRecordService;
	}

	public void setPaymentRecordService(PaymentRecordService paymentRecordService) {
		this.paymentRecordService = paymentRecordService;
	}

	@Override
	public String execute() throws Exception {
		HttpSession session = request.getSession();
		int id = (int) session.getAttribute("id");
		String bcid = request.getParameter("bcid");
		String money = request.getParameter("money");
		double moneyInt = Integer.valueOf(money);
		// ���������˻����
		Bank bank = memberService.findBankById(Integer.valueOf(bcid));
		bank.setBalance(bank.getBalance() - moneyInt);
		// ���»�Ա��Ϣ
		Member member = memberService.findMemberById(id);
		// ���ڻ�ԱΪ����״̬
		member.setState(1);
		// ��������ɷ�ʱ��
		Date currentDate = new Date(System.currentTimeMillis());
		member.setHandleDate(currentDate);
		// �����˻����
		member.setBalance(moneyInt + member.getBalance());
		// ���µ�ǰ��Ա����
		int level = member.getLevel();
		int tempLevel = 0;
		if (moneyInt >= 200 && moneyInt < 400) {
			tempLevel = 1;
		}else if(moneyInt>=400&&moneyInt<800){
			tempLevel = 2;
		}else if(moneyInt >=800){
			tempLevel = 3;
		}
		if(tempLevel > level){
			level = tempLevel;
			member.setLevel(level);
			
			//�����ۿ���Ϣ
			if(level == 1){
				member.setDiscount(9.5);
			}else if(level == 2){
				member.setDiscount(8.8);
			}else{
				member.setDiscount(8.5);
			}
		}
		member.setBcid(Integer.valueOf(bcid));
		memberService.recharge(member, bank);
		session.setAttribute("member", member);
		// ����һ���ɷѼ�¼
		PaymentRecord pr = new PaymentRecord();
		pr.setCid(id);
		Timestamp nowdate = new Timestamp(System.currentTimeMillis());
		pr.setDate(nowdate);
		pr.setBcid(Integer.valueOf(bcid));
		pr.setAmount(moneyInt);
		paymentRecordService.createPaymentRecord(pr);
		
		return "activeMember";
	}
}
