package e.yoppie.newdartsx.viewmodel

import android.annotation.SuppressLint
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.view.View
import com.microsoft.azure.cognitiveservices.search.imagesearch.models.ImagesModel
import e.yoppie.newdartsx.repository.BingImageSearchRepository
import io.reactivex.Completable
import io.reactivex.schedulers.Schedulers

class TargetViewModel : ViewModel() {
    private val bingImageSearchRepository = BingImageSearchRepository()
    private var imagesNextIndex = 0
    private lateinit var images: ImagesModel
    val imageUrl1: MutableLiveData<String> = MutableLiveData()
    val imageUrl2: MutableLiveData<String> = MutableLiveData()
    val imageUrl3: MutableLiveData<String> = MutableLiveData()
    val imageVisibility1: MutableLiveData<Int> = MutableLiveData()
    val imageVisibility2: MutableLiveData<Int> = MutableLiveData()
    val imageVisibility3: MutableLiveData<Int> = MutableLiveData()
    val textVisibility: MutableLiveData<Int> = MutableLiveData()

    init {
        val initialUrl =
            "https://lh3.googleusercontent.com/jGbIrAsW751QsMljI_wgDHG2W31VK8_PAPFhMf7KZwMEEDzWKpA3CMJhSRQLJW2cc3aZ=s180-rw"
        imageUrl1.value = initialUrl
        imageUrl2.value = initialUrl
        imageUrl3.value = initialUrl
        val initialImageVisibility = View.GONE
        imageVisibility1.value = initialImageVisibility
        imageVisibility2.value = initialImageVisibility
        imageVisibility3.value = initialImageVisibility
        textVisibility.value = View.VISIBLE
    }

    @SuppressLint("CheckResult")
    fun setImages(word: String) {
        Completable
            .fromAction { images = bingImageSearchRepository.getImages(word) }
            .subscribeOn(Schedulers.io())
            .subscribe {
                if (images.value().size >= 3) {
                    imageUrl1.postValue(images.value()[0].thumbnailUrl())
                    imageUrl2.postValue(images.value()[1].thumbnailUrl())
                    imageUrl3.postValue(images.value()[2].thumbnailUrl())
                    imagesNextIndex = 3
                }
            }
    }

    fun changeImage() {
        textVisibility.postValue(View.GONE)
        when {
            imageVisibility1.value == View.VISIBLE
                    && imageVisibility2.value == View.GONE
                    && imageVisibility3.value == View.GONE -> {
                imageVisibility1.postValue(View.GONE)
                imageVisibility2.postValue(View.VISIBLE)
                if(images.value().size >= imagesNextIndex + 1){
                    imageUrl1.postValue(images.value()[imagesNextIndex].thumbnailUrl())
                    imagesNextIndex ++
                }else{
                    imageUrl1.postValue(images.value()[0].thumbnailUrl())
                    imagesNextIndex = 1
                }
            }
            imageVisibility1.value == View.GONE
                    && imageVisibility2.value == View.VISIBLE
                    && imageVisibility3.value == View.GONE -> {
                imageVisibility2.postValue(View.GONE)
                imageVisibility3.postValue(View.VISIBLE)
                if(images.value().size >= imagesNextIndex + 1){
                    imageUrl2.postValue(images.value()[imagesNextIndex].thumbnailUrl())
                    imagesNextIndex ++
                }else{
                    imageUrl2.postValue(images.value()[0].thumbnailUrl())
                    imagesNextIndex = 1
                }
            }
            imageVisibility1.value == View.GONE
                    && imageVisibility2.value == View.GONE
                    && imageVisibility3.value == View.VISIBLE -> {
                imageVisibility1.postValue(View.VISIBLE)
                imageVisibility3.postValue(View.GONE)
                if(images.value().size >= imagesNextIndex + 1){
                    imageUrl3.postValue(images.value()[imagesNextIndex].thumbnailUrl())
                    imagesNextIndex ++
                }else{
                    imageUrl3.postValue(images.value()[0].thumbnailUrl())
                    imagesNextIndex = 1
                }
            }
            imageVisibility1.value == View.GONE
                    && imageVisibility2.value == View.GONE
                    && imageVisibility3.value == View.GONE -> {
                imageVisibility1.postValue(View.VISIBLE)
                if(images.value().size >= imagesNextIndex + 1){
                    imageUrl1.postValue(images.value()[imagesNextIndex].thumbnailUrl())
                    imagesNextIndex ++
                }else{
                    imageUrl1.postValue(images.value()[0].thumbnailUrl())
                    imagesNextIndex = 1
                }
            }
        }
    }
}