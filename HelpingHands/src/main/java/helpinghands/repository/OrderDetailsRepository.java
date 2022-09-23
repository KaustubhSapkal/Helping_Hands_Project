package helpinghands.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import helpinghands.pojo.Order;
import helpinghands.pojo.OrderDetails;
import helpinghands.pojo.Product;



@Repository
public interface OrderDetailsRepository extends JpaRepository<OrderDetails, Integer> {
	List<OrderDetails> findByOrder(Order order);

	List<OrderDetails> findByProduct(Product product);
	
	
}
