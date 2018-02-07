package com.blog.util;

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
import org.apache.lucene.index.Term;
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
import org.apache.tomcat.jni.Directory;

import com.blog.entity.Blog;
import com.blog.iutil.AbstractLuceneIndex;

public class LuceneIndexForBlog extends AbstractLuceneIndex<Blog> {

	@Override
	public void addIndex(Blog blog) {
		// TODO Auto-generated method stub
		try{
			IndexWriter iw=getWriter();
			Document doc=new Document();
			doc.add(new StringField("id",String.valueOf(blog.getId()),Field.Store.YES));
			doc.add(new TextField("description",blog.getDes(),Field.Store.YES));
			doc.add(new TextField("title",blog.getTitle(),Field.Store.YES));
			
			iw.updateDocument(new Term("id",String.valueOf(blog.getId())),doc);
			iw.close();
			System.out.println("添加成功");
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}

	@Override
	public void deleteIndex(Integer id) {
		// TODO Auto-generated method stub
		try{
			IndexWriter writer=getWriter();
			writer.deleteDocuments(new Term("id", String.valueOf(id)));
			writer.forceMergeDeletes(); // 强制删除
			writer.commit();
			writer.close();
			System.out.println("删除成功");
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	@Override
	public List<Blog> search(String q) {
		// TODO Auto-generated method stub
		try{
			dir=FSDirectory.open(Paths.get("G:\\lucene"));
			IndexReader reader=DirectoryReader.open(dir);
			IndexSearcher is=new IndexSearcher(reader);
			BooleanQuery.Builder booleanQuery=new BooleanQuery.Builder();
			SmartChineseAnalyzer analyzer=new SmartChineseAnalyzer();
			
			QueryParser parser=new QueryParser("title",analyzer);
			Query query=parser.parse(q);
			QueryParser parser2=new QueryParser("description",analyzer);
			Query query2=parser2.parse(q);
			booleanQuery.add(query,BooleanClause.Occur.SHOULD);
			booleanQuery.add(query2,BooleanClause.Occur.SHOULD);
			TopDocs hits=is.search(booleanQuery.build(), 100);
			QueryScorer scorer=new QueryScorer(query);
			Fragmenter fragmenter=new SimpleSpanFragmenter(scorer);
			
			SimpleHTMLFormatter simpleHTMLFormatter=new SimpleHTMLFormatter("<b><font color='red'>","</font></b>");
			Highlighter highlighter=new Highlighter(simpleHTMLFormatter,scorer);
			highlighter.setTextFragmenter(fragmenter);
			List<Blog>blogs=new LinkedList<Blog>();
			for(ScoreDoc scoreDoc:hits.scoreDocs){
				Document doc=is.doc(scoreDoc.doc);
				Blog blog=new Blog();
				blog.setId(Integer.parseInt(doc.get("id")));
				blog.setDes(doc.get("description"));
				blog.setTitle(doc.get("title"));
				
				String description=doc.get("description");
				String title=doc.get("title");
				if(description!=null){
					TokenStream tokenStream=analyzer.tokenStream("description", new StringReader(description));
					String hdescription=highlighter.getBestFragment(tokenStream, description);
					if(StringUtil.isEmpty(hdescription)){
						blog.setDes(description);
					}
					else{
						blog.setDes(hdescription);
					}
				}
				if(title!=null){
					TokenStream tokenStream=analyzer.tokenStream("title", new StringReader(title));
					String htitle=highlighter.getBestFragment(tokenStream, title);
					if(StringUtil.isEmpty(htitle)){
						blog.setTitle(title);
					}
					else{
						blog.setTitle(htitle);
					}
				}
				blogs.add(blog);
			}
			return blogs;
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return null;
	}

	@Override
	public void updateIndex(Blog blog) {
		// TODO Auto-generated method stub
		try{
			IndexWriter iw=getWriter();
			Document doc=new Document();
			doc.add(new StringField("id",String.valueOf(blog.getId()),Field.Store.YES));
			doc.add(new TextField("description",blog.getDes(),Field.Store.YES));
			doc.add(new TextField("title",blog.getTitle(),Field.Store.YES));
			iw.updateDocument(new Term("id",String.valueOf(blog.getId())),doc);
			iw.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
}
