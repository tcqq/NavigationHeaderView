/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2017 Raphaël Bussa
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.example.navigationheaderview

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import com.tcqq.navigationheaderview.HeaderCallback
import com.tcqq.navigationheaderview.HeaderView
import com.tcqq.navigationheaderview.Item
import com.tcqq.navigationheaderview.Profile
import kotlinx.android.synthetic.main.activity_normal_header.*


class NormalHeaderActivity : AppCompatActivity() {

    companion object {
        private val TAG = NormalHeaderActivity::class.java.name
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_normal_header)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        toolbar.title = getString(R.string.app_name)
        toolbar.inflateMenu(R.menu.menu_main)
        toolbar.setNavigationOnClickListener { view ->
            if (!drawer_layout.isDrawerOpen(GravityCompat.START)) {
                drawer_layout.openDrawer(GravityCompat.START)
            }
        }
        val headerView = nav_view.getHeaderView(0) as HeaderView

        headerView.setOnArrowClickListener {
            headerView.setShowArrow(false)
        }

        val profile = Profile.build {
            id = 2
            username = "Raphaël Bussa"
            email = "raphaelbussa@gmail.com"
            avatarUri = "https://github.com/rebus007.png?size=512"
            backgroundUri =
                "https://images.unsplash.com/photo-1473220464492-452fb02e6221?dpr=2&auto=format&fit=crop&w=767&h=512&q=80&cs=tinysrgb&crop="
        }

        val profile2 = Profile.build {
            id = 4
            username = "Federico Gentile"
            email = "fgentile95dev@icloud.com"
            avatarUri = "https://github.com/FedeGens.png?size=512"
            backgroundUri =
                "https://images.unsplash.com/photo-1469173479606-ada03df615ac?dpr=2&auto=format&fit=crop&w=767&h=511&q=80&cs=tinysrgb&crop="
        }

        val profile3 = Profile.build {
            id = 6
            username = "Luca Rurio"
            email = "rurio.luca@gmail.com"
            avatarUri = "https://github.com/RurioLuca.png?size=512"
            backgroundUri =
                "https://images.unsplash.com/photo-1473789810014-375ed569d0ed?dpr=2&auto=format&fit=crop&w=767&h=511&q=80&cs=tinysrgb&crop="
        }

        val profile4 = Profile.build {
            id = 8
            username = "Krzysztof Klimkiewicz"
            email = "krzkz94@gmail.com"
            avatarUri = "https://github.com/krzykz.png?size=512"
            backgroundUri =
                "https://images.unsplash.com/photo-1452509133926-2b180c6d6245?dpr=2&auto=format&fit=crop&w=767&h=431&q=80&cs=tinysrgb&crop="
        }

        val item = Item.build {
            id = 1
            title = "Remove all profile"
        }

        val item2 = Item.build {
            id = 2
            title = "Remove active profile"
        }

        headerView.setStyle(HeaderView.STYLE_NORMAL)
        headerView.setTheme(HeaderView.THEME_LIGHT)
        headerView.setShowGradient(true)
        headerView.setHighlightColor(ContextCompat.getColor(this, R.color.colorSecondary))
        headerView.addProfile(profile, profile2, profile3, profile4)
        headerView.addDialogItem(item, item2)
        headerView.setDialogTitle("Choose account")
//        headerView.setShowArrow(true)
        headerView.setOnHeaderClickListener { v ->
            drawer_layout.closeDrawer(
                GravityCompat.START,
                true
            )
        }
        headerView.setFragmentManager(supportFragmentManager)
        headerView.setCallback(object : HeaderCallback() {

            override fun onSelect(id: Int, isActive: Boolean): Boolean {
                Log.d(TAG, "profile selected [$id] isActive [$isActive]")
                Toast.makeText(
                    this@NormalHeaderActivity,
                    "profile selected [$id] isActive [$isActive]",
                    Toast.LENGTH_SHORT
                ).show()
                drawer_layout.closeDrawer(GravityCompat.START, true)
                return true
            }

            override fun onItem(id: Int): Boolean {
                when (id) {
                    1 -> headerView.clearProfile()
                    2 -> {
                        val profileId = headerView.profileActive
                        headerView.removeProfile(profileId)
                    }
                }
                return true
            }
        })
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
            return
        }
        super.onBackPressed()
    }
}
