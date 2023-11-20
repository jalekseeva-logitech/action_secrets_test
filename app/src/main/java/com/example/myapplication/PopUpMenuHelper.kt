package com.example.myapplication

import android.content.Context
import android.view.ContextThemeWrapper
import android.view.Gravity
import android.view.MenuItem
import android.view.View
import androidx.annotation.MenuRes
import androidx.appcompat.widget.PopupMenu

class PopUpMenuHelper {

    var latestShownPopupMenu: PopupMenu? = null

    fun showPopupMenu(
        anchorView: View,
        @MenuRes popUpMenu: Int,
        menuItems: List<String>? = null,
        onPopupMenuShown: ((popupMenu: PopupMenu) -> Unit)? = null,
        onMenuItemClickListener: ((item: MenuItem) -> Unit)? = null,
    ) {
        dismissVisibleMenu()
        val wrapper: Context = ContextThemeWrapper(anchorView.context, R.style.PopupMenuStyle)
        val popup = PopupMenu(wrapper, anchorView, Gravity.END)
        menuItems?.forEach { data ->
            popup.menu.add(data)
        }
        popup.menuInflater.inflate(popUpMenu, popup.menu)
        popup.setOnMenuItemClickListener {
            onMenuItemClickListener?.invoke(it)
            true
        }
        popup.show()
        onPopupMenuShown?.invoke(popup)
        latestShownPopupMenu = popup
    }

    fun dismissVisibleMenu() {
        latestShownPopupMenu?.dismiss()
    }
}