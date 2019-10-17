# Kafka Wrapper app
Small project on Spring and Kafka to parse and produce lots of fake data. 
It's in very experimental state, lots of things are hardocded.

### How it works
The app is requiring a csv file that should contain data to be inserted. 
After it will be parsed, that data, record by record will be pushed to kafka.


### How to run
You will need kafka running on localhost:9092 and topic 'engine events' created. 
Then simply build the project and run main file with parameter (csv)

