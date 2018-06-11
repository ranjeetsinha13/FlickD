package com.rs.flickd.widgets

import android.content.Context
import android.graphics.Rect
import android.util.AttributeSet
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rs.flickd.R

class MoviesListRecycleView : RecyclerView {

    private var columnCount: Int = 0

    private var isActive: Boolean = false

    private var needUpdateItemDecoration: Boolean = false

    inner class SpacesItemDecoration(private val space: Int, leftAndRightPadding: Int,
                                     columnWidth: Int, contentWidth: Int) : RecyclerView.ItemDecoration() {
        private val offsets: Array<IntArray>

        init {
            val totalPadding = columnWidth - contentWidth
            offsets = Array(columnCount) { IntArray(2) }
            offsets[0][0] = leftAndRightPadding + space
            for (col in 0 until columnCount - 1) {
                val offsetRight = totalPadding - offsets[col][0]
                offsets[col][1] = offsetRight
                offsets[col + 1][0] = space - offsetRight
            }

            offsets[columnCount - 1][1] = offsets[0][0]
        }

        override fun getItemOffsets(outsideRect: Rect, view: View, parent: RecyclerView, state: State) {
            // Calculate header count.
            // Since headers may change at runtime so need to re-calculate every time.
            // CAUTION: assume header view type will not be 0.

            val headerCount = getHeaderCount(parent)

            var position = parent.getChildLayoutPosition(view)

            // No offset for headers.
            if (position < headerCount) {
                outsideRect.apply {
                    top = 0
                    left = 0
                    right = 0
                    bottom = 0
                }
                return
            }

            position -= headerCount
            val row = position / columnCount // item row
            val column = position % columnCount // item column

            // Add top margin only for the first row to avoid double space between items
            if (row == 0) {
                outsideRect.top = space
            } else {
                outsideRect.top = 0
            }

            outsideRect.apply {
                bottom = space
                left = offsets[column][0]
                right = offsets[column][1]
            }
        }

        private fun getHeaderCount(recyclerView: RecyclerView): Int {
            val adapter = recyclerView.adapter
            var headerCount = 0
            for (index in 0 until adapter?.itemCount!!) {
                if (adapter.getItemViewType(index) == 0) {
                    break
                } else {
                    ++headerCount
                }
            }

            return headerCount
        }
    }

    constructor(context: Context) : super(context) {
        init(context, null, 0)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init(context, attrs, 0)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyle: Int) : super(context, attrs, defStyle) {
        init(context, attrs, defStyle)
    }

    private val adapterDataObserver = object : RecyclerView.AdapterDataObserver() {
        override fun onChanged() {
            updateItemDecoration()
        }

        override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {
            // Invalidate if the items are not appended at the end.
            if (adapter?.itemCount != positionStart + itemCount) {
                updateItemDecoration()
            }
        }

        override fun onItemRangeRemoved(positionStart: Int, itemCount: Int) {
            // Invalidate if the items are not removed at the end.
            if (adapter?.itemCount != positionStart) {
                updateItemDecoration()
            }
        }

        override fun onItemRangeMoved(fromPosition: Int, toPosition: Int, itemCount: Int) {
            updateItemDecoration()
        }

        private fun updateItemDecoration() {
            if (isActive) {
                invalidateItemDecorations()
                layoutManager?.requestLayout()
            } else {
                needUpdateItemDecoration = true
            }
        }
    }

    override fun setAdapter(adapter: RecyclerView.Adapter<*>?) {
        val currentAdapter = getAdapter()
        if (currentAdapter != null && currentAdapter != adapter) { // Compare object, not content.
            currentAdapter.unregisterAdapterDataObserver(adapterDataObserver)
        }

        super.setAdapter(adapter)
        if (adapter == null) {
            return
        }

        adapter.registerAdapterDataObserver(adapterDataObserver)
    }

    private fun init(context: Context, attrs: AttributeSet?, defStyle: Int) {
        setHasFixedSize(true)
        var minCellWidth = resources.getDimensionPixelSize(R.dimen.default_image_width)
        var spacing = resources.getDimensionPixelSize(R.dimen.image_space)

        if (attrs != null) {
            val typeArray = context.obtainStyledAttributes(
                    attrs,
                    R.styleable.MoviesListRecycleView, defStyle, 0
            )

            val n = typeArray.indexCount
            for (i in 0 until n) {
                val attr = typeArray.getIndex(i)
                if (attr == R.styleable.MoviesListRecycleView_min_cell_width) {
                    minCellWidth = typeArray.getDimensionPixelSize(
                            R.styleable.MoviesListRecycleView_min_cell_width,
                            minCellWidth
                    )
                } else if (attr == R.styleable.MoviesListRecycleView_cell_spacing) {
                    spacing = typeArray.getDimensionPixelSize(
                            R.styleable.MoviesListRecycleView_cell_spacing, spacing
                    )
                }
            }

            typeArray.recycle()
        }

        initColumns(minCellWidth, spacing)
    }

    private fun initColumns(minCellWidth: Int, spacing: Int) {
        val metrics = resources.displayMetrics
        val width = metrics.widthPixels
        val realSpacing = (spacing + 1) / 2 * 2 // Make spacing the multiple of 2.
        columnCount = width / minCellWidth
        if (columnCount <= 1) { // Minimum: one column
            columnCount = 1
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(SpacesItemDecoration(realSpacing, 0, width, width - 2 * realSpacing))
        } else {
            val columnWidth = width / columnCount
            val totalSpacing = realSpacing * (columnCount + 1)
            var gridContentWidth = (width - totalSpacing) / columnCount
            gridContentWidth = gridContentWidth / 2 * 2  // Make content width also the multiple of 2.
            val leftAndRightPadding = (width - gridContentWidth * columnCount - totalSpacing) / 2
            layoutManager = GridLayoutManager(context, columnCount)
            addItemDecoration(
                    SpacesItemDecoration(realSpacing, leftAndRightPadding, columnWidth, gridContentWidth)
            )
        }
    }

}



