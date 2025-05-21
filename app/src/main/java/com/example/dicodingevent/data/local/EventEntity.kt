// EventEntity.kt
package com.example.dicodingevent.data.local

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity (tableName = "EventEntity")
@Parcelize
data class EventEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    var id: Int = 0,
    @ColumnInfo(name = "name")
    val name: String? = null,         // Nama event
    @ColumnInfo(name = "ownerName")
    val ownerName: String?= null,    // Nama penyelenggara
    @ColumnInfo(name = "beginTime")
    val beginTime: String? = null,    // Waktu acara
    @ColumnInfo(name = "quota")
    val quota: Int?= null,           // Total kuota
    @ColumnInfo(name = "registrants")
    val registrants: String ?= null,     // Jumlah pendaftar
    @ColumnInfo(name = "description")
    val description: String?=null,   // Deskripsi event
    @ColumnInfo(name = "mediaCover")
    val mediaCover: String?=null,
    @ColumnInfo(name = "link")
    var link: String = ""// URL gambar cover
) : Parcelable
