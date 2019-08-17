package com.xzydonate.news.article;

import java.util.List;

public class ArticleTreeResp {

    private int courseId;
    private int id;
    private String name;
    private int order;
    private int parentChapterId;
    private boolean userControlSetTop;
    private int visible;
    private List<Children> children;

    public static class Children {

        public int courseId;
        public int id;
        public String name;
        public int order;
        public int parentChapterId;
        public boolean userControlSetTop;
        public int visible;
        private List<?> children;

        public List<?> getChildren() {
            return children;
        }

        public void setChildren(List<?> children) {
            this.children = children;
        }

        @Override
        public String toString() {
            return "Children{" +
                    "courseId=" + courseId +
                    ", id=" + id +
                    ", name='" + name + '\'' +
                    ", order=" + order +
                    ", parentChapterId=" + parentChapterId +
                    ", userControlSetTop=" + userControlSetTop +
                    ", visible=" + visible +
                    ", children=" + children +
                    '}';
        }
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public int getParentChapterId() {
        return parentChapterId;
    }

    public void setParentChapterId(int parentChapterId) {
        this.parentChapterId = parentChapterId;
    }

    public boolean isUserControlSetTop() {
        return userControlSetTop;
    }

    public void setUserControlSetTop(boolean userControlSetTop) {
        this.userControlSetTop = userControlSetTop;
    }

    public int getVisible() {
        return visible;
    }

    public void setVisible(int visible) {
        this.visible = visible;
    }

    public List<Children> getChildren() {
        return children;
    }

    public void setChildren(List<Children> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return "ArticleTreeResp{" +
                "courseId=" + courseId +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", order=" + order +
                ", parentChapterId=" + parentChapterId +
                ", userControlSetTop=" + userControlSetTop +
                ", visible=" + visible +
                ", children=" + children +
                '}';
    }
}
