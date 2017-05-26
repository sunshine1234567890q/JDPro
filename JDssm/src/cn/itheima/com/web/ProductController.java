package cn.itheima.com.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cn.itheima.com.Service.ProductService;
import cn.itheima.com.bean.Page;

@Controller
public class ProductController {
	@Autowired
	private ProductService productService;
	
	@RequestMapping("/list.action")
	//String queryString,String catalog_name,String price,String sort,Integer page
	//String queryString,String catalog_name,String price,@RequestParam(defaultValue="0")Integer sort,@RequestParam(defaultValue="1")Integer page,Model model
	public String queryProduct(String queryString,String catalog_name,String price,@RequestParam(defaultValue="0")Integer sort,@RequestParam(defaultValue="1")Integer page,Model model) throws Exception{
		Page pagee=productService.queryProduct(queryString, catalog_name, price, sort, page);
		//回显
		model.addAttribute("queryString",queryString);
		model.addAttribute("catalog_name",catalog_name);
		model.addAttribute("page",page);
		model.addAttribute("price",price);
		model.addAttribute("result",pagee);
		model.addAttribute("sort",sort);
		
		return "product_list";
	}
}
