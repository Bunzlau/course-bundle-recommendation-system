course-bundle-recommendation-system

Overview:

The Quote Recommendation System is a Spring Boot application that generates price quotes for
resource bundles based on teacher requests. Teachers can specify the amount of educational content
they need for various topics, and the system calculates quotes from multiple providers using
predefined pricing rules.

Design Decisions:

1. Strategy Pattern
   In case in the future new calculation rules will be needed, the Strategy Pattern will help to
   encapsulate the different quote calculation rules:

How to Run

1. Clone the Repository
   bash
   git clone https://github.com/your-repo/quote-recommendation-system.git

   
2. Build the Project
   bash
   ./gradlew clean build

3. Run the Application
   bash
   ./gradlew bootRun

4. Test the Application
   Endpoint: POST /api/quotes
   Example Request:
   json
   {
   "topics": {
   "math": 70,
   "science": 40,
   "history": 30
   }
   }
   Using cURL:
   bash
   curl -X POST -H "Content-Type: application/json" -d '{
   "topics": {
   "math": 70,
   "science": 40,
   "history": 30
   }
   }' http://localhost:8080/api/quotes

Testing
Run all unit tests using:

bash
./gradlew test
