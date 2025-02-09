package com.rohan.expense_tracker.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "expense_track")
@Data
public class Expense {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "expense_id")
	private Integer expenseId;
	
	@Column(name = "expense_name")
	private String expenseName;
	
	@Column(name = "expense_price")
	private String expensePrice;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

}
