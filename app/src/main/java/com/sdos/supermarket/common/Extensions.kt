package com.sdos.supermarket.common

import android.app.Activity
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import com.google.android.material.snackbar.Snackbar
import com.sdos.supermarket.R
import com.sdos.supermarket.domain.model.Task

fun Activity.toastLong(text: Int) {
    Toast.makeText(this, getString(text), Toast.LENGTH_LONG).show()
}

fun Activity.getCompatibleColor(color: Int) = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
    this.getColor(color)
} else {
    this.resources.getColor(color)
}

fun BaseFragment.navigate(navDestination: NavDirections ){
    findNavController().navigate(navDestination)
}

fun List<Task>.getTotalWorkLoad(userId: String) =
    this.filter { task -> task.userCode == userId }
        .fold(0) { total: Int, task: Task -> total + task.hours }

fun Fragment.showSnack(message : String){
    view?.let {
        Snackbar.make(it, message, Snackbar.LENGTH_LONG).show()
    }
}

fun ViewGroup.inflate(itemHolder: Int): View = LayoutInflater.from(context).inflate(itemHolder, this, false)
