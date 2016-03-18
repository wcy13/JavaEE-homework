package edu.nju.desserthouse.action;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import edu.nju.desserthouse.model.Dessert;
import edu.nju.desserthouse.model.SalesRecord;
import edu.nju.desserthouse.service.DessertService;
import edu.nju.desserthouse.service.SalesRecordService;

public class MyOrderAction extends BaseAction {
	@Autowired
	private SalesRecordService salesRecordService;
	private DessertService dessertService;

	public SalesRecordService getSalesRecordService() {
		return salesRecordService;
	}

	public void setSalesRecordService(SalesRecordService salesRecordService) {
		this.salesRecordService = salesRecordService;
	}

	public DessertService getDessertService() {
		return dessertService;
	}

	public void setDessertService(DessertService dessertService) {
		this.dessertService = dessertService;
	}

	@Override
	public String execute() throws Exception {
		//�鿴��Ӧcid�����ж���������ǰʱ���Ԥ��ʱ���磬���ȡ��
		//����Ʒid����Ʒ��Ϣ��hashmap��������ʾ
		List<Dessert> dList = dessertService.getAllDessertList();
		HashMap<Integer,Dessert> idMap = new HashMap<Integer,Dessert>();
		for(Dessert d : dList){
			idMap.put(d.getDid(),d);
		}
		request.setAttribute("idMap",idMap );
		HttpSession session = request.getSession(true);
		int id = (int) session.getAttribute("id");
		List<SalesRecord> srList = salesRecordService.getAllSalesRecordList(id);
		request.setAttribute("srList", srList);
		return "myOrderHY";

	}
}
