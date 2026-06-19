package com.ianlw.asiestamos.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import java.io.File
import java.io.FileOutputStream
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object PhotoHelper {

    /**
     * Create a file in app's private directory to store a photo.
     */
    fun createPhotoFile(context: Context): File {
        val timestamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
        val fileName = "GASTO_${timestamp}.jpg"
        val storageDir = File(context.filesDir, "photos")
        if (!storageDir.exists()) storageDir.mkdirs()
        return File(storageDir, fileName)
    }

    /**
     * Save a bitmap to a file and return the path.
     */
    fun saveBitmap(context: Context, bitmap: Bitmap): String {
        val file = createPhotoFile(context)
        FileOutputStream(file).use { out ->
            bitmap.compress(Bitmap.CompressFormat.JPEG, 85, out)
        }
        return file.absolutePath
    }

    /**
     * Load image bytes from a URI.
     */
    fun loadImageBytes(context: Context, uri: Uri): ByteArray? {
        return try {
            context.contentResolver.openInputStream(uri)?.use { stream ->
                stream.readBytes()
            }
        } catch (e: Exception) {
            null
        }
    }

    /**
     * Load image bytes from a file path.
     */
    fun loadImageBytesFromPath(path: String): ByteArray? {
        return try {
            File(path).readBytes()
        } catch (e: Exception) {
            null
        }
    }

    /**
     * Get a compressed version of the image for sending to Gemini.
     * Max dimension 1024px, quality 80%.
     */
    fun compressForGemini(context: Context, uri: Uri): ByteArray? {
        return try {
            val inputStream = context.contentResolver.openInputStream(uri) ?: return null
            val original = BitmapFactory.decodeStream(inputStream)
            inputStream.close()

            val maxDim = 1024
            val scale = minOf(maxDim.toFloat() / original.width, maxDim.toFloat() / original.height, 1f)
            val width = (original.width * scale).toInt()
            val height = (original.height * scale).toInt()

            val scaled = if (scale < 1f) {
                Bitmap.createScaledBitmap(original, width, height, true)
            } else {
                original
            }

            val outputStream = java.io.ByteArrayOutputStream()
            scaled.compress(Bitmap.CompressFormat.JPEG, 80, outputStream)

            if (scaled != original) scaled.recycle()
            original.recycle()

            outputStream.toByteArray()
        } catch (e: Exception) {
            null
        }
    }

    /**
     * Delete a photo file.
     */
    fun deletePhoto(path: String?) {
        path?.let {
            val file = File(it)
            if (file.exists()) file.delete()
        }
    }
}
