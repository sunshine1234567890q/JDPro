package cn.itheima.com.Dao;

import org.apache.solr.client.solrj.SolrQuery;

import cn.itheima.com.bean.Page;

public interface ProductDao {
	public Page queryProduct(SolrQuery solrQuery) throws Exception;
}
