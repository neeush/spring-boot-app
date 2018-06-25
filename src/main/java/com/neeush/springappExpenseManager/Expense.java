package com.neeush.springappExpenseManager;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.validator.constraints.Length;

@Entity
public class Expense {
	
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Id
	private Integer expenseId;
	
	public Integer getExpenseId() {
		return expenseId;
	}

	public void setExpenseId(Integer expenseId) {
		this.expenseId = expenseId;
	}

	private Integer expAmount;
	
	private Date expDate;
	
	private String userId;
	
	private String expenseType;
	
	private String expDescription;
	
	@Length(max=1000)
	private String expDocId;
	
	

	

	public Integer getExpAmount() {
		return expAmount;
	}

	public void setExpAmount(Integer expAmount) {
		this.expAmount = expAmount;
	}

	public Date getExpDate() {
		return expDate;
	}

	public void setExpDate(Date expDate) {
		this.expDate = expDate;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getExpenseType() {
		return expenseType;
	}

	public void setExpenseType(String expenseType) {
		this.expenseType = expenseType;
	}

	public String getExpDescription() {
		return expDescription;
	}

	public void setExpDescription(String expDescription) {
		this.expDescription = expDescription;
	}

	

	public Expense() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Expense [expenseId=" + expenseId + ", expAmount=" + expAmount + ", expDate=" + expDate + ", userId="
				+ userId + ", expenseType=" + expenseType + ", expDescription=" + expDescription + "]";
	}

	public Expense(Integer expAmount, Date expDate, String userId, String expenseType, String expDescription) {
		super();
		this.expAmount = expAmount;
		this.expDate = expDate;
		this.userId = userId;
		this.expenseType = expenseType;
		this.expDescription = expDescription;
	}

	public String getExpDocId() {
		return expDocId;
	}

	public void setExpDocId(String expDocId) {
		this.expDocId = expDocId;
	}

	
	

}
