package helpinghands.controller;

import java.util.ArrayList;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import helpinghands.DTO.CartDTO;
import helpinghands.DTO.OrderDetailsDTO;
import helpinghands.DTO.PlaceOrderDTO;
import helpinghands.pojo.Address;
import helpinghands.pojo.Donor;
import helpinghands.pojo.Order;
import helpinghands.pojo.OrderDetails;
import helpinghands.pojo.Product;
import helpinghands.pojo.Receiver;
import helpinghands.response.OrderResponseDTO;
import helpinghands.response.Response;
import helpinghands.service.AddressService;
import helpinghands.service.DonorService;
import helpinghands.service.OrderService;
import helpinghands.service.OrderdetailService;
import helpinghands.service.ProductService;
import helpinghands.service.ReceiverService;


import org.springframework.web.bind.annotation.RequestParam;



@CrossOrigin
@RestController
@RequestMapping("/api/orders")
public class OrdersController {

	@Autowired
	private OrderService orderService;
	@Autowired 
	private ReceiverService receiverService;
	@Autowired 
	private AddressService addressService;
	@Autowired
	private OrderdetailService orderDetailsService;
	@Autowired
	private ProductService productService;
	
	@Autowired
	private DonorService donorService;
	
	@PostMapping
	public ResponseEntity<?> save(@RequestBody PlaceOrderDTO dto) {	
		Address address=addressService.saveAddress(dto.getAddress());
		Order order=new Order();
		order.setOrderDate(new Date());
		order.setAddress(address);
		Receiver receiver=receiverService.findById(dto.getReceiverid());
		order.setReceiver(receiver);
		Order orders=orderService.saveOrder(order);
		String selleremail="";
		String sellername="";
		Product product=null;
		int qty=0;
		
		OrderDetails od=new OrderDetails();
		for(CartDTO cart : dto.getCart()) {
			
			od.setOrder(orders);
			od.setQty(cart.getQty());
			product=productService.findProductById(cart.getProdid());
			selleremail=product.getDonor().getEmail();
			sellername=product.getDonor().getName();
			qty=cart.getQty();
			od.setProduct(product);
			
			}
			
		
		int	qtys = product.getQty();
		if(qty==0 || qty>qtys) {
		return Response.status(HttpStatus.ACCEPTED);
		}else {
		orderDetailsService.saveOrderDetails(od);
		}
		
		
		int num = qtys-qty;
		product.setQty(num);
		productService.updateProduct(product);
		
		
		System.out.println(dto.getAddress());
		System.out.println(dto.getReceiverid());
		System.out.println(dto.getCart().get(0));
		//send email
		System.out.println(selleremail+" "+sellername+" "+qty);
		

		
		return Response.status(HttpStatus.OK);
	}
	
	
	@GetMapping
	public ResponseEntity<?> findAllOrders(Optional<Integer> receiverid) {
		List<Order> result=null;
		if(receiverid.isPresent()) {
			Receiver receiver=receiverService.findById(receiverid.get());
			 result= orderService.getReceiverOrders(receiver);
		}else {
			result=orderService.getAllOrders();
		}
		return Response.success(result);
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<?> findOrderById(@PathVariable("id") int id) {
		Order order = orderService.findById(id);
		List<OrderDetails> details=orderDetailsService.findByOrder(order);
		List<OrderDetailsDTO> detailsdto=new ArrayList<OrderDetailsDTO>();
		details.forEach(od -> {
			OrderDetailsDTO dto=OrderDetailsDTO.fromEntity(od);
			detailsdto.add(dto);
		});
		OrderResponseDTO result=new OrderResponseDTO();
		result.setOrder(order);
		result.setDetails(detailsdto);
		return Response.success(result);
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<?> deleteOrder(@PathVariable("id") int id) {
		Order order = orderService.findById(id);
		List<OrderDetails> orderList = orderDetailsService.findByOrder(order);
		
		for(OrderDetails orderdetails : orderList) {
		//OrderDetails orderdetails = orderList.get(0);
		System.out.println("OrderDetails = "+orderdetails.getId() );
		OrderDetails ord =  orderDetailsService.findById(orderdetails.getId());
		int qty=ord.getQty();
		
		Product prd=ord.getProduct();
		int pqty=prd.getQty();
		
		qty+=pqty;
		prd.setQty(qty);
		productService.updateProduct(prd);
		orderDetailsService.deleteOrder(orderdetails.getId());
		orderService.deleteOrder(id);
		}
		return Response.status(HttpStatus.OK);
	}
	
	@GetMapping("/seller")
	public ResponseEntity<?> findSellerPrroduct(@RequestParam  String sid) {
		int id = Integer.parseInt(sid);
		System.out.println("Wellcome to Find Seller Product ID = " +id);
		Donor donor= donorService.findById(id);
		List <Product> product = productService.findProducts(id);
		List<Object> obj =new ArrayList<Object>();
		
		for(Product prd : product) {
			List<OrderDetails> ord = orderDetailsService.findByProduct(prd);
			
			for(OrderDetails ords : ord) {
				obj.add(ords);
				System.out.println("The OrderDetails Object is ="+ords);	
			}
			
		}
	
		System.out.println("The Seller Object is ="+donor);
		System.out.println("The Product Object is ="+product);
		System.out.println("The Final Object is ="+obj);
		
		
		return Response.success(obj);
	}
}
