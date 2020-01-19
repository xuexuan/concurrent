package com.test.concurrent.notifywait;

import java.util.concurrent.atomic.AtomicInteger;

public class SequencePrinter {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//System.out.println("helloworld");
		//AtomicInteger ai = new AtomicInteger(1);
		OddEvenPrinter oep = new OddEvenPrinter();
		Thread t1 = new Thread(new SequenceList(oep, true));
		Thread t2 = new Thread(new SequenceList(oep, false));
		t1.start();
		t2.start();
		
		try {
			t1.join();
			t2.join();
			System.out.println("end");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
