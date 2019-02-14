package com.example.plantmaintenance;

import android.content.Context;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.*;
import android.view.*;
import android.widget.*;
public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder>{

    private Context mCtx;
    private List<ProductGS> productList;

    public ProductAdapter(Context mCtx, List<ProductGS> productList) {
        this.mCtx = mCtx;
        this.productList = productList;
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.product_list,parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {
        ProductGS p=productList.get(position);
       // ProductGS product = productList.get(position);
        holder.textViewPrice.setText(String.valueOf("Quantity: "+p.getQuantity()));
        holder.textViewTitle.setText(String.valueOf("Boiler ID: "+p.getId()));
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    class ProductViewHolder extends RecyclerView.ViewHolder {

        TextView textViewTitle, textViewShortDesc, textViewRating, textViewPrice;
        //ImageView imageView;

        public ProductViewHolder(View itemView) {
            super(itemView);

            textViewTitle = itemView.findViewById(R.id.textViewTitle);

            textViewPrice = itemView.findViewById(R.id.textViewPrice);

        }
    }
}
