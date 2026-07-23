package kr.co.ictedu.projectBack.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.ictedu.projectBack.dao.OrderDao;
import kr.co.ictedu.projectBack.vo.OrderFormVO;
import kr.co.ictedu.projectBack.vo.OrderItemVO;

@Service
public class OrderService {

	@Autowired
	private OrderDao orderDao;

	public void addOrder(OrderFormVO ofvo) {
		orderDao.addOrderForm(ofvo);
		List<OrderItemVO> list = ofvo.getOList();
		orderDao.addOrderItem(list);
	}
}
