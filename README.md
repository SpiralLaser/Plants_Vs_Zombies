# Plants_Vs_Zombies Milestone 4
Authors: Kevin Sun, Tri Nhan, Leo Paz, Adam Labelle

The .zip file is a direct clone from the master project in Github. Extract the contents to a new folder, then create a new Java project and set the source to that folder. Can also use other methods to import the project.

Run GameView to start the game. Click any of the plants at the bottom to select the plant you want to place. After clicking one of them, click on any grid tile to plant it at that place. A zombie will spawn at a random row everytime you plant something. You can plant as many plants as you want in one turn, only after clicking end turn will the turn go to the next one. End turn will immediately end your turn and go to the next turn. 

The game has real-time elements in it, every 5 seconds the game will progress to the next turn even if the user did not click end turn.

The save buttton will save the current state of the board. Loading will change the board to the last saved state. Infinite saves and loads can be done. Saving will overwrite the last save point, and loading will only load from the most recent save.

End turn will make all units on the board perform their end of turn functions. A zombie will move one space to the left. A sunflower will produce 1 sunlight every turn, a peashooter will do 1 damage if there is a zombie in it's row. A zombie will do 1 damage if there is a plant in front of it, otherwise it will move 1 space each turn. Currently, all plants except wallnut only have 2 hp, while Wallnut has 3 hp, and zombies have 4 hp, 

Undo will undo your last move made and revert the board to the last state. Redo will undo your last undo and revert the board to before you made the undo. If there are no more undo's left (board is back to start of game) then a message will pop up. If there are no more redo's left (board is back to the latest state) then a message will pop up.

Current win condition is if 15 or more turns have passed and there are no more zombies on the board, the player wins. On turn 15, a boss zombie will spawn. The boss zombie moves every 2nd turn and has 10 hp. If a zombie is already at the column 0 and the turn has ended, the player loses.


# Changes from last milestone:
Undo and redo have been properly fixed. A save and load button has been added. The game now incorporates real-time into it: Every 5 seconds the game will progress 1  turn, so zombies will move or attack, and plants will do their actions as well.

We also couldn't get the zombie animation working in time so we left it as a Z text.

Current bugs:
None




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

Undo, Redo, Save, Load, Real-time game: Kevin Sun
