package com.test.concurrent.lockfree;

public class BlockStack<T> {
	private StackNode<T> head;
	private Integer counter = 0;
	
	public synchronized void push(T value_)
	{
		StackNode<T> newnode = new StackNode<>(value_);
		newnode._next = head;
		head = newnode;
		counter++;
	}
	
	public synchronized T pop()
	{
		if (head == null)
		{
			counter++;
			return null;
		}
		
		StackNode<T> curr = head;
		head = curr._next;
		counter++;
		return curr._node;
	}
	
	public Integer GetCounter()
	{
		return counter;
	}
}
