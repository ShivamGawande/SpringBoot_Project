package com.business.controllors;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.business.basiclogics.Logic;
import com.business.entities.Admin;
import com.business.entities.Orders;
import com.business.entities.Product;
import com.business.entities.User;
import com.business.loginCredentials.AdminLogin;
import com.business.loginCredentials.UserLogin;
import com.business.services.AdminServices;
import com.business.services.OrderServices;
import com.business.services.ProductServices;
import com.business.services.UserServices;

@RestController
public class AdminControllor {
	@Autowired
	private UserServices userServices;
	@Autowired
	private AdminServices adminServices;
	@Autowired
	private ProductServices productServices;
	@Autowired
	private OrderServices orderServices;
	
	private String email;
	private User user;
	
	//validating admin login
	@GetMapping("/adminLogin")
	public String getAllData(@ModelAttribute("adminLogin") AdminLogin adminLogin, Model model) {
		String email=adminLogin.getEmail();
		String password=adminLogin.getPassword();
		if(adminServices.validatingLogin(email, password)) {
			return "redirect:/admin/services";
		}
		else {
			model.addAttribute("error", "Invalid email or password");
			return "Login";
		}
	}
	
	@GetMapping("/userlogin")
	public String userLogin(@ModelAttribute("userLogin") UserLogin userLogin,Model model) {
		email=userLogin.getUserEmail();
		String password=userLogin.getUserPassword();
		if(userServices.validateUser(email, password)) {
			user=userServices.getUserByEmail(email);
			List<Orders> orders = orderServices.getOrderByUser(user);
			model.addAttribute("orders", orders);
			model.addAttribute("name", user.getUname());
			return "BuyProduct";
		}else {
			model.addAttribute("error2", "Invalid email or password");
			return "Login";
		}
	}
	
	//Searching Product By Name
	@PostMapping("/product/search")
	public String seachHandler(@RequestParam("productName") String name, Model model) {
		Product product=productServices.getProductByName(name);
		if(product==null) {
			model.addAttribute("message", "SORRY....! Product Unavilable");
			model.addAttribute("product", product);
			List<Orders> orders = orderServices.getOrderByUser(user);
			model.addAttribute("orders", orders);
			return "BuyProduct";
		}
		List<Orders> orders = orderServices.getOrderByUser(user);
		model.addAttribute("orders", orders);
		model.addAttribute("product", product);
		return "BuyProduct";
	}
	
	//Providing services
	@GetMapping("/admin/services")
	public String returnBack(Model model) {
		List<User> users = userServices.getAll();
		List<Admin> admins = adminServices.getAll();
		List<Product> products = productServices.getAllProducts();
		List<Orders> orders = orderServices.getAllOrders();
		model.addAttribute("users", users );
		model.addAttribute("orders", orders);
		model.addAttribute("products", products);
		model.addAttribute("admins", admins);
		
		return "Admin_Page";
	}
	
	//Invoking addAdmin Page
	@GetMapping("/addAdmin")
	public String addAdminPage() {
		return "Add_Admin";
	}
	
	//Handling AddAdmin
	@PostMapping("addingAdmin")
	public String addAdmin( @ModelAttribute Admin admin)
	{

		this.adminServices.addAdmin(admin);
		return "redirect:/admin/services";

	}
	
	//invoking updateAdmin Page
	@GetMapping("/updateAdmin/{adminId}")
	public String update(@PathVariable("adminId") int id,Model model)
	{
		Admin admin = this.adminServices.getAdmin(id);
		model.addAttribute("admin", admin);
		return "Update_Admin";
	}

	//Handling Update Page
	@GetMapping("/updatingAdmin/{id}")
	public String updateAdmin(@ModelAttribute Admin admin,@PathVariable("id") int id)
	{
		this.adminServices.update(admin, id);
		return "redirect:/admin/services";
	}

	//IHandling delete operation
	@GetMapping("/deleteAdmin/{id}")
	public String deleteAdmin(@PathVariable("id") int id)
	{
		this.adminServices.deleteUser(id);
		return "redirect:/admin/services";
	}

	//Invoking AddProduct Page
	@GetMapping("/addProduct")
	public String addProduct()
	{
		return "Add_Product";
	}

	//Invoking Update Product Page
	@GetMapping("/updateProduct/{productId}")
	public String updateProduct(@PathVariable("productId") int id,Model model)
	{
		Product product=this.productServices.getProduct(id);
		System.out.println(product);
		model.addAttribute("product", product);
		return "Update_Product";
	}

	//Invoking AddUser Page
	@GetMapping("/addUser")
	public String addUser()
	{
		return "Add_User";
	}

	//Invoking UpdateUser Page
	@GetMapping("/updateUser/{userId}")
	public String updateUserPage(@PathVariable("userId") int id,Model model)
	{
		User user = this.userServices.getUser(id);
		model.addAttribute("user", user);
		return "Update_User";
	}
	//Placing  Order
	@PostMapping("/product/order")
	public String orderHandler(@ModelAttribute() Orders order,Model model)
	{
		double  totalAmount = Logic.countTotal(order.getoPrice(),order.getoQuantity());
		order.setTotalAmount(totalAmount);
		order.setUser(user);
		Date d=new Date();
		order.setOrderDate(d);
		this.orderServices.saveOrder(order);
		model.addAttribute("amount",totalAmount);
		return "Order_success";
	}

	@GetMapping("/product/back")
	public String back(Model model)
	{
		List<Orders> orders = this.orderServices.getOrderByUser(user);
		model.addAttribute("orders", orders);
		return "BuyProduct";
	}
}
