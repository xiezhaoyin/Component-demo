package com.xzydonate.basesdk.util;

public interface UrLRouter {

    String APP = "/app";
    String NEWS = "/news";
    String PICTURE = "/picture";
    String VIDEO = "/video";

    String ACT = "Act";
    String FRAG = "Frag";

    String APP_HOME = APP + "/home" + ACT;

    String PICTURE_FRAG = PICTURE + "/picture" + FRAG;
    String PICTURE_PAGE1_FRAG = PICTURE + "/picturePage1" + FRAG;
    String PICTURE_PAGE2_FRAG = PICTURE + "/picturePage2" + FRAG;
    String PICTURE_PAGE3_FRAG = PICTURE + "/picturePage3" + FRAG;
    String PICTURE_PAGE_INFO_ACT = PICTURE + "/picturePageInfo" + ACT;


}
