package uz.kmax.baselibrarys.basefragment

import android.graphics.Color
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import uz.kmax.base.basefragment.BaseFragment
import uz.kmax.baselibrarys.R

class SecondFragment: BaseFragment(R.layout.activity_main2) {
    override fun onViewCreated() {
        var text : TextView = layout.findViewById(R.id.text)
        var back : ConstraintLayout = layout.findViewById(R.id.background)

        text.setOnClickListener {
            text.text = "Ishladi !!!!"
            back.setBackgroundColor(Color.parseColor("#00FFFF"))
        }
    }
}