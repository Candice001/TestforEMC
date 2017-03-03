package emc.test1;

import java.util.HashMap;

public class PartitionInverse {
	
	public Node inverseALink(Node head, int k){
    	if(head == null)
    		return null;
    	if(k < 2) return head; // not necessary to do the following if k < 2
    	
    	// need four position to find k numbers of nodes.
    	// left is the node before the k nodes which need to inverse
    	// right is the node after the k nodes which need to inverse
    	// start is the first node of the k nodes
    	// end is the end node of the k nodes
    	Node pLeft = new Node(-1);
    	Node pStart = head;
    	Node pRight = null;
    	Node pEnd = null;
    	
    	// traverse this link, do the inverse operation for every k nodes.
    	Node pCur = head;
    	int count = 1;
    	while(pCur != null){
    		if(count == k){ // get the k nodes
    			
    			// return head directly if does not inverse
    			// for the first k nodes, in other words, pEnd == null, need to change Head node position to pCur
    			// otherwise, need not to change head
    			if(pEnd == null){
    				head = pCur;
    			}
    			
    			pEnd = pCur; // set end to the Kst node.
    			pRight = pCur.getNext(); // record next node which need to link
    			
    			// call the inverse method and link these inversed k nodes to original link
    			HashMap<String, Node> result = inverseKNodes(pLeft, pStart, pEnd, pRight);
    			pLeft = result.get("pLeft");
    			pStart = result.get("pStart");
    			count = 0;
    			
    			// move pCur to the k+1 node
    			pCur = pRight;
    		}else{
    			pCur = pCur.getNext();
    		}
    		count ++;
    	}
    	
    	return head;
    }
    
    /**
     * for the range from pStart node t pEnd nodes,
     * inverse them and link them between pLeft node and pRight node.
     * 
     * @param pLeft
     * @param pStart
     * @param pEnd
     * @param pRight
     * @return
     */
    private HashMap<String, Node> inverseKNodes(Node pLeft, Node pStart, Node pEnd, Node pRight){
    	
    	Node pre = pStart;
    	Node temp = pre.getNext();
    	Node next = null;
    	while(temp != pRight){ // not get to the k+1 node
    		next = temp.getNext();
    		temp.setNext(pre);
    		pre = temp;
    		temp = next;
    	}
    	
    	// link these k nodes to original link
    	// for the first k nodes (pLeft == null), the left node need not to link
    	if(pLeft != null) 
    		pLeft.setNext(pre);
    	
    	pStart.setNext(pRight);
    	// reset pStart and pLeft after one inverse
    	pLeft = pStart;
    	pStart = pLeft.getNext();
    	
    	HashMap<String, Node> result = new HashMap<String, Node>();
    	result.put("pLeft", pLeft);
    	result.put("pStart", pStart);
    	
    	return result;
    }
    
    public void printALink(Node head){
    	while(null != head){
    		System.out.print(head.getData() + " -> ");
    		head = head.getNext();
    	}
    	System.out.println("NULL");
    }

}

class Node {  
    private int data;  // data
    private Node next = null; // point  
    public Node(int data) {  
        this.data = data;  
    }  
    
    public int getData() {  
        return data;  
    }  
    public void setData(int data) {  
        this.data = data;  
    }        
    public Node getNext() {  
        return next;  
    }  
    public void setNext(Node next) {  
        this.next = next;  
    }
}  

