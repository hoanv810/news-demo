package io.github.hoanv810.core.utils

import android.content.Context
import io.github.hoanv810.core.R

val Context.unknownError: String
    get() = getString(R.string.error_message_error_happened)

val Context.internetError: String
    get() = getString(R.string.error_message_internet_connection)