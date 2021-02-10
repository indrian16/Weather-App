package io.indrian.weatherapp.ui.dialogs

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import io.indrian.weatherapp.R

class LoadingDialogFragment : DialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        isCancelable = false
        return inflater.inflate(R.layout.loading_dialog_layout, container, false)
    }

    override fun onResume() {
        super.onResume()

        val window = dialog?.window
        if (window != null) {

            val params = window.attributes
            params.width = resources.getDimensionPixelSize(R.dimen.loading_dialog_height)
            params.height = resources.getDimensionPixelSize(R.dimen.loading_dialog_width)

            window.attributes = params
            window.setGravity(Gravity.CENTER)
            window.setBackgroundDrawableResource(android.R.color.transparent)
        }
    }

    companion object {

        const val TAG = "loading"

        @JvmStatic
        fun newInstance() = LoadingDialogFragment()
    }
}