package shopping.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import shopping.service.ItemSearchService;

import java.util.Map;

@RestController
@RequestMapping("/search-ms")
public class ItemSearchController {

    @Autowired
    private ItemSearchService itemSearchService;

    @PostMapping("/search")
    public Map search(@RequestBody Map searchMap){
        System.out.println("searchMap:=======>"+searchMap);
        return itemSearchService.search(searchMap);
    }

}
