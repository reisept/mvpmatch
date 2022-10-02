package com.riscovirtual.mvpmatch;

public class Utilities {

	public static boolean isCoinValueValid(int value) {
		boolean ret = false;

		switch (value) {

		case 5:
		case 10:
		case 20:
		case 50:
		case 100:

			ret = true;
			break;

		default:
			ret = false;
			break;
		}

		return ret;
	}

}
