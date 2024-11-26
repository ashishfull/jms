# JMS Point-to-Point and Publisher-Subscriber Example

This project demonstrates a **Publisher-Subscriber messaging pattern** using **Java Message Service (JMS)** with **WebLogic Server**. The example includes a **publisher** that sends messages to a topic and a **subscriber** that consumes messages asynchronously.

## Features
- Publisher and Subscriber implementation using **JMS**.
- Supports **TextMessage** and can be extended for other message types like `ObjectMessage`.
- Uses **try-with-resources** for automatic resource management.
- Demonstrates configuration for **WebLogic Server** with JNDI.

## Technologies Used
- **Java**: 8, 11, or 17
- **JMS (Java Message Service)**: For asynchronous messaging
- **WebLogic Server**: Middleware to manage JMS resources
- **SLF4J + Logback**: For logging

## Getting Started

### Prerequisites
1. **Java Development Kit (JDK)**: Install Java 8, 11, or 17.
2. **Apache Maven**: For dependency management and project building.
3. **WebLogic Server**: Ensure WebLogic is installed and running.

### Setting up WebLogic Server
1. **Create JMS Resources**:
   - Log in to the WebLogic Admin Console (`http://localhost:7001/console`).
   - Create a **JMS Module** (e.g., `JMSModule`).
   - Inside the module, create:
     - **Connection Factory**: `MY_JMS_CF`
     - **Topic**: `MY_JMS_TOPIC`

2. **Verify JNDI Names**:
   - Ensure the Connection Factory (`MY_JMS_CF`) and Topic (`MY_JMS_TOPIC`) are correctly mapped.

### Running the Project

#### 1. **Build the Project**
Use Maven to build the project:
mvn clean install

#### 2. **Run the Publisher**
Execute the Publisher to send a message to the topic:
java -cp target/JMSApp-1.0-SNAPSHOT.jar com.example.jms.JMSTopicPublisher

#### 3. **Run the Subscriber**
Execute the Subscriber to consume messages from the topic:
java -cp target/JMSApp-1.0-SNAPSHOT.jar com.example.jms.JMSTopicSubscriber

## Project Structure
JMSApp
├───src
  ├───main
      └───java
          └───com
              └───ashishrai
                  └───jms
                      │   App.java
                      │
                      ├───point_to_point
                      │       Employee.java
                      │       JMSObjectConsumer.java
                      │       JMSObjectProducer.java
                      │       JMSTextConsumer.java
                      │       JMSTextProducer.java
                      │
                      └───publisher_subscriber
                              JMSTopicPublisher.java
                              JMSTopicSubscriber.java


## Example Outputs

### Publisher Output:
    Message sent to topic: Hello Subscribers!
### Subscriber Output:
    Waiting for messages from topic...
    Received message: Hello Subscribers!
### Object Producer Output:
    Message sent successfully!
### Object Consumer Output:
    Received Employee: Employee[id=1, name=Ashish Rai, phoneNumber=70200xxx95]
### Text Producer Output:
    TextMessage sent: Hello from JMS!
### Text Consumer Output:
    Waiting for messages...
    Received TextMessage: Hello from JMS!

## Version
This version is compact, clean, and easy to copy-paste directly into your project. Let me know if you need any tweaks! 😊



