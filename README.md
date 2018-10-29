# Plants_Vs_Zombies Iteration 1

Authors: Kevin Sun, Tri Nhan, Leo Paz, Adam Labelle

Run PlayGame to start the game.
Any words in ' ' are the exact spelling of what you must type to proceed with the program (e.g type in 'quit' without the '' to quit the program)
Typing 'place' will allow you to specify a plant to plant, either a sunflower or a peashooter. It costs 4 sunlight to plant something. You can then specify a y,x coordinate where you want to plant it. 
Restart will start a new game. End will end your turn and go to the next turn. Quit will exit the program.

A zombie will spawn everytime you plant something. Instead of planting on your turn, you can choose to end your turn immediately or if you do not have enough sunlight to plant something.
A sunflower will produce 1 sunlight every turn, a peashooter will do 1 damage if there is a zombie in it's row.
A zombie will do 1 damage if there is a plant in front of it, otherwise it will move 1 space each turn.
Currently, plants only have 1 hp, while zombies have 3 hp.

Current win condition is if 5 or more turns have passed and there are no more zombies on the board, the player wins. If a zombie is already at the column 0 and the turn has ended, the player loses.
