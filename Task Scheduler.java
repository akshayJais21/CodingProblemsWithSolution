/*

You are given an array of CPU tasks, each represented by letters A to Z, and a cooling time, n. Each cycle or interval allows the completion of one task. Tasks can be completed in any order, but there's a constraint: identical tasks must be separated by at least n intervals due to cooling time.

​Return the minimum number of intervals required to complete all tasks.

Example 1:

Input: tasks = ["A","A","A","B","B","B"], n = 2

Output: 8

Explanation: A possible sequence is: A -> B -> idle -> A -> B -> idle -> A -> B.

After completing task A, you must wait two cycles before doing A again. The same applies to task B. In the 3rd interval, neither A nor B can be done, so you idle. By the 4th cycle, you can do A again as 2 intervals have passed.

Example 2:

Input: tasks = ["A","C","A","B","D","B"], n = 1

Output: 6

Explanation: A possible sequence is: A -> B -> C -> D -> A -> B.

With a cooling interval of 1, you can repeat a task after just one other task.

Example 3:

Input: tasks = ["A","A","A", "B","B","B"], n = 3

Output: 10

Explanation: A possible sequence is: A -> B -> idle -> idle -> A -> B -> idle -> idle -> A -> B.

There are only two types of tasks, A and B, which need to be separated by 3 intervals. This leads to idling twice between repetitions of these tasks.

https://leetcode.com/problems/task-scheduler/description/
 */


import java.util.*;

import static java.lang.Math.abs;

public class Play {
    static int n = 2;

    public static void main(String args[])  throws Exception {
        Character[] tasks = new Character[]{'A', 'A', 'A', 'A', 'A', 'A', 'B', 'C', 'D', 'E', 'F', 'G'};

        int ans = calcTime(tasks,n);
        System.out.println(ans);
    }

    private static int calcTime(Character[] tasks, int n) {

        PriorityQueue<Task> maxHeap = new PriorityQueue<Task>((a,b) -> b.freq - a.freq);
        HashMap<Character,Integer> map = new HashMap<Character,Integer>();
        for(Character character : tasks){
            if(map.containsKey(character)) {
                map.put(character, map.get(character)+1);
            }else
                map.put(character, 1);
        }

        for(Map.Entry<Character,Integer> entry : map.entrySet()){
            Task task = new Task(entry.getKey(), entry.getValue());
            maxHeap.add(task);
        }
        System.out.println(maxHeap);

        List<Character> resultList = new ArrayList<Character>();
        while(!maxHeap.isEmpty()){

            Task task = maxHeap.remove();
            formtaskList(task,resultList);

        }
        System.out.println(resultList);
        return resultList.size();


    }

    private static List<Character> formtaskList(Task task, List<Character> resultList) {

        // find first blank space depicted by $
        int blank = -1;

        int last_pos = -1;

        for(int i = 0; i < resultList.size(); i++){
            if(task.freq == 0 )
                break;
            if(resultList.get(i) == '$' && last_pos == -1){
                resultList.set(i, task.character);
                task.freq -- ;
                last_pos = i;

            }else if(resultList.get(i) == '$' && abs(i-last_pos) > n)
            {
                resultList.set(i, task.character);
                task.freq -- ;
                last_pos = i;
            }
        }



        if( (last_pos == -1) || (last_pos != -1 && task.freq > 0) ) {

            for(int i = 0 ; i < task.freq ; i++)
            {
                resultList.add(task.character);

                if( i < task.freq - 1) {
                    for (int j = 0 ; j < n ; j++)
                        resultList.add('$');

                }
            }
        }
return resultList;
    }


}
class Task{
    Character character;
    int freq;
    Task(Character c, int freq){
        this.character = c;
        this.freq =  freq;

    }
}

// ["A","A","A","B","B","B"], n = 2
// A = 3, B = 3       A B _ A B _ AB

// "A","C","A","B","D","B" n = 1
// A = 2 B = 2 c = 1 d = 1
// AbAbcd


// ABABCD

// A = 2 B = 2 c = 2 d = 1



//        (highest appearing charcter feq. - 1)*n = no of empty spaces in between
//
//// ["A","A","A","B","B","B"], n = 2
//// A = 3, B = 3       A B _ A B _ AB
//
