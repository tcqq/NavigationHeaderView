package com.tcqq.navigationheaderview

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Created by raphaelbussa on 14/01/17.
 */

@Parcelize
data class Profile(
    val id: Int,
    val avatarUri: String?,
    val avatarRes: Int,
    val backgroundUri: String?,
    val backgroundRes: Int,
    val username: String?,
    val email: String?
) : Parcelable {

    private constructor(builder: Builder) : this(
        builder.id,
        builder.avatarUri,
        builder.avatarRes,
        builder.backgroundUri,
        builder.backgroundRes,
        builder.username,
        builder.email
    )

    companion object {
        inline fun build(block: Builder.() -> Unit) = Builder().apply(block).build()
    }

    class Builder {
        var id: Int = 0
        var avatarUri: String? = null
        var avatarRes: Int = 0
        var backgroundUri: String? = null
        var backgroundRes: Int = 0
        var username: String? = null
        var email: String? = null

        fun build() = Profile(this)
    }

}
