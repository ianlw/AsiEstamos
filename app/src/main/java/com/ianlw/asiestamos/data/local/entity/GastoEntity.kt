package com.ianlw.asiestamos.data.local.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "gastos",
    foreignKeys = [ForeignKey(
        entity = RegistroEntity::class,
        parentColumns = ["id"],
        childColumns = ["registroId"],
        onDelete = ForeignKey.CASCADE
    )],
    indices = [Index(value = ["registroId"])]
)
data class GastoEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val registroId: Int,
    val monto: Double,
    val producto: String,
    val descripcionIA: String,
    val categoria: String
)
