import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyLinkedList implements Iterable<Integer> {

    public String z = "Элемент не найден";
    public int size;
    public Node first;
    public Node last;

    public void clear() {
        this.size = 0;
        this.first = null;
        this.last = null;
    }
    public void add(Integer value) {
        if (this.first == null) {
            first = new Node(null, null, value);
        } else {
            Node prevElement = this.last == null ? this.first : this.last;
            this.last = new Node(prevElement, null, value);
            prevElement.next = this.last;
        }
        this.size++;
    }

    private boolean checkIndexToRange(int index) {
        return index >= 0 && index < this.size;
    }

    public Node getLinkByIndex(int index) {
        Node result;
        if (this.size >> 1 >= index) {
            result = this.first;
            for (int i = 0; i < index; i++) {
                result = result.next;
            }
        } else {
            result = this.last;
            for (int i = this.size - 1; i > index; i--) {
                result = result.prev;
            }
        }
        return result;
    }

    public boolean addAll(Integer... values) {
        boolean result = values != null && values.length > 0;
        if (result) {
            for (Integer value : values) {
                add(value);
            }
        }
        return result;
    }

    public Integer get(int index) {
       Integer result;
        if (checkIndexToRange(index)) {
            result = getLinkByIndex(index).value;
        } else {
            throw new NoSuchElementException(this.z);
        }
        return result;
    }

    private boolean deleteByLink(Node node) {
        boolean result = node != null;
        if (result) {
            if (node.next == null && node.prev == null) {
                first = null;
                last = null;
            } else if (node.prev == null) {
                first = node.next;
                first.prev = null;
            } else if (node.next == null) {
                last = node.prev;
                last.next = null;
            } else {
                node.prev.next = node.next;
                node.next.prev = node.prev;
            }
            size--;
        }
        return result;
    }

    public boolean delete(int index) {
        boolean result = checkIndexToRange(index);
        if (result) {
            result = deleteByLink(getLinkByIndex(index));
        }
        return result;
    }

    public boolean addIndex(int index, Integer value) {
        boolean result = true;
        if (index == this.size) {
            add(value);
        } else if (checkIndexToRange(index)) {
            Node oldElement = getLinkByIndex(index);
            Node newElement = new Node(oldElement.prev, oldElement, value);
            if (oldElement.prev == null) {
                this.first = newElement;
                this.last = oldElement;
            } else {
                oldElement.prev.next = newElement;
                oldElement.prev = newElement;
            }
            this.size++;
        } else {
            result = false;
        }
        return result;
    }
    public Integer update(int index, Integer value) {
        Integer result;
        if (checkIndexToRange(index)) {
            Node temp = getLinkByIndex(index);
            result = temp.value;
            temp.value = value;
        } else {
            throw new NoSuchElementException(this.z);
        }
        return result;
    }
    public Integer[] toArray() {
        Integer[] resultArray = new Integer[this.size];
        int index = 0;
        for (Node link = this.first; link != null; link = link.next) {
            resultArray[index++] = link.value;
        }
        return resultArray;
    }

    public String toString() {
        return Arrays.toString(toArray());
    }


    public Iterator<Integer> iterator() {
        return new IteratorLinked();
    }

    private class IteratorLinked implements Iterator<Integer> {
        private Node cursor = first;

        public boolean hasNext() {
            return this.cursor != null;
        }
        public Integer next() {
            if (!hasNext()) {
                throw new NoSuchElementException(z);
            }
            Integer result = cursor.value;
            this.cursor = this.cursor.next;
            return result;
        }
        public void remove() {
        }
    }
}