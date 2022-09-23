package helpinghands.DTO;

import java.util.List;

import helpinghands.pojo.Address;



public class PlaceOrderDTO {

	private Address address;
	private List<CartDTO> cart;
	private int receiverid;
	private int donorid;
	
	
	public int getReceiverid() {
		return receiverid;
	}
	public void setReceiverid(int receiverid) {
		this.receiverid = receiverid;
	}
	public int getDonorid() {
		return donorid;
	}
	public void setDonorid(int donorid) {
		this.donorid = donorid;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public List<CartDTO> getCart() {
		return cart;
	}
	public void setCart(List<CartDTO> cart) {
		this.cart = cart;
	}
	
	
	
	
	
}
