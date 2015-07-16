package github.vikram.javaee.jms;

import javax.jms.Destination;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.sun.messaging.ConnectionConfiguration;

public class OrderConsumer {
	 
	  public static void main(String[] args) throws NamingException, JMSException {
	 
		   System.out.println("Waiting for order...");
		  
		  /*
		   * Method 1: Instantiating the ConnectionFactory and Queue
		   */
		  //Connection Factory
		  com.sun.messaging.ConnectionFactory connectionFactory = new com.sun.messaging.ConnectionFactory();
		  connectionFactory.setProperty(ConnectionConfiguration.imqAddressList, "localhost:7676,broker2:5000,broker3:9999"); 
		  
		  //Destination: Queue
		  Destination  queue = new com.sun.messaging.Queue("Queue1");
		  
		  
		  /*
		   * Method 2: Using JNDI Lookup for ConnectionFactory and Queue
		   */
		  // Gets the JNDI context
		  //Context jndiContext = new InitialContext();
	    
	      // Connection Factory
		  // ConnectionFactory connectionFactory = (ConnectionFactory) jndiContext.lookup("jms/javaee7/ConnectionFactory");
	     
		  //Destination: Queue
		  //Destination queue = (Destination) jndiContext.lookup("Topic1");
	   
	    // Loops to receive the messages
	    try (JMSContext jmsContext = connectionFactory.createContext()) {
	      while (true) {
	        OrderDTO order = jmsContext.createConsumer(queue).receiveBody(OrderDTO.class) ;
	        System.out.println("Order received: " + order);
	      }
	    }
	  }
}