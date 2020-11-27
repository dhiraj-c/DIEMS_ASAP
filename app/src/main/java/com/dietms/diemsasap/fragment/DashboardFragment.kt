package com.dietms.diemsasap.fragment

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dietms.diemsasap.R
import com.dietms.diemsasap.adapter.DashboardRecyclerAdapter
import com.dietms.diemsasap.model.Board
import com.dietms.diemsasap.model.Upload
import com.google.firebase.database.*


class DashboardFragment : Fragment() {

    lateinit var recyclerDashboard: RecyclerView

    lateinit var layoutManager: RecyclerView.LayoutManager

//    lateinit var btnCheckInternet: Button

    lateinit var recyclerAdapter: DashboardRecyclerAdapter

    val boardInfoList = arrayListOf<Board>(
        Board("Notice Board", "", R.drawable.ic_dashboard)
//        Board("Scholarship Notice", "Department Name", R.drawable.ic_dashboard),
//        Board("Accounts Notice", "Department Name", R.drawable.ic_dashboard),
//        Board("Sports Notice", "Department Name", R.drawable.ic_dashboard)
    )

    // Notification
//    private lateinit var databaseReference: DatabaseReference
//    private lateinit var firebaseDatabase: FirebaseDatabase
//    private lateinit var uploads: List<Upload>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_dashboard, container, false)

        recyclerDashboard = view.findViewById(R.id.recyclerDashboard)

        // Notification Android Oreo
//        firebaseDatabase = FirebaseDatabase.getInstance()
//        databaseReference.addChildEventListener(object : ChildEventListener {
//            override fun onCancelled(p0: DatabaseError) {
//            }
//
//            override fun onChildMoved(p0: DataSnapshot, p1: String?) {
//            }
//
//            override fun onChildChanged(p0: DataSnapshot, p1: String?) {
//            }
//
//            @RequiresApi(Build.VERSION_CODES.O)
//            override fun onChildAdded(p0: DataSnapshot, p1: String?) {
//                notification()
//            }
//
//            override fun onChildRemoved(p0: DataSnapshot) {
//            }
//        })

//        btnCheckInternet = view.findViewById(R.id.btnCheckInternet)
//
//        btnCheckInternet.setOnClickListener {
//            if (ConnectionManager().checkconnectivity(activity as Context)) {
//                // Internet is Available
//                val dialog = AlertDialog.Builder(activity as Context)
//                dialog.setTitle("Success")
//                dialog.setMessage("Internet Connection Found")
//                dialog.setPositiveButton("Ok") { text, listener ->
//                    // Do nothing
//                }
//                dialog.setNegativeButton("Cancel") { text, listener ->
//                    // Do nothing
//                }
//                dialog.create()
//                dialog.show()
//            } else {
//                // Internet is not available
//                val dialog = AlertDialog.Builder(activity as Context)
//                dialog.setTitle("Error")
//                dialog.setMessage("Internet Connection Not Found")
//                dialog.setPositiveButton("Ok") { text, listener ->
//                    // Do nothing
//                }
//                dialog.setNegativeButton("Cancel") { text, listener ->
//                    // Do nothing
//                }
//                dialog.create()
//                dialog.show()
//            }
//        }

        layoutManager = LinearLayoutManager(activity)

        recyclerAdapter = DashboardRecyclerAdapter(activity as Context, boardInfoList)

        recyclerDashboard.adapter = recyclerAdapter

        recyclerDashboard.layoutManager = layoutManager

//        recyclerDashboard.addItemDecoration(
//            DividerItemDecoration(
//                recyclerDashboard.context,
//                (layoutManager as LinearLayoutManager).orientation
//            )
//        )

        return view
    }

    // Notification Android Oreo
//    @RequiresApi(Build.VERSION_CODES.O)
//    private fun notification() {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            val channel =
//                NotificationChannel("n", "n", NotificationManager.IMPORTANCE_DEFAULT)
//            val manager =
//                context?.let { getSystemService<NotificationManager>(it? })
////            val manager: NotificationManager = getSystemService<NotificationManager>(
////                NotificationManager::class.java
////            )
//
//            manager?.createNotificationChannel(channel)
//        }
//        val builder: Notification.Builder = Notification.Builder(context, "n")
//            .setContentTitle("DIEMS ASAP")
//            .setSmallIcon(R.drawable.ic_notification)
//            .setAutoCancel(true)
//            .setContentText("New Notice")
//        val managerCompat: NotificationManagerCompat? = context?.let {
//            NotificationManagerCompat.from(
//                it
//            )
//        }
//        managerCompat?.notify(999, builder.build())
//    }
}
