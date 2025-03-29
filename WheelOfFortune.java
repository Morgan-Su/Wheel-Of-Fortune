/*
2024-05-30
*/

import java.util.Scanner; // This imports the scanner used for user input
import java.util.Random; // imports random number generator for wheel spin and AI decisions
import java.io.*; // File reader for questions saved in file
import java.io.PrintWriter; // Imports read to file for storing user scores into txt file
	public class SuMorganWheelOfFortune {
		public static void main(String[]args) throws FileNotFoundException {
			Scanner ms=new Scanner(System.in);
			Random random = new Random();			
			File myFile=new File("leaderboard.txt"); // Creates file named leaderboard ( txt for scores )
			
			PrintWriter filePW = new PrintWriter(myFile); // Creates new printwriter object for writing to file
			
			int roundCounter=0; // tracks how many rounds out of 3 have been played
			int totalUserMoney=0; // total money user made even after rounds are finished
			int totalOppMoney=0; // total money opponent has made after rounds to compare
			
			String[]answerKey=new String[40]; // creates string array to store 40 elements
			
			File file=new File("answers.txt"); // creates file named answers ( With all 40 questions )
			Scanner scan=new Scanner(file); // Creates scanner for file
			
			for(int m=0; m<40; m++) {
				answerKey[m]=scan.nextLine(); // for every question in the file, scan and gets put into different array index
			}
			
			
			System.out.println("Welcome to Wheel of Fortune!" );
			System.out.print("What is your name?: ");
			String name=ms.nextLine(); // User is allowed to enter name
			
			System.out.println(name+" would you like to know the rules? (y/n)");
			String ques="";
				while(!ques.equals("y")||!ques.equals("y")){
				ques=ms.nextLine();
				
					if(ques.equals("y")) { // If user replies with yes, print out the rules as dictated
						System.out.println("- You will be playing against an AI contestant");
						System.out.println("-Each of you will spin a wheel per turn and land on a cash prize");
						System.out.println("- Now, you will each guess a consonant in the sentence");
						System.out.println("Every consonant, sentence or vowel must be lowercase");
						System.out.println("- For every letter you get correct, you recieve the cash prize and Winner Takes All!");
						System.out.println("");
						System.out.println("-----------------Extra Information------------------");
						System.out.println("You can buy a vowel at the end of each consonant with your own money");
						System.out.println("Press !, if you know the sentence ( Use at start of the turn )");
					}	
				
					else if(ques.equals("n")) { // If user enters "n", do not print rules and proceed with game
						System.out.println("Lets get into it then!");
						break;
					}
					else {
						System.out.println("Invalid Input, Please Try Again!"); // If not either y or n, repeat until input valid
					}
					
				}
			
				System.out.println("");
				
				System.out.println("Please enter anything to indicate you are ready to spin the wheel!");
				ms.nextLine();
			
				System.out.println("");
				System.out.println("");
	
				
				
				
			for(int i=0; i<3; i++) { // 3 Rounds, 3 times
			
				int randomQuestion=random.nextInt(39); // Generates random number from 0-40 in order to select question for round
				
				
				int randomNumber=0; // Determines cash slot the spin lands on each round for the user
				int cashPrize=0; // Cash prize is how much the user would receive per spin
				int wPR=0; // How many letters ( user selected ) is in the sentence
				int totalCash=0; // Cash per round ( reset )
				int consCounter=0; // How times user had inputed a consonant for counter
				int winCounter=0; // How many letters out of the sentence has been guessed
				int quesCounter=0; //Every space
				int vowelCounter=0; // Number of times user has tried to guess a vowel
				int vPR=0; // Number of specific letters ( User guess ) in sentence 
				int length=2; // Initializes and declares variable length
				int used=0;
				int vowelList=0;
				int vowLen=0;
				int aiCons=0;
				int aPR=0; // Number of specific consonants ( AI guess ) in sentence
				int aiTotalCash=0; // Cash Ai makes per round
				int aiPR=0; // Number of specific letters ( AI guess ) in sentence
				int vowsFill=0; // Determines whether all vowels have been used ( Counter )
				int consFill=0; // Determines whether all consonants have been used ( Counter )

				String winner=""; // Determines winner of each round
				String vowelTruth="no"; // Declares variable to use for input validation
				String passable="";			
				String vowel="";
				String wasted="1";
				String verified="1";
				String moneyCount=""; // Number of words inside of the sentence
				String confirm=""; // User input to decide consonant or sentence guess
				String question=answerKey[randomQuestion]; // determines the question
				String realQuestion="";	// determines the sentence without split markers / with spaces
				String[] ind = question.split("\\|"); // stores each of the words of sentence into array indexes
				
				char guess='a'; // initialize and declare AI guess
				char[] consArray=new char[21]; // Holds all user and AI consonant guesses and ( below are all possible consonants )
				char[] everyCons=new char[] {'q','w','r','t','y','p','s','d','f','g','h','j','k','l','z','x','c','v','b','n','m'};
				char[] haoyangArray=new char[5]; // Holds all user and AI vowel guesses
				char[] everyVow=new char[] {'a','i','e','o','u'}; // Holds all possible vowels to use
			
				System.out.println("");
				System.out.println("The Key is "+question); // Prints the answer
				System.out.println("");
				
				String userAnswer=""; // Declares and initializes user Answer
				
				for(int t=0; t<(ind.length); t++) {
					realQuestion=realQuestion+ind[t];
					moneyCount=moneyCount+ind[t];       // Initializes variables realQuestion and moneyCount
					realQuestion=realQuestion+" ";      // Each time there is a new word in sentence
														// Each time there is new word or sentence, becomes part of realQuestion
				}
				realQuestion=realQuestion.trim();       // Trim realQuestion of the space at the end
				
				roundCounter=roundCounter+1;            // New round so +1
				System.out.println("Good luck, you have entered into round "+roundCounter);
				System.out.println("");
				
				int boardNum=ind[0].length()+ind[1].length()+1;	 // Determines length of sentence without spaces			
				
				int quesLen=question.length();                   // Determines total length of the question
				
				char[] quesArray=new char[quesLen]; // Puts every letter of question into an array for processing
				char[] visArray=new char[quesLen];  // How the actual board would look like with empty spaces / boxes
				
				for(int q=0; q<question.length(); q++) {
					quesArray[q]=question.charAt(q); // for each letter, gets put into next index of array
				}
				
				for(int w=0; w<question.length(); w++) {
					if (quesArray[w]==('|')){       // Same thing as quesArray, but each letter becomes _, and | becomes a space
						visArray[w]=(' ');
					}
					else {
						visArray[w]=('_');
					}
				}
				
				for(int r=0; r<quesLen; r++) {
					if(quesArray[r]!=('|')) {
						quesCounter=quesCounter+1;  // Loops through entire sentence and counts every space
					}
				}
												
				userAnswer=""; // declares userAnswer
				
				while(!question.equals(userAnswer)) { // while userAnswer is not the sentence, don't end the round
					
					for(int k=0; k<haoyangArray.length; k++) { 
						for(int l=0; l<everyVow.length; l++) { // At the start of each round, count whether max number of vowels have been inputed
							if(haoyangArray[k]==everyVow[l]) {
								vowsFill=vowsFill+1;
							}
						}
					}
					
					for(int z=0; z<consArray.length; z++) {
						for(int x=0; x<everyCons.length; x++) { // At the start of each round, count whether every consonant has already been used
							if(consArray[z]==everyCons[x]) {
								consFill=consFill+1;
							}
						}
					}
					
					randomNumber=random.nextInt(71)+1; // Generates random number from 1-71 for cash wheel spin
					cashPrize=wheelSpin(randomNumber); // Calls method wheelSpin
						
					
					System.out.println("");
					System.out.println("Please enter anything to continue: "); // Asks for user input to continue
					ms.nextLine();
					System.out.println("");
					
					System.out.println(" ╔═══════════════════════════════════════╗"); // The following section prints the board
					System.out.println("|                 question:"+roundCounter+"              |"); // Prints question number user is competing on
					System.out.print("              ");
					
					for(int p=0; p<=boardNum; p++) {      // Prints first letter of the sentence with the help of boardNum
						System.out.print(visArray[p]);    // Determines what to actually print with same letters in visibleArray format                       
						System.out.print(" ");
					}
					System.out.print("        ");
					
					System.out.println("");

					System.out.print("               ");
					for(int p=boardNum+1; p<question.length(); p++) {   // Prints the remainder of the letters from sentence
						System.out.print(visArray[p]);
						System.out.print(" ");
					}
					System.out.print("         ");
					
					System.out.println("");
					
					System.out.println("|                                         |");
					System.out.println("|                                         |");
					System.out.println(" ╚═══════════════════════════════════════╝");
					
					System.out.println("Please guess a consonant or press ! to guess the sentence :"); // Asks user to enter a consonant or to guess the sentence

						wasted="1";
						verified="1";
						length=2;
					
					while(verified.equals("1")||wasted.equals("1")||length==2) {	
						
						// While conditions for successful user input haven't been met, stay in loop
						
						wasted="0";
						verified="1";
						length=2;
						
						// If the input is ! to guess sentence,	break from input validation
						
						confirm=ms.nextLine();
						
						if(confirm.equals("!")) {
							break;
						}
						
						// Check if it is a consonant that has already been entered by comparing it in a loop of all previous entries
						
						char reader='a';
						
						try { // Try catch for any empty inputs
								reader=confirm.charAt(0);
								for(int o=0; o<consArray.length; o++) {
									if(reader==consArray[o]) {
									wasted="1";
									}
								}
						} catch(Exception e) {
						}
										
						// Check if consonant is even valid in the first place ( Not vowel or special character )
						
						for(int u=0; u<everyCons.length; u++) {
							if(reader==everyCons[u]) {
								verified=("0");

							}
							
							// Confirms that it is only one letter and not an entire sentence
							
						}
						if(confirm.length()==1) {
							length=1;
						}
						
						// If any of the conditions haven't been met, output invalid input
						
						if(!verified.equals("0")||!wasted.equals("0")||confirm.length()!=1) {
							System.out.println("Invalid input, make sure you haven't entered this letter before + is consonant");
						}
									
					}
					
					if(confirm.equals("!")) { // If user wants to guess, prompt them for the sentence
						System.out.println("Well, Guess the sentence right now! ");
						userAnswer=ms.nextLine();
						
						if(userAnswer.equals(realQuestion)) { // If user answer is equal to the sentence
							System.out.println("Not bad, you got it right! ");
							wPR=moneyCount.length()-winCounter; // Count how much the remaining letters are worth all together
							quesCounter=winCounter; 
							for(int y=0; y<visArray.length; y++) {
								visArray[y]=quesArray[y];
								if(quesArray[y]=='|') { // Reveal every letter in the sentence by assigning visible array the correct letter
									visArray[y]=' ';
								}
							}
							
							winner="player";         // Let the winner be the player
							question=(userAnswer);   // Let he answer be the question etc... vice versa
							
						}
						else {
							System.out.println("Your turn has been skipped! Congratulations for failing!");
						}                           // If user hadn't managed to guess, skip their turn for this round
						
						
					}
					
					
					else {
						consArray[consCounter]=confirm.charAt(0); // Enter the user guess for consonant into the consArray
						for(int e=0; e<quesLen; e++) {
							if(consArray[consCounter]==quesArray[e]) {   // Loop through all letters in question and compare them to entered ones
								visArray[e]=consArray[consCounter];
								winCounter=winCounter+1;                // If found, add to the permanent counter
								wPR=wPR+1;                              // Add to temporary counter to track how many letters this round
							}
							else {
							
							}
						
						}
						
						consCounter=consCounter+1; // Enter a new section for the consonant array
						
					}
					
					
					
					System.out.println("");
										
					if(quesCounter==winCounter) {  // If all letters have been found by player, player becomes the winner
						winner="player";
						System.out.println("You have won round!"); // Print that user has won the round
						userAnswer=question;                       // meet the conditions to end the round
					}
					
					System.out.println(" ╔═══════════════════════════════════════╗"); // Print board state after a guess has been made
					System.out.println("|                 question:"+roundCounter+"              |");
					System.out.print("              ");
					
					for(int p=0; p<=boardNum; p++) {
						System.out.print(visArray[p]);
						System.out.print(" ");
					}
					System.out.print("        ");
					
					System.out.println("");

					System.out.print("               ");
					for(int p=boardNum+1; p<question.length(); p++) {
						System.out.print(visArray[p]);
						System.out.print(" ");
					}
					System.out.print("         ");
					
					System.out.println("");
					
					System.out.println("|                                         |");
					System.out.println("|                                         |");
					System.out.println(" ╚═══════════════════════════════════════╝");
	
					totalCash=cashPrize*wPR+totalCash; // Calculate money user has made this round ( Number of letters*Cash spin on wheel )
					System.out.println("");
					System.out.println("Your total winnings are currently: "+totalCash); // Output total winnings
					
					wPR=0; // Reset words per round		
					
					if(!confirm.equals("!")&&quesCounter!=winCounter&&vowsFill!=5) { // If you haven't tried to guess the sentence, 5 vowels haven't been used and no-one has won yet, you can guess a vowel
						System.out.println("Would you also like to buy a vowel? (y/n)");
						
						String vowelChoice="";
						
						while(!vowelChoice.equals("y")&&!vowelChoice.equals("n")) { // Yes or no, or else you must re enter your decision
							vowelChoice=ms.nextLine();
							if(!vowelChoice.equals("y")&&!vowelChoice.equals("n")) {
								System.out.println("Invalid input, please try again!");
							}
						}
						

						if(vowelChoice.equals("y")) {
							
							System.out.println("Go ahead and enter the vowel of your choice!");
							
							passable="false";
							
							while(passable.equals("false")) { // While conditions for a good variable haven't been met:
								vowel=ms.nextLine(); // Loop the prompting process for input
								
								char vowelChar='a'; // initialize and declare character that holds user vowel
								
								try { // try catch any missing inputs
										vowelChar=vowel.charAt(0);
								} catch(Exception e) {
									
								}
										
								used=0;
								vowelList=1;
								vowLen=0;
								
								// If vowel length is not one, set vowelLen to false ( essentially )
								
								if(vowel.length()!=1) {
									vowLen=1;
								}
								
								// If vowelChar is found to have been already used, set used to invalid
															
								for(int a=0; a<haoyangArray.length; a++) {
									if(vowelChar==haoyangArray[a]) {
										used=1;
									}
								}
									
								// If it is not part of possible vowels, set to invalid
								
								for(int s=0; s<everyVow.length; s++) {
									if(vowelChar==everyVow[s]) {
									vowelList=0;
									}	
								}
								
								// If any of conditions are not met: print invalid, if all are met: passable becomes valid
								
								if(used==1||vowelList==1||vowLen==1) {
									System.out.println("Invalid input, make sure you haven't entered this letter before + is vowel");
									passable="false";
								}
								else {
									passable="true";
								}
					
							}
														
							haoyangArray[vowelCounter]=vowel.charAt(0); // Register the vowel used into the vowel Array
							
							for(int e=0; e<quesLen; e++) {
								if(haoyangArray[vowelCounter]==quesArray[e]) {
									visArray[e]=haoyangArray[vowelCounter];  // If vowel is part of the sentence let it become visible
									winCounter=winCounter+1;                 // Add 1 to the counter of total letters you and AI have guessed
									vPR=vPR+1;                               // Add 1 to temporary counter of # of guessed vowels
							
							}
						}
							
							if(quesCounter==winCounter) {                   // If finding vowel results in all letters being found, set winner to be player
								winner="player";
								System.out.println("You have won round!"); // Print you have won the round
								userAnswer=question; // Meet conditions to end round
							}
							
							vowelCounter=vowelCounter+1; // Else if not won, go to next space in vowels used array for next time
							
							System.out.println(" ╔═══════════════════════════════════════╗"); // Print out the results of the vowel incident
							System.out.println("|                 question:"+roundCounter+"              |");
							System.out.print("              ");
							
							for(int p=0; p<=boardNum; p++) {
								System.out.print(visArray[p]);
								System.out.print(" ");
							}
							System.out.print("        ");
							
							System.out.println("");

							System.out.print("               ");
							for(int p=boardNum+1; p<question.length(); p++) {
								System.out.print(visArray[p]);
								System.out.print(" ");
							}
							System.out.print("         ");
							
							System.out.println("");
							
							System.out.println("|                                         |");
							System.out.println("|                                         |");
							System.out.println(" ╚═══════════════════════════════════════╝");
							
							System.out.println("");
							totalCash=totalCash-(cashPrize*vPR); // If you tried to guess a vowel, print current amount you have now after subtraction
							System.out.println("Your total winnings are currently: "+totalCash);

							vPR=0;
							
						}
						else if(vowelChoice.equals("n")) {
							// If you don't want to guess, just skip everything
						}
					}
					
					System.out.println("");
					
					if(quesCounter!=winCounter) {
							System.out.println("Please enter anything to move to next turn! ");
					}       // If you haven't won yet, press anything to move to next turn
					else {
						System.out.println("Please enter anything to continue! ");
							// If you won, press anything to continue to next round
					}
					ms.nextLine();
					
					
					// AI Turn begins here
					
					if(!winner.equals("player")) { // If you haven't won yet, move to AI turn
				
						int aiCash=random.nextInt(71)+1; // Money amount AI has rolled
						int aiRollPR=0;
						int randomConSen=random.nextInt(100); // AI determines if it should guess a consonant or sentence
						int randomSen=random.nextInt(100);// Determines if sentence guess was successful or not
						int aiVow=random.nextInt(100); // Should the AI purchase a vowel?
						int randomVow=random.nextInt(4); // Which vowel should be picked?
					
						if(aiCash>=1 && aiCash<=12) { // All the odds from 250 - 10000
							aiRollPR=250;
						}
						else if(aiCash>=13 && aiCash<=24) {
							aiRollPR=500;
						}
						else if(aiCash>= 25 && aiCash<=36) {
							aiRollPR=750;
						}
						else if(aiCash>=37 && aiCash<=48) {
							aiRollPR=1000;
						}
						else if(aiCash>=49 && aiCash<=60) {
							aiRollPR=2000;
						}
						else if(aiCash>=61 && aiCash<=66) {
						aiRollPR=0;
						}
						else if(aiCash>=67 && aiCash<=71) {
							aiRollPR=10000;
						}
						
						System.out.println("Your opponent has spun a "+aiRollPR); // Print the amount the AI has spun
					
						System.out.println("");
						System.out.println("--------- Guessing has begun ---------");
						System.out.println("");
					
						
						randomSen=4;
						randomConSen=90;
						
						if(randomConSen>=0&&randomConSen<81&&consFill!=21) {
					
							boolean valida=false;    // For near 80% and if every consonant hasn't been guessed yet, execute the following code
					
								while(valida==false) { // While guess from AI is invalid:
							
									valida=true;
							
									aiCons=random.nextInt(21); // Generate new consonant
									guess=everyCons[aiCons];
									for(int f=0; f<consArray.length; f++) { // Check with every consonant that has been guessed
										if(guess==consArray[f]){
											valida=false; // If found, set condition to re do the process
										}
									}
								}
						
								System.out.println("The Opponent has guessed "+guess); // Print the consonant AI has guessed
							
								consArray[consCounter]=guess; // Add the AI guess to array of consonant guesses
								for(int e=0; e<quesLen; e++) {
									if(consArray[consCounter]==quesArray[e]) {
										visArray[e]=consArray[consCounter]; // If matching, make visible
										winCounter=winCounter+1; // Add to total numbers of letters that have been guessed for now
										aPR=aPR+1; // Add 1 for each time a consonant appears
									}
								
									else {
							
									}
								}
						
								if(quesCounter==winCounter) { // If AI manages to match all letters during its turn, it will win
									winner="ai";
									userAnswer=question; // Set conditions for win
								}
						
								aiTotalCash=aiTotalCash+aiRollPR*aPR; // AI cash this round = letters in guess*money rolled
						
								System.out.println("The consonant has appeared "+aPR+" number of times");       // Print out all of the AI results
								System.out.println("The Opponent currently has a total of: "+aiTotalCash+"$ ");
						
								aPR=0; // Reset number of letters guessed per round to 0
						
								consCounter=consCounter+1; // Go to next index for the consonants guessed
						
						}
					
						else {

							
							if(randomSen>=0&&randomSen<6) {            // If the AI dosen't guess execute the following
								aPR=moneyCount.length()-winCounter;    // Less than 6% AI manages to guess entire sentence
								quesCounter=winCounter;
								for(int f=0; f<visArray.length; f++) {     // Make everything visible, add all money to AI total
									visArray[f]=quesArray[f];
									//if(quesArray[f]=='|') {
										//visArray[f]=' ';
									//}
								}
							
								
								System.out.println("The Opponent has managed to guess the entire sentence! ");
								winner="ai";            // Print the opponent results and let winner be AI
								question=(userAnswer);
								System.out.println("Please proceed accordingly and enter anything!");
							
							aiTotalCash=aiTotalCash+(aPR*aiRollPR);
							
							}
							else {
								System.out.println("Opponent has guessed sentence incorrectly");
								System.out.println("");       // Rest of the percent: print the AI has failed
							}
						}

						String string=("");
						
						
						for(int k=0; k<realQuestion.length(); k++) {
							char character=realQuestion.charAt(k);       // Create new string separated by spaces using charAt and concatenating it
							string=string+character+" ";
						}
						
						int firstIdx=string.indexOf("  ");
						int secondIdx=string.indexOf("  ", firstIdx+"  ".length()); // Discover where the sentence might be too long for 1 line and save index of location
						
						if(winner.equals("ai")||winner.equals("player")) {
							System.out.println(" ╔═══════════════════════════════════════╗"); // Print board state after a guess has been made
							System.out.println("|                 question:"+roundCounter+"              |");
							System.out.print("              ");
						
						
							System.out.print(string.substring(0,secondIdx));           // Print first part of the new sentence
							System.out.print(" ");
						
							System.out.print("        ");
						
							System.out.println("");

							System.out.print("               ");
						
							System.out.print(string.substring(secondIdx));             // Print the rest of the words
							System.out.print(" ");
						
							System.out.print("         ");
						
							System.out.println("");
						
							System.out.println("|                                         |");
							System.out.println("|                                         |");
							System.out.println(" ╚═══════════════════════════════════════╝");
					
							System.out.println("");
					}
					
						
						
						else {
							System.out.println(" ╔═══════════════════════════════════════╗"); // Print out the results of the vowel incident
							System.out.println("|                 question:"+roundCounter+"              |");
							System.out.print("              ");
							
							for(int p=0; p<=boardNum; p++) {
								System.out.print(visArray[p]);
								System.out.print(" ");
							}
							System.out.print("        ");
							
							System.out.println("");

							System.out.print("               ");
							for(int p=boardNum+1; p<question.length(); p++) {
								System.out.print(visArray[p]);
								System.out.print(" ");
							}
							System.out.print("         ");
							
							System.out.println("");
							
							System.out.println("|                                         |");
							System.out.println("|                                         |");
							System.out.println(" ╚═══════════════════════════════════════╝");
							
							System.out.println("");
						}
												
						
						if(randomConSen>=0&&randomConSen<81) { // If the AI decides to guess a consonant:
											
							if(aiVow>=0&&aiVow<31&&vowsFill!=5) { // 30% it buys a vowel + needs to ensure there are still some left
							
								vowelTruth="no";
							
								while(vowelTruth.equals("no")) {
									randomVow=random.nextInt(5); // Goes through array of possible vowels until reaches one that hasn't been used
									vowelTruth="a";
									for(int g=0; g<haoyangArray.length; g++) {
										if(everyVow[randomVow]==haoyangArray[g]) {
											vowelTruth="no";

										}
									}
								
								}
							
								System.out.println("The Opponent has tried to guess the vowel "+everyVow[randomVow]); // Print which vowel the AI has chosen
							
								haoyangArray[vowelCounter]=everyVow[randomVow]; // Add vowel chosen to use vowels already
							
								for(int c=0; c<quesLen; c++) {
									if(haoyangArray[vowelCounter]==quesArray[c]) { // Check if any vowels are present in the question
										visArray[c]=haoyangArray[vowelCounter]; 
										aiPR=aiPR+1; // Check how many vowels there are
										winCounter=winCounter+1; // Add to total number of letters guessed
									}
								}
							
								vowelCounter=vowelCounter+1; // Create new space for the used vowel Array for next turn
							
								aiTotalCash=aiTotalCash-(aiRollPR*aiPR); // Subtract the amount that it used to purchase vowel
								System.out.println("Your opponent now has "+aiTotalCash);
								
								System.out.println(" ╔═══════════════════════════════════════╗"); // Print the results of the vowel gamble
								System.out.println("|                 question:"+roundCounter+"              |");
								System.out.print("              ");
						
								for(int p=0; p<=boardNum; p++) {
									System.out.print(visArray[p]);
									System.out.print(" ");
								}
								System.out.print("        ");
						
								System.out.println("");

								System.out.print("               ");
								for(int p=boardNum+1; p<question.length(); p++) {
									System.out.print(visArray[p]);
									System.out.print(" ");
								}
								System.out.print("         ");
						
								System.out.println("");
						
								System.out.println("|                                         |");
								System.out.println("|                                         |");
								System.out.println(" ╚═══════════════════════════════════════╝");	
						
								System.out.println("");
								
								if(quesCounter==winCounter) {
									winner="ai";
									userAnswer=question;
								}
							
							}
						
							else {
							
							}
						}
					
						aiPR=0;
					
						System.out.println("Please enter anything to continue: "); // Prompt the user for continuation to next turn of theirs
						ms.nextLine();
						System.out.println("");
					
						consFill=0; // Reset all counters for ( # of consonants and vowels left )
						vowsFill=0; // counters rely on the beginning being 0
					
					}
					
				}
				
				if(winner.equals("ai")){ // If the winner of the round is the AI
					totalOppMoney=totalOppMoney+aiTotalCash; // Transfer to bank account all the won money
					System.out.println("You have lost the round, the Ai has "+totalOppMoney); // Print losing message
				}
				else if(winner.equals("player")) { // If the player wins
					totalUserMoney=totalUserMoney+totalCash; // Transfer winning money to final account
				}
				System.out.println("You currently have "+totalUserMoney); // Print player current status
				
			}
			
			System.out.println(""); // Execute the following when all 3 rounds are done
			System.out.println("So, the game has finally ended");
			System.out.println("Congratulations");
			if(totalUserMoney>totalOppMoney) {
				System.out.println("You have beaten the AI"); // If you made more money than the AI congratulate and offer spot in the leaderboards
				System.out.println("");

				System.out.println("------ !Offer! ------");
				System.out.println("");

				System.out.println("Would you like to have you name on the leaderboard? (y/n)");
				
				String leaderboardQ=ms.nextLine();
				
				if(leaderboardQ.equals("y")) {
					filePW.println(name+" "+totalUserMoney+" has defeated the AI");
					System.out.println("Congrats! ");
				}
				else {
					System.out.println("You have missed your only chance. GoodBYE");
				}
				
			}
			else {
				System.out.println("You have lost. This is the end"); // If you made less money than the AI, print losing message
			}
			
			ms.close();      // Close Scanner
			filePW.close();  // Close File Writer
			
	}
					
		public static int wheelSpin(int num) { // New method for calculate the amount of money user receives
			int bankRoll=0;
			int s=0;
			String randomStorage="0";
			String cashArray[]= {"250","500","750","1 K","2 K"," 0 ","10K"}; // Every possible cash prize in an array to display
			String a[]=new String[7];  // Order of the wheel display
						
			if(num>=1 && num<=12) {
				bankRoll=250;          // Actual number to be returned for calculating ( bankRoll )
				s=0;                   // Determines which number was selected
			}
			else if(num>=13 && num<=24) {
				bankRoll=500;
				s=1;
			}
			else if(num>= 25 && num<=36) {
				bankRoll=750;
				s=2;
			}
			else if(num>=37 && num<=48) {
				bankRoll=1000;
				s=3;
			}
			else if(num>=49 && num<=60) {
				bankRoll=2000;
				s=4;
			}
			else if(num>=61 && num<=66) {
				bankRoll=0;
				s=5;
			}
			else if(num>=67 && num<=71) {
				bankRoll=10000;
				s=6;
			}
				
			for(int i=0; i<=6; i++) {
				a[i]=cashArray[(s+i)%7]; // Assigns each nu		mber an array starting at array[s], adding for a specific order, while remainders going back to the beginning	
			}		
			
			if(s!=5 && s!=6) {     // Determines whether wheel lands on special space or normal
			
				for(int i=0; i<=4; i++) {	
				if(a[i]==" 0 ") {
					randomStorage=(" 0 "); // storage and shuffling system for different cases
					a[i]=a[5];
					a[5]=randomStorage;
					}
				else if(a[i]=="10K") {
					randomStorage=("10K");
					a[i]=a[6];
					a[6]=randomStorage;
					}
				}
				
				// Prints out the wheel shape for each of the cases with ( a / s )
				
				System.out.println("   .......:::::..-***-........................");
				System.out.println(":   - "+a[6]+" - "+a[1]+" - "+a[0]+" - "+a[2]+" - "+a[3]+" - "+a[4]+" -   :");
				System.out.println("-::::::::::::::::-***-:::::::::::::::::::::::::");
				System.out.println(":"+a[4]+"-                 			 ."+a[5]+":");
				System.out.println("-:::=                     	    	 -::::");
				System.out.println(":"+a[3]+"-                 	 		 ."+a[0]+":");
				System.out.println("----+                        		 ----:");
				System.out.println(":"+a[2]+"-                     		 ."+a[1]+":");
				System.out.println("----+                $"+(bankRoll)+"$               =---:");
				System.out.println(":"+a[1]+"-                 	 		 ."+a[2]+":");
				System.out.println("----+                     	    	 ----:");
				System.out.println(":"+a[0]+"-                 	   		 ."+a[3]+":");
				System.out.println("-:::=                    	     	-::::");
				System.out.println(":"+a[5]+":                        		 ."+a[4]+":");
				System.out.println("-::::::::::::::::::::::::::::::::::::::::::::");
				System.out.println(":   - "+a[4]+" - "+a[3]+" - "+a[2]+" - "+a[1]+" - "+a[0]+" - "+a[6]+" .   :");
				System.out.println("   .........................................");
				
			}
			
			else {
				
				
					
				if(s==5) {
					for(int i=0; i<=4; i++) {	
						if(a[i]==" 0 ") {
							randomStorage=(" 0 ");
							a[i]=a[0];
							a[0]=randomStorage;
							}
						else if(a[i]=="10K") {
							randomStorage=("10K");
							a[i]=a[1];
							a[1]=randomStorage;
							}
						}
				}
				
				if(s==6) {
					for(int i=0; i<=4; i++) {	
						if(a[i]==" 0 ") {
							randomStorage=(" 0 ");
							a[i]=a[1];
							a[1]=randomStorage;
							}
						else if(a[i]=="10K") {
							randomStorage=("10K");
							a[i]=a[0];
							a[0]=randomStorage;
							}
						}
				}
				
				System.out.println("   .......:::::..-***-........................");
				System.out.println(":   - "+a[5]+" - "+a[6]+" - "+a[0]+" - "+a[2]+" - "+a[3]+" - "+a[4]+" -   :");
				System.out.println("-::::::::::::::::-***-:::::::::::::::::::::::::");
				System.out.println(":"+a[4]+"-                 			 ."+a[5]+":");
				System.out.println("-:::=                     	    	 -::::");
				System.out.println(":"+a[3]+"-                 	 		 ."+a[6]+":");
				System.out.println("----+                      	    	 ----:");
				System.out.println(":"+a[2]+"-                     		 ."+a[1]+":");
				System.out.println("----+                $"+(bankRoll)+"$              =---:");
				System.out.println(":"+a[1]+"-                 	 		 ."+a[2]+":");
				System.out.println("----+                     	    	 ----:");
				System.out.println(":"+a[6]+"-                 	   		 ."+a[3]+":");
				System.out.println("-:::=                    	     	-::::");
				System.out.println(":"+a[5]+":                        		 ."+a[4]+":");
				System.out.println("-::::::::::::::::::::::::::::::::::::::::::::");
				System.out.println(":   - "+a[4]+" - "+a[3]+" - "+a[2]+" - "+a[0]+" - "+a[6]+" - "+a[5]+" .   :");
				System.out.println("   .........................................");
				
			}
				
			
			return bankRoll;
			
		}
	}


