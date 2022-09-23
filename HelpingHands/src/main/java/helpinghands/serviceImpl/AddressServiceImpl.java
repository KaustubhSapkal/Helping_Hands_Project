package helpinghands.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import helpinghands.pojo.Address;
import helpinghands.repository.AddressRepository;
import helpinghands.service.AddressService;



@Service
public class AddressServiceImpl implements AddressService {

	@Autowired AddressRepository dao;
	
	@Override
	public Address saveAddress(Address address) {
		// TODO Auto-generated method stub
		return dao.save(address);
	}

	@Override
	public Address findAddress(int id) {
		// TODO Auto-generated method stub
		return dao.getById(id);
	}

}
