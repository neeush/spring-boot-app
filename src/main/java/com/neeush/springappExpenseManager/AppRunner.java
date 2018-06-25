package com.neeush.springappExpenseManager;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class AppRunner implements CommandLineRunner{
	
	@Autowired
	private ExpenseRepository expenseRepository;
	
	@Override
	public void run(String... args) throws Exception {
		
		
		
		/*Expense exp1 = new Expense(1000, null, "Sreenivas", "Groceries", "Rice 1 Kg");
		Expense exp2 = new Expense(1000, null, "Neeush", "Groceries", "Rice 2 Kg");
		Expense exp3 = new Expense(1000, null, "Ramesh", "Groceries", "Rice 3 Kg");
		Expense exp4 = new Expense(1000, null, "Naresh", "Groceries", "Rice 4 Kg");
		Expense exp5 = new Expense(1000, null, "Joy", "Groceries", "Rice 5 Kg");
		
		expenseRepository.deleteAll();
		
		expenseRepository.save(exp1);
		expenseRepository.save(exp2);
		expenseRepository.save(exp3);
		expenseRepository.save(exp4);
		expenseRepository.save(exp5);*/
	
	}
	
	
}
