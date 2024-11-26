
package com.ashishrai.jms.point_to_point;

import java.util.Properties;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import javax.naming.Context;
import javax.naming.InitialContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JMSObjectConsumer {

	private static final Logger LOGGER = LoggerFactory.getLogger(JMSObjectConsumer.class);

	public static void main(String[] args) {

		try {
			// Set up JNDI properties
			Properties props = new Properties();
			props.put(Context.INITIAL_CONTEXT_FACTORY, "weblogic.jndi.WLInitialContextFactory");
			props.put(Context.PROVIDER_URL, "t3://localhost:7001");
			// Get JNDI context
			Context context = new InitialContext(props);
			// Lookup connection factory and queue
			ConnectionFactory factory = (ConnectionFactory) context.lookup("MY_JMS_CF");
			Destination queue = (Destination) context.lookup("MY_JMS_QUEUE");
			// Create connection, session, and consumer
			try (Connection connection = factory.createConnection();
			    Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			    MessageConsumer consumer = session.createConsumer(queue)) {
				// Start connection and receive message
				connection.start();
				Message message = consumer.receive();
				if (message instanceof ObjectMessage) {
					ObjectMessage objectMessage = (ObjectMessage) message;
					Employee employee = (Employee) objectMessage.getObject();
					LOGGER.info("Received Employee: " + employee);
				} else {
					LOGGER.info("Received non-ObjectMessage");
				}
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
	}
}
