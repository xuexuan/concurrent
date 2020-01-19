package com.test.concurrent.lockfree;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

//the code has problem, normally blockstack should get less than non-block stack in the same period.
//here is contrast.
public class DataPrinter {
	public static void main(String[] args) throws InterruptedException {
		//5seconds to get 5,467,823 in the stack, 
		NonBlockStack<Integer> stack = new NonBlockStack<Integer>();
		//5seconds to get 70,591,123 in the stack
		//BlockStack<Integer> stack = new BlockStack<Integer>();
		Random random = new Random();
		for (int i = 0; i < 10000; ++i)
		{
			stack.push(random.nextInt());
		}
		
		List<Thread> list = new ArrayList<>();
		int pushThreadNum = 2;
		int popThreadNum = 2;
		
		for(int i = 0; i < pushThreadNum; ++i)
		{
			Thread t = new Thread(() ->  {
				while(true)
				{
					stack.push(random.nextInt());
				}
			});
			
			t.setDaemon(true);
			list.add(t);
		}
		
		for(int i = 0; i < popThreadNum; ++i)
		{
			Thread t = new Thread(() ->  {
				while(true)
				{
					stack.pop();
				}
			});
			
			t.setDaemon(true);
			list.add(t);
		}
		
		for (Thread t: list)
		{
			t.start();
		}
		
		Thread.sleep(10000);
		System.out.println(String.format("%,d in the stack", stack.GetCounter()));
	}
}
