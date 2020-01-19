package com.test.concurrent.lockfree;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.LockSupport;

public class NonBlockStack<T> 
{
	private AtomicReference<StackNode<T>> head = new AtomicReference<>();
	private AtomicInteger counter = new AtomicInteger(1);
	
	public void push(T value_)
	{
		StackNode<T> s = new StackNode<T>(value_);
		while (true)
		{
			StackNode<T> currentNode = head.get();
			s._next = currentNode;
			if (head.compareAndSet(currentNode, s))
			{
				break;
			}
			else
			{
				LockSupport.parkNanos(1);
			}
		}
		counter.incrementAndGet();
	}
	
	public T pop()
	{
		StackNode<T> curr = head.get();
		while (curr != null)
		{
			StackNode<T> next = curr._next;
			if (head.compareAndSet(curr, next))
			{
				break;
			}
			else
			{
				LockSupport.parkNanos(1);
			}
		}
		counter.incrementAndGet();
		return curr != null ? curr._node : null;
	}
	
	public int GetCounter()
	{
		return counter.get();
	}
}


class StackNode<T>
{
	public T _node;
	public StackNode<T> _next;
	
	public StackNode(T value_)
	{
		this._node = value_;
		this._next = null;
	}
}