package com.wyc.http.http

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 *作者： wyc
 * <p>
 * 创建时间： 2018/12/13 15:07
 * <p>
 * 文件名字： com.wyc.kotlin.project.model
 * <p>
 * 类的介绍：
 */
@Parcelize
data class MovieBean(
        val count: Int,
        val start: Int,
        val subjects: MutableList<SubjectsBean>,
        val title: String,
        val total: Int
) : Parcelable

@Parcelize
data class SubjectsBean(
        val alt: String,
        val casts: MutableList<CastsBean>,
        val collect_count: Int,
        val directors: MutableList<DirectorsBean>,
        val genres: Array<String>,
        val id: Int,
        val images: ImagesBean,
        val original_title: String,
        val rating: RatingBean,
        val subtype: String,
        val title: String,
        val year: String
) : Parcelable

@Parcelize
data class CastsBean(
        val alt: String,
        val avatars: AvatarsBean,
        val id: String,
        val name: String
) : Parcelable

@Parcelize
data class DirectorsBean(
        val alt: String,
        val avatars: AvatarsBean,
        val id: String,
        val name: String
) : Parcelable

@Parcelize
data class ImagesBean(
        val large: String,
        val medium: String,
        val small: String
) : Parcelable

@Parcelize
data class RatingBean(
        val average: String,
        val max: String,
        val min: String,
        val stars: String
) : Parcelable

@Parcelize
data class AvatarsBean(
        val large: String,
        val medium: String,
        val small: String
) : Parcelable
