package com.test.concurrent.notifywait;

import java.util.concurrent.atomic.AtomicInteger;

public class SequenceList implements Runnable{

	OddEvenPrinter oep;
	//private AtomicInteger _ai;// = new AtomicInteger(1);
	private Boolean _isOdd;
	
	public SequenceList(/*AtomicInteger ai_,*/OddEvenPrinter oep_,  Boolean isOdd_)
	{
		//_ai = ai_;
		_isOdd = isOdd_;
		oep = oep_;
	}
	
	public void run() 
	{
		 if (_isOdd)
		 {
			 oep.PrintOdd();
		 }
		 else
		 {
			 oep.PrintEven();
		 }
	}
}

