package com.example.pixelstudio

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.pixelstudio.dev.DevActivity
import com.google.android.material.bottomsheet.BottomSheetBehavior

class MainActivity : AppCompatActivity() {

    private var drawingView : DrawingView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //This function handles all the views and buttons inside the Miscellaneous layout
        initMiscellaneous()

        drawingView = findViewById(R.id.drawing_view)
        drawingView?.setSizeForBrush(2.toFloat())
    }

    //handelling layout miscellaneous
    fun initMiscellaneous() {
        //setting up the included layoutMiscellaneous in the CreateNote xml file
        val layoutMiscellaneous = findViewById<LinearLayout>(R.id.layoutMiscellaneous)
        val bottomSheetBehavior = BottomSheetBehavior.from(layoutMiscellaneous)
        //setting up an on click Listener on the textMiscellaneous textView in the layoutMiscellaneous xml file
        layoutMiscellaneous.findViewById<View>(R.id.textMiscellaneous).setOnClickListener {
            if (bottomSheetBehavior.state != BottomSheetBehavior.STATE_EXPANDED) {
                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED)
            } else {
                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED)
            }
        }

        //here developer information button is handled
        layoutMiscellaneous.findViewById<View>(R.id.developer_button)
            .setOnClickListener { //setting the behaviour of the bottom sheet layout included in this current layout
                bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED

                //Open developer activity using Intent
                Intent(applicationContext, DevActivity::class.java).also{
                    startActivity(it)
                    //adding custom activity transition animation when Intent opens a new Activity on a Button Press
                    overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
                }
            }

        //here brush button is handled
        //this button will allow users to set the brush size
        layoutMiscellaneous.findViewById<View>(R.id.brush_button)
            .setOnClickListener { //setting the behaviour of the bottom sheet layout included in this current layout
                bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED

                //logic to set the brush size
                Log.i("brush size", "Brush size button clicked")
            }
    }
}