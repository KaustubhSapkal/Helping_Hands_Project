package helpinghands.service;

import helpinghands.pojo.Address;

public interface AddressService {
	Address saveAddress(Address address);
	Address findAddress(int id);
}
