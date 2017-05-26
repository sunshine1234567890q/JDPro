package cn.itheima.com.Service;

import javax.naming.directory.SearchResult;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrQuery.ORDER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sun.rowset.internal.Row;

import cn.itheima.com.Dao.ProductDao;
import cn.itheima.com.bean.Page;
@Service
public class ProductService {
	@Autowired
	private ProductDao productDao;
	private static final int ROWS=60;
	
	public Page queryProduct(String queryString,String catalog_name,String price,Integer sort,Integer page) throws Exception{
		//拼接查询条件
		SolrQuery query=new SolrQuery();
		//设置查询条件
		if(queryString!=null && !"".equals(queryString)){
			query.setQuery(queryString);
		}else{
			query.setQuery("*:*");
		}
		//
		if(catalog_name!=null && !"".equals(catalog_name)){
			query.addFilterQuery("product_catalog_name:"+catalog_name);
		}
		if(price!=null && !"".equals(price)){
			String[] strings=price.split("-");
			query.addFilterQuery("product_price:["+strings[0]+" TO "+strings[1]+"]");
		}
		if(sort==0){
			query.setSort("product_price",ORDER.desc);
		}else{
			query.setSort("product_price",ORDER.asc);
		}
		if(page!=null){
			page=1;
		}
		//分页条件
		query.setStart((page-1)*ROWS);
		query.setRows(ROWS);
		//设置默认搜索域 keywords是啥
		query.set("df", "product_keywords");
		//开启高亮显示
		query.setHighlight(true);
		query.addHighlightField("product_name");
		query.setHighlightSimplePre("<em style='color:red'>");
		query.setHighlightSimplePost("</em>");
		
		Page searchResult=productDao.queryProduct(query);
		//取总记录数
		long recordCount=searchResult.getRecordCount();
		long pageCount=recordCount/ROWS;
		if(recordCount % ROWS>0) pageCount++;
		searchResult.setPageCount(pageCount);
		return searchResult;
	}
}
