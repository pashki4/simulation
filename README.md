# Simulation
The essence of the project is to simulate a 2D world that is populated by herbivores and predators.  Herbivores feed on grass. Predators have an attack power that allows them to hunt herbivores.
***
![](src/main/resources/WorldMap.png) 

### Features
* [Breadth First Search](https://en.wikipedia.org/wiki/Breadth-first_search) algorithm finds a way bypassing obstacles (🌳 🪨)
* 🦊 - predator moves towards and attack near herbivores. When hp = 0 herbivore disappears from the map. 
* 🐔 - herbivore moves towards 🌻 and eats nearest.
* `WorldMapConsoleRenderer` displays the current state and turn count 