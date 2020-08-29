package com.example.bottomnavbartests.Recycleviews.Item

import android.content.Context
import com.example.bottomnavbartests.Models.User
import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.item_text_message.*


class PersonItem(val person: User,
                 val userId: String,
                 private val context: Context
)
    : Item() {
    override fun bind(viewHolder: ViewHolder, position: Int) {
        viewHolder.textView_name.text = person.name
        viewHolder.textView_bio.text= person.bio
    }

    override fun getLayout(): Int {
        TODO("Not yet implemented")
    }


}