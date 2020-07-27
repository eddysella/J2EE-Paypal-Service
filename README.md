This project is composed of three web applications which are all deployed on the same payara server

RESTAPI - The currency conversion REST API service

ThriftServer - The timestamp Thrift server

webapps2020 - The payment service

 All three projects must be deployed for the service to work correctly  

The exported versions of the projects are included in the directory 'zipped'

The uncompressed versions are included in the directory 'normal'

A folder called 'config' is included which contains all configuration files for the payara domain and the JDBC Realm. The folder is a copy of the payara domain directory included for easy replacement at payaraglassfishdomains domainname.



This file contains a description of every source file in the three projects (API, Thrift Service, Payment Service). The directory paths reflect the paths of the projects once they've been opened in netbeans.



This file contains a description of every source file in the three projects (API, Thrift Service, Payment Service). The directory paths reflect the paths of the projects once they've been opened in netbeans.

Project RESTAPI-1.0

Description This project contains the API service accessible at RESTAPI .

Files

Directory Source PackagesYABOY.RESTCurrencyConverter

1) API.java
Has a single function called getConversionRate (accessible at RESTAPIconversionfromcurrencytocurrency) which returns the conversion rate between two currencies. The rate is based on the GBP which has a value of 1. Random values were decided for the other rates.

2) Currency.java
Encapsulates all information about a currency (double value, String name) into a convenient Object. To add or manipulate a currency name or value edit this.

3) JAXRSConfiguration.java
Registers the API onto the baseurlconversion URL path.


Project ThriftServer-1.0

Description This project contains the Thrift service accessible at port 20000.

Files 

Directory Source PackagesYABOY.ThriftServer

1) Handler.java
Holds the implementation of the getTimestamp function. Served when it's requested by the client.

2) Server.java
The Thrift Server.

3) TimestampService.java
Auto-generated code - contains interfaces and implementations for the thrift service.


Project webapps2020

Description The Payment Service

Files

Directory Web Pages

1) error.xhtml
Redirected here if login failed.

2) index.xhtml
Main page for service, not secure.

3) login.xhtml
Login page.

4) userRegister.xhtml
Unsecure page to register new user.

Directory Web PagesAdmin

1) adminHome.xhtml
The main admin page. Displays all user accounts and transactions.

2) adminRegister.xhtml
Web page to register more admins, accesible only by an admin.

Directory Web PagesUser

1) userHome.xhtml
The main user page. Displays all inboundoutbound transactions and payment requests. Provides access to user features through buttons.

2) userPay.xhtml
Web page to make a payment.

3) userPaymentRequest.xhtml
Web page to request a payment.

Directory Web PagesWEB-INF

1) faces-config.xml
Contains all explicit navigation rules and registers enum validator for currency selection in user register form.

2) glassfish-web.xml

3) web.xml
Contains login configuration, security constraints for user groups.

Directory Source PackagesYABOY.API

1) APIAccess.java
Encapsulates the functionality required to access the API into a convenient class so new functionality can be easily added.

Directory Source PackagesYABOY.DAO

Files in this directory are in pairs ( an interface and an implementation). They are stateless ejb's which provide access to the persistence layer. Each pair is directly related to an entity in Source PackagesYABOY.Assignment.Entities, for every entity there is a DAO.

Directory Source PackagesYABOY.DTO

1) User.java
This file combines information about a user into a convenient object for display in the admin page.

Directory Source PackagesYABOY.EJB

1) AdminRegisterService.java
Encapsulates all functionality to register a new admin (Locked to admins only). Accesses the DAO layer.

2) AdminService.java
The Admin Service interface.

3) AdminServiceBean.java
Encapsulates all logic for admin functionality. Accesses the DAO layer.

4) UserRegisterService.java
Encapsulates all functionality to register a new user. Accesses the DAO layer. Accessible by anyone.

5) UserService.java
The User Service interface.

6) UserServiceBean.java
Encapsulates all logic for user functionality. Accesses the DAO layer.

Directory Source PackagesYABOY.Entities

1) Credentials.java
An entity, represents the Credentials Table in the database. Holds user username, password and unique ID.

2) Funds.java
An entity, represents the Funds Table in the database. Holds user funds and currency type, referenced by unique ID.

3) PaymentRequests.java
An entity, represents the PaymentRequests Table in the database. Holds all paymeent requests referenced by unique id and their status (pending, declined, paid).

4) Transactions.java 
An entity, represents the Transactions Table in the database. Holds all transactions referenced by unique ID.

5) UserGroup.java
An entity, represents the UserGroup Table in the database. Holds a users permissions level referenced by unique ID.

Directory Source PackagesYABOY.JSF

All files in this directory are webpage backing beans which access functions from the business beans in Source PackagesYABOY.Assignment.EJB. They never directly access the persistence layer.

Directory Source PackagesYABOY.Thrift

1) ThriftClient.java
This file encapsulates all logic to fetch the timestamp from the thrift service running on port 20000 into a single static function.

2) TimestampService.java
Auto-generated code - contains interfaces and implementations for the thrift service.

Directory Source PackagesYABOY.resources

1) AdminDBInit.java
This is a startup singleton ejb which initializes an admin into the database if it doesn't already exist.

2) Currency.java
Encapsulates a label into a convenient enum to represent a currency. Should be updated if a new currency is added to the API.
