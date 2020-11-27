package com.dietms.diemsasap.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.dietms.diemsasap.R
import com.dietms.diemsasap.activity.NoticeBoardActivity
import com.dietms.diemsasap.model.Board
import kotlinx.android.synthetic.main.recycler_dashboard_single_row.view.*

class DashboardRecyclerAdapter(val context: Context, val itemList: ArrayList<Board>) :
    RecyclerView.Adapter<DashboardRecyclerAdapter.DashboardViewHolder>() {

    class DashboardViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val txtBoardName: TextView = view.findViewById(R.id.txtBoardName)
        val txtBoardDepartment: TextView = view.findViewById(R.id.txtBoardDepartment)
        val imgBoardImage: ImageView = view.findViewById(R.id.imgBoardImage)
        val llContent: LinearLayout = view.findViewById(R.id.llContent)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DashboardViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_dashboard_single_row, parent, false)

        return DashboardViewHolder(view)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: DashboardViewHolder, position: Int) {
        val board = itemList[position]
        holder.txtBoardName.text = board.boardName
        holder.txtBoardDepartment.text = board.boardDepartment
        holder.imgBoardImage.setImageResource(board.boardImage)

        holder.llContent.setOnClickListener {
//            Toast.makeText(context, "Clicked on ${holder.txtBoardName.text}", Toast.LENGTH_SHORT)
//                .show()
            val intent = Intent(context, NoticeBoardActivity::class.java)
            intent.putExtra("Title", board.boardName)
            context.startActivity(intent)
        }
    }
}