package helpinghands.service;

import java.util.List;

import helpinghands.pojo.Order;
import helpinghands.pojo.OrderDetails;
import helpinghands.pojo.Product;



public interface OrderdetailService {

	void saveOrderDetails(OrderDetails od);
	OrderDetails findById(int id);
	void deleteOrder(int id);
	List<OrderDetails> findByOrder(Order order);
	List<OrderDetails> findByProduct(Product product);
}
