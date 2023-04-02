package com.example.kartoffeltask.Adapter

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.kartoffeltask.DataModel.DataItem
import com.example.kartoffeltask.R
import com.example.kartoffeltask.databinding.ItemListBinding
import kotlin.collections.List

class ProductAdapter(var list: List<DataItem>, var itemClick: itemClickListener) : RecyclerView.Adapter<ProductAdapter.RecyclerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        return RecyclerViewHolder(ItemListBinding.inflate(LayoutInflater.from(parent.context),parent,false), itemClick)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    interface itemClickListener {
        fun getItem(position: Int)
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        holder.bindData(list, position)
    }

    class RecyclerViewHolder(val binding: ItemListBinding, var itemClick: itemClickListener): RecyclerView.ViewHolder(binding.root) {

        fun bindData(userList: List<DataItem>, position: Int) {
            binding.productPrice.apply {
                paintFlags = paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
                text = context.getString(R.string.price,userList[position].price.toString())
            }
            binding.discountPrice.apply {
                text = context.getString(R.string.price,userList[position].discount_price.toString())
            }
            Glide.with(itemView.getContext()).load(userList[position].image).into(binding.productImage)
            binding.productName.text = userList[position].title
            itemView.setOnClickListener(View.OnClickListener {
                itemClick.getItem(adapterPosition)
            })
        }
    }
}