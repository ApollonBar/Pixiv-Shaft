package ceui.lisa.fragments;

import androidx.recyclerview.widget.LinearLayoutManager;

import ceui.lisa.R;
import ceui.lisa.adapters.CommentAdapter;
import ceui.lisa.http.Retro;
import ceui.lisa.model.CommentsBean;
import ceui.lisa.model.IllustCommentsResponse;
import io.reactivex.Observable;

import static ceui.lisa.activities.Shaft.sUserModel;

public class FragmentComment extends BaseListFragment<IllustCommentsResponse, CommentAdapter, CommentsBean> {

    private int illustID;
    private String title;

    public static FragmentComment newInstance(int id, String title) {
        FragmentComment comment = new FragmentComment();
        comment.illustID = id;
        comment.title = title;
        return comment;
    }

    @Override
    void initLayout() {
        mLayoutID = R.layout.fragment_illust_list;
    }

    @Override
    void initRecyclerView() {
        super.initRecyclerView();
        LinearLayoutManager manager = new LinearLayoutManager(mContext);
        mRecyclerView.setLayoutManager(manager);
    }

    @Override
    String getToolbarTitle() {
        return title + "的评论";
    }

    @Override
    Observable<IllustCommentsResponse> initApi() {
        return Retro.getAppApi().getComment(sUserModel.getResponse().getAccess_token(), illustID);
    }

    @Override
    Observable<IllustCommentsResponse> initNextApi() {
        return Retro.getAppApi().getNextComment(sUserModel.getResponse().getAccess_token(), nextUrl);
    }

    @Override
    void initAdapter() {
        mAdapter = new CommentAdapter(allItems, mContext);
    }
}
