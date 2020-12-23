import org.junit.Ignore;

import java.util.List;
import java.util.Scanner;

import static org.junit.Assert.*;
@Ignore
public class MyLinkedListTest {

    @org.junit.Test
    public void add() {
        MyLinkedList list = new MyLinkedList();
        list.add(1);
        assertNotNull(list);
    }

    @org.junit.Test
    public void delete() {
        MyLinkedList list = new MyLinkedList();
        list.add(1);
        list.add(2);
        list.delete(0);
        assertNotNull(list);
    }

    @org.junit.Test
    public void update() {
        MyLinkedList list = new MyLinkedList();
        list.add(1);
        list.update(0,3);
        assertNotNull(list);
    }


}