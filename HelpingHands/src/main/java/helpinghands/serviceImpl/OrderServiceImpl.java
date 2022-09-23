package helpinghands.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import helpinghands.pojo.Order;
import helpinghands.pojo.Receiver;
import helpinghands.repository.OrderRepository;
import helpinghands.service.OrderService;



@Service
public class OrderServiceImpl implements OrderService {

	@Autowired OrderRepository dao;
	
	@Override
	public Order saveOrder(Order order) {
		// TODO Auto-generated method stub
		return dao.save(order);
	}

	@Override
	public List<Order> getAllOrders() {
		// TODO Auto-generated method stub
		return dao.findAll();
	}

	@Override
	public List<Order> getReceiverOrders(Receiver receiver) {
		// TODO Auto-generated method stub
		return dao.findByReceiver(receiver);
	}

	@Override
	public Order findById(int id) {
		// TODO Auto-generated method stub
		return dao.getById(id);
	}

	@Override
	public void deleteOrder(int id) {
		// TODO Auto-generated method stub
		Order d=dao.getById(id);
		dao.delete(d);
		
	}

	
}
