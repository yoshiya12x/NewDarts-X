package e.yoppie.newdartsx.repository

import com.microsoft.azure.cognitiveservices.search.imagesearch.BingImageSearchAPI
import com.microsoft.azure.cognitiveservices.search.imagesearch.BingImageSearchManager
import com.microsoft.azure.cognitiveservices.search.imagesearch.models.ImagesModel

class BingImageSearchRepository {
    companion object {
        const val SECRET_KEY = ""
    }

    private val client: BingImageSearchAPI

    init {
        client = BingImageSearchManager.authenticate(SECRET_KEY)
    }

    fun getImages(word: String): ImagesModel = client.bingImages().search().withQuery(word).execute()
}