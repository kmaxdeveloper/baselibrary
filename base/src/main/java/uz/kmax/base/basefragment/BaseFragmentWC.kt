package uz.kmax.base.basefragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import uz.kmax.base.fragmentcontroller.FragmentController
import uz.kmax.base.typlealias.BaseInflate

/***
 *  Kmax Developer - 2022.12.16
 *  Typing in Kotlin
 *  Made In Uzbekistan
 */

abstract class BaseFragmentWC<VB : ViewBinding>(
    private val inflate: BaseInflate<VB>
) : Fragment() {
    private var _binding: VB? = null
    val binding get() = _binding!!
    private val controller = FragmentController.controller

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = inflate.invoke(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onViewCreated()
    }

    abstract fun onViewCreated()

    fun replaceFragment(setFragment: Fragment) {
        controller?.replaceFragment(setFragment)
    }

    fun back(){
        controller?.back()
    }

    fun startMainFragment(fragment: Fragment){
        controller?.startMainFragment(fragment)
    }

    fun activityDestroy(){
        activity?.finish()
    }

    fun backOneFragment(){
        controller?.getLastFragment()
    }
}
