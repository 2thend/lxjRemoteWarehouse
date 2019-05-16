package com.lxj.elastic.repository;

import com.lxj.elastic.bean.Book;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

/**
 * Created by liuxinjian on 2018/6/17.
 */
public interface BookRepository  extends ElasticsearchRepository<Book, Integer>{


    //参照https://docs.spring.io/spring-data/elasticsearch/docs/3.0.8.RELEASE/reference/html/
    List<Book> findByBookNameLike(String bookName);
}
