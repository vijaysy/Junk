package vsy.example.followme;

public class StaticClass {

	private static String nums[],addr;
	private static double Slat ,Slan;

	public static String[] getNums() {
		return nums;
	}

	public static void setNums(String nums[]) {
		StaticClass.nums = nums;
	}

	public static double getSlat() {
		return Slat;
	}

	public static void setSlat(double slat) {
		Slat = slat;
	}

	public static double getSlan() {
		return Slan;
	}

	public static void setSlan(double slan) {
		Slan = slan;
	}

	public static String getAddr() {
		return addr;
	}

	public static void setAddr(String addr) {
		StaticClass.addr = addr;
	}
	
}
