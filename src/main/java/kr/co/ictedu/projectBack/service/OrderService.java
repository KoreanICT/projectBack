package kr.co.ictedu.projectBack.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.ictedu.projectBack.dao.OrderDao;

@Service
public class OrderService {

	@Autowired
	private OrderDao orderDao;

}
