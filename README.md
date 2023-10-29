# Pokedex Compose

### List + Detail Views
<img src="/readme/List.png" height="500">
<img src="/readme/Detail.png" height="500">

### Tablet (adaptive layouts)
![Tablet View](/readme/Tablet.png)

### Light + Dark Theme
<img src="/readme/LightTheme.png" height="500">

### Sandbox project to showcase:
* Jetpack Compose
* Model VIew ViewModel (MVVM) Architecture
* Compose Navigation Library
* Custom Compose Preview Annotations
* Adaptive Layouts (Phone + Tablet + Foldables)
* Hilt Dependency Injection
* Retrofit for connecting with Pokemon API
* Room Database for local data caching
* Coil for image loading + image caching
* Light + Dark theme
* Coroutines
* Kotlin Flows
* Graceful configuration change handling
* SOLID principles

#### Architecture Details
![Diagram that shows architecture for Pokedex project](/readme/PokedexDiagram.jpg)
* Single Activity sets up NavigationHost
* NavigationHost sets up navigation routes and connects to the respective composable screen
    * ListScreen shows list of all Pokemon. Selecting one opens the Detail view
    * DetailScreen shows a Pokemon's stats/details
    * NavHost determines when to show a single or double-pane view. I.E. Tablets will render the List and Detail views at the same time
* Each screen composable automatically initializes their own ViewModel through dependency injection
* Viewmodel holds a reference to the PokemonRepository (implementation gets injected with Hilt), and is responsible for providing the coroutine scope / launching coroutines
* PokemonRepository abstracts implementation logic
    * Holds references to Local and Remote data sources
    * Determines when to make a network call vs pulling from local cache. Only makes network calls when it has to
    * Always caches data after fetching remotely
    * Exposes Pokemon Flow so that it can be observed from ViewModel
    * Only exposes Pokemon data class
        * Network call Data Transfer Objects (model for data layer) are converted to type Pokemon (model for domain layer)
        * Local database entity objects (model for data layer) are converted to type Pokemon (model for domain layer)
        * In other words, the repository separates the data layer from the domain layer models
* RemoteDataSource uses Retrofit to initialize API
    * Exposes functions that return PokemonDto models
* LocalDataSource uses Room to create database
    * Exposes functions that return PokemonEntity models