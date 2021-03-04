package com.clp3z.xapotestapp.base.interfaces

/**
 * Created by Clelia López on 02/26/21
 */
interface Listener {

    interface HomeViewListener {

        fun onViewScroll(
            visibleItemCount: Int,
            totalItemCount: Int,
            firstVisibleItemPosition: Int
        )

        fun onItemSelected(id: Int)
    }
}
