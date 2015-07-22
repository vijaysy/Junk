package com.codecraft.mcs.network;


public interface APIObserver {
    public void onAPIResponse(boolean success, String response, int responseCode, int apiIndex);
}
