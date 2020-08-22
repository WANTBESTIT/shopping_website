package shopping.service;

import com.gec.shopping.pojo.TbItem;
import com.gec.shopping.pojo.entity.RestPage;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;
import java.util.Map;

public interface ItemSearchService {

    public  Map search(Map searchEntity);

    void importList(List<TbItem> itemList);

    void deleteByGoodsIds(List<Long> asList);
}
