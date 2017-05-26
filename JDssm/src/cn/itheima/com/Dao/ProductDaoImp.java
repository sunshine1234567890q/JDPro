package cn.itheima.com.Dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cn.itheima.com.bean.Page;
import cn.itheima.com.bean.Product;
@Repository
public class ProductDaoImp implements ProductDao{
	@Autowired
	private SolrServer solrServer;
	public Page queryProduct(SolrQuery solrQuery) throws Exception{
		Page page=new Page();
		//根据query对象查询列表
		QueryResponse response=solrServer.query(solrQuery);
		SolrDocumentList results = response.getResults();
		List<Product> list=new ArrayList<Product>();
		for (SolrDocument solrDocument : results) {
			Product product=new Product();
			product.setPid((String)solrDocument.get("id"));
			//取高亮显示
			Map<String, Map<String, List<String>>> highlighting = response.getHighlighting();
			List<String> li=highlighting.get(solrDocument.get("id")).get("product_name");
			String productName="";
			if(li!=null && li.size()>0){
				productName=li.get(0);
			}else{
				productName=(String)solrDocument.get("product_name");
			}
			product.setName(productName);
			product.setCatalog_name((String)solrDocument.get("product_catalog_name"));
			product.setPrice((float)solrDocument.get("product_price"));
			product.setDescription((String)solrDocument.get("product_description"));
			product.setPicture((String)solrDocument.get("product_picture"));
			list.add(product);
		}
		page.setList(list);
		return page;
	}
}
