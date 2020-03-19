package com.jackthenewest.businessrulepattern.application.exception

class NotFoundMapKeyException(
  message: String,
  cause: Exception? = null
) : RuntimeException(message, cause)
