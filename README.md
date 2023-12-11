# Portfolio project IDATA1003 - 2023

STUDENT NAME = "Jacob Lein"  
STUDENT ID = "593618"

## Project description


This project is the final evaluation of IDATT 1003 (Programmering 1). The task was to create a simplified system to represent a single train station's departures. The system includes a text-based user interface. The user interface is obligated to include certain choices for a potential operator.  

## Project structure
Within the source code there are different folders representing the classes. The "models" folder contains the modelclasses *TrainDeparture* and *TrainRegister*. *TrainDeparture* represents a single departure, and contains all methods and variables relevant to a single departure. *TrainRegister* is a collection of train departures and contains methods relevant to this collection. The "Utils" folder consists only of the train table's clock.
The *UserInterface* class responds to the operator's choices and inputs, and this is where you find the text-based interface. The main method is found in the *TrainDispatchApp*.
JUnit-test classes can be found in the source folder under the *test* folder.    

## Link to repository

[Repository](https://gitlab.stud.idi.ntnu.no/JacobLein/mappevurdering)

## How to run the project

To start the program the user needs to run the class *TrainDispatchApp*. The program handles wrong inputs, and gives user feedback in the console.

## How to run the tests

You can either run the tests through your own IDE. Or you can compile the program using Maven.

## References

[//]: # (TODO: Include references here, if any. For example, if you have used code from the course book, include a reference to the chapter.
Or if you have used code from a website or other source, include a link to the source.)
