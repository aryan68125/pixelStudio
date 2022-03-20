package com.example.pixelstudio

import android.app.Dialog
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.os.Environment
import android.os.Handler
import android.util.Log
import android.view.View
import android.view.Window
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.pixelstudio.dev.DevActivity
import com.google.android.material.bottomsheet.BottomSheetBehavior
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream


class MainActivity : AppCompatActivity() {

    private var drawingView : DrawingView? = null
    var selectedBrushColor = ""
    var eraserColor = ""

    //this variable is required by the custom progress dialog when the image is being saved in the storage of this device
    var customProgressDialog : Dialog? = null

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

        //here undo button is handled
        layoutMiscellaneous.findViewById<View>(R.id.undo_button)
            .setOnClickListener { //setting the behaviour of the bottom sheet layout included in this current layout

                //Logic to undo an action in the application
                //call the function to undo the drawing from the DrawingView file
                drawingView?.onClickUndo()
            }

        //here redo button is handled
        layoutMiscellaneous.findViewById<View>(R.id.redo_button)
            .setOnClickListener { //setting the behaviour of the bottom sheet layout included in this current layout

                //Logic to undo an action in the application
                //call the function to undo the drawing from the DrawingView file
                drawingView?.onClickRedo()
            }

        //here save button is handled
//        layoutMiscellaneous.findViewById<View>(R.id.save_button)
//            .setOnClickListener { //setting the behaviour of the bottom sheet layout included in this current layout
//
//                //showing the save dialog box animation to the user
//                showProgressDialog()
//
////                saving the drawn image Logic or Function will come here
//                lifecycleScope.launch {
//                    //here we will run the process of saving file
//                    val flDrawingView : FrameLayout = findViewById(R.id.fl_drawing_view_container)
//                    saveBitmapFile(getBitmapFromView(flDrawingView))
//
//                }
//
//            }
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

    //This function will convert the drawn image on the screen of this application into a Bitmap OR an image which can be stored in teh device's memory OR internal storage
    private fun getBitmapFromView(view: View) : Bitmap
    {
          //Logic to convert a view into a bitmap
        val returnedBitmap = Bitmap.createBitmap(view.width, view.height, Bitmap.Config.ARGB_8888)
        //bind the canvas that is on the view
        val canvas = Canvas(returnedBitmap)
        view.draw(canvas)
        return returnedBitmap
    }

    //Create a coroutine so that we can run the saving of a file into the background
    private suspend fun saveBitmapFile(mBitmap : Bitmap?) : String {
        var result = ""
        withContext(Dispatchers.IO){
            if(mBitmap != null){
                try{
                    val byte = ByteArrayOutputStream()
                    mBitmap.compress(Bitmap.CompressFormat.PNG,100, byte)

                    val file_id: Long = System.currentTimeMillis()/1000
                    val filename = "PixelStudio" + file_id + ".png"
                    val path = externalCacheDir?.absoluteFile.toString() + File.separator + filename

                    val file = File(path)
                    val fileOutputStream = FileOutputStream(file)
                    fileOutputStream.write(byte.toByteArray())
                    fileOutputStream.close()
                    result = file.absolutePath
                    runOnUiThread{
                        if(result.isNotEmpty()){
                            Toast.makeText(this@MainActivity, "File saved successfully : $result", Toast.LENGTH_LONG).show()
                        }
                        else{
                            Toast.makeText(this@MainActivity, "Oops!!! Something wen wrong while saving the file", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
                catch(e: Exception){
                    result = ""
                    e.printStackTrace()
                }

            }
        }
        return result
    }

    //this function will handle the progress dialog and show it when the user is saving the file
    private fun showProgressDialog(){
        customProgressDialog = Dialog(this@MainActivity)

        customProgressDialog?.requestWindowFeature(Window.FEATURE_NO_TITLE)

        //set the screen content of the dialogbox from the custom layout resource file
        customProgressDialog?.setContentView(R.layout.dialog_cutom_progress)

        val v : View = getWindow().getDecorView();
        v.setBackgroundResource(android.R.color.transparent)

        //start the dialog box
        customProgressDialog?.show()

        //running a delay of three seconds in the background
        lifecycleScope.launch {
            // we used the postDelayed(Runnable, time) method
            // to send a message with a delayed time.
            Handler().postDelayed({
                //here we will write the code to remove the dialog box from the screen
                if (customProgressDialog!=null){
                    customProgressDialog?.dismiss()
                    customProgressDialog = null
                }
            }, 2570) // 3000 is the delayed time in milliseconds.
        }

    }
}