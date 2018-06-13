package com.cjj.buttomsheetdemo;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by cjj on 2016/2/24.
 */
public class RecyclerViewActivity extends AppCompatActivity {

    private static final String shareStr[] = {
            "微信","QQ","空间","微博","GitHub","CJJ测试\nRecyclerView自适应","微信朋友圈","短信","推特","遇见微信","QQ","空间","微博","GitHub"
    };

    private ArrayList<ShoppingDetail> namelist = new ArrayList<>();
    private int number;

    public BottomSheetBehavior behavior;
    public RecyclerView recyclerView,listview;
    public View blackView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acitivity_rv);

        int a = 1;

        for (String name: shareStr) {
            ShoppingDetail detail = new ShoppingDetail();
            detail.setName(name);
            detail.setNumber(a++);
            namelist.add(detail);
        }

        // The View with the BottomSheetBehavior
        CoordinatorLayout coordinatorLayout = (CoordinatorLayout) findViewById(R.id.cl);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        final ShoppingCarListAdapter shoppingCarListAdapter = new ShoppingCarListAdapter(this, namelist);
        shoppingCarListAdapter.setRemoveClickListener(new ShoppingCarListAdapter.RemoveClickListener() {
            @Override
            public void onItemClick(int pos) {
                number = namelist.get(pos).getNumber();
                if (number > 1) {
                    namelist.get(pos).setNumber(--number);
                    shoppingCarListAdapter.notifyItemChanged(pos);
                }
                else {
                    namelist.remove(pos);
                    shoppingCarListAdapter.notifyDataSetChanged();
                }
            }
        });
        shoppingCarListAdapter.setAddClickListener(new ShoppingCarListAdapter.AddClickListener() {
            @Override
            public void onItemClick(int pos) {
                number = namelist.get(pos).getNumber();
                if (number < 30) {
                    namelist.get(pos).setNumber(++number);
                    shoppingCarListAdapter.notifyItemChanged(pos);
                }

            }
        });
        recyclerView.setAdapter(shoppingCarListAdapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        behavior = BottomSheetBehavior.from(recyclerView);

        behavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                if(newState == BottomSheetBehavior.STATE_COLLAPSED||newState == BottomSheetBehavior.STATE_HIDDEN){
//                    blackView.setBackgroundColor(Color.TRANSPARENT);
                    blackView.setVisibility(View.GONE);
                }
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {
                // React to dragging events
                blackView.setVisibility(View.VISIBLE);
                ViewCompat.setAlpha(blackView,slideOffset);
            }
        });

        findViewById(R.id.tv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (behavior.getState() == BottomSheetBehavior.STATE_COLLAPSED) {
                    behavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                } else {
                    behavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }
            }
        });

        listview = (RecyclerView) findViewById(R.id.listview);
        listview.setLayoutManager(new LinearLayoutManager(this));

        SimpleStringRecyclerViewAdapter simpleStringRecyclerViewAdapter = new SimpleStringRecyclerViewAdapter(this, namelist);
        simpleStringRecyclerViewAdapter.setItemClickListener(new SimpleStringRecyclerViewAdapter.ItemClickListener() {
            @Override
            public void onItemClick(int pos) {
                Toast.makeText(RecyclerViewActivity.this, "pos--->" + pos, Toast.LENGTH_LONG).show();
            }
        });
        listview.setAdapter(simpleStringRecyclerViewAdapter);
        listview.setItemAnimator(new DefaultItemAnimator());

        blackView = findViewById(R.id.blackview);
        blackView.setBackgroundColor(Color.parseColor("#60000000"));
        blackView.setVisibility(View.GONE);
        blackView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                behavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
            }
        });


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }



    private List<String> getRandomSublist(String[] array, int amount) {
        ArrayList<String> list = new ArrayList<>(amount);
        Random random = new Random();
        while (list.size() < amount) {
            list.add(array[random.nextInt(array.length)]);
        }
        return list;
    }


}
