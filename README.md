# Algorithm Visualizer

I had wanted to experiment with algorithms, so I made this project. Open the project in IntelliJ (I use IntelliJ, don't know how well it will work in another IDE). It uses the Gradle build setup, so it should be portable.

Add algorithms in the algorithms package and run them. There is an Example which shows adding multiple graphs to a single window and a BFS 2-colorable example for a simple realistic algorithm.

You can step back and forward in time, you define a step in your algorithm by calling `manager.step()`. Your algorithm will halt until the next button is pressed to advance to the next stage.

### Todo:
 [ ] Add more data structures. A list and stack would be useful. Also add directed and weighted graphs. Those should be easy to add.
 
 [ ] Add a message box so the algorithm can print text output on what it's doing without switching back to the IDE consols