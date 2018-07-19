package com.xzydonate.picture.page1;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.widget.ImageView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.bumptech.glide.Glide;
import com.xzydonate.basesdk.activity.BaseEventFragment;
import com.xzydonate.basesdk.adapter.recyclerAdapter.BaseQuickAdapter;
import com.xzydonate.basesdk.adapter.recyclerAdapter.BaseViewHolder;
import com.xzydonate.basesdk.util.UrLRouter;
import com.xzydonate.picture.IPictureView;
import com.xzydonate.picture.PicturePresenter;
import com.xzydonate.picture.PictureResp;
import com.xzydonate.picture.R;
import com.xzydonate.picture.R2;

import java.util.List;

import butterknife.BindView;

@Route(path = UrLRouter.PICTURE_PICTURE_PAGE1_FRAG)
public class PicturePage1Fragment extends BaseEventFragment implements IPictureView {
    @BindView(R2.id.recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R2.id.swipeRFLayout)
    SwipeRefreshLayout mSwipeRFLayout;

    private PicturePresenter picturePresenter = null;
    private BaseQuickAdapter adapter = null;

    @Override
    public int createView(Bundle savedInstanceState) {
        return R.layout.fragment_picture_page1;
    }

    @Override
    public void initView() {
        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

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
        Log.d("xzy","loadSuccess " );
        if (data.size() > 0) {

            adapter = new BaseQuickAdapter<PictureResp, BaseViewHolder>(R.layout.page1_recycler_item, data) {

                @Override
                protected void convert(BaseViewHolder helper, PictureResp item) {
                    helper.setText(R.id.tv, item.getDesc());
                    Glide.with(getContext()).load(item.getUrl()).into((ImageView) helper.getView(R.id.iv));
                }
            };

            adapter.setEnableLoadMore(true);
            mRecyclerView.setAdapter(adapter);

        }
    }

    @Override
    public void loadFail(String errCode, String errMsg) {
        Log.d("xzy","loadFail " );
    }

    @Override
    public void loadMoreSuccess(List<PictureResp> data) {

    }

    @Override
    public void loadMoreFail(String errCode, String errMsg) {

    }
}
