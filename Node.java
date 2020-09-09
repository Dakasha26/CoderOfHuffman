
package toi.lab2;


class Node{
    private Node leftNode;
    private Node rightNode;
    
    private char letter;
    private int priority;
            
    
    // Для листьев дерева
    public Node(char letter, int priority){
        this.letter = letter;
        this.priority = priority;
    }
    
    // Для узлов
    public static Node createRelation(Node first, Node second){
        int priority = first.priority + second.priority;
        Node emptyNode = new Node(first, second, priority);
        return emptyNode;
    }
    
    private Node(Node leftNode, Node rightNode, int priority){
        this.leftNode = leftNode;
        this.rightNode = rightNode;
        this.priority = priority;
    }
    
    
    public boolean hasNeighbors(){
        if(leftNode == null && rightNode == null)
            return false;
        else
            return true;
    } 
    
    public Node getLeftNeighbor(){
        return leftNode;
    }
    
    public Node getRightNeighbor(){
        return rightNode;
    }
    
    public int getPriority(){
        return priority;
    }
    
    public char getLetter(){
        return letter;
    }
}
