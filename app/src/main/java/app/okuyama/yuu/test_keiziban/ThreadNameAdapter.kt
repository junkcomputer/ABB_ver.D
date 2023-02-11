package app.okuyama.yuu.test_keiziban

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import app.okuyama.yuu.test_keiziban.databinding.ThreadNameBinding
import com.google.firebase.ktx.Firebase
import org.intellij.lang.annotations.JdkConstants.TitledBorderTitlePosition

class ThreadNameAdapter : RecyclerView.Adapter<ThreadNameViewHolder>(){
    //private val ThreadList: MutableList<Datas> = mutableListOf()
    private val ThreadList: MutableList<ThreadNames> = mutableListOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ThreadNameViewHolder {
        val binding = ThreadNameBinding.inflate(LayoutInflater.from(parent.context),parent, false)
        return ThreadNameViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ThreadNameViewHolder,position: Int) {
        //val Datas = ThreadList[position]
        val ThreadNames = ThreadList[position]
        //holder.binding.nametextView.text = Datas.name
        holder.binding.nametextView.text = ThreadNames.thread
    }

    override fun getItemCount(): Int {
        return ThreadList.size
    }

    //fun updateThreads(newList: List<Datas>) {
    fun updateThreads(newList: List<ThreadNames>) {
        ThreadList.clear()
        ThreadList.addAll(newList)
        notifyDataSetChanged()
    }
}