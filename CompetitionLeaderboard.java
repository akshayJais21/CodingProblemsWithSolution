// Online Java Compiler
// Use this editor to write, compile and run your Java code online
import java.util.*;
public class HelloWorld {
    public static void main(String[] args) {
        System.out.println("Try programiz.pro");
        Leaderboard leaderboard = new Leaderboard();
        // leaderboard.addScore(playerId, score);
        // int ans = leaderboard.top(k);
        // leaderboard.reset(playerId);
        leaderboard.addScore(1,73);   // leaderboard = [[1,73]];
        leaderboard.addScore(2,56);   // leaderboard = [[1,73],[2,56]];
        leaderboard.addScore(3,39);   // leaderboard = [[1,73],[2,56],[3,39]];
        leaderboard.addScore(4,51);   // leaderboard = [[1,73],[2,56],[3,39],[4,51]];
        leaderboard.addScore(5,4);    // leaderboard = [[1,73],[2,56],[3,39],[4,51],[5,4]];
        System.out.println(leaderboard.top(1));           // returns 73;
        leaderboard.reset(1);         // leaderboard = [[2,56],[3,39],[4,51],[5,4]];
        leaderboard.reset(2);         // leaderboard = [[3,39],[4,51],[5,4]];
        leaderboard.addScore(2,51);   // leaderboard = [[2,51],[3,39],[4,51],[5,4]];
        System.out.println(leaderboard.top(3));           // returns 141 = 51 + 51 + 39;
    }
}
class Player{
    int id;
    int score;
    public Player(int id,int score){
        this.id = id;
        this.score = score;
    }
}
class Leaderboard {
    /**
     * @param playerId: ID of a player.
     * @param score: Score of the player.
     * @return: nothing
     */
     // declaring minheap 
     PriorityQueue<Player> queue ;
     Map<Integer,Player> map;
     public Leaderboard(){
         this.queue =  new PriorityQueue<>((a,b) -> (b.score - a.score));
         this.map = new HashMap<>();
         
     }
    public void addScore(int playerId, int score) {
        // --- write your code here ---
        if(map.containsKey(playerId)){
            Player p = map.get(playerId);
            queue.remove(p);
            p.score = p.score + score;
            queue.add(p);
        }else
        {
            Player p = new Player(playerId,score);
            map.put(playerId,p);
            queue.add(p);
        }
    }

    /**
     * @param k: Top k players.
     * @return: Summary of the scores of the top k players.
     */
    public int top(int k) {
        // --- write your code here ---
        int sum = 0;
        List<Player> list = new ArrayList<>();
        while(list.size()!= k && !queue.isEmpty()){
            Player p = queue.peek();
            list.add(p);
            queue.remove();
            sum = sum + p.score;
        }
        
        for(Player p1: list)
            queue.add(p1);
        return sum;
    }

    /**
     * @param playerId: ID of a player.
     * @return: nothing
     */
    public void reset(int playerId) {
        // --- write your code here ---
        if(map.containsKey(playerId)){
            Player p = map.get(playerId);
            queue.remove(p);
            p.score = 0;
            queue.add(p);
        }
        
        
    }
}
