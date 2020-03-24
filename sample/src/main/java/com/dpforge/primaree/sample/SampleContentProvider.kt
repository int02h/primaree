package com.dpforge.primaree.sample

import android.content.ContentProvider
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.net.Uri
import com.dpforge.primaree.sample.log

class SampleContentProvider : ContentProvider() {

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<String>?): Int = 0

    override fun getType(uri: Uri): String? = null

    override fun insert(uri: Uri, values: ContentValues?): Uri? = null

    override fun onCreate(): Boolean {
        log("SampleContentProvider.onCreate")
        return true
    }

    override fun query(
        uri: Uri, projection: Array<String>?, selection: String?,
        selectionArgs: Array<String>?, sortOrder: String?
    ): Cursor? = null

    override fun update(
        uri: Uri, values: ContentValues?, selection: String?,
        selectionArgs: Array<String>?
    ): Int = 0

    companion object {
        fun query(context: Context) {
            context.contentResolver.query(
                Uri.parse("content://${context.packageName}.provider/*"),
                null,
                null,
                null,
                null
            )?.close()
        }
    }
}
