Problem: Develop a simple program to rank-order Walmart product recommendations based upon customer reviews.
The program should invoke the Walmart Labs Open API to implement the following workflow:
1.Search for products based upon a user-provided search string
2.Use the first item in the search response as input for a product recommendation search
3.Retrieve reviews of the first 10 product recommendations
4.Rank order the recommended products based upon the review sentiments

Solution: Developed an application model that solves the above problem.

How the application model works:
Assumption : 
1.Overall Average Rating Property of the review is considered for recommended product review rating.
2."ipod" is taken as query string.

1.The application is divided into 3 modules:
	client : Establishes connections with Walmart Open API using Jersey client
	clientservicecalls  : Builds url,receives JSON response and returns JSON response to application model as String.
	homeworkapplication : Main Method that calls the model
2.Created test scripts using Junit Framework to test the application model.
    testHomeUserApplicationFunctionality : Tests the rank order mechanism of the application.
	testNullChecks : checks if the result Map<ItemID,OverallAverageRating> is null.

How to run the application model:
1.The pom.xml is configured with building,compiling and packaging the jar files. 
2.We need to first build the pom.xml using maven by passing "mvn clean install" as the goal.
3.This is will generate a Jar file in the target location
4.We can execute this jar file using java - jar WalmartHomeWork.jar

Technologies Used:
Java,Maven,Junit,Github,Jersey client,Eclipse

If time permits How we can enhance this:
1.We can develop a builder pattern mechanism using pojos to parse the Json response retrieved from the Search API. 
2.We can develop User Interface for the application
3.We can develop effective review rating mechanism that uses Semantic review analysis for rankingrecommended product instead of overall average review ranking which was available as property for the review. 
