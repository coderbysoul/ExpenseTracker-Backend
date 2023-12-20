package com.expense.tracker.backend.demo;

import com.expense.tracker.backend.demo.Expense;
import com.expense.tracker.backend.demo.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

@RequestMapping("/api/expenses")
public class ExpenseController {

    @Autowired
    private ExpenseRepository expenseRepository;

    @Autowired
    public ExpenseController(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    @GetMapping
    public List<Expense> getAllExpenses() {
        return (List<Expense>) expenseRepository.findAll();
    }

    @PostMapping
    public Expense addExpense(@RequestBody Expense expense) {
        return expenseRepository.save(expense);
    }

    @GetMapping("/{id}")
    public Expense getExpenseById(@PathVariable Long id) {
        return expenseRepository.findById(id).orElse(null);
    }

    @PutMapping("/{id}")
    public Expense updateExpense(@PathVariable Long id, @RequestBody Expense updatedExpense) {
        Expense existingExpense = expenseRepository.findById(id).orElse(null);
        if (existingExpense != null) {
            existingExpense.setDescription(updatedExpense.getDescription());
            existingExpense.setAmount(updatedExpense.getAmount());
            existingExpense.setCategory(updatedExpense.getCategory());
            existingExpense.setDate(updatedExpense.getDate());
            return expenseRepository.save(existingExpense);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public void deleteExpense(@PathVariable Long id) {
        expenseRepository.deleteById(id);
    }
}
