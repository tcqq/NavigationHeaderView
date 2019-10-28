package com.tcqq.navigationheaderview

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Created by raphaelbussa on 14/01/17.
 */

@Parcelize
data class Item(
    val id: Int,
    val title: String?
) : Parcelable {

    private constructor(builder: Builder) : this(
        builder.id,
        builder.title
    )

    companion object {
        inline fun build(block: Builder.() -> Unit) = Builder().apply(block).build()
    }

    class Builder {
        var id: Int = 0
        var title: String? = null

        fun build() = Item(this)
    }
}