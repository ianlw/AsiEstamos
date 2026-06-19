package com.ianlw.asiestamos.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "registros")
data class RegistroEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val titulo: String,
    val tieneEstablecimiento: Boolean,
    val tipoEntrada: String, // "texto" | "voz" | "foto"
    val inputOriginal: String,
    val fotoPath: String?,
    val latitud: Double?,
    val longitud: Double?,
    val nombreUbicacion: String?,
    val fecha: Long // timestamp millis
)
