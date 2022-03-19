package com.example.pixelstudio

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.example.pixelstudio.dev.DevActivity
import com.google.android.material.bottomsheet.BottomSheetBehavior

class MainActivity : AppCompatActivity() {

    private var drawingView : DrawingView? = null
    var selectedBrushColor = ""
    var eraserColor = ""

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

        //setting up the color selector for the brush here
        val imageColor1 = layoutMiscellaneous.findViewById<ImageView>(R.id.imageColor1)
        val imageColor2 = layoutMiscellaneous.findViewById<ImageView>(R.id.imageColor2)
        val imageColor3 = layoutMiscellaneous.findViewById<ImageView>(R.id.imageColor3)
        val imageColor4 = layoutMiscellaneous.findViewById<ImageView>(R.id.imageColor4)
        val imageColor5 = layoutMiscellaneous.findViewById<ImageView>(R.id.imageColor5)
        val imageColor6 = layoutMiscellaneous.findViewById<ImageView>(R.id.imageColor6)
        val imageColor7 = layoutMiscellaneous.findViewById<ImageView>(R.id.imageColor7)

        //The functions below will set the brushes color when the user selects a color
        layoutMiscellaneous.findViewById<View>(R.id.imageColor1).setOnClickListener {
            selectedBrushColor = "#FFBB86FC"
            //set image programetically
            imageColor1.setImageResource(R.drawable.ic_done)
            imageColor2.setImageResource(0)
            imageColor3.setImageResource(0)
            imageColor4.setImageResource(0)
            imageColor5.setImageResource(0)
            imageColor6.setImageResource(0)
            imageColor7.setImageResource(0)
            //now setting the color of the brush here
            drawingView?.setColor(selectedBrushColor)
        }

        layoutMiscellaneous.findViewById<View>(R.id.imageColor2).setOnClickListener {
            selectedBrushColor = "#FDD835"
            //set image programetically
            imageColor1.setImageResource(0)
            imageColor2.setImageResource(R.drawable.ic_done)
            imageColor3.setImageResource(0)
            imageColor4.setImageResource(0)
            imageColor5.setImageResource(0)
            imageColor6.setImageResource(0)
            imageColor7.setImageResource(0)
            //now setting the color of the brush here
            drawingView?.setColor(selectedBrushColor)
        }

        layoutMiscellaneous.findViewById<View>(R.id.imageColor3).setOnClickListener {
            selectedBrushColor = "#F44336"
            //set image programetically
            imageColor1.setImageResource(0)
            imageColor2.setImageResource(0)
            imageColor3.setImageResource(R.drawable.ic_done)
            imageColor4.setImageResource(0)
            imageColor5.setImageResource(0)
            imageColor6.setImageResource(0)
            imageColor7.setImageResource(0)
            //now setting the color of the brush here
            drawingView?.setColor(selectedBrushColor)
        }

        layoutMiscellaneous.findViewById<View>(R.id.imageColor4).setOnClickListener {
            selectedBrushColor = "#8E24AA"
            //set image programetically
            imageColor1.setImageResource(0)
            imageColor2.setImageResource(0)
            imageColor3.setImageResource(0)
            imageColor4.setImageResource(R.drawable.ic_done)
            imageColor5.setImageResource(0)
            imageColor6.setImageResource(0)
            imageColor7.setImageResource(0)
            //now setting the color of the brush here
            drawingView?.setColor(selectedBrushColor)
        }

        layoutMiscellaneous.findViewById<View>(R.id.imageColor5).setOnClickListener {
            selectedBrushColor = "#43A047"
            //set image programetically
            imageColor1.setImageResource(0)
            imageColor2.setImageResource(0)
            imageColor3.setImageResource(0)
            imageColor4.setImageResource(0)
            imageColor5.setImageResource(R.drawable.ic_done)
            imageColor6.setImageResource(0)
            imageColor7.setImageResource(0)
            //now setting the color of the brush here
            drawingView?.setColor(selectedBrushColor)
        }

        layoutMiscellaneous.findViewById<View>(R.id.imageColor6).setOnClickListener {
            selectedBrushColor = "#212B6A"
            //set image programetically
            imageColor1.setImageResource(0)
            imageColor2.setImageResource(0)
            imageColor3.setImageResource(0)
            imageColor4.setImageResource(0)
            imageColor5.setImageResource(0)
            imageColor6.setImageResource(R.drawable.ic_done)
            imageColor7.setImageResource(0)
            //now setting the color of the brush here
            drawingView?.setColor(selectedBrushColor)
        }

        layoutMiscellaneous.findViewById<View>(R.id.imageColor7).setOnClickListener {
            selectedBrushColor = "#000000"
            //set image programetically
            imageColor1.setImageResource(0)
            imageColor2.setImageResource(0)
            imageColor3.setImageResource(0)
            imageColor4.setImageResource(0)
            imageColor5.setImageResource(0)
            imageColor6.setImageResource(0)
            imageColor7.setImageResource(R.drawable.ic_done)
            //now setting the color of the brush here
            drawingView?.setColor(selectedBrushColor)
        }

        //here eraser button is handled
        layoutMiscellaneous.findViewById<View>(R.id.eraser)
            .setOnClickListener { //setting the behaviour of the bottom sheet layout included in this current layout
                bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED

                //open eraser size selector dialog box via eraser size selector dialog box
                showEraserSizeChooserDialog()
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

        //here brush size selector button is handled
        //this button will allow users to set the brush size
        layoutMiscellaneous.findViewById<View>(R.id.brush_button)
            .setOnClickListener { //setting the behaviour of the bottom sheet layout included in this current layout
                bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED

                //logic to set the brush size
                Log.i("brush size", "Brush size button clicked")
                //call the BrushsizeChooser dailog function that will activate the brush size chooser dialog button when the brush button is pressed
                showBrushSizeChooserDialog()
            }
    }

    //this function handles the functionality of the brush button dialog box
    //it will allow user to choose the size of the brush that they want
    private fun showBrushSizeChooserDialog(){
        //create anm object so that we can set the brush chooser dialog box
        val brushDialog = Dialog(this)
        brushDialog.setContentView(R.layout.dialog_brush_size)
        brushDialog.setTitle("Brush Size: ")

        //setting up an onclick listener on small brush button in the dialog box
        brushDialog.findViewById<View>(R.id.ib_small_brush)
            .setOnClickListener {

                //logic to select the brush size == small
                drawingView?.setSizeForBrush(5.toFloat())
                if(selectedBrushColor.isEmpty() || selectedBrushColor == null || selectedBrushColor == "")
                {
                    selectedBrushColor = "#000000"
                    drawingView?.setColor(selectedBrushColor)
                    brushDialog.dismiss()
                }
                else{
                    drawingView?.setColor(selectedBrushColor)
                    brushDialog.dismiss()
                }
            }

        //setting up an onclick listener on medium brush button in the dialog box
        brushDialog.findViewById<View>(R.id.ib_medium_brush)
            .setOnClickListener {

                //logic to select the brush size == medium
                drawingView?.setSizeForBrush(15.toFloat())
                if(selectedBrushColor.isEmpty() || selectedBrushColor == null || selectedBrushColor == "")
                {
                    selectedBrushColor = "#000000"
                    drawingView?.setColor(selectedBrushColor)
                    brushDialog.dismiss()
                }
                else{
                    drawingView?.setColor(selectedBrushColor)
                    brushDialog.dismiss()
                }
            }

        //setting up an onclick listener on large brush button in the dialog box
        brushDialog.findViewById<View>(R.id.ib_large_brush)
            .setOnClickListener {

                //logic to select the brush size == large
                drawingView?.setSizeForBrush(25.toFloat())
                if(selectedBrushColor.isEmpty() || selectedBrushColor == null || selectedBrushColor == "")
                {
                    selectedBrushColor = "#000000"
                    drawingView?.setColor(selectedBrushColor)
                    brushDialog.dismiss()
                }
                else{
                    drawingView?.setColor(selectedBrushColor)
                    brushDialog.dismiss()
                }
            }

        brushDialog.show()
    }

    //this function handles the functionality of the eraser button dialog box
    //it will allow user to choose the size of the eraser that they want
    private fun showEraserSizeChooserDialog(){
        //create anm object so that we can set the brush chooser dialog box
        val eraserDialog = Dialog(this)
        eraserDialog.setContentView(R.layout.eraser_size)
        eraserDialog.setTitle("Eraser Size: ")

        //setting up an onclick listener on small brush button in the dialog box
        eraserDialog.findViewById<View>(R.id.ib_small_eraser)
            .setOnClickListener {

                //logic to select the brush size == small
                drawingView?.setSizeForBrush(5.toFloat())
                eraserColor = "#FFFFFFFF"
                drawingView?.setColor(eraserColor)
                eraserDialog.dismiss()
            }

        //setting up an onclick listener on medium brush button in the dialog box
        eraserDialog.findViewById<View>(R.id.ib_medium_eraser)
            .setOnClickListener {

                //logic to select the brush size == medium
                drawingView?.setSizeForBrush(15.toFloat())
                eraserColor = "#FFFFFFFF"
                drawingView?.setColor(eraserColor)
                eraserDialog.dismiss()
            }

        //setting up an onclick listener on large brush button in the dialog box
        eraserDialog.findViewById<View>(R.id.ib_large_eraser)
            .setOnClickListener {

                //logic to select the brush size == large
                drawingView?.setSizeForBrush(25.toFloat())
                eraserColor = "#FFFFFFFF"
                drawingView?.setColor(eraserColor)
                eraserDialog.dismiss()
            }

        eraserDialog.show()
    }
}