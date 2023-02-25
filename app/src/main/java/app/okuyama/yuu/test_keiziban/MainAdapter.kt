package app.okuyama.yuu.test_keiziban

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import app.okuyama.yuu.test_keiziban.databinding.MainRecyclerBinding
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class MainAdapter : RecyclerView.Adapter<MainViewHolder>(){
    val nameList: MutableList<Datas> = mutableListOf()
    val textList: MutableList<Datas> = mutableListOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val binding = MainRecyclerBinding.inflate(LayoutInflater.from(parent.context),parent, false)
        return MainViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainViewHolder,position: Int) {
        Log.d("namelist" ,position.toString())
        Log.d("textlist",nameList[position].toString())
        //val tempName = nameList[position]
        //val tempText = textList[position]
        holder.binding.nametextview.text = nameList[position].name.toString()
        holder.binding.texttextview.text = textList[position].text.toString()
        //Log.d("xxxxxx",tempName.name.toString())
    }

    override fun getItemCount(): Int = nameList.size

    fun updateThreads(newList: List<Datas>) {
        nameList.clear()
        nameList.addAll(newList)
        textList.clear()
        textList.addAll(newList)
        notifyDataSetChanged()
    }
}