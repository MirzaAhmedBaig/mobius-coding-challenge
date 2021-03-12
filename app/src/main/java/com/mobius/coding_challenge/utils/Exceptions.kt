package com.mobius.coding_challenge.utils

import java.io.IOException

class ApiExceptions(message: String?, val code: Int?) : IOException(message)
