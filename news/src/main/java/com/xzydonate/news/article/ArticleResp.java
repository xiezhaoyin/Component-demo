package com.xzydonate.news.article;

import java.util.List;

public class ArticleResp {

    private int curPage;
    private int offset;
    private boolean over;
    private int pageCount;
    private int size;
    private int total;
    private List<Article> datas;

    public int getCurPage() {
        return curPage;
    }

    public void setCurPage(int curPage) {
        this.curPage = curPage;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public boolean isOver() {
        return over;
    }

    public void setOver(boolean over) {
        this.over = over;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<Article> getDatas() {
        return datas;
    }

    public void setDatas(List<Article> datas) {
        this.datas = datas;
    }

    public static class Article {
        public String apkLink;
        public String author;
        public int chapterId;
        public String chapterName;
        public boolean collect;
        public int courseId;
        public String desc;
        public String envelopePic;
        public boolean fresh;
        public int id;
        public String link;
        public String niceDate;
        public String origin;
        public String projectLink;
        public String publishTime;
        public int superChapterId;
        public String superChapterName;
        public String title;
        public int type;
        public int userId;
        public int visible;
        public int zan;
        private List<NewsInfoTag> tags;

        public List<NewsInfoTag> getTags() {
            return tags;
        }

        public void setTags(List<NewsInfoTag> tags) {
            this.tags = tags;
        }

        @Override
        public String toString() {
            return "Article{" +
                    "apkLink='" + apkLink + '\'' +
                    ", author='" + author + '\'' +
                    ", chapterId=" + chapterId +
                    ", chapterName='" + chapterName + '\'' +
                    ", collect=" + collect +
                    ", courseId=" + courseId +
                    ", desc='" + desc + '\'' +
                    ", envelopePic='" + envelopePic + '\'' +
                    ", fresh=" + fresh +
                    ", id=" + id +
                    ", link='" + link + '\'' +
                    ", niceDate='" + niceDate + '\'' +
                    ", origin='" + origin + '\'' +
                    ", projectLink='" + projectLink + '\'' +
                    ", publishTime='" + publishTime + '\'' +
                    ", superChapterId=" + superChapterId +
                    ", superChapterName='" + superChapterName + '\'' +
                    ", tags=" + tags +
                    '}';
        }

        public static class NewsInfoTag {
            public String name;
            public String url;
        }
    }
}
