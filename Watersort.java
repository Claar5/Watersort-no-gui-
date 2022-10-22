import java.util.*;
public class Watersort{
	Character top = null;
	static Character red = new Character('r');
	static Character green = new Character('g');
	static Character blue = new Character('b');
	
	public static void ShowAll(StackAsMyArrayList<Character>[] input){
		
		for(int i =0; i< 5; i++)
		{
			System.out.println("Bottle "+i + " : "+input[i]);
		}
	}
	
	public static boolean checkSolved(StackAsMyArrayList<Character>[] input){
		boolean flag = false;
		int count =0;
		
		for(int i = 0; i< 5; i++){
			if(input[i].checkStackUniform()){
				if(input[i].getStackSize() == 4 || input[i].getStackSize() == 0){
					count++;
				}
			}
		}
		if(count == 5){
			flag = true;
			
		}
		return flag;
		
	}
	public static void main(String args[]){
		
		StackAsMyArrayList<Character>[] bottles = new StackAsMyArrayList[5];
		System.out.println("\nDisplaying watersort bottles:");
		
		for(int i =0; i < 5; i++)
		{
			bottles[i] = new StackAsMyArrayList<>();
		}
		
		//Character[] ch = {blue, red, green};
		
		MyArrayList<Character> ch = new MyArrayList<>();
		ch.add(0, blue);
		ch.add(1, red);
		ch.add(2, green);
		
		
		Random rand = new Random();
		
		int bb;
		int aa;
		int k=0;
		int red =0;
		int blue =0;
		int green=0;
		int colorLim=4;
		int r = 1;
		int b = 1;
		int g = 1;
		
		while(k<12){
			bb = rand.nextInt(5);
			if(bottles[bb].getStackSize()< 4){
				aa = rand.nextInt(ch.getSize());
				bottles[bb].push(ch.get(aa));
				k++;
				if(((Comparable)(bottles[bb].peep()).toString().charAt(0)).compareTo('b') == 0){
					blue++;
				}
				if(((Comparable)(bottles[bb].peep()).toString().charAt(0)).compareTo('r') == 0){
					red++;
				}
				if(((Comparable)(bottles[bb].peep()).toString().charAt(0)).compareTo('g') == 0){
					green++;
				}
				//(((Comparable)blue.compareTo(colorLim)) >= 0)
				if(blue >= colorLim){
					while(b>0){
						ch.remove(0);
						
						b--;
					}
				}
				if(red >= colorLim){
					while(r>0){
						r--;
						
						if(b ==0)
							ch.remove(0);
						else
							ch.remove(1);
					}
				}
				if(green >= colorLim){
					while(g>0){
						g--;
						
						if(b ==0){
							if(r == 0) {}
							
							else
								ch.remove(1);	
						}
							
						else if(r==0)
							ch.remove(1);
						else
							ch.remove(2);
					}
				}
			
			}

		}
		
		ShowAll(bottles);
		
	
		Scanner input = new Scanner(System.in);
		int from;
		int to;
		while(!checkSolved(bottles)){

			System.out.print("Pick bottle to move from: ");
			from = input.nextInt();
			if(from < 0 || from > 4){
				System.out.println("invalid number, chose again");
				from = input.nextInt();
			}
			if(bottles[from].getStackSize() == 0){
				System.out.println("Invalid number, chose again: ");
				from = input.nextInt();
			}
			System.out.print("Bottle to move to:  ");
			to = input.nextInt();
			if(to < 0 || to > 4){
				System.out.println("invalid number, chose again");
				from = input.nextInt();
			}
			
			
			Character move;
			boolean check = true;
			while(bottles[to].peep() == bottles[from].peep() && check){
					if(bottles[to].getStackSize() < 4){
						move = bottles[from].pop();
						bottles[to].push(move);
					}
					else{
						System.out.println("Insufficient space in target bottle");
						check = false;
						
					}
			
			}
			
			
			if(bottles[to].getStackSize() == 0){
				bottles[to].push(bottles[from].pop());
				
				while(bottles[to].peep() == bottles[from].peep()){
					bottles[to].push(bottles[from].pop());
				}
			}
			
			ShowAll(bottles);
			if(checkSolved(bottles)){
				System.out.println("\nHooray, you completed the game!");
			}
		}
		
		
	}	
	
	
}