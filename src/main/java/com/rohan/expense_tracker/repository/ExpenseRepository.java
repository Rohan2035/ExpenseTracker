package com.rohan.expense_tracker.repository;

import com.rohan.expense_tracker.entity.Expense;
import com.rohan.expense_tracker.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Integer> {
    @Query("SELECT e from Expense e where e.user.username=:username")
    Optional<List<Expense>> findExpenseByUsername(@Param("username") String username);

}
