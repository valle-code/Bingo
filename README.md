# Bingo 🍀
Fully automated console bingo game made in Java. 
# How to install
Paste the following command into your Eclipse workspace through the Terminal:
```
git clone "https://github.com/valle-code/Bingo"
```
Once done import the project into Eclipse following this steps in the main menu:
<pre>File > Import > General > Existing Projects into Workspace > Next > Browse Folder "Bingo" > Finish</pre>
You should be able to execute the game now.
# How to use 
When you execute the game, press "Enter ↵" to continue until the game is finished.
# Features
The game is made using basic Java OOP, implementing the following objects to make it work:
### Cardboard
Cardboard doesn't have duplicate numbers, and each an every time a number from the cardboard equals the one that was randomly selected in the raffle, it's replaced by a "0".

[![cardboard.jpg](https://i.postimg.cc/ZRbVxQ3m/cardboard.jpg)](https://postimg.cc/Nyn6gppC)
### Player
Players are generated randomly, and asigned names such as "Jugador 1". Players are able to buy cardboards to keep playing. Players decide on buying depending on the amount of money that they have and their "gambling addiction", which is a stat randomly given to every player. When a player runs out of money, they get kicked out of the game. 

[![player.jpg](https://i.postimg.cc/63bLLptV/player.jpg)](https://postimg.cc/sQSh3yB1)
### Lobby
The Lobby automatically starts the game when all players are ready. When a line of numbers from one player's cardboard is completed, that player will receive a little money price. When a full cardboard is completed, otherwise known as Bingo!, the games finishes and gives the prize to the winner. 

<img src = "./img/lobby.gif"></img>
# Credit
This game was a team project done with [@la-lo-go](https://github.com/la-lo-go) which received a 10/10 mark 💯. You can see our code contributions in the javadoc. 
