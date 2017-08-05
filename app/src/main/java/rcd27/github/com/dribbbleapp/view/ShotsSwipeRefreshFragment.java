package rcd27.github.com.dribbbleapp.view;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.List;

import rcd27.github.com.dribbbleapp.R;
import rcd27.github.com.dribbbleapp.model.ShotVisualObject;
import rcd27.github.com.dribbbleapp.presenter.Presenter;
import rcd27.github.com.dribbbleapp.presenter.ShotsFragmentPresenter;

public class ShotsSwipeRefreshFragment extends Fragment
        implements rcd27.github.com.dribbbleapp.view.View {

    private SwipeRefreshLayout swipeRefreshLayout;

    private ListView shotsListView;

    //TODO FIXME https://habrahabr.ru/post/334710/
    private ShotListAdapter listAdapter;

    private Presenter presenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        presenter = new ShotsFragmentPresenter(this);
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.swipe_fragment, container, false);
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swiperefresh);

        shotsListView = (ListView) view.findViewById(R.id.shot_list);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        listAdapter = new ShotListAdapter(getContext());
        shotsListView.setAdapter(listAdapter);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.updateActual();
            }
        });

        presenter.updateActual();
    }

    @Override
    public void update(@NonNull List<ShotVisualObject> shots) {
        listAdapter.clear();
        for (ShotVisualObject shot : shots) {
            listAdapter.add(shot);
            listAdapter.notifyDataSetChanged();
        }
        swipeRefreshLayout.setRefreshing(false);
    }
}
