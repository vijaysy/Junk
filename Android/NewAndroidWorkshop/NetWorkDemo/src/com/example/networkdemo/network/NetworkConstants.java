package com.example.networkdemo.network;

public class NetworkConstants {

	public static final String BASE_URL = "http://192.168.224.46";// dev

	public static enum Communicate {
		GET_DEMO(1, "and.php"),
		GET_DEMO2(2, "and2.php"),
		UNKNOWN (-1,"");

		private int mApiIndex;
		private String mUrl;

		Communicate(int apiIndex, String url) {
			this.mApiIndex = apiIndex;
			this.mUrl = url;
		}

		public int apiIndex() {
			return mApiIndex;
		}

		public String url() {
			return mUrl;
		}

		public static Communicate getFromApiIndex(int apiIndex) {

			for (Communicate c : Communicate.values()) {
				if (apiIndex == c.apiIndex()) {
					return c;
				}

			}
			return UNKNOWN;

		}
	}

}
