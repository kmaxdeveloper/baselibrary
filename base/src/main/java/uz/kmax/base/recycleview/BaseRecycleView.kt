package uz.kmax.base.recycleview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import uz.kmax.base.typlealias.BaseInflate

/** 16.01.2025 y.
 *  RecycleView ni ixchamlashtirilgan kodi
 *  Kmax Programmers
 */

abstract class BaseRecycleView<T : ViewBinding, D>(
    private val inflate: BaseInflate<T>
) : RecyclerView.Adapter<BaseRecycleView.BaseViewHolder<T>>() {

    private var onTaskListener: ((task : Int , message : String) -> Unit)? = null
    fun setOnTaskListener(listener: (task : Int , message : String) -> Unit) { onTaskListener = listener }

    private var onItemClickListener: (() -> Unit)? = null
    fun setOnItemClickListener(listener: () -> Unit) { onItemClickListener = listener }

    private val items = ArrayList<D>()

    fun setData(newItems: ArrayList<D>) {
        items.clear()
        items.addAll(newItems)
        notifyDataSetChanged()
    }

    fun sendMessage(task : Int , message: String){
        onTaskListener?.invoke(task,message)
    }

    fun onItemClicked(){
        onItemClickListener?.invoke()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<T> {
        val binding = inflate(LayoutInflater.from(parent.context), parent, false)
        return BaseViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BaseViewHolder<T>, position: Int) {
        bind(holder.binding, items[position])
    }

    override fun getItemCount(): Int = items.size

    abstract fun bind(binding: T, item: D)

    class BaseViewHolder<T : ViewBinding>(val binding: T) : RecyclerView.ViewHolder(binding.root)
}
