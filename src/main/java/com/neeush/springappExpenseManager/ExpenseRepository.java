package com.neeush.springappExpenseManager;

import org.springframework.data.repository.CrudRepository;

public interface ExpenseRepository  extends CrudRepository<Expense, String>{


}
