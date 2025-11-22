package uz.kmax.base.fragment.extended

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.viewbinding.ViewBinding
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import uz.kmax.base.typlealias.BaseInflate

/***
 *  Kmax Developer - 2025.11.23
 *  Typing in Kotlin
 *  Made In Uzbekistan
 *
 *  Class haqida - bu class yordamida Fragment kod qismini ixhamlash imkoniyati mavjud.
 *  BU class BaseFragmentNV ning kengaytirilgan versiyasi hisoblanadi ! .
 *  Class ichida Toast , Flow , SnackBar hamda LOG funksiyalari mavjud
 */

abstract class BaseFragmentNEX<VB : ViewBinding>(
    private val inflate: BaseInflate<VB>
) : Fragment() {
    private var _binding: VB? = null
    val binding get() = _binding!!

    protected lateinit var navController: NavController

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
        navController = Navigation.findNavController(view)
        onViewCreated()
    }

    abstract fun onViewCreated()

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    //////////////////////////////////////////////
    // Toast
    protected fun showToast(msg: String) {
        Toast.makeText(requireContext(), msg, Toast.LENGTH_SHORT).show()
    }

    // SnackBar
    protected fun snack(msg: String) {
        Snackbar.make(binding.root, msg, Snackbar.LENGTH_SHORT).show()
    }

    // Log
    protected fun log(tag : String,msg: String) {
        Log.d(tag, msg)
    }

    // Hide keyboard
    protected fun hideKeyboard() {
        val imm = requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view?.windowToken, 0)
    }

    // Collect Flow safely
    protected fun <T> collectFlow(flow: Flow<T>, collect: suspend (T) -> Unit) {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                flow.collect(collect)
            }
        }
    }

    // Handle back press
    protected fun handleBackPress(action: () -> Unit) {
        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    action()
                }
            }
        )
    }
}