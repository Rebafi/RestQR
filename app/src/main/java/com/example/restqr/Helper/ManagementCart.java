package com.example.restqr.Helper;

import android.content.Context;
import android.widget.Toast;

import com.example.restqr.Domain.FoodDomain;
import com.example.restqr.Interface.ChangeQuantityListener;

import java.util.ArrayList;

public class ManagementCart {
    private Context context;
    private TinyDB tinyDB;

    public ManagementCart(Context context){
        this.context = context;
        this.tinyDB = new TinyDB(context);
    }

    public void insertFood(FoodDomain item){
        ArrayList<FoodDomain> shoppingList = getListCart();
        boolean existAlready = false;
        int n = 0;
        for(int i = 0; i < shoppingList.size(); i++){
            if(shoppingList.get(i).getTitle().equals(item.getTitle())){
                existAlready = true;
                n = i;
                break;
            }
        }
        if(existAlready){
            shoppingList.get(n).setQuantity(item.getQuantity());
        } else {
            shoppingList.add(item);
        }
        tinyDB.putListObject("CardList", shoppingList);
        Toast.makeText(context, "added to your cart", Toast.LENGTH_SHORT).show();
    }

    public ArrayList<FoodDomain> getListCart(){
        return tinyDB.getListObject("CartList");
    }

    public void incrementQuantity(ArrayList<FoodDomain> shoppingList, int position, ChangeQuantityListener changeQuantityListener){
        shoppingList.get(position).setQuantity(shoppingList.get(position).getQuantity() + 1);
        tinyDB.putListObject("CartList", shoppingList);
        changeQuantityListener.changed();
    }

    public void decrementQuantity(ArrayList<FoodDomain> shoppingList, int position, ChangeQuantityListener changeQuantityListener){
        if(shoppingList.get(position).getQuantity()==1){
            shoppingList.remove(position);
        } else {
            shoppingList.get(position).setQuantity(shoppingList.get(position).getQuantity() - 1);
        }
        tinyDB.putListObject("CartList", shoppingList);
        changeQuantityListener.changed();
    }

    public Double getTotalPrice(){
        ArrayList<FoodDomain> shoppingList = getListCart();
        double fee = 0;
        for(int i = 0; i < shoppingList.size(); i++){
            fee += (shoppingList.get(i).getFee() * shoppingList.get(i).getQuantity());
        }
        return fee;
    }
}
