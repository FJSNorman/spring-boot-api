to generate jar:
mvn clean package

to run application:
java -jar user-api-0.0.1-SNAPSHOT.jar

Examples of requests:

	Get all users:
		http://localhost:8080/users/all
		
	Get user by ID: (Throws 404 error if user does not exist)
		http://localhost:8080/users/1
		
	Add user (Throws 400 error if a field is empty or missing)
		POST http://localhost:8080/users/add
		{
			"firstName": "Name",
			"lastName": "lastName",
			"dateOfBirth": "2000-01-01"
		}
		
	Update user: (Throws 400 error if a field is empty or missing)
		PUT http://localhost:8080/users/update/3
		{
			"firstName": "Name",
			"lastName": "lastName",
			"dateOfBirth": "2000-01-01"
		}
		
	Delete user: (Throws 404 error if user does not exist)
		DELETE: http://localhost:8080/users/delete/2
