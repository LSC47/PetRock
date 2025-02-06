Steps to build and run program

(0)Open terminal at project folder location. Example: .../petrock_project/

(1)Compile maven project: mvn compile

(2)Package (build) the project: mvn package

(3)RunProgram (With .jar file name that was created within the target folder): java -jar target/petrock_project-1.0-SNAPSHOT.jar

(*)Rebuild Project (if any issues): mvn clean package
