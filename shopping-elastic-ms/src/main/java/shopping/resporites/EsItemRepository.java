package shopping.resporites;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import shopping.document.ElstItem;

public interface EsItemRepository extends ElasticsearchRepository<ElstItem,Long> {

    @Query("{\"multi_match\": {\"query\": \"?0\",\"fields\": [\"title\",\"brand\"]}}")
    public Page<ElstItem> findByKeyword(String keyword, Pageable pageable);
}
