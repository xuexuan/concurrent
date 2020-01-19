package com.test.concurrent.notifywait;

public class OddEvenPrinter {
	private int _count = 1;
	//private AtomicInteger _ai;// = new AtomicInteger(1);
	
	public synchronized void PrintOdd()
	{
		while (_count < 10)
		{
			while (_count % 2 == 0)
			{
				try {
					
					wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			System.out.println(_count);
			_count += 1;
			notify();
		}
	}
	
	public synchronized void PrintEven()
	{
		while (_count < 10)
		{
			while (_count % 2 != 0)
			{
				try {
					wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			System.out.println(_count);
			_count += 1;
			notify();
		}
	}
}
