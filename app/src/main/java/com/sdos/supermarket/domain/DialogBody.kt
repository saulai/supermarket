package com.sdos.supermarket.domain

data class DialogBody(
  val title: String? = null,
  val body: String? = null,
  val acceptButton: String? = null,
  val cancelButton: String? = null,
  val icon: Int? = null
)
