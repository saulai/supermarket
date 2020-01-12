package com.sdos.supermarket.common

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.sdos.supermarket.domain.model.Task

fun BaseFragment.navigate(navDestination: NavDirections) {
    findNavController().navigate(navDestination)
}

fun List<Task>.getTotalWorkLoad(userId: String) =
    this.filter { task -> task.userCode == userId }
        .fold(0) { total: Int, task: Task -> total + task.hours }

fun Fragment.showSnack(message: String) {
    view?.let {
        Snackbar.make(it, message, Snackbar.LENGTH_LONG).show()
    }
}

fun ViewGroup.inflate(itemHolder: Int): View =
    LayoutInflater.from(context).inflate(itemHolder, this, false)
