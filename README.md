**Author:**  Aravind Kandavel

**Project Name:**  ChatService

**Project Type:**  REST API using Java Spring Framework


**Namespace :**     java.com.example.chatservice
**Objective:**     This project is a REST API for a Chat client.   

   The Chat Service will following 3 methods.

 
  1. HTTP POST method with no parameters to create a new room chat service. The method's body contains JSON string with Name and description of the chat room
     
  2. HTTP POST method with Path variable - Room ID to send Message to chat room. This method expected to invoked with a JSON body with who is the chat sender and content of the message
     
  3. HTTP GET method with path variable - Room ID to get recent messages from the chat room. There is no body for this message.
     

  This API service has been built with MVC pattern

   **Model**
          
           room.java - room data
           
           message.java - message data
           
           createRoomRequest.java - to construct the JSON body for the createRoom method
           
           sendMessageRequest.java - to construct the JSON body for the send message method
           
           resonse.java - A generic response for all the mehtods. This has reference to Room or Sent Message or List of messages or exception based on method called.
           
           exception.java - a generic exception object contains the exception message and error code
           
 **Business Service**
 
          chatService.java
          
  **Data Repot object**
  
          chatStore.java

  **Controller**
  
            chatServiceController.java
     
 **Entry point**
 
            chatserviceApplication.java

            

**Installation Guide:**

       1. This has been created in the visual studio code IDE. It utilizes various extensions for Java, Spring Boot Extension pack, Spring Boot Dashboard, Spring Boot Tools, and Spring Initializer.
       
       2. When running a Spring Boot application from Visual Studio Code, it uses an embedded web server (Tomcat web server)
       
       3. The application is by default on localhost and port 8080.
       
       4. When required to be deployed into a remote server or a cloud server, the same code can be built to create JAR file and can be deployed into the remote server's web server.
       
       
  **How to run and test the application**
  
       1. From Visual studio code, Run menu prompt, the applicaiton can be started. 
       
       2. The application will be running in the embedded web server and listening to the port 8080
       
       3. This service can be tested from external tool POSTMAN.  The postman collection has pre-configured 3 items for each method.
       
      
       
