package uz.kmax.base.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment

/***
 *  Kmax Developer - 2022.12.16
 *  Typing in Kotlin
 *  Made In Uzbekistan
 */

abstract class BaseFragment(
    @LayoutRes layoutId: Int
) : Fragment() {

    var setLayoutRes = layoutId
    lateinit var layout : View

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        layout = inflater.inflate(
            setLayoutRes,
            container,
            false
        )
        return layout
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onViewCreated()
    }

    abstract fun onViewCreated()
}
