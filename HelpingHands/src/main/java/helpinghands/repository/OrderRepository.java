package helpinghands.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import helpinghands.pojo.Order;
import helpinghands.pojo.Receiver;



@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
	List<Order> findByReceiver(Receiver receiver);
}
