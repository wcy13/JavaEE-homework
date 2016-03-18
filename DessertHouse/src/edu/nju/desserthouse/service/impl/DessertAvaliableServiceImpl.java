package edu.nju.desserthouse.service.impl;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import edu.nju.desserthouse.dao.DessertAvaliableDao;
import edu.nju.desserthouse.dao.DessertDao;
import edu.nju.desserthouse.dao.PlanDao;
import edu.nju.desserthouse.dao.PlanListDao;
import edu.nju.desserthouse.dao.ShopClerkDao;
import edu.nju.desserthouse.dao.ShopDao;
import edu.nju.desserthouse.model.Dessert;
import edu.nju.desserthouse.model.DessertAvailableBranchVO;
import edu.nju.desserthouse.model.DessertAvailableVO;
import edu.nju.desserthouse.model.DessertAvaliable;
import edu.nju.desserthouse.model.DessertVO;
import edu.nju.desserthouse.model.Plan;
import edu.nju.desserthouse.model.PlanList;
import edu.nju.desserthouse.model.Shop;
import edu.nju.desserthouse.service.DessertAvaliableService;

public class DessertAvaliableServiceImpl implements DessertAvaliableService {
	@Autowired
	private DessertAvaliableDao dessertAvaliableDao;
	private PlanDao planDao;
	private PlanListDao planListDao;
	private DessertDao dessertDao;
	private ShopDao shopDao;
	private ShopClerkDao shopClerkDao;

	public ShopDao getShopDao() {
		return shopDao;
	}

	public void setShopDao(ShopDao shopDao) {
		this.shopDao = shopDao;
	}

	public PlanDao getPlanDao() {
		return planDao;
	}

	public void setPlanDao(PlanDao planDao) {
		this.planDao = planDao;
	}

	public PlanListDao getPlanListDao() {
		return planListDao;
	}

	public void setPlanListDao(PlanListDao planListDao) {
		this.planListDao = planListDao;
	}

	public DessertAvaliableDao getDessertAvaliableDao() {
		return dessertAvaliableDao;
	}

	public void setDessertAvaliableDao(DessertAvaliableDao dessertAvaliableDao) {
		this.dessertAvaliableDao = dessertAvaliableDao;
	}

	public DessertDao getDessertDao() {
		return dessertDao;
	}

	public void setDessertDao(DessertDao dessertDao) {
		this.dessertDao = dessertDao;
	}

	public ShopClerkDao getShopClerkDao() {
		return shopClerkDao;
	}

	public void setShopClerkDao(ShopClerkDao shopClerkDao) {
		this.shopClerkDao = shopClerkDao;
	}

	@Override
	public void createAvaliableDeesert(int pid) {
		Plan plan = planDao.find(pid);
		List<PlanList> planList = planListDao.getAllPlanListList();
		for (PlanList pl : planList) {
			if (pl.getPid() == pid && pl.getAmount() > 0) {
				DessertAvaliable da = new DessertAvaliable();
				da.setAmount(pl.getAmount());
				da.setDate(pl.getDate());
				da.setDid(pl.getDid());
				da.setPrice(pl.getPrice());
				da.setSid(plan.getSid());
				dessertAvaliableDao.save(da);
			}
		}
	}

	@Override
	public DessertAvailableVO getMemberBuyDesserts() {
		List<DessertAvaliable> daList = dessertAvaliableDao.getAllDessertAvaliableList();
		// ����������������ƻ���shop
		List<Shop> shops = new ArrayList<Shop>();

		ArrayList<String> strings = new ArrayList<String>();
		ArrayList<Integer> shopId = new ArrayList<Integer>();

		for (DessertAvaliable da : daList) {
			// ���������мƻ���shop id�б�
			if (!shopId.contains(da.getSid())) {
				shopId.add(da.getSid());
			}
			// �������е���+���ڵ�string list
			String s = String.valueOf(da.getSid()) + String.valueOf(da.getDate());
			if (!strings.contains(s)) {
				strings.add(s);
			}
		}
		// ����shop id���鹹�������мƻ���shop�б�
		for (Integer sid : shopId) {
			shops.add(shopDao.find(sid));
		}
		// ���shops��Ӧ�мƻ���date�б�
		Calendar calendar = Calendar.getInstance();
		String dayCurrent = new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime());
		HashMap<Shop, List<Date>> sdMap = new HashMap<Shop, List<Date>>();
		for (Shop shop : shops) {
			List<Date> dateList = new ArrayList<Date>();
			for (DessertAvaliable da : daList) {
				if (da.getSid() == shop.getSid()) {
					if (dayCurrent.compareTo(String.valueOf(da.getDate())) <= 0) {
						if (!dateList.contains(da.getDate())) {
							dateList.add(da.getDate());
						}
					}
				}
			}
			sdMap.put(shop, dateList);
		}
		// ������+���ڶ�Ӧ����Ʒ��Ϣ
		HashMap<String, List<DessertVO>> sddMap = new HashMap<String, List<DessertVO>>();
		for (String s : strings) {
			List<DessertVO> dvoList = new ArrayList<DessertVO>();
			for (DessertAvaliable da : daList) {
				String temp = String.valueOf(da.getSid()) + String.valueOf(da.getDate());
				if (s.equals(temp) && da.getAmount() > 0) {
					DessertVO dvo = new DessertVO();
					dvo.setAmount(da.getAmount());
					dvo.setPrice(da.getPrice());
					dvo.setDaid(da.getDaid());
					;
					Dessert dessert = dessertDao.find(da.getDid());
					dvo.setDid(dessert.getDid());
					dvo.setName(dessert.getName());
					dvo.setImg(dessert.getImage());
					dvoList.add(dvo);
				}
			}
			sddMap.put(s, dvoList);
		}

		DessertAvailableVO davo = new DessertAvailableVO();
		davo.setSddMap(sddMap);
		davo.setSdMap(sdMap);
		davo.setShops(shops);
		return davo;
	}

	@Override
	public void sellAvaliableDessert(int daid, int amount) {
		DessertAvaliable da = dessertAvaliableDao.find(daid);
		da.setAmount(da.getAmount() - amount);
		dessertAvaliableDao.updateByDessertAvaliableId(da);
	}

	@Override
	public DessertAvailableBranchVO getBranchSaleDesserts(int scid) {
		// ��õ�Ա��Ӧ�ĵ���id
		int sid = shopClerkDao.find(scid).getSid();
		System.out.println("sid:"+sid);
		// ������п�����Ʒ�б�
		List<DessertAvaliable> daList = dessertAvaliableDao.getAllDessertAvaliableList();
		// ����date�б�
		List<Date> dateList = new ArrayList<Date>();
		Calendar calendar = Calendar.getInstance();
		String dayCurrent = new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime());
		for (DessertAvaliable da : daList) {
			if (da.getSid() == sid) {
				Date date = da.getDate();
				System.out.println("date:"+date);
				if ((!dateList.contains(date)) && (dayCurrent.compareTo(String.valueOf(da.getDate())) <= 0)) {
					dateList.add(date);
				}
			}
		}
		// ���date��Ӧ����Ʒ�б�
		HashMap<Date, List<DessertVO>> ddMap = new HashMap<Date, List<DessertVO>>();
		for (Date d : dateList) {
			List<DessertVO> dvoList = new ArrayList<DessertVO>();
			for (DessertAvaliable da : daList) {
				if ((da.getSid() == sid) && (da.getDate().compareTo(d) == 0) && (da.getAmount() > 0)) {
					DessertVO dvo = new DessertVO();
					dvo.setAmount(da.getAmount());
					dvo.setPrice(da.getPrice());
					dvo.setDaid(da.getDaid());
					;
					Dessert dessert = dessertDao.find(da.getDid());
					dvo.setDid(dessert.getDid());
					dvo.setName(dessert.getName());
					dvo.setImg(dessert.getImage());
					dvoList.add(dvo);
				}
			}
			ddMap.put(d, dvoList);
		}
		DessertAvailableBranchVO dabvo = new DessertAvailableBranchVO();
		dabvo.setDateList(dateList);
		dabvo.setDdMap(ddMap);
		return dabvo;
	}

	@Override
	public void modifyDessertAvailableAfterCancel(int sid, Date date,int did, int amount) {
		List<DessertAvaliable> daList = dessertAvaliableDao.getAllDessertAvaliableList();
		for(DessertAvaliable da:daList){
			if((da.getSid()==sid)&&(da.getDate().compareTo(date)==0)&&(da.getDid()==did)){
				da.setAmount(da.getAmount()+amount);
				dessertAvaliableDao.updateByDessertAvaliableId(da);
				break;
			}
		}
	}

}
