package com.example.restqr.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.restqr.Helper.ManagementCart;
import com.example.restqr.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class CartListActivity extends AppCompatActivity {
    private RecyclerView.Adapter adapter;
    private RecyclerView recyclerViewCartList;
    private ManagementCart managementCart;
    TextView totalFeeTxt, tipsTxt, taxTxt, totalTxt, emptyTxt;
    private double tax;
    private ScrollView scrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_list);

        managementCart = new ManagementCart(this);
        initView();
        // initList();
        CalculateCart();
        bottomNavigation();
    }

    private void bottomNavigation() {
        FloatingActionButton floatingActionButton = findViewById(R.id.cartBtn2);
        LinearLayout homeBtn = findViewById(R.id.homeBtn2);

        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CartListActivity.this, MainActivity.class));
            }
        });

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CartListActivity.this, CartListActivity.class));
            }
        });
    }



    private void initView() {
        recyclerViewCartList=findViewById(R.id.cartView);  // MAYBE ERROR
        totalFeeTxt=findViewById(R.id.totalFeeTxt);
        tipsTxt =findViewById(R.id.tipsTxt);
        taxTxt=findViewById(R.id.taxTxt);
        totalTxt=findViewById(R.id.totalTxt);
        emptyTxt=findViewById(R.id.emptyTxt);
        scrollView=findViewById(R.id.scrollView3);
    }



    // @ToDo THIS !!!
    /*
    private void initList(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerViewCartList.setLayoutManager(linearLayoutManager);
        adapter = new CartListAdapter(managementCart.getListCart(), this, new ChangeQuantityListener() {
            @Override
            public void changed() {  }  // CalculateCart();
        });
    }
     */




    private void CalculateCart(){
        double percentTax = 0.02;

        tax = Math.round(managementCart.getTotalPrice() * percentTax);

        // @ToDo Fix long double values
        double itemsCost = Math.round(managementCart.getTotalPrice());
        double tips = Math.round(itemsCost * 0.1);
        double overallCost = itemsCost + tax + tips;  // managementCart.getTotalPrice() + tax + tips

        totalFeeTxt.setText("$"+ itemsCost);
        taxTxt.setText("$"+ tax);
        tipsTxt.setText("$"+ tips);
        totalTxt.setText("$" + overallCost);
    }
}