package uz.kmax.baselibrarys.networkmonitor

import uz.kmax.base.basefragment.BaseFragmentWC
import uz.kmax.base.network.monitor.NetworkMonitor
import uz.kmax.baselibrarys.databinding.LayoutNetworkBinding

class NetworkFragment: BaseFragmentWC<LayoutNetworkBinding>(LayoutNetworkBinding::inflate) {
    lateinit var networkMonitor : NetworkMonitor
    override fun onViewCreated() {
        networkMonitor = NetworkMonitor(requireActivity().application)
        networkMonitor.observe(requireActivity()){
            if (it){
                binding.statusInternet.text = "Internet : Online"
            }else{
                binding.statusInternet.text = "Internet : Offline"
            }
        }
    }
}