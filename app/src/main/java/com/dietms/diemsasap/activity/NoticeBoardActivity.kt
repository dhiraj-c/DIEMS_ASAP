package com.dietms.diemsasap.activity

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationManagerCompat
import androidx.core.view.GravityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dietms.diemsasap.R
import com.dietms.diemsasap.adapter.NoticeBoardRecyclerAdapter
import com.dietms.diemsasap.model.Notice
import com.dietms.diemsasap.model.Upload
import com.google.firebase.database.*
import com.google.firebase.storage.FirebaseStorage

class NoticeBoardActivity : AppCompatActivity() {

    lateinit var recyclerNoticeBoard: RecyclerView

    lateinit var layoutManager: RecyclerView.LayoutManager

    lateinit var toolbar: androidx.appcompat.widget.Toolbar

    lateinit var progressCircle: ProgressBar

    lateinit var recyclerAdapter: NoticeBoardRecyclerAdapter

    private lateinit var storage: FirebaseStorage

    private lateinit var databaseReference: DatabaseReference

    private lateinit var databaseListener: ValueEventListener

    private lateinit var uploads: List<Upload>

    var toolbarTitle: String? = "Notices"

    // Notification
    private lateinit var firebaseDatabase: FirebaseDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notice_board)

        if (intent != null) {
            toolbarTitle = intent.getStringExtra("Title")
        }

        toolbar = findViewById(R.id.toolbar)
        setUpToolbar()

        recyclerNoticeBoard = findViewById(R.id.recyclerNoticeBoard)
        recyclerNoticeBoard.setHasFixedSize(true)

        layoutManager = LinearLayoutManager(this)

        (layoutManager as LinearLayoutManager).reverseLayout = true
        (layoutManager as LinearLayoutManager).stackFromEnd = true

        progressCircle = findViewById(R.id.progress_circle)

//        recyclerAdapter = NoticeBoardRecyclerAdapter(this, noticeInfoList)
//
//        recyclerNoticeBoard.adapter = recyclerAdapter

        recyclerNoticeBoard.layoutManager = layoutManager

        uploads = arrayListOf()

        recyclerAdapter = NoticeBoardRecyclerAdapter(this@NoticeBoardActivity, uploads)

        recyclerNoticeBoard.adapter = recyclerAdapter

//        recyclerAdapter.setOnItemClickListener(this@NoticeBoardActivity)

        storage = FirebaseStorage.getInstance()

        databaseReference = FirebaseDatabase.getInstance().getReference("notices")

        databaseListener = databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                (uploads as ArrayList<Upload>).clear()
                for (postSnapshot in dataSnapshot.children) {
                    val upload = postSnapshot.getValue(Upload::class.java)
                    if (upload != null) {
                        upload.key = postSnapshot.key
                    }
                    (uploads as ArrayList<Upload>).add(upload!!)
                }

//                recyclerAdapter = NoticeBoardRecyclerAdapter(this@NoticeBoardActivity, uploads)
//
//                recyclerNoticeBoard.adapter = recyclerAdapter
//
//                recyclerAdapter.setOnItemClickListener(this@NoticeBoardActivity)

                recyclerAdapter.notifyDataSetChanged()

                progressCircle.visibility = View.INVISIBLE
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Toast.makeText(this@NoticeBoardActivity, databaseError.message, Toast.LENGTH_SHORT)
                    .show()

                progressCircle.visibility = View.INVISIBLE
            }
        })

        // Notification Android Oreo
        firebaseDatabase = FirebaseDatabase.getInstance()
        databaseReference.addChildEventListener(object : ChildEventListener {
            override fun onCancelled(p0: DatabaseError) {
            }

            override fun onChildMoved(p0: DataSnapshot, p1: String?) {
            }

            override fun onChildChanged(p0: DataSnapshot, p1: String?) {
            }

            @RequiresApi(Build.VERSION_CODES.O)
            override fun onChildAdded(p0: DataSnapshot, p1: String?) {
                notification()
            }

            override fun onChildRemoved(p0: DataSnapshot) {
            }
        })
    }

    fun setUpToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar?.title = toolbarTitle
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        val id = item.itemId

        if (id == android.R.id.home) {
            finish()
        }

        return super.onOptionsItemSelected(item)
    }

//    override fun onItemClick(position: Int) {
//        // Do Nothing
//    }
//
//    override fun onWhateverClick(position: Int) {
//        Toast.makeText(this@NoticeBoardActivity, "Whatever Click at position", Toast.LENGTH_SHORT)
//            .show()
//    }
//
//    override fun onDeleteClick(position: Int) {
//        val selectedItem = uploads[position]
//        val selectedKey = selectedItem.key
//
//        val imageReference = storage.getReferenceFromUrl(selectedItem.imageUrl)
//        imageReference.delete().addOnSuccessListener {
//            databaseReference.child(selectedKey).removeValue()
//            Toast.makeText(this@NoticeBoardActivity, "Notice Deleted", Toast.LENGTH_SHORT).show()
//        }
//        Toast.makeText(
//            this@NoticeBoardActivity,
//            "Delete Click at position" + position,
//            Toast.LENGTH_SHORT
//        )
//            .show()
//    }

    // Notification Android Oreo
    @RequiresApi(Build.VERSION_CODES.O)
    private fun notification() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel =
                NotificationChannel("n", "n", NotificationManager.IMPORTANCE_DEFAULT)
            val manager: NotificationManager = getSystemService<NotificationManager>(
                NotificationManager::class.java
            )
            manager.createNotificationChannel(channel)
        }
        val builder: Notification.Builder = Notification.Builder(this, "n")
            .setContentTitle("DIEMS ASAP")
            .setSmallIcon(R.drawable.ic_notification)
            .setAutoCancel(true)
            .setContentText("New Notice")
        val managerCompat: NotificationManagerCompat = NotificationManagerCompat.from(this)
        managerCompat.notify(999, builder.build())
    }

    override fun onDestroy() {
        super.onDestroy()
        databaseReference.removeEventListener(databaseListener)
    }
}
