import junit.framework.*;
import java.util.*;

/**
 * This class tests the functionality of the List12 class.
 * @author Jimmy Li cs12sdm
*/

public class List12Tester extends junit.framework.TestCase
{
  private List<String> sList;
  private List<Integer> iList;
 
/**
 * This method sets up the tester as a JUnit graphical display
*/

  public static void main(String args[]){
    junit.swingui.TestRunner.main(new String[] {"List12Tester"});
  }
 
/**
 * This method tests both the add() and contains() method of the 
 * java.util.List<E> interface. It checks that the contains method returns
 * the true for each element in the list.
 * @see java.util.List<E> 
*/

  public void testAddContains()
  {
    sList.add("A");
    sList.add("B");
    assertTrue(sList.contains("A"));
    assertTrue(sList.contains("B"));
    assertFalse(sList.contains("C"));
    assertFalse(sList.contains(null));
  }

/**
 * This method checks the Iterator<E> hasNext() and next() methods to make
 * sure that they actually detect and manipulate the next visible element.
 *
 * @see java.util.List<E> 
*/

  public void testIterator()
  {
    sList.add("A");
    sList.add("B");
    sList.add("C");
    Iterator<String> iterate = sList.iterator();
    assertTrue(iterate.hasNext());
    assertNotNull(iterate.next());
  }

/**
 * This method checks the Iterator<E> next() method to make sure it fails
 * when there is no elements in the list.
 *
 * @see java.util.List<E> 
*/

  public void testIteratorNoneLeft()
  {
    Iterator<String> iterate = sList.iterator();
    try {
      iterate.next();
      fail();
    }
    catch(NoSuchElementException e) {
      assertTrue(true);
    }
  }

/**
 * This method checks the Iterator<E> remove() method to make sure it fails
 * when there is no elements in the list.
 *
 * @see java.util.List<E> 
*/

  public void testIteratorRemove()
  {
    sList.add("A");
    Iterator<String> iterate = sList.iterator();
    try {
      iterate.remove();
      fail();
    }
    catch(IllegalStateException e) { 
      assertTrue(true);
    }
  }

/**
 * This method checks the Iterator<E> remove() method to make sure it fails
 * when remove() is called twice without calling next().
 *
 * @see java.util.List<E> 
*/

  public void testIteratorRemoveDup()
  {
    sList.add("A");
    sList.add("B");
    Iterator<String> iterate = sList.iterator();
    iterate.next();
    iterate.remove();
    try {
      iterate.remove();
      fail();
    }
    catch(IllegalStateException e) { 
      assertTrue(true);
    }
  }

/**
 * This method checks the add() and get() methods of java.util.List to insure
 * that the get() method retrieves the elements that were added in the 
 * correct order. 
 *
 * @see java.util.List<E> 
*/

  public void testAddGet()
  {
    for(int i=1; i<=1000; i++) {
      iList.add(i);
    }
    for(int i=0; i<1000; i++) {
      assertEquals(iList.get(i), new Integer(i+1));
    }  
  }

/**
 * This method checks the add() return type java.util.List. 
 *
 * @see java.util.List<E> 
*/

  public void testAddReturn()
  {
    assertTrue(sList.add("A"));
    assertTrue(sList.add("B"));
    assertTrue(sList.add("C"));
  }
 
/**
 * This method checks that add() adds to the correct index.
 *
 * @see java.util.List<E> 
*/

  public void testAddIndexBegin()
  {
    sList.add(0, "A");
    assertEquals(sList.get(0),"A");
  }

/**
 * This method checks that add() adds to the correct index.
 *
 * @see java.util.List<E> 
*/

  public void testAddIndexEnd()
  {
    sList.add("A");
    sList.add("B");
    sList.add("C");
    sList.add(3, "A");
    assertEquals(sList.get(0),"A");
    assertEquals(sList.get(1),"B");
    assertEquals(sList.get(2),"C");
    assertEquals(sList.get(3),"A");
  }
  
/**
 * This method checks that clear() empties the list
 *
 * @see java.util.List<E> 
*/

  public void testClear()
  {
    sList.add("A");
    sList.add("B");
    sList.clear();
    assertFalse(sList.contains("A"));
    assertFalse(sList.contains("B"));
  }

/**
 * This method checks that contains() correctly returns true if the list
 * contains those elements.
 *
 * @see java.util.List<E> 
*/

  public void testContains()
  {
    sList.add("A");
    sList.add("B");
    assertTrue(sList.contains("A"));
    assertTrue(sList.contains("B"));
  }

/**
 * This method checks the equals() method for an empty list
 *
 * @see java.util.List<E> 
*/

  public void testEqualsEmptyList()
  {
    assertTrue(iList.equals(sList));
  }
  
/**
 * This method checks that the equals() method returns false when the two 
 * lists differ in types.
 *
 * @see java.util.List<E> 
*/

  public void testEqualsDiffLists()
  {
    sList.add("A");
    iList.add(new Integer(1));
    assertFalse(iList.equals(sList));
  }
 
/**
 * This method checks that the equals() method returns true when the two 
 * lists are exactly the same.
 *
 * @see java.util.List<E> 
*/

  public void testEqualsSameList()
  {
    sList.add("A");
    sList.add("B");
    List<String> s2List = sList;
    assertTrue(sList.equals(s2List));
  }
 
/**
 * This method checks that the equals() method returns false when the two 
 * lists differ in size.
 *
 * @see java.util.List<E> 
*/

  public void testEqualsDiffListSize()
  {
    List<String> s2List = new List12<String>();
    sList.add("A");
    sList.add("B");
    sList.add("C");
    s2List.add("A");
    s2List.add("B");
    s2List.add("C");
    s2List.add(null);
    assertFalse(sList.equals(s2List));
  }
 
/**
 * This method checks that the equals() method returns true when the two 
 * lists are the same
 *
 * @see java.util.List<E> 
*/

  public void testEqualsMatch()
  {
    List<String> s2List = new List12<String>();
    sList.add("A");
    sList.add("B");
    sList.add("C");
    s2List.add("A");
    s2List.add("B");
    s2List.add("C");
    assertTrue(sList.equals(s2List));
  }

/**
 * This method checks that the add() method does not go out of bounds. 
 *
 * @see java.util.List<E> 
*/

  public void testGetOutOfBounds()
  {
    sList.add("A");
    sList.add("B");
    sList.add("C");
    try {
      sList.get(-1);
      fail();
    }
    catch(IndexOutOfBoundsException e) {
      assertTrue(true);
    }
    try {
      sList.get(sList.size()+1);
      fail();
    }
    catch(IndexOutOfBoundsException e) {
      assertTrue(true);
    }
  }
 
/**
 * This method checks that the hashCode() method returns the same values for
 * equal lists.
 *
 * @see java.util.List<E> 
*/

  public void testHashCode()
  {
    sList.add("A");
    List<String> dList = sList;
    assertNotNull(sList.hashCode());
    assertEquals(sList.hashCode(), dList.hashCode());
  }
 
/**
 * This method checks that the hashCode() method returns different values for
 * different lists.
 *
 * @see java.util.List<E> 
*/

  public void testHashCodeNotEqualsDiffType()
  {
    sList.add("A");
    sList.add("B");
    iList.add(new Integer(1));
    iList.add(new Integer(2));
    assertFalse(sList.hashCode() == iList.hashCode());
  }

/**
 * This method checks that the hashCode() methods return value and compares it
 * to the actual equation of the hashCode.
 *
 * @see java.util.List<E> 
*/

  public void testHashCodeEquation()
  {
    sList.add("A");
    sList.add("B");
    int hashCode = 1;
    Iterator<String> i = sList.iterator();
    while(i.hasNext()) {
      Object obj = i.next();
      hashCode = 31*hashCode + (obj == null ? 0 : obj.hashCode());
    }
    assertEquals(hashCode, sList.hashCode());
  }

/**
 * This method checks that the equals() methods return value for null elements.
 *
 * @see java.util.List<E> 
*/

  public void testEqualsNullList()
  {
    iList.add(null);
    sList.add(null);
    assertTrue(iList.equals(sList));
  }

/**
 * This method checks that the indexOf() methods to make sure that the element
 * at a certain index is the same.
 *
 * @see java.util.List<E> 
*/

  public void testIndexOf()
  {
    iList.add(0);
    iList.add(1);
    iList.add(2);
    iList.add(3);
    iList.add(4);
    assertTrue(iList.indexOf(4) == 4);
  }
  
/**
 * This method checks the isEmpty() method to make sure that it returns true
 * when the list is empty. 
 *
 * @see java.util.List<E> 
*/

  public void testIsEmpty()
  {
    assertTrue(sList.isEmpty());
  }

/**
 * This method checks the isEmpty() method to make sure that it returns false
 * when the list is not empty.
 *
 * @see java.util.List<E> 
*/

  public void testIsEmpty2()
  {
    sList.add("A");
    assertFalse(sList.isEmpty());
  }

/**
 * This method checks the remove() method to make sure it actually removes
 * the element from the list.
 *
 * @see java.util.List<E> 
*/

  public void testRemove()
  {
    sList.add("A");
    sList.add("B");
    sList.add("C");
    sList.remove("B");
    assertFalse(sList.contains("B"));
    assertEquals(sList.get(1), "C");
    assertEquals(sList.get(0), "A");
  }

/**
 * This method checks the remove() method to make sure it actually removes
 * the first instance of the element from the list and not the others.
 *
 * @see java.util.List<E> 
*/

  public void testRemoveDup()
  {
    iList.add(1);
    iList.add(2);
    iList.add(3);
    iList.add(2);
    iList.remove(new Integer(2));
    assertTrue(iList.contains(2));
    assertEquals(iList.get(0), new Integer(1));
    assertEquals(iList.get(1), new Integer(3));
    assertEquals(iList.get(2), new Integer(2));
  }

/**
 * This method checks the remove() method to make sure it actually removes
 * numbers from the list.
 *
 * @see java.util.List<E> 
*/

/*
  public void testRemoveNum()
  {
    iList.add(1);
    iList.add(2);
    iList.add(3);
    iList.remove(1);
    assertEquals(iList.get(0), new Integer(1));
    assertEquals(iList.get(1), new Integer(3));
  }
*/

/**
 * This method checks the remove() method to make sure it actually removes
 * nothing if the specific element is not in the list.
 *
 * @see java.util.List<E> 
*/

  public void testRemoveNothing()
  {
    iList.add(1);
    iList.add(2);
    iList.add(3);
    iList.remove(new Integer(4));
    assertEquals(iList.get(0), new Integer(1));
    assertEquals(iList.get(1), new Integer(2));
    assertEquals(iList.get(2), new Integer(3));
  }

/**
 * This method checks to make sure that the remove() method cannot reach
 * an index that out of bounds.
 *
 * @see java.util.List<E> 
*/
/*
  public void testRemoveNeg()
  {
    try {
      sList.remove(0);
      fail();
    }
    catch(IndexOutOfBoundsException e) {
      assertTrue(true);
    }
  }
*/
/**
 * This method checks to make sure that the set() method actually changes
 * the value of an element at a certain location
 *
 * @see java.util.List<E> 
*/

  public void testSet()
  {
    iList.add(1);
    iList.add(2);
    iList.set(0, new Integer(-12));
    assertEquals(iList.get(0), new Integer(-12));
    assertEquals(iList.get(1), new Integer(2));
  }

/**
 * This method checks to make sure that the set() method does not go out of
 * bounds.
 *
 * @see java.util.List<E> 
*/

  public void testSetOutOfBounds()
  {
    try {
      sList.set(-1, "A");
      fail();
    }
    catch(IndexOutOfBoundsException e) {
      assertTrue(true);
    }
  }

/**
 * This method checks to make sure that the add() method does not go out of
 * bounds.
 *
 * @see java.util.List<E> 
*/

  public void testAddOutOfBounds()
  {
    try {
      sList.add(-1, "A");
      fail();
    }
    catch(IndexOutOfBoundsException e) {
      assertTrue(true);
    }
  }

/**
 * This method checks to make sure that the size() method returns a size of 0
 * if the size is actually 0.
 *
 * @see java.util.List<E> 
*/

  public void testSizeNone()
  {
    assertEquals(sList.size(), 0);
  }

/**
 * This method checks to make sure that the size() method returns a size of 5
 * if the size is actually 5.
 *
 * @see java.util.List<E> 
*/

  public void testSizePos()
  {
    iList.add(1);
    iList.add(2);
    iList.add(3);
    iList.add(4);
    iList.add(5);
    assertEquals(iList.size(), 5);
  }

/**
 * This method checks to make sure that the add() method does not add out of 
 * bounds.
 *
 * @see java.util.List<E> 
*/

  public void testSizeOutOfBounds()
  {
    try {
      sList.add(1, "A");
      fail();
    }
    catch(IndexOutOfBoundsException e) {
      assertTrue(true);
    }
  }
 
/**
 * This method sets the initial condition before a JUnit test method. 
*/

  public void setUp()
  {
    sList = new List12<String>();
    iList = new List12<Integer>();
  }
 
/**
 * This method sets the post condition after a JUnit test method. 
*/
  public void tearDown()
  {
    sList = null;
    iList = null;
  }
}
