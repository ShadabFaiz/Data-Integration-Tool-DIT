# Data-Integration-Tool-DIT
An College project for  MultiQueryConverter.  
At the current stage,it only supports MongoDB-to-MySQL and vice-versa.However since it has not been implemented yet,so the result will be only the query you have entered.   
# Prerequisite:  
    1.WebServer like Tomcat/Weblogic/Glassfish.
    2.JDK (no brain required for this.).
    3. MySql v5.5 or above.
    4.A Web Broser.
    
# Technologies Use:  
  **FRONT-END:** HTML, CSS.  
  **BACK-END:** JSP, Tomcat v8.5, JDK v1.8.0_121.
    
# HOW TO RUN?  
 ## Installation
    1. Install JDK 1.8 or above (provided with the cd).
    2. Install Tomcat Server v8.3 or above (provided with the cd).
    3. Install MySql v5.5 or above (provided with the cd).
## Running
    4. Copy the directory “DIT” to the “webapp” directory of Tomcat Server.The default location is C:\Program Files\Tomcat\webapps\.Make    sure there is no other directory with the name “DIT”.
    5. Open a web browser.
    6. In the address bar, type: localhost:port-no/DIT
     Default port no: 8080
  
 
# Authentication information for login.  
  **Username:** temp.  
  **Password:** temp.  
   
# If you think you can make it better,then do it.  
# Where to see it live on the net?  
 [ Here ](http://testing-env.cloud.hostnet.nl/DIT/) Link is not working currently since the account was trail period only.  
            New link will be updated soon.  
# Queries currently supported.  
    1.show databasses
    2.drop database_Name;
    3.use database_Name;
    4.create databases database_Name;
    5.show tables;
    6.drop table_Name;
    7.select * from table_Name;
    8.select * from table_Name where col1>2 and col<3; (only >, <, =, >=, <= conditions are supported.)
    9.select col1,col2,col3 from table_Name;
    10.Combination of 8 & 9.
  
# ver 0.2  
    1. Earlier when the Homepage would be refreshed,every time the query was send to process and new 'RecentFile'  
       were created.This could have caused in creation of high number of 'Recentfile' with same query.This is fixed now.  
# ver 0.3
    1. Earlier when user has logged out,even then with back button on web browser,anyone can uses the application  
       without login.This issue has been solved
