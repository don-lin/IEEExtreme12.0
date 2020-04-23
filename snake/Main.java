package snake;

//Don't place your source in a package
import java.util.*;
import java.lang.*;
import java.io.*;

//Please name your class Main
class Main {
	class Position{
		int x,y;
		public Position(int a,int b) {
			x=a;
			y=b;
		}
	}
	class Snake{
		Position start;
		Position end;
		public Snake(Position s, Position e) {
			start = s;
			end = e;
		}
		public void move(Player p) {
			p.moveTo(end);
		}
	}
	class Player{
		Position position;
		int number;
		int index;
		public Player(Position p,int i,int n) {
			position = p;
			index = i;
			number = n;
		}
		public void moveTo(Position p) {
			position.x = p.x;
			position.y = p.y;
		}
	}
	
	public static void main (String[] args) throws Exception {
		Main m = new Main();
	    Scanner in = new Scanner(System.in);
		int size = in.nextInt();
		Position bored[] = new Position[size*size+2];
		bored[0] = m.new Position(0, 1);
		bored[size*size+1] = m.new Position(0, size);
		for(int i = 0;i < size;i++) {
			for(int j = 0;j < size;j++) {
				if(i % 2 == 0) {
					bored[1+i*size+j] = m.new Position(j+1, i+1);
				}else {
					bored[1+i*size+j] = m.new Position(size-j, i+1);
				}
			}
		}
		int player_number = in.nextInt();
		ArrayList<Player> players = new ArrayList<Player>();
		for (int i = 0; i < player_number; i++) {
			players.add(m.new Player(m.new Position(0, 1), 0, i+1));
		}
		int s_num = in.nextInt();
		Snake[] snakes = new Snake[bored.length];
		for(int i=0;i<s_num;i++) {
			int x = in.nextInt(),y = in.nextInt();
			Position start = m.new Position(x, y);
			Position end = m.new Position(in.nextInt(), in.nextInt()); 
			if(y%2==1) {
				snakes[(y-1)*size+x] = m.new Snake(start, end);
			}else {
				snakes[(y-1)*size+5-x] = m.new Snake(start, end);
			}
		}
		int l_num = in.nextInt();
		for(int i=0;i<l_num;i++) {
			int x = in.nextInt(),y = in.nextInt();
			Position start = m.new Position(x, y);
			Position end = m.new Position(in.nextInt(), in.nextInt()); 
			if(y%2==1) {
				snakes[(y-1)*size+x] = m.new Snake(start, end);
			}else {
				snakes[(y-1)*size+5-x] = m.new Snake(start, end);
			}
		}
		int dice_num = in.nextInt();
		int steps[]=new int[dice_num];
		for(int i=0;i<dice_num;i++) {
			steps[i] = in.nextInt()+in.nextInt();
		}
		for (int i = 0; i < steps.length; i++) {
			if(players.size()==0) break;
			Player player = players.get(i%players.size());
			player.index+=steps[i];
			if(player.index >= bored.length-1){
				System.out.println(player.number+" "+"winner");
				players.remove(i%players.size());
			}else {
				Position des = bored[player.index];
				player.moveTo(des);
				if(snakes[player.index] != null) {
					snakes[player.index].move(player);
				}
			}
		}
		for(int i = 0;i < players.size();i++) {
			System.out.println(players.get(i).number+" "+players.get(i).position.x+" "+players.get(i).position.y);
		}
		in.close();
	}
}