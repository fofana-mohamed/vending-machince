package com.techelevator;

import com.techelevator.application.VendingMachineApplication;

import java.io.FileNotFoundException;
import java.io.IOException;

public class VendingMachine {

	public static void main(String[] args) throws IOException {
		VendingMachineApplication application = new VendingMachineApplication();
		application.run();
	}
}
