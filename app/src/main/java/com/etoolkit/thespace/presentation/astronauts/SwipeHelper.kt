package com.etoolkit.thespace.presentation.astronauts

import android.widget.Toast
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView

abstract class SwipeHelper(private val recyclerView: RecyclerView) :
    ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.UP) {


    override fun getMovementFlags(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder
    ): Int {
        return super.getMovementFlags(recyclerView, viewHolder)

    }

    override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
        return false
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        Toast.makeText(this.recyclerView.context, "on Swiped", Toast.LENGTH_SHORT).show()
    }

}