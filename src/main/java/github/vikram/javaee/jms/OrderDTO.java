package github.vikram.javaee.jms;

import java.io.Serializable;
import java.util.Date;

public class OrderDTO implements Serializable {
	 
	  private Long orderId;
	  private Date creationDate;
	  private String customerName;
	  private Float totalAmount ;
	  
	public OrderDTO(long l, Date date, String string, Float totalAmount2) {
		
	}
	
	public Long getOrderId() {
		return orderId;
	}
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public Float getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(Float totalAmount) {
		this.totalAmount = totalAmount;
	}
	 
	    
}