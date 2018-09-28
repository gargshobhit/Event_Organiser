Technologies Used:
Java 1.8
Apache Maven:- 3.3.9
junit:- 4.12
IDE:- IntelliJ 

Requirement to run the EventOrganiser App

1) create a new folder and copy the provided EventOrganiser.zip
2) extract the zip file in the current folder
3) open the comman prompt using commoan 'cmd'
3) go to folder 'EventOrganiser' by running the cd EventOrganiser
4) run the command java -jar EventOrganiser.jar
5) Its a Maven project and can be open in eclipse or IntelliJ

1) activity name must be end with 'Sprint' or 'min'. All Sprint duration defaly to 15 mins.
2) input file must be in classpath under src\main\resources

Below are the description for the main classes and their functionality.

1) EventConstant.java :-  This class is used to provide all the constant and error messages required to run the application.

2) FileLoader.java :- This class check the format of input file and load all the activites.

3) EventServiceImpl.java :- This class first find calculate the Teams/Organisers required to perform the event and then distribute the activites between Teams/Organisers. It also takecare if any 'Break' or common activity like 'Presentation' is required which should be attaanded by all the participants.
