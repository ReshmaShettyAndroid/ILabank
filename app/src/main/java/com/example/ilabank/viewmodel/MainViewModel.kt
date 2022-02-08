package com.example.ilabank.viewmodel

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import com.example.ilabank.model.ImageDataClass


public class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val context: Context;
    private var imageModelList: MutableList<ImageDataClass>? = mutableListOf<ImageDataClass>()

    init {
        context = application.applicationContext
    }

    fun prepareImageList(): MutableList<ImageDataClass>? {
        lateinit var imageDataList: ImageDataClass
        var url = "https://picsum.photos/200/300?random=3"
        for (i in 1..3) {
            val labels: MutableList<String>? = mutableListOf<String>()
            for (j in 1..20) {
                labels?.add("Labels:-" + i + "." + j)
            }
            imageDataList = ImageDataClass(url + i, labels)
            imageModelList?.add(imageDataList)  

        }
        return imageModelList;
    }

}
