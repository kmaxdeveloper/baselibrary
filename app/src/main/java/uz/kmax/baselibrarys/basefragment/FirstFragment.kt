package uz.kmax.baselibrarys.basefragment

import android.graphics.Color
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import uz.kmax.base.basefragment.BaseFragment
import uz.kmax.baselibrarys.R

class FirstFragment : BaseFragment(R.layout.activity_main2){
    override fun onViewCreated() {
        var text : TextView = requireView().findViewById(R.id.text)
        var back : ConstraintLayout = requireView().findViewById(R.id.background)

        text.setOnClickListener {
            text.text = "Ishladi !!!"
            back.setBackgroundColor(Color.parseColor("#FF00FF"))
        }
    }
}