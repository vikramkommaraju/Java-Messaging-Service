package github.vikram.javaee.jms;

import java.util.Date;

import javax.jms.Destination;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.naming.NamingException;

import com.sun.messaging.ConnectionConfiguration;


public class OrderProducer {
	
	public static void main(String[] args) throws NamingException, JMSException {
		 
		System.out.println("Creating new order...");
	    Float totalAmount = Float.valueOf(args[0]);
	    OrderDTO order = new OrderDTO(1234l, new Date(), "Betty Moreau", totalAmount);
	    System.out.println("Sending order: " + order);
	    
	    
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
//	    	Hashtable env = new Hashtable();
//	    	env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.enterprise.naming.impl.SerialInitContextFactory");
//	   
//	    	
//	    	Context jndiContext = new InitialContext(env);
//	    
//	       //Connection Factory
//		   ConnectionFactory connectionFactory = (ConnectionFactory) jndiContext.lookup("jms/javaee7/ConnectionFactory");
//	     
//		  //Destination: Queue
//		    Destination queue = (Destination) jndiContext.lookup("Queue1");

	    try (JMSContext jmsContext = connectionFactory.createContext()) {
	    	System.out.println("Sending order...");
	    	
	    	/*
	    	 * Here we set the property "orderAmount" and assign it the value of totalAmount
	    	 * ExpensiveOrderMDB will use this property to invoke onMessage() 
	    	 */
	    	jmsContext.createProducer().setProperty("orderAmount", totalAmount).send(queue, order);
	        System.out.println("Sent order!");
	    }
	  }

}
