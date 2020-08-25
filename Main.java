
import java.util.Scanner;




public class Main 
{
	public static void main(String args[])
	{
		Board game = new Board(3,5);//make the board whatever dimensions you want(the x an y are flipped)
		game.fillSquare(0, 0, 2);//fill any space with a number(the x and y are flipped)
		game.fillSquare(1, 0, 2);
		game.fillSquare(0, 1, 2);
		game.fillSquare(1, 1, 2);
		
		
		Scanner scanner = new Scanner(System.in);
		
		
		while(true)
		{
			
			game.generateNewtile();
			game.printStuff();
			int direction = scanner.nextInt();//1 is right, 2 is left, 3 is up, 4 is down
			game.moveBoard(direction);
		}	
	}
}
