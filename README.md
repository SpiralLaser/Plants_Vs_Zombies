# Plants_Vs_Zombies Milestone 2
Authors: Kevin Sun, Tri Nhan, Leo Paz, Adam Labelle

The .zip file is a direct clone from the master project in Github. Extract the contents to a new folder, then create a new Java project and set the source to that folder. Can also use other methods to import the project.

Run GameView to start the game. Click any of the plants at the bottom to select the plant you want to place. After clicking one of them, click on any grid tile to plant it at that place. A zombie will spawn at a random row everytime you plant something. You can plant as many plants as you want in one turn, only after clicking end turn will the turn go to the next one. End turn will immediately end your turn and go to the next turn.


PlayGame was removed because it was printing the board to the console. We incorporated the main method into GameView. PvZGame was removed because it had the same functionality as the Model. It's methods were merged into PvZModel. Location was removed because it had the same functionality as GridCell. It's methods were merged into GridCell. GameBoard was refactored to PvZModel. PvZModel incorporates all the data and methods needed for a model in a MVC structure.


End turn will make all units on the board perform their end of turn functions. A zombie will move one space to the left. A sunflower will produce 1 sunlight every turn, a peashooter will do 1 damage if there is a zombie in it's row. A zombie will do 1 damage if there is a plant in front of it, otherwise it will move 1 space each turn. Currently, all plants except wallnut only have 2 hp, while Wallnut has 3 hp, and zombies have 4 hp, 

Undo will undo your last move made and revert the board to the last state. Redo will undo your last undo and revert the board to before you made the undo. If there are no more undo's left (board is back to start of game) then a message will pop up. If there are no more redo's left (board is back to the latest state) then a message will pop up.

Current win condition is if 15 or more turns have passed and there are no more zombies on the board, the player wins. On turn 15, a boss zombie will spawn. The boss zombie moves every 2nd turn and has 10 hp. If a zombie is already at the column 0 and the turn has ended, the player loses.

Changes from last milestone:
Graphic improvements on GUI with images of plants and zombies. More plants have been added, current roster consists of Sunflower, Peashooter, Twin Sunflower, Repeater, and Wallnut. An unlimited undo and redo button has been added.

Our stack was not working properly. However we intended for when the user performs an action, the board is pushed onto the undo stack and the redo stack is cleared. When the user does an undo, the undo stack is popped and the board is set to the popped board, and the board is pushed onto the redo stack. When the user does a redo, the redo stack is popped, redo is called, and the board is pushed onto the undo stack.

We also couldn't get the zombie animation working on time so we left it as a Z text.


# Contributions


UML Class Diagram: Adam Labelle

UML Sequence Diagram: Leo Paz

Test Cases: Adam Labelle and Tri Nhan

Model: Kevin Sun

Controller: Leo Paz

View: Tri Nhan

A group member was responsible for a single component of the MVC for every milestone.

Events and Event Handlers: Leo Paz

All plants, All Zombies: Kevin Sun
