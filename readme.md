# News Feed project Read Me
This project consumes the daily motion API to retrieve news from the news channel.

Here's a detailed overview of it


## Modularization
The project use modularization to enforce separation concerns and the principle of single responsibility. There are other advantages like reduce compilation time by compiling only the modules that have been modified since the last build. This strategy helps to improve build times and reduce cost by minutes in the CI/CD.

There are two types of modules. We have the Android library modules, which contain Android framework implementations like Fragments or ViewModel. Each feature has its module, and it can be called using the navigation component URI feature.

Some Android modules may not contain features, for example, the Repository Module, which is an Android Module because it can implement data sources from the Android Framework, just if we want to implement a cache using the Android Library Room. 

We also have the Kotlin only modules. These modules don't need to know anything about Android because it is not the module job. Their job is to provide a contract, like the public modules, provide data classes or network calls. This strategy enforces the single responsibility principle, separations of concerns, and the Kotlin modules compile faster since they don't use the Android Gradle Plugin.

## Module List

### App
This module is the Android App entry point, the app configuration with all the Koin Modules. This module contains the MainActivity and the navigation graph that is used all along with the App.


## News Feed
This feature module contains the list that shows all the videos and the KoinModule, which should be implemented by the App.

## Player
This module contains the detail screen that is shown after the user clicks on one of the videos of the list. Like the News Feed module, this module also includes the KoinModule that should be implemented by the App.

## Repository

The repository module contains the Repository, which is implemented by the App and his job it's to call the respective data sources.

## DataSource

The Datasource module provides a contract that the data source implementation should follow. We only had a network data source. If we ever want to implement a cache, we can implement this contract, and the Repository should be able to call it when we pass it as a parameter. 

## NetworkDataSource

This Datasource implementation is in charge of creating the request to the API. Inside this module, we can found two Mappers that are in charge of converting from network models to domain models, which are the ones used in the entire App. In this module, you can also find how we can use the DataSource module to have different approaches with the implementation details. 


## Model

This module contains all the data classes used in the domain and presentation layers of the App.

## ApiModel

This module contains all the data classes retrieved from the server before they are converted to domain Models. 

## Shared

This module contains logic that can be shared across modules, for example, Extension functions. 

## Di

This module function is to handle the dependency injection of the App.


### Module Organization

Some modules contain a public and a fake module. The public module creates a contract that can be implemented by the production code and the fake module. The fake module can be used in testing and to provide fake data.



