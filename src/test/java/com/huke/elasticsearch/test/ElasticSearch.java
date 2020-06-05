package com.huke.elasticsearch.test;

import com.huke.elasticsearch.ItemRepository;
import com.huke.elasticsearch.pojo.Item;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.AggregationBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.sort.SortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ElasticSearch {

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    @Autowired
    private ItemRepository itemRepository;

    @Test
    public void testIndex() {
        this.elasticsearchTemplate.createIndex(Item.class);
        this.elasticsearchTemplate.putMapping(Item.class);
    }

    @Test
    public void testCreat() {
        Item item = new Item(1L, "2", "3", "4", 444.0, "4");
        this.itemRepository.save(item);

        List<Item> itemList = new ArrayList<>();
        this.itemRepository.saveAll(itemList);
    }

    @Test
    public void testFind() {
        Optional<Item> item = this.itemRepository.findById(1l);
        Iterable<Item> price = this.itemRepository.findAll(Sort.by("price").descending());
        price.forEach(System.out::println);
    }

    @Test
    public void testFindByTitle() {
        List<Item> temp = this.itemRepository.findByTitle("手机");
        temp.forEach(System.out::println);
    }

    @Test
    public void testSearch() {
        //通过查询构建器工具构建条件
        MatchQueryBuilder matchQueryBuilder = QueryBuilders.matchQuery("title", "手机");
        Iterable<Item> search = this.itemRepository.search(matchQueryBuilder);
        search.forEach(System.out::println);
    }

    @Test
    public void testNative() {
        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
        nativeSearchQueryBuilder.withQuery(QueryBuilders.matchQuery("title", "手机"));
        nativeSearchQueryBuilder.withSort(SortBuilders.fieldSort("price").order(SortOrder.DESC));
        Page<Item> search = this.itemRepository.search(nativeSearchQueryBuilder.build());
        List<Item> content = search.getContent();
        content.forEach(System.out::println);
    }

    @Test
    public  void testAggs(){
        //初始化自定义构造器
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
        //添加聚合
        queryBuilder.addAggregation(AggregationBuilders.terms("brandaggs").field("brand"));
        //添加结果集过滤不包含任何字段儿
        //queryBuilder.withSourceFilter()
    }
}
