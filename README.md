# SportDec Search Mashup API

This solution aims to return a mashup data from repositories on GitHub using a keyword and looking for their latest tweets on Twitter social media.

### Configuration
Navigate to ```conf``` folder and open the ```application.conf``` file:
Insert your Twitter API keys in the lines bellow:
1) twitter.api.key
2) twitter.api.secret
```
#GitHub Api
github.api.url = "https://api.github.com"

#Twitter API
twitter.api.url = "https://api.twitter.com"
twitter.api.key = "INSERT_HERE_YOUR_TWITTER_CONSUMER_KEY"
twitter.api.secret = "INSERT_HERE_YOUR_TWITTER_CONSUMER_SECRET"
```

### Installation
Run maven package to generate a JAR file into target folder
```
mvn package
```
This maven command will produce the embedded JAR to execute the application.

### Running 
Navigate to the ```/target``` folder:
```
cd PROJECT_PATH/target
```
Execute the application running the following Java command line:
```
java -jar search-mashup-api-0.0.1-SNAPSHOT.jar -k  [KEYWORD] -o [OUTPUT]
```

| Argument | Type | Description | Usage
| ------ | ------ | ------ | -------- |
| -k | ```String``` | Keyword to search projects and tweets | Example: Football |
| -o | ```String``` | Output representational format. | * ```text``` - Results diplayed as Standard text human-readable ```json```- Results displayed in a JSON format | 

### Examples
* Search with output in Text
```
java -jar search-mashup-api-0.0.1-SNAPSHOT.jar -k Football -o text
```
```
----------- SPORTDEC SEARCH MASHUP API -----------
Keyword searched: Football
9 project(s) found:
--------------------------------------------------
Project: nflgame
Description: nflgame
Url: https://github.com/BurntSushi/nflgame
Latest Tweets:		-> 1 tweets were found.
	User: NFL Game Live
	Date: 22/04/2018 23:49:08
	Tweet: RT @NFL: Today in @NFLhistory... the @Broncos traded up in the 2010 @NFLDraft to select @TimTebow 25th overall!

2018 #NFLDraft, April 26-2…

*******************************************
```
* Search with output in Json
```
java -jar search-mashup-api-0.0.1-SNAPSHOT.jar -k Football -o json
```
```
[{"name":"nflgame","description":"An API to retrieve and read NFL Game Center JSON data. It can work with real-time data, which can be used for fantasy football.","url":"https://github.com/BurntSushi/nflgame","owner":"BurntSushi","createDate":"2012-08-29T10:00:58.000Z","latestTweet":[{"createdAt":"2018-04-22T23:49:08.000Z","text":"RT @NFL: Today in @NFLhistory... the @Broncos traded up in the 2010 @NFLDraft to select @TimTebow 25th overall!\n\n2018 #NFLDraft, April 26-2…","user":"NFL Game Live","userUrl":""}]}]
```
##

### Models
#### Repository
| Property               | Type | Description
| ---------------------- | ---- | -----------|
| name | ```string```| Name of GitHub project/repository |
| description | ```string```| Description of GitHub project |
| url | ```string```| URL of GitHub repository |
| owner | ```string```| GitHub login name of the project owner |
| createDate | ```Date``` | DateTime of the creation of the project. JSON date is displayed as yyyy-MM-dd'T'HH:mm:ss'Z' |
| latestTweet | ```Tweet[]``` | ArrayList of Tweet items. |

#### Tweet
| Property               | Type | Description
| ---------------------- | ---- | -----------|
| createdAt | ```Date```| DateTime of the creation of tweet. JSON date is displayed as yyyy-MM-dd'T'HH:mm:ss'Z'  |
| text | ```string```| Tweet text |
| user | ```string```| Login of the user who created the Tweet. |
| userUrl | ```string```| The URL for the Twitter user's profile | 

##

### Tech

SportDec Search Mashup API was written in Java 8 using the Eclipse as editor.

* [Java.com] - Java SE 1.8
* [Eclipse.org] - Oxygen.1a Release (4.7.1a)
* [Maven.apache.org] - Maven (Project Management Tool)

The Unit Tests are available and were running with JUnit.

### Author

* Emerson Gomes <emersongomes@me.com>