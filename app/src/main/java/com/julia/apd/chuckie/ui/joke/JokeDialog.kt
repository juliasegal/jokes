package com.julia.apd.chuckie.ui.joke

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import com.julia.apd.chuckie.R

interface JokeDialogCompleteCallback {
    fun complete()
}

class JokeDialog {
    companion object {
        fun showJoke(context: Context, joke: String, callback: JokeDialogCompleteCallback? = null) {
            AlertDialog.Builder(context)
                .apply {
                    setTitle(R.string.joke_dialog_title)
                    setMessage(joke)
                    setNegativeButton(R.string.joke_dialog_button) { dlg: DialogInterface, _: Int ->
                        dlg.dismiss()
                        callback?.complete()
                    }
                }
                .show()
        }
    }
}