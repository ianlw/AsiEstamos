package com.ianlw.asiestamos.utils

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.core.content.FileProvider
import com.google.gson.GsonBuilder
import com.ianlw.asiestamos.domain.model.Registro
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object ExportHelper {

    fun exportAsJson(context: Context, registros: List<Registro>): Uri? {
        return try {
            val gson = GsonBuilder().setPrettyPrinting().create()
            val json = gson.toJson(registros)
            val file = createExportFile(context, "json")
            file.writeText(json)
            getShareableUri(context, file)
        } catch (e: Exception) {
            null
        }
    }

    fun exportAsCsv(context: Context, registros: List<Registro>): Uri? {
        return try {
            val sb = StringBuilder()
            sb.appendLine("Registro ID,Título,Fecha,Tipo Entrada,Producto,Monto,Categoría,Descripción IA")
            registros.forEach { reg ->
                val dateStr = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault()).format(Date(reg.fecha))
                reg.gastos.forEach { gasto ->
                    sb.appendLine("${reg.id},\"${reg.titulo}\",${dateStr},${reg.tipoEntrada.valor},\"${gasto.producto}\",${gasto.monto},${gasto.categoria},\"${gasto.descripcionIA}\"")
                }
            }
            val file = createExportFile(context, "csv")
            file.writeText(sb.toString())
            getShareableUri(context, file)
        } catch (e: Exception) {
            null
        }
    }

    private fun createExportFile(context: Context, extension: String): File {
        val timestamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
        val dir = File(context.filesDir, "exports")
        if (!dir.exists()) dir.mkdirs()
        return File(dir, "asi_estamos_$timestamp.$extension")
    }

    private fun getShareableUri(context: Context, file: File): Uri {
        return FileProvider.getUriForFile(
            context,
            "${context.packageName}.fileprovider",
            file
        )
    }

    fun shareFile(context: Context, uri: Uri, mimeType: String) {
        val intent = Intent(Intent.ACTION_SEND).apply {
            type = mimeType
            putExtra(Intent.EXTRA_STREAM, uri)
            addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        }
        context.startActivity(Intent.createChooser(intent, "Exportar datos"))
    }
}
