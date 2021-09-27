package com.example.ilabank.view.activity

import android.content.Context
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.MotionEventCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.example.ilabank.R
import com.example.ilabank.model.ImageDataClass
import com.example.ilabank.view.adapter.LableRecyclerAdapter
import com.example.ilabank.view.adapter.ViewPagerAdapter
import com.example.ilabank.viewmodel.MainViewModel


class MainActivity : AppCompatActivity() {
    private lateinit var viewpager: ViewPager2;
    private lateinit var searchTxt: SearchView;
    private lateinit var recylcerLabels: RecyclerView;
    private lateinit var sliderDots: LinearLayout;
    private lateinit var viewPagerAdapter: ViewPagerAdapter;
    private var dotscount = 0
    private var dots: ArrayList<ImageView> = ArrayList();
    private lateinit var mainViewModel: MainViewModel;
    private var labelList: MutableList<ImageDataClass>? = null;
    private lateinit var labelAdapter: LableRecyclerAdapter;
    private var selectedLabelList: MutableList<String>? = null;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        objectInitialization()
        viewInitialization()
    }

    fun objectInitialization() {
        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
    }

    fun viewInitialization() {
        viewpager = findViewById(R.id.viewpager)
        searchTxt = findViewById(R.id.searchTxt)
        recylcerLabels = findViewById(R.id.recylcerLabels)
        sliderDots = findViewById(R.id.linrSliderDots)
        viewPageAdatperSetting(this);
        recyclerViewSetting();
        searchData();
        createSliderDotsViews()
    }

    fun viewPageAdatperSetting(context: Context) {
        labelList = mainViewModel.prepareImageList()
        viewPagerAdapter = ViewPagerAdapter(context = this, labelList = labelList)
        viewpager.setAdapter(viewPagerAdapter)

        viewpager.registerOnPageChangeCallback(object : OnPageChangeCallback() {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels)
            }

            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                for (i in 0 until dotscount) {
                    dots.get(i).setImageDrawable(
                        ContextCompat.getDrawable(
                            applicationContext,
                            R.drawable.nonactive_dots
                        )
                    )
                }

                dots.get(position).setImageDrawable(
                    ContextCompat.getDrawable(applicationContext, R.drawable.activedot)
                )
                selectedLabelList = labelList?.get(position)?.labels;
                setadapter();
            }

            override fun onPageScrollStateChanged(state: Int) {
                super.onPageScrollStateChanged(state)
            }
        })
    }

    fun createSliderDotsViews() {
        dotscount = viewPagerAdapter.getItemCount();
        for (i in 0 until dotscount) {
            dots.add(ImageView(this))
            dots.get(i).setImageDrawable(
                ContextCompat.getDrawable(
                    applicationContext,
                    R.drawable.nonactive_dots
                )
            )
            val params = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            params.setMargins(8, 0, 8, 0)
            sliderDots.addView(dots.get(i), params)
        }

        dots.get(0).setImageDrawable(
            ContextCompat.getDrawable(
                applicationContext,
                R.drawable.activedot
            )
        )
    }

    fun recyclerViewSetting() {
        recylcerLabels.layoutManager = LinearLayoutManager(this)
        selectedLabelList = labelList?.get(0)?.labels;
        setadapter();
        recylcerLabels.setNestedScrollingEnabled(false);
    }

    fun setadapter() {
        searchTxt.setQuery("", false);
        searchTxt.clearFocus();
        labelAdapter = LableRecyclerAdapter(this, selectedLabelList)
        recylcerLabels.adapter = labelAdapter
    }

    fun searchData() {
        searchTxt.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                labelAdapter?.filter(selectedLabelList, newText)
                return false
            }
        })
    }
}
