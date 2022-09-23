package helpinghands.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import helpinghands.pojo.Address;



@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {

}
