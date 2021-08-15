## Run
Download the source code and execute the following command inside the **root folder** 'se-interview'

## Command
0. Prerequisites Java11 or higher is needed check the version currently installed with ````java --version```
1. Execute this ```./mvn clean install &&  java -jar target/sudokuplus-1.0-SNAPSHOT.jar <BOARD_SOURCE_FILE>```
* **[Mandatory Field] <BOARD_SOURCE_FILE>** is the file containing the definition of the board as a CSV file were every 
line on the file corresponds with the row of the board only numbers allowed, must be inside the root folder of the project.
* ### e.g.
```./mvn clean install && java -jar target/sudokuplus-1.0-SNAPSHOT.jar assignment/samples/inputSample_9x4.txt```

## Output
The output will log a message with the result
