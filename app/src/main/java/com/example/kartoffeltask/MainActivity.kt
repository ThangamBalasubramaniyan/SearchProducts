package com.example.kartoffeltask

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SearchView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kartoffeltask.Adapter.ProductAdapter
import com.example.kartoffeltask.DataModel.DataItem
import com.example.kartoffeltask.DetailProduct.DetailProductActivity
import com.example.kartoffeltask.ViewModel.MainViewModel
import com.example.kartoffeltask.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), ProductAdapter.itemClickListener {

    private lateinit var binding: ActivityMainBinding
    private var matchTitle: ArrayList<DataItem> = arrayListOf()
    private var title: ArrayList<DataItem> = arrayListOf()
    private var myadapter: ProductAdapter = ProductAdapter(title, this)
    var image: String = ""
    var name: String = ""
    var price: String = ""
    var discountPrice: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel = ViewModelProvider(this@MainActivity).get(MainViewModel::class.java)
        val recyclerView: RecyclerView = findViewById(R.id.rvXml)
        viewModel.getProducts()
        viewModel.myResponseList.observe(this, Observer {
            myadapter.list = it
            title = it
            myadapter = ProductAdapter(it,this)
            recyclerView.adapter = myadapter
        })
        setupRecyclerView()
        performSearch()
    }

    private fun setupRecyclerView() = binding.rvXml.apply {
        adapter = ProductAdapter(title,this@MainActivity).also {
            binding.rvXml.adapter = it
            binding.rvXml.adapter!!.notifyDataSetChanged()
        }
        binding.searchView.isSubmitButtonEnabled = true
        layoutManager = GridLayoutManager(this@MainActivity,2)
    }

    private fun performSearch() {
        binding.searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                search(query)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                search(newText)
                return true
            }
        })
    }

    private fun search(text: String?) {
        matchTitle = arrayListOf()

        text?.let {
            title.forEach {
                if (it.title.contains(text, true)) {
                    matchTitle.add(it)
                }
            }
            updateRecyclerView()
            if (matchTitle.isEmpty()) {
                Toast.makeText(this, "No match found", Toast.LENGTH_SHORT).show()
            }
            updateRecyclerView()
        }
    }

    private fun updateRecyclerView() {
        binding.rvXml.apply {
            myadapter.list = matchTitle
            myadapter?.notifyDataSetChanged()
        }
    }

    override fun getItem(position: Int) {
        val viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel.myResponseList.observe(this, {
            image = it.get(position).image
            name = it.get(position).title
            price = it.get(position).price.toString()
            discountPrice = it.get(position).discount_price.toString()

            val intent = Intent(this, DetailProductActivity::class.java)
            intent.putExtra("Image", image)
            println("Thangam ${image}")
            intent.putExtra("Name", name)
            intent.putExtra("Price", price)
            intent.putExtra("Discount Price", discountPrice)
            startActivity(intent)
        })
    }
}