package uz.kmax.base.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import uz.kmax.base.fragmentcontroller.FragmentController
import uz.kmax.base.typlealias.BaseInflate

abstract class BaseFragmentVM<VB : ViewBinding, VM : ViewModel>(
    private val inflate: BaseInflate<VB>,
    private val viewModelClass: Class<VM>
) : Fragment() {

    private var _binding: VB? = null
    val binding get() = _binding!!

    private val controller = FragmentController.controller

    protected lateinit var viewModel: VM

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = inflate.invoke(inflater, container, false)
        viewModel = ViewModelProvider(this, ViewModelFactory(requireContext())).get(viewModelClass)
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

    fun back() {
        controller?.back()
    }

    fun startMainFragment(fragment: Fragment) {
        controller?.startMainFragment(fragment)
    }

    fun activityDestroy() {
        activity?.finish()
    }

    fun backOneFragment() {
        controller?.getLastFragment()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    class ViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return try {
                modelClass.getConstructor(Context::class.java).newInstance(context)
            } catch (e: Exception) {
                throw IllegalArgumentException("Could not create ViewModel: ${modelClass.name}", e)
            }
        }
    }
}
