package com.vachara.pscmenu2.activity

import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.vachara.pscmenu2.R

class MainActivity : AppCompatActivity() {


    //สร้างตัวแปรไว้สำหรับทำงานกับ view Binging
        lateinit var navController: NavController
        lateinit var appBarConfiguration: AppBarConfiguration
        lateinit var navHostFragment: NavHostFragment
        lateinit var mainToolbar: Toolbar
        lateinit var mainDrawerLayout: DrawerLayout
        lateinit var mainNavigationView: NavigationView
        lateinit var mainBottomNavigationView: BottomNavigationView //แก้ไขเป็น BottomNavigationView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //ทำการ Find View
        mainToolbar = findViewById(R.id.main_toolbar)
        mainDrawerLayout = findViewById(R.id.drawer_layout)
        mainNavigationView = findViewById(R.id.main_navigation_view)
        mainBottomNavigationView = findViewById(R.id.main_bottom_navigation_view)

        //กำหนดค่าเริ่มต้นให้กับ nacController
        navHostFragment = supportFragmentManager.findFragmentById(R.id.main_nav_host) as NavHostFragment
        navController = navHostFragment.navController

        //กำหนด appBar
        appBarConfiguration = AppBarConfiguration.Builder(
            R.id.homeFragment,
            R.id.productFragment,
            R.id.addproductFragment,  //แก้ตัว P เป็น p เล็ก
            R.id.notificationFragment,
            R.id.accountFragment
        ).setOpenableLayout(mainDrawerLayout).build()

        //เรียกการทำงานกับ Toolbar
        setSupportActionBar(mainToolbar)

        //Toolbar แสดง ICon เมนู
        setupActionBarWithNavController(navController,appBarConfiguration)

        //เรียกใช้งานเมนูด้านข้าง
        mainNavigationView.setupWithNavController(navController)

        //เรียกใช้เมนูด่านล่าง
        mainBottomNavigationView.setupWithNavController(navController)

    }
    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController,appBarConfiguration)
    }
}