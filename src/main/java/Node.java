public class Node {

      public Node prev;
      public Node next;
      public Integer value;

      Node(Node prev, Node next, Integer value) {
         this.prev = prev;
         this.next = next;
         this.value = value;
      }
   }
