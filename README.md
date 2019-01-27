# Integer Incrementer
parallel-counter-app increments an Integer column in mysql table Number atomically.

## Used Approach 

Assumption:-Count Reads are not as frequents as writes.

Write :- Keep count local(AtomicLong) to each app and update to databse periodically. 
This approach reduces overhead of thread thread synchronization and increases app performance 
reducing load on database at the same time.
It consistently updates counter even if deployed on distributed servers because once update command is fired to mysql 
it updates counter atomically(row level locking).


Read :- Count can be read from Database directly. 


Limitation:-
Some count might be lost in case of app crash.
e.g if we are updating the in memory count to table every 10 seconds and the app crashes , counts of at most 10 seconds will be lost.

Solutions:-
Reduce the update frequency 
Fire each update count request to database instead of local count


## Suggested Approach 1
Use Redis server and use its atomic increment feature to updated the count consistently. More scalable,distributed, consistent and fault tolerant than approach 1;

Faster Simultaneous reads/writes.


## Suggested Approach 2 (for very high concurent increments requests)
[VM1,VM2,VM3..VMn] --------each increment event----->  KAFKA --------->  Flink{accumulate count}-----------------> Sink [Redis/Mysql/Cassandra]  
  

## System Requirements
1. Java 8
2. Maven 3
3. Mysql 

## Instructions to run the app.

1. clone the app from github.
2. Go to extracted app directory.
3. Provide mysql connection url, user password properties in `/src/main/resources/application.properties`
4. Build application using command `mvn clean install`.
5. Run App using command `mvn spring-boot run` 
6. go to apache jmeter bin folder and run below command to run the test
7. To modify the jmx file open it in jmeter GUI and change.
8. Run jmeter test using command line e.g [ D:\work\personal\parallel-counter-app\parallel-counter-app\CounterApi.jmx  -l ApiTestReport1.jtl]
6. Stop app using ctrl+c
7. Run another cycle if needed.

Note 1:- App creates a new table named Number on each startup (drops older one if exists).
Note 2:- make sure app is shut down properly before another run.
Note 3:- I ran the test using 10000 users with ramp up period of 10 seconds, the count in table was 10000 (subject to jmeter finished with 0% error).
