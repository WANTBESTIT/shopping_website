package shopping.service.impl;

import com.gec.shopping.pojo.TbItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import shopping.document.ElstItem;
import shopping.resporites.EsItemRepository;
import shopping.service.ItemSearchService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ItemSearchServiceImpl implements ItemSearchService {

    @Autowired
    private EsItemRepository repository;

    @Override
    public Map search(Map searchMap) {
        System.out.println("进行search");
        Map map = new HashMap();
        Page<ElstItem> page = repository.findByKeyword(searchMap.get("keywords").toString(),null);
        map.put("rows",page.getContent());
        return map;
    }

    @Override
    public void importList(List<TbItem> itemList) {
    }

    @Override
    public void deleteByGoodsIds(List<Long> asList) {

    }
}
