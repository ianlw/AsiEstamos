package com.ianlw.asiestamos.data.local.relation

import androidx.room.Embedded
import androidx.room.Relation
import com.ianlw.asiestamos.data.local.entity.GastoEntity
import com.ianlw.asiestamos.data.local.entity.RegistroEntity

data class RegistroConGastos(
    @Embedded val registro: RegistroEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "registroId"
    )
    val gastos: List<GastoEntity>
)
