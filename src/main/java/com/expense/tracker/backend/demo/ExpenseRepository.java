package com.expense.tracker.backend.demo;


import org.springframework.data.repository.CrudRepository;

import com.expense.tracker.backend.demo.Expense;

public interface ExpenseRepository extends CrudRepository<Expense, Long> {
}
