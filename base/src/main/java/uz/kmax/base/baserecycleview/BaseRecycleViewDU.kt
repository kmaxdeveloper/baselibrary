package uz.kmax.base.baserecycleview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import uz.kmax.base.tools.BaseDiffUtilCallback
import uz.kmax.base.typlealias.BaseInflate

/** 16.01.2025 y.
 *  RecycleView ni ixchamlashtirilgan kodi
 *  Kmax Programmers
 *
 */

abstract class BaseRecycleViewDU<T : ViewBinding, D>(
    private val inflate: BaseInflate<T>
) : RecyclerView.Adapter<BaseRecycleViewDU.BaseViewHolder<T>>() {

    private var onTaskListener: ((task : Int , message : String) -> Unit)? = null
    fun setOnTaskListener(listener: (task : Int , message : String) -> Unit) { onTaskListener = listener }

    private var onItemClickListener: (() -> Unit)? = null
    fun setOnItemClickListener(listener: () -> Unit) { onItemClickListener = listener }

    private val items = ArrayList<D>()

    fun setItems(newItems: List<D>) {
        val diffCallback = BaseDiffUtilCallback(
            oldList = items,
            newList = newItems,
            areItemsTheSame = ::areItemsTheSame,
            areContentsTheSame = ::areContentsTheSame
        )
        val diffResult = DiffUtil.calculateDiff(diffCallback)

        items.clear()
        items.addAll(newItems)
        diffResult.dispatchUpdatesTo(this)
    }

    abstract fun areItemsTheSame(oldItem: D, newItem: D): Boolean
    abstract fun areContentsTheSame(oldItem: D, newItem: D): Boolean

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
