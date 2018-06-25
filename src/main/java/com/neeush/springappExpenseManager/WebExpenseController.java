package com.neeush.springappExpenseManager;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.Principal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.stream.Stream;

import javax.servlet.http.HttpServletResponse;
import javax.websocket.server.PathParam;

import org.apache.tomcat.util.http.fileupload.util.Streams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class WebExpenseController {
	

	@Autowired
	ExpenseRepository expRepo;
	
	@Autowired
	BoxStorageService boxStorageService;
	
	private static final Logger log = LoggerFactory.getLogger(WebExpenseController.class);
	
	@RequestMapping(value="/familyexpenses",method = RequestMethod.GET)
	public String getExpenses(Model model,Principal principal) {
		log.info("Get Expense :" + principal.getName());
		model.addAttribute("message", "Whats up");
		Iterable<Expense> allExpenses = expRepo.findAll();
		model.addAttribute("list", allExpenses);
		model.addAttribute("user", "Hello,"+principal.getName());

		return "welcome";
	}
	@RequestMapping(value="/login",method = RequestMethod.GET)
	public String getExpenses1(Model model) {
		log.info("Get Expense");
		model.addAttribute("message", "Whats up");
		//Iterable<Expense> allExpenses = expRepo.findAll();
		//model.addAttribute("list", allExpenses);

		return "login-page";
	}
	@RequestMapping(value="/familyexpenses",method = RequestMethod.POST)
	public String addExpenses(@RequestParam("userId") String username,
			@RequestParam("expAmount") String expAmount,@RequestParam("expenseType") String expenseType,
			@RequestParam("expDescription") String expenseDescription,@RequestParam("paidDate") String paidDate,@RequestParam("file") MultipartFile file, Model model) throws ParseException {
		log.info("Add Expense");

		Expense exp = new Expense( );
		
		exp.setUserId(username);
		Integer in = new Integer(expAmount);
		exp.setExpAmount(in);
		exp.setExpenseType(expenseType);
		exp.setExpDescription(expenseDescription);
		Date date=new SimpleDateFormat("yyyy-mm-dd").parse(paidDate);  
	    System.out.println(paidDate+"\t"+date); 
		exp.setExpDate(date);
		
		if(file!=null && !file.isEmpty()) {
			
			String url = boxStorageService.store(file);
			exp.setExpDocId(url);
		}
		
		
		expRepo.save(exp);
		
		
		
		model.addAttribute("message", "Expense Added");
		Iterable<Expense> allExpenses = expRepo.findAll();
		model.addAttribute("list", allExpenses);

		return "welcome";
	}
		
}
