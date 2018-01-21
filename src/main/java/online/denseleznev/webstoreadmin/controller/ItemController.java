package online.denseleznev.webstoreadmin.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import online.denseleznev.webstoreadmin.domain.Item;
import online.denseleznev.webstoreadmin.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/item")
public class ItemController {

	@Autowired
	private ItemService itemService;

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String addItem(Model model) {
		Item item = new Item();
		model.addAttribute("item", item);
		return "addItem";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String addItemPost(@ModelAttribute("item") Item item, HttpServletRequest request) {
		itemService.save(item);

		MultipartFile itemImage = item.getItemImage();

		try {
			byte[] bytes = itemImage.getBytes();
			String name = item.getId() + ".png";
			BufferedOutputStream stream = new BufferedOutputStream(
					new FileOutputStream(new File("src/main/resources/static/image/item/" + name)));
			stream.write(bytes);
			stream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "redirect:itemList";
	}
	
	@RequestMapping("/itemInfo")
	public String itemInfo(@RequestParam("id") Long id, Model model) {
		Item item = itemService.findOne(id);
		model.addAttribute("item", item);
		
		return "itemInfo";
	}
	
	@RequestMapping("/updateItem")
	public String updateItem(@RequestParam("id") Long id, Model model) {
		Item item = itemService.findOne(id);
		model.addAttribute("item", item);
		
		return "updateItem";
	}
	
	@RequestMapping(value="/updateItem", method=RequestMethod.POST)
	public String updateItemPost(@ModelAttribute("item") Item item, HttpServletRequest request) {
		itemService.save(item);
		
		MultipartFile itemImage = item.getItemImage();
		
		if(!itemImage.isEmpty()) {
			try {
				byte[] bytes = itemImage.getBytes();
				String name = item.getId() + ".png";
				
				Files.delete(Paths.get("src/main/resources/static/image/item/"+name));
				
				BufferedOutputStream stream = new BufferedOutputStream(
						new FileOutputStream(new File("src/main/resources/static/image/item/" + name)));
				stream.write(bytes);
				stream.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return "redirect:/item/itemInfo?id="+ item.getId();
	}
	
	@RequestMapping("/itemList")
	public String itemList(Model model) {
		List<Item> itemList = itemService.findAll();
		model.addAttribute("itemList", itemList);
		return "itemList";
		
	}
	
	@RequestMapping(value="/remove", method=RequestMethod.POST)
	public String remove(
			@ModelAttribute("id") String id, Model model
			) {
		itemService.removeOne(Long.parseLong(id.substring(8)));
		List<Item> itemList = itemService.findAll();
		model.addAttribute("itemList", itemList);
		
		return "redirect:/item/itemList";
	}

}
