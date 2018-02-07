package com.blog.util;


import java.io.IOException;
import java.io.StringReader;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;

import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.BooleanClause;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.highlight.Fragmenter;
import org.apache.lucene.search.highlight.Highlighter;
import org.apache.lucene.search.highlight.QueryScorer;
import org.apache.lucene.search.highlight.SimpleHTMLFormatter;
import org.apache.lucene.search.highlight.SimpleSpanFragmenter;
import org.apache.lucene.store.FSDirectory;

import com.blog.entity.User;
import com.blog.iutil.AbstractLuceneIndex;

public class LuceneIndexForUser extends AbstractLuceneIndex<User>{
	
	
	//添加博客索引
	public void addIndex(User user){
		try {
			IndexWriter writer=getWriter();
			Document doc=new Document();
			doc.add(new StringField("id",String.valueOf(user.getId()),Field.Store.YES));
			doc.add(new TextField("username",user.getName(),Field.Store.YES));
			doc.add(new TextField("description",user.getDes(),Field.Store.YES));
		
			writer.updateDocument(new Term("id",String.valueOf(user.getId())),doc);
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("添加成功");
	}
	
	//删除指定博客索引
	public void deleteIndex(Integer userId){
		try {
			IndexWriter writer=getWriter();
			writer.deleteDocuments(new Term("id",String.valueOf(userId)));
			writer.forceMergeDeletes();
			writer.commit();
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	//查询用户
	public List<User> search(String q){
		try{
			dir=FSDirectory.open(Paths.get("G:\\lucene"));
			IndexReader reader=DirectoryReader.open(dir);
			IndexSearcher is=new IndexSearcher(reader);
			BooleanQuery.Builder booleanQuery=new BooleanQuery.Builder();
			SmartChineseAnalyzer analyzer=new SmartChineseAnalyzer();
		
			QueryParser parser=new QueryParser("username",analyzer);
			Query query=parser.parse(q);
			QueryParser parser2=new QueryParser("description",analyzer);
			Query query2=parser2.parse(q);
			booleanQuery.add(query,BooleanClause.Occur.SHOULD);
			booleanQuery.add(query2,BooleanClause.Occur.SHOULD);
			TopDocs hits=is.search(booleanQuery.build(), 100);
			QueryScorer scorer=new QueryScorer(query);
			Fragmenter fragmenter=new SimpleSpanFragmenter(scorer);
			//设置样式
			SimpleHTMLFormatter simpleHTMLFormatter=new SimpleHTMLFormatter("<b><font color='red'>","</font></b>");
			Highlighter highlighter=new Highlighter(simpleHTMLFormatter,scorer);
			highlighter.setTextFragmenter(fragmenter);
			List<User>userList=new LinkedList<User>();
			for(ScoreDoc scoreDoc:hits.scoreDocs){
				Document doc=is.doc(scoreDoc.doc);
				User user=new User();
				user.setId(Integer.parseInt(doc.get("id")));
				user.setDes(doc.get("description"));
				String username=doc.get("username");
				String description=doc.get("description");
				if(username!=null){
					TokenStream tokenStream=analyzer.tokenStream("username", new StringReader(username));
					String husername=highlighter.getBestFragment(tokenStream, username);
					if(StringUtil.isEmpty(husername)){
						user.setName(username);
					}
					else{
						user.setName(husername);
					}
				}
				if(description!=null){
					TokenStream tokenStream=analyzer.tokenStream("description", new StringReader(description));
					String hContent=highlighter.getBestFragment(tokenStream, description);
					if(StringUtil.isEmpty(hContent)){
						if(description.length()<=200){
							user.setDes(description);
						}
						else{
							user.setDes(description.substring(0, 200));
						}
					}
					else{
						user.setDes(hContent);
					}
				}
				userList.add(user);
			}
			return userList;
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	//更新博客索引
	public void updateIndex(User user){
	try{
		IndexWriter iw=getWriter();
		Document doc=new Document();
		doc.add(new StringField("id",String.valueOf(user.getId()),Field.Store.YES));
		doc.add(new TextField("description",user.getDes(),Field.Store.YES));
		doc.add(new TextField("username",user.getName(),Field.Store.YES));
		iw.updateDocument(new Term("id",String.valueOf(user.getId())),doc);
		iw.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
