package uz.kmax.baselibrarys.basefragmentwc

import uz.kmax.base.basefragment.BaseFragmentWC
import uz.kmax.baselibrarys.basefragment.FirstFragment
import uz.kmax.baselibrarys.basefragment.SecondFragment
import uz.kmax.baselibrarys.databinding.ActivityMain2Binding

class KotlinFragment : BaseFragmentWC<ActivityMain2Binding>(ActivityMain2Binding::inflate) {
    override fun onViewCreated() {
        binding.text.setOnClickListener {
            activityDestroy() // this is destroy application and exit app (is working only single activity)
            replaceFragment(SecondFragment()) // this is replace Fragment
            startMainFragment(FirstFragment()) // this is start Main Fragment
        }
    }
}