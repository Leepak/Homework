Todo_App
A simple Todo App using the MVVM clean architecture component. 
Feature:
Login
Add Task
Update Task
Delete All Task

Architecture
Model-View-View Model is an architectural approach used to abstract the state and behavior of a view, which allows us to separate the development of the UI from the business logic
The diagram below is the complete architecture diagram with the component that you are going to implement in this task highlighted. Every task will have such a diagram to help you understand where the current component fits into the overall structure of the app, and to see how the components are connected.
  
Because all the components interact, you will encounter references to these components throughout this practical, so here is a short explanation of each.


Entity: In the context of Architecture Components, the entity is an annotated class that describes a database table.
SQLite database: On the device, data is stored in an SQLite database. The Room presentence library creates and maintains this database for us.
DAO: Short for data access object. A mapping of SQL queries to functions. You used to have to define these queries in a helper class. When you use a DAO, your code calls the functions, and the components take care of the rest.
Room database: Database layer on top of an SQLite database that takes care of mundane tasks that you used to handle with a helper class. The Room database uses the DAO to issue queries to the SQLite database based on functions called.
Repository: A class that you create for managing multiple data sources. In addition to a Room database, the Repository could manage remote data sources such as a web server.
ViewModel: Provides data to the UI and acts as a communication center between the Repository and the UI. Hides the backend from the UI. ViewModel instances survive device configuration changes.
LiveData: A data holder class that follows the observe pattern which means that it can be observed. Always holds/caches latest version of data. Notifies its observers when the data has changed. Generally, UI components observe relevant data. LiveData is lifecycle-aware, so it automatically manages stopping and resuming observation based on the state of its observing activity or fragment.

This app is implements:
•	Room
•	ViewModel
•	LiveData
•	Data Binding
•	Dagger
Requirements
•	Android Studio v3.1.3 or higher
•	Compile Sdk Version: 27




