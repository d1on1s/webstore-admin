package online.denseleznev.webstoreadmin.service.impl;

import java.util.List;

import online.denseleznev.webstoreadmin.domain.Item;
import online.denseleznev.webstoreadmin.repository.ItemRepository;
import online.denseleznev.webstoreadmin.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemServiceImpl implements ItemService {
	
	@Autowired
	private ItemRepository itemRepository;
	
	public Item save(Item item) {
		return itemRepository.save(item);
	}
	
	public List<Item> findAll() {
		return (List<Item>) itemRepository.findAll();
	}
	
	public Item findOne(Long id) {
		return itemRepository.findOne(id);
	}
	
	public void removeOne(Long id) {
		itemRepository.delete(id);
	}
}
