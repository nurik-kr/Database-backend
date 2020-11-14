package kg.nurik.databasefirebase.data.model.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class NewsItem(
    var desc: String = "",
    var image: String = "",
    var title: String = ""
) : Parcelable