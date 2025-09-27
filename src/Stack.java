public class Stack {
  private Node top;

  Stack(){
      this.top = null;
  }

  public void push(int data){
      Node newNode = new Node(data);
      newNode.next = top;
      top = newNode;
  }

  public void pop(){
      if(isEmpty()){
          throw new RuntimeException("Stack is empty");
      }
      int pop = top.data;
      top = top.next;
      System.out.println(pop);
  }

  public boolean isEmpty(){
      if(top == null){
          return true;
      }
      return false;
  }

  public int peek(){
      if(isEmpty()){
          throw new RuntimeException("Stack is empty");
      }
      return top.data;
  }

  public void traverse(){
      Node temp = top;
      while(temp != null){
          System.out.println(temp.data);
          temp = temp.next;
      }
  }

  public static void main(String args[]){
      Stack stack = new Stack();
      stack.push(10);
      stack.push(20);
      stack.traverse();
      stack.peek();
      stack.pop();
  }
}
