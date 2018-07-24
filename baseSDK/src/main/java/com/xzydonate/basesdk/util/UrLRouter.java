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

    String NEWS_FRAG = NEWS + "/news" + FRAG;
    String NEWS_PAGE1_FRAG = NEWS + "/newsPage1" + FRAG;
    String NEWS_PAGE2_FRAG = NEWS + "/newsPage2" + FRAG;
    String NEWS_PAGE3_FRAG = NEWS + "/newsPage3" + FRAG;
    String NEWS_PAGE_INFO_ACT = NEWS + "/newsPageInfo" + ACT;

    String VIDEO_FRAG = VIDEO + "/video" + FRAG;
    String VIDEO_PAGE1_FRAG = VIDEO + "/videoPage1" + FRAG;
    String VIDEO_PAGE2_FRAG = VIDEO + "/videoPage2" + FRAG;
    String VIDEO_PAGE3_FRAG = VIDEO + "/videoPage3" + FRAG;
    String VIDEO_PAGE_INFO_ACT = VIDEO + "/videoPageInfo" + ACT;


}
