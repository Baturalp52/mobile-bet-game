package com.termproject.fragments

import android.os.Bundle
import android.util.Log
import android.view.GestureDetector
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.core.view.GestureDetectorCompat
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.termproject.CouponRecyclerViewAdapter
import com.termproject.FixturesRecyclerViewAdapter
import com.termproject.R
import com.termproject.databinding.FragmentCouponsBinding



/**
 * A simple [Fragment] subclass.
 * Use the [CouponsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CouponsFragment : Fragment() {
    lateinit var binding: FragmentCouponsBinding
    private var gDetector: GestureDetectorCompat? = null
    private lateinit var adapter: CouponRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCouponsBinding.inflate(layoutInflater)

        val recyclerView = binding.CouponRecyclerView
        val layoutManager = LinearLayoutManager(context)
        layoutManager!!.orientation = LinearLayoutManager.VERTICAL
        recyclerView.layoutManager = layoutManager

        adapter = CouponRecyclerViewAdapter(requireContext())

        setupRecyclerView()
        gDetector = GestureDetectorCompat(requireContext(), CustomGesture())

        recyclerView.adapter = adapter
        return binding.root
    }
    private fun setupRecyclerView() {
        // ... existing RecyclerView setup ...

        val itemTouchHelperCallback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
                return false // not important in this context
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition // get position of the swiped item

                Snackbar.make(binding.CouponRecyclerView, "Bet deleted", Snackbar.LENGTH_LONG).show()
                Log.d("BET", "DELETED")
            }
        }

        val itemTouchHelper = ItemTouchHelper(itemTouchHelperCallback)
        itemTouchHelper.attachToRecyclerView(binding.CouponRecyclerView)
    }

    inner class CustomGesture: GestureDetector.SimpleOnGestureListener() {

        override fun onLongPress(e: MotionEvent) {
            Log.i("gesturebro", "onLongPress")
        }

        override fun onFling(e1: MotionEvent?, e2: MotionEvent, velocityX: Float, velocityY: Float): Boolean {
            Log.i("gesturebro", "onFling")
            return false
        }
        override fun onDoubleTapEvent(e: MotionEvent): Boolean {
            Log.i("gesturebro", "onDoubleTapEvent")
            return true
        }
    }


}