package com.burak.photoshare.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.PopupMenu
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.burak.photoshare.R
import com.burak.photoshare.adapter.PostAdapter
import com.burak.photoshare.databinding.FragmentFeedBinding
import com.burak.photoshare.model.Post
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.firestore


class FeedFragment : Fragment(), PopupMenu.OnMenuItemClickListener {

    private var _binding: FragmentFeedBinding? = null
    private val binding get() = _binding!!
    private lateinit var auth : FirebaseAuth
    private lateinit var db : FirebaseFirestore

    private lateinit var popup : PopupMenu

    val postList : ArrayList<Post> = arrayListOf()

    private var adapter : PostAdapter? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = Firebase.auth
        db = Firebase.firestore
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFeedBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.floatingActionButton.setOnClickListener { floatingButtonClicked(it) }

        popup = PopupMenu(requireContext(),binding.floatingActionButton)
        val inflater = popup.menuInflater
        inflater.inflate(R.menu.my_popup_menu,popup.menu)
        popup.setOnMenuItemClickListener(this)

        getFireStoreData()

        adapter = PostAdapter(postList)
        binding. feedRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.feedRecyclerView.adapter = adapter
    }

    private fun getFireStoreData() {
        db.collection("Posts").orderBy("date", Query.Direction.DESCENDING).addSnapshotListener { value, error ->
            if (error != null) {
                Toast.makeText(requireContext(),error.localizedMessage,Toast.LENGTH_LONG).show()
            } else {
                if (value != null) {
                    if (!value.isEmpty) {
                        postList.clear()
                        // if not null
                        val documents = value.documents
                        for(document in documents) {
                            val comment = document.get("comment") as String // casting
                            val email = document.get("email") as String
                            val downloadUrl = document.get("downloadUrl") as String

                            val post = Post(email, comment, downloadUrl)
                            postList.add(post)
                        }
                        adapter?.notifyDataSetChanged()
                    }
                }
            }
        }
    }

    fun floatingButtonClicked(view : View) {
        popup.show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onMenuItemClick(item: MenuItem?): Boolean {
        if (item?.itemId == R.id.uploadItem) {
            val action = FeedFragmentDirections.actionFeedFragmentToUploadFragment()
            Navigation.findNavController(requireView()).navigate(action)
        } else if(item?.itemId == R.id.logoutItem) {
            auth.signOut()
            val action = FeedFragmentDirections.actionFeedFragmentToUserFragment()
            Navigation.findNavController(requireView()).navigate(action)
        }

        return true
    }


}