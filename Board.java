//2048 instructions: 
//build the board with the dimensions of your choice and fill squares with values of your choice
//use the number keys 1 2 3 and 4 to join numbers in the specified direction
//1 is right, 2 is left, 3 is up, 4 is down
//hit enter to see the new board
//when two tiles with the same number touch, they will merge into one



public class Board 
{
	
	private int length;//number of squares vertical
	private int width;//number of squares horizontal
	int[][] grid;
	
	public Board(int l, int w)//creates the board
	{
		length = l;
		width = w;
		grid = new int [length][width];
		
		for(int x = 0; x<length; x++)
		{
			for(int y=0; y<width; y++)
			{
				grid[x][y]=0;
			}
		}
	}
	
	public void printStuff()//prints out the grid
	{
		for(int x = 0; x<length; x++)
		{
			for(int y=0; y<width; y++)
			{
				System.out.print(grid[x][y]);
				
			}
			System.out.println("");
		}
	}
	
	public int getij(int i, int j)//return the value of a particular square
	{
		return grid[i][j];
	}
	
	public void fillSquare(int i, int j, int x)//fills the grid 
	{
		grid[i][j] = x;
	}
	
	
	
	public boolean generateNewtile()//generates a new tile 
	{
		int empty=0;
		for(int x = 0; x<length; x++)
		{
			for(int y = 0; y<width; y++)
			{
				if(grid[x][y]==0)
				{
					empty++;
				}
			}
		}
		
		if(empty < 1)
		{
			return false;
		}
		
		double randomnum = Math.random();
		int newnum = 0;
		if(randomnum >= .9)
		{
			newnum=4;
		}
		else
		{
			newnum=2;
		}

		int n = (int)(Math.random()*empty);
		int count = 0;
		for(int x = 0; x<length; x++)
		{
			for(int y = 0; y<width; y++)
			{
				if(grid[x][y]==0)
				{
					count++;
					if(count==n)
					{
						grid[x][y]=newnum;
						return true;
					}
				}
			}
		}
		return true;
	}
	
	public static int[] merge1d(int arr[])//merge one array to the right 
	{
		int dst = arr.length-1;
		boolean searchingforpair=false;
		int pair = -1;
		int[]newarr = new int[arr.length];
		for(int src = arr.length-1; src>=0; src--)
		{
			
			if(!searchingforpair && arr[src]!=0)
			{
				pair = arr[src];
				searchingforpair=true;
			}
			else if(searchingforpair)
			{
				if(arr[src]==pair)
				{
					newarr[dst]=2*pair;
					searchingforpair=false;
					dst--;
					
				}
				else if(arr[src]==0)
				{
					
				}
				else
				{
					newarr[dst]=pair;//not paired pair
					dst--;
					pair=arr[src];
				}
			}
		}
		if(searchingforpair)
		{
			newarr[dst]=pair;
		}
		return newarr;
	}
	
	public static int[] flipArray(int arr[])
	{
		int[]newarr = new int[arr.length];
		for(int x = 0; x<arr.length; x++)
		{
			newarr[x]=arr[arr.length-x-1];
		}
		return newarr;
	}
	
	public void moveBoard(int direction)//1 is right, 2 is left, 3 is up, 4 is down
	{
		
		if(direction==1)//board is moving right
		{
			for(int x = 0; x<length; x++)
			{
				grid[x]=merge1d(grid[x]);
			}
		}
		if(direction==2)//board is moving left
		{
			for(int x = 0; x<length; x++)
			{
				int[] flippedleft = flipArray(grid[x]);
				grid[x] = flipArray(merge1d(flippedleft));
			}
		}
		
		if(direction==3)//board is moving up
		{
			int[] column = new int[length];
			for(int y = 0; y<width; y++) //loop over columns
			{
				for(int x = 0; x<length; x++)
				{
					column[x] = grid[x][y]; //take the column out
				}
				int[] flippedup = flipArray(column); //flip column
				int[] merged=flipArray(merge1d(flippedup));
				for(int x = 0; x<length; x++)
				{
					grid[x][y]= merged[x];
				}
			}
		}
		if(direction ==4)//board is moving down
		{
			int[] column = new int[length];
			for(int y=0; y<width; y++)
			{
				for(int x = 0; x<length; x++)
				{
					column[x]=grid[x][y];
				}
				int[]merged = merge1d(column);
				for(int x = 0; x<length; x++)
				{
					grid[x][y]= merged[x];
				}
			}
		}
		else
		{
			
		}
	}
}
