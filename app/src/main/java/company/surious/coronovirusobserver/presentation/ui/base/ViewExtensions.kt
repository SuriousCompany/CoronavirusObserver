package company.surious.coronovirusobserver.presentation.ui.base

import android.content.Context
import android.widget.Toast
import company.surious.coronovirusobserver.R

fun Context.showWillBeImplementedToast() {
    Toast.makeText(this, getString(R.string.will_be_implemented), Toast.LENGTH_SHORT).show()
}