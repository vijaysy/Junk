package com.codecraft.mcs.network;

public class NetworkConstants {

    public static final String BASE_URL = "http://private-7c432-incident3.apiary-mock.com";//dev
  //public static final String BASE_URL = "http://private-720e6-production1.apiary-mock.com"; //production

    public static final int UPLOAD_API_INDEX_START = 10000;
    public static final int UPLOAD_PHOTO_API_INDEX_START = 20000;


    public static enum Communicate {
        GET_INCIDENTS               (1, "getIncidents"),
        GET_USERNAMES               (2, "getUserNames"),
        GET_TEMPLATES               (3, "getTemplates"),
        GET_WARDS                   (4, "getWards"),
        GET_SOURCES                 (5, "getSources"),
        GET_SOURCE_ACTION_PLANS     (6, "getSourceActionPlans"),
        GET_SOURCE_ACTION_REVIEWS   (7, "getSourceActionReviews"),
        GET_INCIDENT_REVISITS       (8, "getIncidentRevisits"),

        SEND_SELFIE_NAME            (-1, "sendSelfieName"),
        UPLOAD_PHOTO                (-2, "uploadPhoto"),
        SEND_SUPERVISOR_NAME_PASSWORD (-3,"sendSupervisorNameAndPassword"),
        UNKNOWN                     (-1000, "unknown");

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
