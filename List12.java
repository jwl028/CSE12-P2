import java.util.*;

/**
 * This class is a singly linked list
 * @author Jimmy Li cs12sdm
*/

public class List12<E> implements java.util.List<E> {
  private Node<E> head;
  private int size;

/**
 * Constructor - creates empty list
 * 
*/

  public List12()
  {
    head = null;
    size = 0;
  }
 /**
 * Inner Node class
 * 
*/

  private static class Node<T>
  {
    private T data;
    private Node<T> next; 
/**
 * Constructor - sets data and next to null
 * 
*/

    private Node()
    {
      this.data = null; 
      this.next = null;
    }

/**
 * Constructor - sets data to passed in data and next to null
 * 
*/
    private Node(T data)
    {
      this.data = data;
      this.next = null;
    }

/**
 * Constructor - sets data to passed in data and next passed in next
 * 
*/
    private Node(T data, Node<T> next)
    {
      this.data = data;
      this.next = next;
    }

/**
 * This method returns the next Node element
 *
*/

    private Node<T> getSuccessor()
    {
      return this.next;
    }
/**
 * This method sets next Node element
 *
*/
    private void setSuccessor(Node<T> n) 
    {
      this.next = n;
    }

/**
 * This method returns the data in the Node element
 *
*/
    private T getData()
    {
      return this.data;
    }

/**
 * This method sets data in the Node element
 *
*/
    private void setData(T t)
    {
      this.data = t;
    }
  }  

/**
 * This inner class creates an iterator which iterates through the list
 *
*/

  private class LLIter implements java.util.Iterator<E>
  {
    //Node next to be visited
    private Node<E> next;
    //Node last visited
    private Node<E> lastReturned;
    //Predecessor of lastReturned
    private Node<E> pred;

/**
 * Constructor which initializes the iterator to begin in the front of the list
 *
*/
    LLIter() 
    {
      if(head != null) {
        next = head.next;
      }
      else {
        next = null;
      }
      lastReturned = null;
      pred = null;
    }

/**
 * This method checks to see whether there is a succeeding element
 *
*/
   
    public boolean hasNext()
    {
      return next != null;
    }

/**
 * This method returns the next element if there is one
 *
*/

    public E next() 
    {
      if(next == null) {
        throw new NoSuchElementException();
      }
      pred = lastReturned;
      lastReturned = next;
      next = next.next;
      return lastReturned.data;
    }

/**
 * This method removes the current element that the iterator is pointing at
 *
*/

    public void remove()
    {
      if(lastReturned == null) {
        throw new IllegalStateException();
      }
      lastReturned = null;
      pred = next;
    }
  }

/**
 * This method passed in element o to the end of the list and returns true
 * if the method added successfully and false if it hadn't 
 *
*/

  public boolean add(E o)
  {
    //Class cast exception when object is not the same type as list
    //if(!(o instanceof List)) {
    //throw new ClassCastException();
    //}
    //Node to be added
    Node<E> newNode  = new Node<E>(o);
    //If list is empty then the head is the new node
    if(head == null) {
      newNode.setSuccessor(head);
      head = newNode;
      size++;
      return true;
    }
    else {
      //Creates a cursor that iterates to the last element of the list
      Node<E> cursor = head;
      while(cursor.getSuccessor() != null) {
        cursor = cursor.getSuccessor();
      }
      //Adds the new node to the end of the list
      cursor.setSuccessor(newNode);
      size++;
      return true;
    }
  }

/**
 * This method passed in element o to a passed in index
 *
*/

  public void add(int index, E element)
  {
    //New node to be added
    Node<E> newNode = new Node<E>(element);
    //If index is not within the range of the actual size of the list, then 
    //throw an exception
    if(index < 0 || index > size) {
      throw new IndexOutOfBoundsException();
    }
    //Class cast exception when object is not the same type as list
   // if(!(newNode instanceof List)) {
  //    throw new ClassCastException();
    //}
    //Loop through with a cursor to the index of the list to add the element
    Node<E> cursor = head;
    //If node is added in the front of the list then the head points to the
    //new node
    if(index == 0) {
      newNode.setSuccessor(head);
      head = newNode;
      size++;
    }
    else {
      while(--index > 0)
      {
        cursor = cursor.getSuccessor();
      }
      //Inserts the new node at the index specified
      newNode.setSuccessor(cursor.getSuccessor());
      cursor.setSuccessor(newNode);
      size++;
    }
  }

/**
 * This method clears the list
 *
*/

  public void clear()
  { 
    //Empties the list
    head = null;    
  }

/**
 * This method checks to see whether the passed in object is contained in the
 * list
 *
*/

  public boolean contains(Object o)
  {
    int index = size;
    Node<E> cursor = head;
    //If list is empty then it does not contain anything
    if(head ==  null) {
      return false;
    }
    else {
    //Creates a cursor and loops through every element to check whether there
    //are any elements matching o
      while(index > 0 && o != null) {
        if(o.equals(cursor.getData())) {
          return true;
        }
        if(cursor.getSuccessor() != null) {
          cursor = cursor.getSuccessor();
        }
        index--;
      }
      //If the object is null then run another check using double equals
      if(o == null) {
        while(index-- > 0) {
          if(o == cursor.getData()) {
            return true;
          }
          if(cursor.getSuccessor() != null) {
            cursor = cursor.getSuccessor();
          }
        }
      }
      //Returns false if list does not contain object o
      return false;
    }
  }

/**
 * This method checks to see whether the passed in list is equal to the 
 * calling list
 *
*/

  public boolean equals(Object o)
  {
    //Compares the object to the List class
    if(o instanceof List && o != null) {
      List12 k = (List12)o;
      //Checks the size of both object and the 
      if(k.size() != size) {
        return false;
      }
      //Checks each term of each list
      for(int i = 0; i < size; i++) {
        if(k.get(i) == null) {
          if(k.get(i) != get(i)) {
            return false;
          }
        }
        else if(!(get(i).equals(k.get(i)))) {
          return false;
        }
      }
      return true; 
    }
    else { 
      return false;
    }
  }

/**
 * This method returns the element at the passed in index
 *
*/

  public E get(int index)
  {
    if(index < 0 || index > size) {
      throw new IndexOutOfBoundsException();
    }
    //Loop through with a cursor to the index of the list to add the element
    Node<E> cursor = head;
    while(index-- > 0)
    {
      cursor = cursor.getSuccessor();
    }
    //Inserts the new node at the index specified
    return cursor.getData();
  }

/**
 * This method computes the hashCode
 *
*/

  public int hashCode()
  {
    int hashCode = 1;
    Iterator<E> i = iterator();
    while (i.hasNext()) {
      E obj = i.next();
      hashCode = 31*hashCode + (obj==null ? 0 : obj.hashCode());
    } 
    return hashCode;
  }

/**
 * This method returns the index of the passed in object that the object
 * is on in the list
 *
*/

  public int indexOf(Object o)
  {
    int index = 0;
    //If list is empty then return -1
    if(head ==  null) {
      return -1;
    }
    else {
      Node<E> cursor = head;
      //Compares all the nodes and returns the index of the first similar node
      while(index < size) {
        if(o.equals(cursor.getData())) {
          return index;
        }
        if(cursor.getSuccessor() != null) {
         cursor = cursor.getSuccessor();
        }
        index++;
      }
      return -1;
    }
  }

/**
 * This method checks if the list is empty
 *
*/

  public boolean isEmpty()
  {
    if(head ==  null)
    {
      return true;
    }
    else {
      return false;
    }
  }

/**
 * This method returns the inner class iterator
 *
*/

  public Iterator<E> iterator()
  {
    return new LLIter();
  }

/**
 * This method removes the specified object from the list
 *
*/

  public boolean remove(Object o)
  {
    //If list is empty, return false
    if(head ==  null) {
      return false;
    }
    Node<E> cursor = head;
    Node<E> target = cursor.getSuccessor();
    //Checks to see if the list contains element o and removes it
    for(int i = 0; i < size; i++) {
      if(o.equals(get(i))) {
        cursor.setSuccessor(target.getSuccessor());
        return true;
      }
      if(i > 0) {
        cursor = cursor.getSuccessor();
        target = target.getSuccessor();
      }
    }
    return false;
  }

/**
 * This method changes the data contained in the element at the specified
 * index
 *
*/

  public E set(int index, E element)
  {
    Node<E> cursor = head;
    //Throws exception if index is out of bounds
    if(index < 0 || index >= size) {
      throw new IndexOutOfBoundsException();
    }
    //Sets cursor to index position
    while(index-- > 0) {
      cursor = cursor.getSuccessor();
    } 
    //Saves previous element
    E oldElement = cursor.getData();
    //Replaces old element with new element
    cursor.setData(element);
    return oldElement;
  }

/**
 * This method returns the size of the list
 *
*/

  public int size()
  {
    return size;
  }

/**
 * This method is not supported but is required for implementation
 *
*/

  public List<E> subList(int arg0, int arg1) 
  {
    throw new UnsupportedOperationException();
  }

/**
 * This method is not supported but is required for implementation
 *
*/

  public boolean addAll(Collection<? extends E> c)
  {
    throw new UnsupportedOperationException();
  }

/**
 * This method is not supported but is required for implementation
 *
*/

  public boolean addAll(int index, Collection<? extends E> c)
  {
    throw new UnsupportedOperationException();
  }

/**
 * This method is not supported but is required for implementation
 *
*/

  public boolean containsAll(Collection<?> c)
  {
    throw new UnsupportedOperationException();
  }

/**
 * This method is not supported but is required for implementation
 *
*/

  public int lastIndexOf(Object o)
  {
    throw new UnsupportedOperationException();
  }

/**
 * This method is not supported but is required for implementation
 *
*/

  public ListIterator<E> listIterator()
  {
    throw new UnsupportedOperationException();
  }

/**
 * This method is not supported but is required for implementation
 *
*/

  public ListIterator<E> listIterator(int index)
  {
    throw new UnsupportedOperationException();
  }

/**
 * This method is not supported but is required for implementation
 *
*/
  public E remove(int index)
  {
    throw new UnsupportedOperationException();
  }

/**
 * This method is not supported but is required for implementation
 *
*/

  public boolean removeAll(Collection<?> c)
  {
    throw new UnsupportedOperationException();
  }

/**
 * This method is not supported but is required for implementation
 *
*/
  public boolean retainAll(Collection<?> c)
  {
    throw new UnsupportedOperationException();
  }

/**
 * This method is not supported but is required for implementation
 *
*/
  public Object[] toArray()
  {
    throw new UnsupportedOperationException();
  }

/**
 * This method is not supported but is required for implementation
 *
*/
  public <T> T[] toArray(T[] a) 
  {
    throw new UnsupportedOperationException();
  }
}
