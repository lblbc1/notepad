package cn.hsp.notepad.ui

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import cn.hsp.notepad.R
import cn.hsp.notepad.db.Note
import kotlinx.android.synthetic.main.item_note.view.*

/**
 * 厦门大学计算机专业 | 前华为工程师
 * 专注《零基础学编程系列》https://cxyxy.blog.csdn.net/article/details/121134634
 * 包含：Java | 安卓 | 前端 | Flutter | iOS | 小程序 | 鸿蒙
 * 公众号：花生皮编程
 */
class NoteAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var mDataList = mutableListOf<Note>()
    private lateinit var mContext: Context
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val data = mDataList[position]
        holder.itemView.textView.text = data.content
        holder.itemView.setOnClickListener {
            gotoEditNote(data.id)
        }
    }

    fun setData(dataList: List<Note>) {
        mDataList.clear()
        mDataList.addAll(dataList)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = mDataList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        mContext = parent.context
        val view = LayoutInflater.from(mContext).inflate(R.layout.item_note, parent, false)
        return ViewHolder(view)
    }

    private fun gotoEditNote(noteId: Int) {
        val intent = Intent(mContext, EditNoteActivity().javaClass)
        intent.putExtra("noteId", noteId)
        mContext.startActivity(intent)
    }

    class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView!!)

}