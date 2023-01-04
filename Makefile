runTests:runFrontendDeveloperTests runDataWranglerTests runAlgorithmEngineerTests

run:	compile
	java AirportApplication
	
compile:
	javac AirportApplication.java

runFrontendDeveloperTests:runFrontendTest

runFrontendTest:
	javac -cp .:junit5.jar AirportFrontTest.java
	java -jar junit5.jar -cp . --scan-classpath

runDataWranglerTests: DataWranglerTests.class
	java -jar junit5.jar --class-path=. --include-classname=.* --select-class=DataWranglerTests

DataWranglerTests.class:
	javac -cp .:junit5.jar DataWranglerTests.java

runAlgorithmEngineerTests: AlgorithmEngineerTests.class AirportLoader.class
	java -jar junit5.jar --class-path=. --include-classname=.* --select-class=AlgorithmEngineerTests

AlgorithmEngineerTests.class: AlgorithmEngineerTests.java
	javac -cp .:junit5.jar AlgorithmEngineerTests.java

runBackendDeveloperTest: BackendDeveloperTest.class
	java -jar junit5.jar --class-path=. --include-classname=.* --select-class=BackendDevloperTest
BackendDeveloperTest.class: BackendDeveloperTest.java

clean:
	rm *.class
