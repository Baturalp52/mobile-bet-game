package com.termproject.fragments

import android.os.Bundle
import android.util.Log
import android.view.GestureDetector
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.GestureDetectorCompat
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.termproject.CouponRecyclerViewAdapter
import com.termproject.MainActivity
import com.termproject.R
import com.termproject.databinding.FragmentCouponBinding
import com.termproject.db.coupon.CouponViewModel
import com.termproject.db.user.UserViewModel


/**
 * A simple [Fragment] subclass.
 * Use the [CouponFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CouponFragment(
    private val context: MainActivity,
    private val couponViewModel: CouponViewModel,
    private val userViewModel: UserViewModel
) : Fragment() {
    lateinit var binding: FragmentCouponBinding
    private var gDetector: GestureDetectorCompat? = null
    private lateinit var adapter: CouponRecyclerViewAdapter
    private var maxRate = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCouponBinding.inflate(layoutInflater)

        val recyclerView = binding.CouponRecyclerView
        val layoutManager = LinearLayoutManager(context)
        layoutManager!!.orientation = LinearLayoutManager.VERTICAL
        recyclerView.layoutManager = layoutManager

        adapter = CouponRecyclerViewAdapter(requireContext(), couponViewModel.coupon.playedGames)

        setupRecyclerView()
        gDetector = GestureDetectorCompat(requireContext(), CustomGesture())

        recyclerView.adapter = adapter


        if (couponViewModel.coupon.playedGames.size > 0) {
            maxRate = couponViewModel.coupon.playedGames.map { it.playedBet.odd }
                .reduce { acc, rate ->
                    acc * rate
                }
        }

        updateRateWin()

        binding.editTextText.editText?.addTextChangedListener {
            if (it != null) {
                updateRateWin()
            }
        }

        binding.btnPlay.setOnClickListener {
            val amount = binding.editTextText.editText?.text.toString().toInt()

            couponViewModel.playCoupon(amount)
            userViewModel.reduceCredit(amount)


            maxRate = 0.0
            updateRateWin()

            context.loadFragment(context.bulletinFragment)

            context.binding.bottomNav.selectedItemId = R.id.bulletin

            Toast.makeText(context, "Your coupon has created!", Toast.LENGTH_SHORT).show()

        }


        return binding.root
    }

    private fun setupRecyclerView() {
        // ... existing RecyclerView setup ...

        val itemTouchHelperCallback =
            object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
                override fun onMove(
                    recyclerView: RecyclerView,
                    viewHolder: RecyclerView.ViewHolder,
                    target: RecyclerView.ViewHolder
                ): Boolean {
                    return false // not important in this context
                }

                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {

                    val position = viewHolder.adapterPosition // get position of the swiped item

                    Snackbar.make(binding.CouponRecyclerView, "Bet deleted", Snackbar.LENGTH_LONG)
                        .show()
                    couponViewModel.coupon.playedGames.removeAt(position)
                    context.updateBadge()


                    if (couponViewModel.coupon.playedGames.size > 0) {
                        maxRate = couponViewModel.coupon.playedGames.map { it.playedBet.odd }
                            .reduce { acc, rate ->
                                acc * rate
                            }
                    }
                    updateRateWin()


                    adapter.notifyDataSetChanged()

                }
            }

        val itemTouchHelper = ItemTouchHelper(itemTouchHelperCallback)
        itemTouchHelper.attachToRecyclerView(binding.CouponRecyclerView)
    }

    private fun updateRateWin() {
        binding.maxRate.text = String.format("%.2f", maxRate)

        val maxProfitStr = binding.editTextText.editText?.text.toString()
        if (maxProfitStr.isNotEmpty()) {
            val maxProfit = maxProfitStr.toDouble() * maxRate
            binding.maxProfit.text = String.format("%.2f", maxProfit)
            binding.btnPlay.isEnabled = true
        } else {
            binding.maxProfit.text = ""
            binding.btnPlay.isEnabled = false
        }
    }

    inner class CustomGesture : GestureDetector.SimpleOnGestureListener() {

        override fun onLongPress(e: MotionEvent) {
            Log.i("gesturebro", "onLongPress")
        }

        override fun onFling(
            e1: MotionEvent?,
            e2: MotionEvent,
            velocityX: Float,
            velocityY: Float
        ): Boolean {
            Log.i("gesturebro", "onFling")
            return false
        }

        override fun onDoubleTapEvent(e: MotionEvent): Boolean {
            Log.i("gesturebro", "onDoubleTapEvent")
            return true
        }
    }


}