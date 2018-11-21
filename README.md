# Plants_Vs_Zombies Milestone 2
Authors: Kevin Sun, Tri Nhan, Leo Paz, Adam Labelle

The .zip file is a direct clone from the master project in Github. Extract the contents to a new folder, then create a new Java project and set the source to that folder. Can also use other methods.

Run GameView to start the game. Click either S or P at the bottom to select the plant you want to place. S is for sunflower, P is for peashooter. After clicking one of them, click on any grid tile to plant it at that place. A zombie will spawn at a random row everytime you plant something. You can plant as many plants as you want in one turn, only after clicking end turn will the turn go to the next one. End will end your turn and go to the next turn.


PlayGame was removed because it was printing the board to the console. We incorporated the main method into GameView. PvZGame was removed because it had the same functionality as the Model. It's methods were merged into PvZModel. Location was removed because it had the same functionality as GridCell. It's methods were merged into GridCell. GameBoard was refactored to PvZModel. PvZModel incorporates all the data and methods needed for a model in a MVC structure.


End turn will make all units on the board perform their end of turn functions. A zombie will move one space to the left. A sunflower will produce 1 sunlight every turn, a peashooter will do 1 damage if there is a zombie in it's row. A zombie will do 1 damage if there is a plant in front of it, otherwise it will move 1 space each turn. Currently, plants only have 1 hp, while zombies have 3 hp.


Current win condition is if 5 or more turns have passed and there are no more zombies on the board, the player wins. If a zombie is already at the column 0 and the turn has ended, the player loses.


Next iteration will have an unlimited redo and undo button and will have more plants available. We will be altering the View to contain more buttons for the new plants, as well as the redo and undo buttons. The redo and undo buttons will be done using w


Bugs: Currently the restart and quit buttons do not work. Sunlight does not appear at start of game, must first plant something or click end turn.


UML Class Diagram: Adam Labelle

UML Sequence Diagram: Leo Paz

Test Cases: Adam Labelle and Tri Nhan

Model: Kevin Sun

Controller: Leo Paz

View: Tri Nhan

Events and Event Handlers: Leo Paz

All plants, Zombie: Kevin Sun
