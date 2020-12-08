# ReimburseMe! : Employee Reimbursment System (ERS)
## Revature Project #1

## Summary
* The Expense Reimbursement System (ERS) will manage the process of reimbursing employees for expenses incurred while on company time. 
* All employees in the company can login and submit requests for reimbursement and view their past tickets and pending requests. 
* Finance managers can log in and view all reimbursement requests and past history for all employees in the company. 
* Finance managers are authorized to approve and deny requests for expense reimbursement.
* Users can upload a document or image of their receipt when submitting reimbursements which can stored in the database and reviewed by a financial manager.

#### Employee User Stories 
- An Employee can login
- An Employee can view the Employee Homepage
- An Employee can logout
- An Employee can submit a reimbursement request
- An Employee can upload an image of his/her receipt as part of the reimbursement request
- An Employee can view their pending reimbursement requests
- An Employee can view their resolved reimbursement requests
- An Employee can view their information
- An Employee can update their information

#### Manager User Stories
- A Manager can login
- A Manager can view the Manager Homepage
- A Manager can logout
- A Manager can approve/deny pending reimbursement requests
- A Manager can view all pending requests from all employees
- A Manager can view images of the receipts from reimbursement requests
- A Manager can view all resolved requests from all employees and see which manager resolved it
- A Manager can view all Employees
- A Manager can view reimbursement requests from a single Employee 

## Technical Requirements

* The back-end system uses **Hibernate** to connect to an **AWS RDS Postgres database**. 
* The application is deployed onto a Tomcat Server. 
* The middle tier uses Servlet technology for dynamic Web application development. 
* The front-end view uses HTML/CSS/JavaScript to make an application that can call server-side components.
* The API follows REST by making HTTP requests between client and server using JSON and AJAX.
* Passwords are encrypted in Java using BCrypt and securely stored in the database. 
* The middle tier follows a front controller, layered architecture to achieve efficient Agile development.
* The application has reasonable test coverage of the service layer thanks to JUnit and Mockito, and implements log4j for appropriate logging. 

**Reimbursement Statuses Diagram** 
![](./imgs/state-chart.jpg)

**Reimbursement Types**

Employees must select the type of reimbursement as: LODGING, TRAVEL, FOOD, or OTHER.

**Logical Model**
![](./imgs/logical.jpg)

**Physical Model**
![](./imgs/physical.jpg)

**Use Case Diagram**
![](./imgs/use-case.jpg)

**Activity Diagram**
![](./imgs/activity.jpg)
