
package puzzleproject;

import java.util.*;

public class SearchTree {
    
//     This class contains all search algorithms plus some utilty methods needed in those algorithm
    
    
 private Node root;
    private String goalSate;
    

    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }

    public String getGoalSate() {
        return goalSate;
    }

    public void setGoalSate(String goalSate) {
        this.goalSate = goalSate;
    }

    public SearchTree(Node root, String goalSate) {
        this.root = root;
        this.goalSate = goalSate;
    }


    //******************************************************************************************************



    public void breadthFirstSearch() {

        Set<String> stateSets = new HashSet<String>();
        
        int time = 0;
        Node node = new Node(root.getState());
        Queue<Node> queue = new LinkedList<Node>();
        Node currentNode = node;
        while (!currentNode.getState().equals(goalSate)) {
            stateSets.add(currentNode.getState());
            List<String> nodeSuccessors = NodeUntil.getSuccessors(currentNode.getState());
            for (String n : nodeSuccessors) {
                if (stateSets.contains(n))
                    continue;
                stateSets.add(n);
                Node child = new Node(n);
                currentNode.addChild(child);
                child.setParent(currentNode);
                queue.add(child);

            }
            currentNode = queue.poll();
            time += 1;
        }

        NodeUntil.printSolution(currentNode, stateSets, root, time);

    }
//**********************************************************************************************

   public void depthFirstSearch() {
        // stateSet is a set that contains node that are already visited
        Set<String> stateSets = new HashSet<String>();
        int time = 0;
        Node node = new Node(root.getState());
        //the stack that store nodes that we should expand
        Stackk<Node> mainStack = new Stackk<>();
        //the stack that contains the successors of the expanded node
        Stackk<Node> successorsStack = new Stackk<>();
        Node currentNode = node;
        while (!currentNode.getState().equals(goalSate)) {
            stateSets.add(currentNode.getState());
            List<String> nodeSuccessors = NodeUntil.getSuccessors(currentNode.getState());
            for (String n : nodeSuccessors) {
                if (stateSets.contains(n))
                    continue;
                stateSets.add(n);
                Node child = new Node(n);
                currentNode.addChild(child);
                child.setParent(currentNode);
                successorsStack.push(child);

            }
            //we add the stack that contains the successors of the visted node to the beginning of the main stack
            mainStack.addStack(successorsStack);
            //successors stack should be cleared in order to store the next expaneded's successors
            successorsStack.clear();
            currentNode = mainStack.pop();
            time += 1;
            nodeSuccessors.clear();
        }
        NodeUntil.printSolution(currentNode, stateSets, root, time);

    }


   
    

    /**
     * Find the goal using A* algorithm. The huristic is the real cost to the current node from the initial Node
     * plus the estimated cost from the current node to the goal node
     */
    //*************************************************************************************************
    public void aStar(Heurastic heurastic) {
        // stateSet is a set that contains node that are already visited
        Set<String> stateSets = new HashSet<String>();
      
        int time = 0;
        Node node = new Node(root.getState());
        node.setTotalCost(0);

        // the comparator compare the cost values and make the priority queue to be sorted based on cost values
        NodePriorityComparator nodePriorityComparator = new NodePriorityComparator();

        // a queue that contains nodes and their cost values sorted. 10 is the initial size
        PriorityQueue<Node> nodePriorityQueue = new PriorityQueue<Node>(10, nodePriorityComparator);
        Node currentNode = node;
        while (!currentNode.getState().equals(goalSate)) {
            stateSets.add(currentNode.getState());
            List<String> nodeSuccessors = NodeUntil.getSuccessors(currentNode.getState());
            for (String n : nodeSuccessors) {
                if (stateSets.contains(n))
                    continue;
                stateSets.add(n);
                Node child = new Node(n);
                currentNode.addChild(child);
                child.setParent(currentNode);

                if (heurastic == Heurastic.H_ONE)
                    child.setTotalCost(currentNode.getTotalCost() + Character.getNumericValue(child.getState().charAt(child.getParent().getState().indexOf('0'))), heuristicOne(child.getState(), goalSate));
                else if (heurastic == Heurastic.H_TWO)
                    child.setTotalCost(currentNode.getTotalCost() + Character.getNumericValue(child.getState().charAt(child.getParent().getState().indexOf('0'))), heuristicTwo(child.getState(), goalSate));
               
                nodePriorityQueue.add(child);

            }
            currentNode = nodePriorityQueue.poll();
            time += 1;
        }
        NodeUntil.printSolution(currentNode, stateSets, root, time);
    }


   


 
    //*************************************************************************************************
    // This heuristic estimates the cost to the goal from current state by calculating the Manhathan distance from each misplaced
    // tile to its right position in the goal state
    private int heuristicOne(String currentState, String goalSate) {
        int difference = 0;
        for (int i = 0; i < currentState.length(); i ++)
            for (int j = 0; j < goalSate.length(); j ++)
                if (currentState.charAt(i) == goalSate.charAt(j))
                    difference = difference + ((Math.abs(i % 3 - j % 3)) + Math.abs(i / 3 + j / 3));
        return difference;
    }



    
    
    
//    Ecleudian
    private int heuristicTwo(String currentState, String goalSate) {


  int difference = 0;
        for (int i = 0; i < currentState.length(); i ++)
            for (int j = 0; j < goalSate.length(); j ++)
                if (currentState.charAt(i) == goalSate.charAt(j))
                    difference = (int) (difference +  Math.sqrt(Math.pow((i - i), 2) + Math.pow((j - j), 2)));
        return difference;
    }
    
    
    
}
