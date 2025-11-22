package uz.kmax.base.fragment.extended

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.viewbinding.ViewBinding
import uz.kmax.base.fragmentcontroller.FragmentController
import uz.kmax.base.typlealias.BaseInflate
import androidx.navigation.findNavController

/***
 *  Kmax Developer - 2025.11.23
 *  Typing in Kotlin
 *  Made In Uzbekistan
 */

abstract class BaseFragmentEX<VB : ViewBinding>(
    private val inflate: BaseInflate<VB>
) : Fragment() {
    private var _binding: VB? = null
    val binding get() = _binding!!

    protected lateinit var navController: NavController
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
        try {
            navController = view.findNavController()
        }catch (ex : IllegalStateException){
            ex.printStackTrace()
        }
        onViewCreated()
    }

    abstract fun onViewCreated()

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    fun startNavigate(id : Int){
        if (::navController.isInitialized) {
            navController.navigate(id)
        }
    }

    fun replaceFragment(setFragment: Fragment) {
        controller?.replaceFragment(setFragment)
    }

    fun goControllerBack(){
        controller?.back()
    }

    fun startMainFragment(fragment: Fragment){
        controller?.startMainFragment(fragment)
    }

    fun activityDestroy(){
        activity?.finish()
    }

    fun getControllerLastFragment(){
        controller?.getLastFragment()
    }
}