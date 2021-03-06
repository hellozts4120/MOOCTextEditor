package textgen;

import java.util.AbstractList;


/** A class that implements a doubly linked list
 * 
 * @author UC San Diego Intermediate Programming MOOC team
 *
 * @param <E> The type of the elements stored in the list
 */
public class MyLinkedList<E> extends AbstractList<E> {
	LLNode<E> head;
	LLNode<E> tail;
	int size;

	/** Create a new empty LinkedList */
	public MyLinkedList() {
		// TODO: Implement this method
		head = new LLNode<E>(null);
		tail = new LLNode<E>(null);
		head.next = tail;
		tail.prev = head;
	}

	/**
	 * Appends an element to the end of the list
	 * @param element The element to add
	 */
	public boolean add(E element ) 
	{
		// TODO: Implement this method
		add(size, element);
		return true;
	}

	/** Get the element at position index 
	 * @throws IndexOutOfBoundsException if the index is out of bounds. */
	public E get(int index) 
	{
		// TODO: Implement this method.
		LLNode<E> temp = new LLNode<E>(null);
		if(index < 0){
			throw new IndexOutOfBoundsException("Provided index is lesser than 0");
		}
		else if(index >= size){
			throw new IndexOutOfBoundsException("Provided index is larger than the number of elements in the List");
		}
		else{
			temp = head;
			for(int i = 0; i <= index; i++){
				temp = temp.next;
				if(temp == null){
					throw new NullPointerException("Provided index is wrong, no such index!");
				}
			}
		}
		return temp.data;
	}

	/**
	 * Add an element to the list at the specified index
	 * @param The index where the element should be added
	 * @param element The element to add
	 */
	public void add(int index, E element ) 
	{
		// TODO: Implement this method
		if(index < 0){
			throw new IndexOutOfBoundsException("Provided index is lesser than 0");
		}
		else if(index > size){
			throw new IndexOutOfBoundsException("Provided index is larger than the number of elements in the List");
		}
		else if(element == null){
			throw new NullPointerException("Provided element value is null");
		}
		else{
			LLNode<E> temp = head;
			for(int i = 0; i < index; i++){
				temp = temp.next;
				if(temp == null){
					throw new NullPointerException("Provided index is wrong, no such index!");
				}
			}
			LLNode<E> newNode = new LLNode(element, temp);
			size++;
		}
	}


	/** Return the size of the list */
	public int size() 
	{
		// TODO: Implement this method
		return size;
	}

	/** Remove a node at the specified index and return its data element.
	 * @param index The index of the element to remove
	 * @return The data element removed
	 * @throws IndexOutOfBoundsException If index is outside the bounds of the list
	 * 
	 */
	public E remove(int index) 
	{
		// TODO: Implement this method
		if(index < 0){
			throw new IndexOutOfBoundsException("Provided index is lesser than 0");
		}
		else if(index > size){
			throw new IndexOutOfBoundsException("Provided index is larger than the number of elements in the List");
		}
		else{
			LLNode<E> temp = head;
			for(int i = 0; i <= index; i++){
				temp = temp.next;
				if(temp == null){
					throw new NullPointerException("Provided index is wrong, no such index!");
				}
			}
			if(temp == null){
				throw new NullPointerException("Provided index is wrong, no such index!");
			}
			E ReturnData = temp.data;
			temp.prev.next = temp.next;
			temp.next.prev = temp.prev;
			size--;
			return ReturnData;
		}
	}

	/**
	 * Set an index position in the list to a new element
	 * @param index The index of the element to change
	 * @param element The new element
	 * @return The element that was replaced
	 * @throws IndexOutOfBoundsException if the index is out of bounds.
	 */
	public E set(int index, E element) 
	{
		// TODO: Implement this method
		if(index < 0){
			throw new IndexOutOfBoundsException("Provided index is lesser than 0");
		}
		else if(index > size){
			throw new IndexOutOfBoundsException("Provided index is larger than the number of elements in the List");
		}
		else if(element == null){
			throw new NullPointerException("Provided element value is null");
		}
		else{
			LLNode<E> temp = head;
			for(int i = 0; i < index; i++){
				temp = temp.next;
				if(temp == null){
					throw new NullPointerException("Provided index is wrong, no such index!");
				}
			}
			E ReturnData = temp.next.data;
			temp.next.data = element;
			return ReturnData;
		}
	}   
}

class LLNode<E> 
{
	LLNode<E> prev;
	LLNode<E> next;
	E data;

	// TODO: Add any other methods you think are useful here
	// E.g. you might want to add another constructor

	public LLNode(E e) 
	{
		this.data = e;
		this.prev = null;
		this.next = null;
	}

	public LLNode(E e, LLNode<E> temp){
		this(e);
		this.prev = temp;
		this.next = temp.next;
		temp.next.prev = this;
		temp.next = this;
	}
}
