package work.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import work.bean.Item;
import work.service.ItemsService;

public class ItemsAction extends ActionSupport {

	private ItemsService itemsService = new ItemsService();

	
	private Item item = null;
	private List<Item> items = new ArrayList<>();

	
	// 列出所有商品
	public String list() {
		// 一般情况下，要把业务处理的过程交给 service处理
		items = itemsService.listAll(item == null ? -1 : item.getStatus());
		return SUCCESS;
	}
	
	// 下架商品
	public String down() {
		itemsService.changeStatus(item, 1);
		return SUCCESS;
	}
	
	// 上架商品
	public String up() {
		itemsService.changeStatus(item, 0);
		return SUCCESS;
	}
	
	// 新增商品
	public String add() {

		String msg = "添加失败！";
		
		if(itemsService.addItem(item))
			msg = "添加成功！";
		
		ServletActionContext.getRequest().setAttribute("msg", msg);
		
		item = null;
		
		return SUCCESS;
		
	}
	
	

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}
}
