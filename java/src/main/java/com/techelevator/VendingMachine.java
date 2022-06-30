package com.techelevator;

import com.techelevator.application.VendingMachineApplication;

import java.io.FileNotFoundException;

public class VendingMachine {

	public static void main(String[] args) throws FileNotFoundException {
		VendingMachineApplication application = new VendingMachineApplication();
		application.run();
	}
}
