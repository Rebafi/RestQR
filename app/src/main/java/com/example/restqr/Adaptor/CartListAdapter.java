package com.example.restqr.Adaptor;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.restqr.Activity.CartListActivity;
import com.example.restqr.Domain.FoodDomain;
import com.example.restqr.Helper.ManagementCart;
import com.example.restqr.Interface.ChangeQuantityListener;
import com.example.restqr.R;

import java.util.ArrayList;

public class CartListAdapter extends RecyclerView.Adapter<CartListAdapter.ViewHolder> {
    private ArrayList<FoodDomain> foodDomains;
    private ManagementCart managementCart;
    private ChangeQuantityListener changeQuantityListener;

    public CartListAdapter(ArrayList<FoodDomain>foodDomains, CartListActivity context, ChangeQuantityListener changeQuantityListener){
        this.foodDomains = foodDomains;
        this.managementCart = new ManagementCart(context);
        this.changeQuantityListener = changeQuantityListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View inflate= LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_cart, parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull CartListAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position){
        holder.title.setText(foodDomains.get(position).getTitle());
        holder.pricePerPiece.setText(String.valueOf(foodDomains.get(position).getFee()));
        holder.totalPrice.setText(String.valueOf(Math.round((foodDomains.get(position).getQuantity() * foodDomains.get(position).getFee()) * 100) / 100));
        holder.num.setText(String.valueOf(foodDomains.get(position).getQuantity()));

        int drawableResourceId = holder.itemView.getContext().getResources().getIdentifier(foodDomains.get(position).getPic(),
                "drawable", holder.itemView.getContext().getPackageName());

        Glide.with(holder.itemView.getContext())
                .load(drawableResourceId)
                .into(holder.pic);

        holder.plusItem.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                managementCart.incrementQuantity(foodDomains, position, new ChangeQuantityListener() {
                    @Override
                    public void changed(){
                        notifyDataSetChanged();
                        changeQuantityListener.changed();
                    }
                });
            }
        });

        holder.minusItem.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                managementCart.decrementQuantity(foodDomains, position, new ChangeQuantityListener() {
                    @Override
                    public void changed(){
                        notifyDataSetChanged();
                        changeQuantityListener.changed();
                    }
                });
            }
        });
    }

    @Override
    public int getItemCount(){
        return foodDomains.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
    TextView title, pricePerPiece;
    ImageView pic, plusItem, minusItem;
    TextView totalPrice, num;
    public ViewHolder(@NonNull View itemView){
        super(itemView);
        title=itemView.findViewById(R.id.titleTxt);
        pricePerPiece=itemView.findViewById(R.id.pricePerPiece);
        pic=itemView.findViewById(R.id.pic);
        totalPrice=itemView.findViewById(R.id.totalPrice);
        num=itemView.findViewById(R.id.numberItemTxt);  // R.id.num
        plusItem=itemView.findViewById(R.id.plusCartBtn);
        minusItem=itemView.findViewById(R.id.minusCartBtn);
    }

    }
}
