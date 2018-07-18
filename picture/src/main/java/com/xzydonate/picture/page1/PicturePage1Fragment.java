package com.xzydonate.picture.page1;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.widget.BaseAdapter;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.xzydonate.base.activity.BaseEventFragment;

import com.xzydonate.base.util.UriRouter;
import com.xzydonate.picture.IPictureView;
import com.xzydonate.picture.PicturePresenter;
import com.xzydonate.picture.PictureResp;
import com.xzydonate.picture.R;
import com.xzydonate.picture.R2;


import java.util.List;

import butterknife.BindView;
@Route(path = UriRouter.PICTURE_PICTURE_PAGE1_FRAG)
public class PicturePage1Fragment extends BaseEventFragment implements IPictureView {
    @BindView(R2.id.recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R2.id.swipeRFLayout)
    SwipeRefreshLayout mSwipeRFLayout;

    private PicturePresenter picturePresenter = null;
    private BaseAdapter adapter = null;
    @Override
    public int createView(Bundle savedInstanceState) {
        return R.layout.fragment_picture_page1;
    }

    @Override
    public void initView() {
         picturePresenter = new PicturePresenter();
         picturePresenter.createPresenter(this);
         picturePresenter.loadPictures();
    }

    @Override
    public void resumeView() {

    }

    @Override
    public void destroyView() {

    }

    @Override
    public void onReceive(boolean isSticky, String eventType, Object event) {

    }

    @Override
    public void loadSuccess(List<PictureResp> data) {
//          adapter = new BaseQuickAdapter<>
    }

    @Override
    public void loadFail(String errCode, String errMsg) {

    }

    @Override
    public void loadMoreSuccess(List<PictureResp> data) {

    }

    @Override
    public void loadMoreFail(String errCode, String errMsg) {

    }
}
