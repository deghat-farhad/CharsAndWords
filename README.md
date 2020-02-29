
# CharsAndWords

This application use clean architecture and has three modules:

Data module: to grab web page source from the Internet. here I grab full webpage source from beginning to end to use as the input of Domain layer use cases.

Domain module: this module is independent of all other modules and is written as a Kotlin library. all business logic happens here:

First use case "TenthCharacterUseCase" is about getting 10th char of the webpage content, I treat webpage content as an array of char so the tenth character is which has index number 9.

For the second use case "Every10thCharacterUseCase", technical assignment wanted us to make an array on every 10th characters and that`s achieved by filtering webpage source string characters by index, string arrays started from index 0 but counting started from 1 so first we have to increase indexes by 1 and then every character with an index divisible into 10 should remain and others must be ignored.

3rd use case "WordCounterUseCase" is about counting occurrence of every unique word, so I split the webpage source into it words using "/s+" regex and count occurrence of every unique word using "groupingBy" and "eachCount", then I make a string from those to show in a text view.

these use cases are the core of this application so to be sure about how it works and everything`s working as they should, unit tests are existed for all of them.

App module (Presentation): this module use MVVM design pattern to present all information to the user as soon as user request them.
