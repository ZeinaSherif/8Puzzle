
package puzzleproject;

import java.util.Scanner;

/**
 *
 * @author LENOVO
 */
public class PuzzleProject {

    final static private String GOAL_STATE = "012345678";
    static long startTime, finishTime;
       
    public static void main(String[] args) {
        Scanner input=new Scanner(System.in);
        System.out.println ("Enter initial State");
        String state =input.next();
        System.out.println("Enter the search method");
        System.out.println("1) BFS\t2) DFS\t3) A*");
        int x=input.nextInt();
        
        String rootState = state;
        

        SearchTree search = new SearchTree(new Node(rootState), GOAL_STATE);
        if (x==1){
          startTime = System.currentTimeMillis();
          search.breadthFirstSearch();
          finishTime = System.currentTimeMillis();
        }
        else if (x==2){
          startTime = System.currentTimeMillis();
          search.depthFirstSearch();
          finishTime = System.currentTimeMillis();
        }
        else if (x==3){
          System.out.println ("1) Manhattan \n2)Ecleudian");
          int a=input.nextInt();
          if (a==1){
            startTime = System.currentTimeMillis();
            search.aStar(Heurastic.H_ONE);
            finishTime = System.currentTimeMillis();
                }
          else if (a==2){
            startTime = System.currentTimeMillis();
            search.aStar(Heurastic.H_TWO);
            finishTime = System.currentTimeMillis();
            }
        
        }
        long totalTime = finishTime - startTime;
        System.out.println("Time : " + totalTime);


    }

    }
    

