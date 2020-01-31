package company.surious.coronovirusobserver.presentation.ui.binding

import android.view.View
import androidx.databinding.BindingConversion


@BindingConversion
fun convertbooleanToVisibility(visible: Boolean): Int {
    return if (visible) {
        View.VISIBLE
    } else {
        View.INVISIBLE
    }
}