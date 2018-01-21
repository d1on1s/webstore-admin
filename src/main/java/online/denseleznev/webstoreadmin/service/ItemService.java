package online.denseleznev.webstoreadmin.service;

import online.denseleznev.webstoreadmin.domain.Item;

import java.util.List;

public interface ItemService {
	
	Item save(Item item);

	List<Item> findAll();
	
	Item findOne(Long id);
	
	void removeOne(Long id);
}
