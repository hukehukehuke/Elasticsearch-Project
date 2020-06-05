package com.huke.elasticsearch;

import com.huke.elasticsearch.pojo.Item;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface ItemRepository extends ElasticsearchRepository<Item,Long> {
   List<Item> findByTitle(String title);
}
