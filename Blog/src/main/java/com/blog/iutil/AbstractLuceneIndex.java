package com.blog.iutil;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

public abstract class AbstractLuceneIndex<T> {
	protected Directory dir=null;
	
	protected IndexWriter getWriter() throws IOException{
		dir=FSDirectory.open(Paths.get("G:\\lucene"));
		SmartChineseAnalyzer analyzer=new SmartChineseAnalyzer();
		IndexWriterConfig iwc=new IndexWriterConfig(analyzer);
		IndexWriter writer=new IndexWriter(dir,iwc);
		return writer;
	}
	
	public abstract void addIndex(T Object);
	
	public abstract void deleteIndex(Integer id);
	
	public abstract List<T> search(String q);
	
	public abstract void updateIndex(T Object);
}
