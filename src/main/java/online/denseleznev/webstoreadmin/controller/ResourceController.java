package online.denseleznev.webstoreadmin.controller;

import java.util.ArrayList;

import online.denseleznev.webstoreadmin.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ResourceController {

	@Autowired
	private ItemService itemService;
	
	@RequestMapping(value="/item/removeList", method=RequestMethod.POST)
	public String removeList(
			@RequestBody ArrayList<String> itemIdList, Model model
			){
		
		for (String id : itemIdList) {
			String itemId =id.substring(8);
			itemService.removeOne(Long.parseLong(itemId));
		}
		
		return "delete success";
	}
}
