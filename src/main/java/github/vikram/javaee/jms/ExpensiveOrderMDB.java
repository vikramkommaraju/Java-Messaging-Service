package github.vikram.javaee.jms;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.ejb.Startup;
import javax.jms.JMSConnectionFactoryDefinition;
import javax.jms.JMSDestinationDefinition;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

@MessageDriven(mappedName = "jms/javaee7/Queue", activationConfig = {
        @ActivationConfigProperty(propertyName  = "acknowledgeMode",
                                  propertyValue = "Auto-acknowledge"),
        @ActivationConfigProperty(propertyName = "destinationType",
        						  propertyValue = "javax.jms.Topic"),
        @ActivationConfigProperty(propertyName  = "messageSelector",
                                  propertyValue = "orderAmount > 1000")
})
@JMSConnectionFactoryDefinition(name = "jms/javaee7/ConnectionFactory", className = "javax.jms.ConnectionFactory")
@JMSDestinationDefinition(name = "jms/javaee7/Queue", className = "javax.jms.Topic", interfaceName = "javax.jms.Queue", destinationName = "Queue1")
@Startup
public class ExpensiveOrderMDB implements MessageListener {
 
  public void onMessage (Message message) {
    try {
    	System.out.println("Expensive order received!");
        OrderDTO order = message.getBody(OrderDTO.class) ;
        System.out.println("Expensive order received: " + order);
    } catch (JMSException e) {
      e.printStackTrace();
    }
  }
  
    public ExpensiveOrderMDB() {
    	
    }
  
  	@PostConstruct
	private void populateDB() {
		System.out.println("Constructed a new OrderMDB!");
	}
	
	@PreDestroy
	private void clearDB() {
		System.out.println("About to destory OrderMDB");
	}
}
