package edu.nju.desserthouse.model;

public class Goods {
	private int did;//��create��ʱ����ã���Ϊû��plid��
	private int amount;
	private double price;
	private int plid;//��update��ʱ�����
	public Goods(){}
	public Goods(int did, int amount, double price, int plid) {
		super();
		this.did = did;
		this.amount = amount;
		this.price = price;
		this.plid = plid;
	}
	public int getDid() {
		return did;
	}
	public void setDid(int did) {
		this.did = did;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getPlid() {
		return plid;
	}
	public void setPlid(int plid) {
		this.plid = plid;
	}
	
	
}
