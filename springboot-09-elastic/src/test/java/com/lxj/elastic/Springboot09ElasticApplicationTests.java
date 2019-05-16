package com.lxj.elastic;

import com.lxj.elastic.bean.Aritcle;
import com.lxj.elastic.bean.Book;
import com.lxj.elastic.repository.BookRepository;
import io.searchbox.client.JestClient;
import io.searchbox.core.Index;
import io.searchbox.core.Search;
import io.searchbox.core.SearchResult;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Springboot09ElasticApplicationTests {

	@Autowired
	JestClient jestClient;


	@Autowired
	BookRepository bookRepository;


	@Test
	public void testRepositoryIndex(){
		Book book = new Book();
		book.setId(1);
		book.setAuthor("曹雪芹");
		book.setBookName("《红楼梦》");
		bookRepository.index(book);
	}

	@Test
	public void testRepositorySearch(){
		List<Book> byBookNameLike = bookRepository.findByBookNameLike("梦");
		System.out.println("book list = ");
		System.out.println(byBookNameLike);
	}



	@Test
	public void contextLoads() {
		//1.给ES中索引（保存）一个文档。
		Aritcle aritcle = new Aritcle();
		aritcle.setId(1);
		aritcle.setAuthor("zhangsang");
		aritcle.setContent("a good news");

		//构建一个索引功能
		Index build = new Index.Builder(aritcle).index("liuxinjian").type("news").build();

		try {
			//执行
			jestClient.execute(build);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}


	//测试搜索
	@Test
	public void search(){
		//查询表达式
		String json = "{\n" +
				"    \"query\" : {\n" +
				"        \"match\" : {\n" +
				"            \"content\" : \"news\"\n" +
				"        }\n" +
				"    }\n" +
				"}";
		//构造搜索功能
		Search search = new Search.Builder(json).addIndex("liuxinjian").addType("news").build();

		//执行
		try {
			SearchResult searchResult = jestClient.execute(search);
			System.out.println("searchResult = ");
			System.out.println(searchResult.getJsonString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
