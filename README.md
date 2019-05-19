# Rochambeau-Game
Rock–paper–scissors : A player who decides to play rock will beat another player who has chosen scissors ("rock crushes scissors"), but will lose to one who has played paper ("paper covers rock"); a play of paper will lose to a play of scissors ("scissors cuts paper"). If both players choose the same shape, the game is tied and is usually immediately replayed to break the tie.

Welcome to the world of Avengers.
This is a demonstration project which would create a console based game based on the configuration provided into files under location “resources/content”
These are sample files and can be modified to suit the requirement
Input_levels.txt
Stage_0_points.txt

Running Project
#from your favourite IDE or
mvn clean package
java -jar target/game-0.0.1-SNAPSHOT.jar


Game and Rules
1.	You need to create an Avataar for yourself 
(Name in Characters please. If not, you would be forced to choose one!) 
2.	If the Avataar (case insensitive) already exits into the storage, it will resume from last saved state.
3.	There are 3 preloaded stages and hungry enemies awaiting at each stage. You are initially assigned 50 points to fight, those enemies
4.	The game starts at stage 0. You can use “explore” option to see list of your “allowed” moves and “alive” enemies, at the current stage anytime.
5.	Now, when you choose to “Fight”, following things should be kept in mind :
•	At start of each fight, you would be shown a list of moves and the corresponding number to chose for the move
•	When you select a move, your “intelligent” enemy also selects one and points would be calculated based on your moves and the stage you are on. The calculation logic is mentioned in section below.
•	“Enemy” is Intelligent as you won’t be able to predict their behaviour at any stage.
•	As we are in a “closed” world, we would validate your moves and calculate your points and “display” your move score and total score
•	Incase, your total scores reaches “0”,  your Game would be terminated
•	If not , you are ready to fight next enemy Yodha ! (provided, they remain alive).
•	To keep the rules of game simple :
i.	Your enemy score does not increase, if they attack you
ii.	If you both play the same move, that is counted as “0” and benefit is given to you.

6.	When you are busy playing and want to pause it for a while, you can use the "Save" option from the menu.  You can resume, by choosing your Avataar name from the “Build/Create Avataar" option. Also, ensure, you are on same machine, as we live in a "closed" world. 

7.	The stages and the points configuration is placed at “contents/” folder and can be modified easily by just configuring the classes at stages and move.

Point calculation
These are loaded from configuration files and can be modified to change actions
Stage 0 (contents/Stage_0_points.txt)

You/ Enemy->	Kick	Slap	Jump	Hammer	PaintRed
Kick	0	10	-10	-10	10
Slap	-10	0	10	10	-10
Jump	10	-10	0	10	-10
Hammer	10	-10	-10	0	10
PaintRed	-10	10	10	-10	0

¬¬Stage 1 (contents/Stage_1_points.txt)

You/ Enemy->	Fly	Shoot	LaserAttack
Fly	0	-15	15
Shoot	15	0	-15
LaserAttack	-15	15	0

Stage 2 (contents/Stage_2_points.txt)

You/ Enemy->	PowerPunch	Sword	Slam	Gun
PowerPunch	0	20	-20	0
Sword	-20	0	0	20
Slam	20	0	0	
Gun	0	-20	20	0

