package com.sdos.supermarket.common.view

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AlertDialog
import com.sdos.supermarket.R
import com.sdos.supermarket.domain.DialogBody
import kotlinx.android.synthetic.main.custom_view_lightbox.view.*

typealias ListenerDialog = () -> Unit

class CustomDialogView(
  context: Context,
  dialogBody: DialogBody? = null,
  private val acceptButtonClicked: ListenerDialog? = null,
  private val cancelButtonClicked: ListenerDialog? = null,
  private val cancelable: Boolean = true
) : Dialog(context) {

  private val context = context as Activity
  private var dialogView: View = LayoutInflater.from(context).inflate(R.layout.custom_view_lightbox, null)
  private var dialog: AlertDialog.Builder
  lateinit var builder: AlertDialog

  init {
    dialogBody?.let {
      it.title?.let { title ->
        dialogView.lightboxTitleTextView.text = title
      }
      it.body?.let { body ->
        dialogView.lightboxBodyTextView.text = body
      }
      it.acceptButton?.let { acceptButton ->
        dialogView.lightboxAcceptButton.text = acceptButton
      }
      it.cancelButton?.let { cancelButton ->
        dialogView.lightboxCancelButton.visibility = View.VISIBLE
        dialogView.lightboxCancelButton.text = cancelButton
      }
      it.icon?.let { icon ->
        dialogView.lightboxImageView.setImageResource(icon)
      }
    }

    dialogView.lightboxAcceptButton.setOnClickListener(){
      acceptButtonClicked?.invoke()
      dismiss()
    }

    dialogView.lightboxCancelButton.setOnClickListener{
      cancelButtonClicked?.invoke()
      dismiss()
    }

    dialog = AlertDialog.Builder(context).setView(dialogView)
  }

  override fun show() {
    if (!context.isFinishing) {
      builder = dialog.show()
      builder.setCanceledOnTouchOutside(cancelable)
    }
  }

  fun setLightBoxBodyText(message: String) {
    dialogView.lightboxBodyTextView.text = message
  }

  override fun dismiss() {
    if (!context.isFinishing && ::builder.isInitialized) {
      builder.dismiss()
    }
  }
}
