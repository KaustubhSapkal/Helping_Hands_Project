package helpinghands.service;

import java.util.List;

import helpinghands.pojo.Order;
import helpinghands.pojo.Receiver;



public interface OrderService {

	Order saveOrder(Order order);
	List<Order> getAllOrders();
	List<Order> getReceiverOrders(Receiver receiver);
	Order findById(int id);
	void deleteOrder(int id);
	
}
