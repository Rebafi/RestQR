package com.example.restqr.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.restqr.Adaptor.CartListAdapter;
import com.example.restqr.Helper.ManagementCart;
import com.example.restqr.Interface.ChangeQuantityListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.example.restqr.R;

public class CartListActivity extends AppCompatActivity {
    private RecyclerView.Adapter adapter;
    private RecyclerView recyclerViewList;
    private ManagementCart managementCart;
    TextView  totalFeeTxt, deliveryTxt, taxTxt, totalTxt, emptyTxt;
    private double tax;
    private ScrollView scrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_list);

        managementCart = new ManagementCart(this);
        initView();
        initList();
        CalculateCart();
        bottomNavigation();
    }

    private void bottomNavigation(){
        FloatingActionButton floatingActionButton = findViewById(R.id.cartBtn);
        LinearLayout homeBtn = findViewById(R.id.homeBtn);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CartListActivity.this, CartListActivity.class));
            }
        });

        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CartListActivity.this, MainActivity.class));
            }
        });
    }

    private void initView(){
        recyclerViewList=findViewById(R.id.recyclerView);
        totalFeeTxt=findViewById(R.id.totalFeeTxt);
        deliveryTxt=findViewById(R.id.deliveryTxt);
        taxTxt=findViewById(R.id.taxTxt);
        totalTxt=findViewById(R.id.totalTxt);
        emptyTxt=findViewById(R.id.emptyTxt);
        scrollView=findViewById(R.id.scrollView3);
        recyclerViewList=findViewById(R.id.cartView);
    }

    private  void initList(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false);
        recyclerViewList.setLayoutManager(linearLayoutManager);
        adapter = new CartListAdapter(managementCart.getListCart(), this, new ChangeQuantityListener() {
            @Override
            public void changed(){
                CalculateCart();
            }
        });
    }

    private void CalculateCart(){
        double percentTax = 0.02;
        double delivery = 10;

        tax= Math.round((managementCart.getTotalPrice() * percentTax) * 100) / 100;
        double itemsCost = Math.round(managementCart.getTotalPrice() * 100) / 100;
        double overallCost = Math.round((managementCart.getTotalPrice() + tax + delivery) * 100) / 100;

        totalFeeTxt.setText("$"+ itemsCost);
        taxTxt.setText("$"+ tax);
        deliveryTxt.setText("$"+ delivery);
        totalTxt.setText("$" + overallCost);
    }
}