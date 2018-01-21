package online.denseleznev.webstoreadmin.repository;

import online.denseleznev.webstoreadmin.domain.Item;
import org.springframework.data.repository.CrudRepository;

public interface ItemRepository extends CrudRepository<Item, Long>{

}
