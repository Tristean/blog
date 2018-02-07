package com.blog.test.sqlTest;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.blog.dto.output.BlogDetails;
import com.blog.entity.Blog;
import com.blog.service.imp.BlogBaseServiceImp;
import com.blog.services.BlogBaseService;
import com.blog.util.LuceneIndexForBlog;

public class BlogSqlTest {
	ApplicationContext app=new ClassPathXmlApplicationContext("spring.xml");
	@Autowired
	BlogBaseService bbs=app.getBean(BlogBaseServiceImp.class);
	
	@Test
	public void test() throws UnsupportedEncodingException{
		Blog blog=new Blog();
		blog.setContext("�ҵĲ���");
		blog.setDes("�ҵĲ���");
		blog.setTitle("����");
		bbs.createBlog(blog, 3);
	}
	@Test
	public void test1() throws UnsupportedEncodingException{
		Blog blog=new Blog();
		blog.setContext("Ŭ��ѧϰ");
		blog.setTitle("���湤��");
		blog.setDes("���Բ���");
		blog.setId(3);
		blog.setUserId(3);
		bbs.updateBlog(blog, 3);
	}
	@Test
	public void test2() throws UnsupportedEncodingException{
		List<BlogDetails>blogs=bbs.getUserBlogList(3);
		System.out.println(blogs==null);
	}
	@Test
	public void test3(){
		LuceneIndexForBlog lib=new LuceneIndexForBlog();
		Blog blog=bbs.selectByPrimaryKey(3);
		lib.addIndex(blog);
	}
	@Test
	public void test4(){
		Blog blog=bbs.selectByPrimaryKey(3);
		System.out.println(blog.getDes());
		LuceneIndexForBlog lib=new LuceneIndexForBlog();
		List<Blog>blogs=lib.search("���Բ���");
		System.out.println(blogs.size());
	}

}
