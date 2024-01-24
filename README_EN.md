<div align="right" size="1px">
<a href='https://github.com/vanskarner/CleanExampleKT'>Spanish</a>
</div>
<br>
Simple Android application in Kotlin that follows the best practices of Martin, Robert C., in his book **Clean Architecture: A Craftsman's Guide to Software Structure and Design** (2017).

This simplified example can be considered an extension of the more complete and documented repository, where the best practices behind software architecture are discussed in depth and can be found at [CleanMovie](https://github.com/vanskarner/CleanMovie/wiki).

<p align="center">
  <img src="https://github.com/vanskarner/CleanMovie/assets/39975255/7d7c53a6-7c85-4456-a725-99814d3b1eb5" alt="CleanArchitectureCover_2017" style="display: block; margin: auto; width: 400px;">
</p>

## What's new in this repository?

Not much, the theory is still the same as can be found in [CleanMovie](https://github.com/vanskarner/CleanMovie/wiki). The novelty is that Kotlin, Corrutinas, ViewModel, LiveData and Hilt are used.

## Why has it been separated into modules? Do all projects have to be multi-module?

The separation into modules obeys to comply with the predefined architectural approach, in this case we are following the [Package by Component](https://github.com/vanskarner/CleanMovie/wiki/The-Code-Decoupling#package-by-component) approach. 

Not necessarily all projects must be multi-module, you can literally copy all the classes and interfaces of the user component and put it inside the app module in a package called for example "com.vanskarner.cleanexamplekt.features.user" and update the remaining with minimal effort, however the problem is that you can skip levels 
as explained [here](https://github.com/vanskarner/CleanMovie/wiki/The-Code-Decoupling#the-discipline-is-not-enough).


## The CleanMovie wiki states that data structures should not have methods that convert from one format to another. However, there are extensions here that perform that task. Is there a contradiction?

![image](https://github.com/vanskarner/CleanExampleKT/assets/39975255/160c79c9-2f62-46b3-90bc-b1d061c02a98)


No, not at all. Here it is important to consider the context. Kotlin, with its extensions, offers the advantage of inheritance without its disadvantages. 
Therefore, there is no contradiction, and it is totally valid to choose this way of mapping, just as it would also be valid to create a dedicated class for that task. Both options are valid.

## Why aren't diagrams and graphs of the metrics included?

As I said before, this is a simplified example. If you have read and understood the [CleanMovie](https://github.com/vanskarner/CleanMovie/wiki) wiki, you should have no problem doing it yourself. As for the metrics, I have not found a software that contains all the metrics that JArchitect provides us with.

## Will more examples be included, such as using DataBinding, Compose and Navigation?

Possibly, later on, I will include more branches using additional features or details. Also, you can do it and suggest it [here](https://github.com/vanskarner/CleanExampleKT/discussions/categories/ideas).

## Does it include tests?

| User  | ViewModel |
| --- | --- |
|  ![user](https://github.com/vanskarner/CleanExampleKT/assets/39975255/357da6aa-19c1-4749-bf64-06807a8c92ed) | ![UI_ViewModel](https://github.com/vanskarner/CleanExampleKT/assets/39975255/9ff51fd2-d315-4d7d-8e1b-f6775455015a) |

| UI Android |
| --- |
| ![UI_Android](https://github.com/vanskarner/CleanExampleKT/assets/39975255/e4449e2d-36cd-4c3f-a3dc-d1d108b949ad) |

Of course, every good architecture has tests. The necessary unit and instrumentation tests are found in each component.

## Is there anything else I should know or am missing?

Yes, look at how each class is constructed, what access modifier is used in each class and interface, as well as how the packages are arranged; everything has a purpose.

## Who are you?

The same person who wrote the wiki and code for [CleanMovie](https://github.com/vanskarner/CleanMovie) 😁.

## Discussions

See the discussion section [HERE](https://github.com/vanskarner/CleanExampleKT/discussions).
