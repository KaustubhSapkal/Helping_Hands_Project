package helpinghands.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import helpinghands.pojo.Order;
import helpinghands.pojo.OrderDetails;
import helpinghands.pojo.Product;
import helpinghands.repository.OrderDetailsRepository;
import helpinghands.service.DonorService;
import helpinghands.service.OrderdetailService;



@Service
public class OrderDetailsServiceImpl implements OrderdetailService {

	@Autowired OrderDetailsRepository dao;
	@Autowired DonorService donorService;
	@Override
	public void saveOrderDetails(OrderDetails od) {
		// TODO Auto-generated method stub
		dao.save(od);
	}

	@Override
	public OrderDetails findById(int id) {
		// TODO Auto-generated method stub
		return dao.getById(id);
	}

	@Override
	public List<OrderDetails> findByOrder(Order order) {
		// TODO Auto-generated method stub
		return dao.findByOrder(order);
	}

	@Override
	public void deleteOrder(int id) {
		// TODO Auto-generated method stub
		OrderDetails d=dao.getById(id);
		dao.delete(d);
	}

	@Override
	public List<OrderDetails> findByProduct(Product product) {
		// TODO Auto-generated method stub
		return dao.findByProduct(product);
	}

}
