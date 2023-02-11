package app.okuyama.yuu.test_keiziban

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import app.okuyama.yuu.test_keiziban.databinding.MainRecyclerBinding

class MainAdapter : RecyclerView.Adapter<MainViewHolder>(){
    private val MainList: MutableList<Datas> = mutableListOf()
    //private val ThreadList: MutableList<ThreadNames> = mutableListOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val binding = MainRecyclerBinding.inflate(LayoutInflater.from(parent.context),parent, false)
        return MainViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainViewHolder,position: Int) {
        val Datas = MainList[position]
        //val ThreadNames = ThreadList[position]
        holder.binding.nametextview.text = Datas.name
        holder.binding.texttextview.text = Datas.text
        //holder.binding.nametextView.text = ThreadNames.thread
    }

    override fun getItemCount(): Int {
        return MainList.size
    }

    fun updateThreads(newList: List<Datas>) {
    //fun updateThreads(newList: List<ThreadNames>) {
        MainList.clear()
        MainList.addAll(newList)
        notifyDataSetChanged()
    }
}