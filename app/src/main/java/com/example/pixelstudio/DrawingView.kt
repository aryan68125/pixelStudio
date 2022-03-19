package com.example.pixelstudio

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.TypedValue
import android.view.MotionEvent
import android.view.View

class DrawingView(context : Context, attrs: AttributeSet) : View(context, attrs){
    //we are going to use this as a view
    //this class will be visible inside the main activity
    //if you want to draw something then you need to do those tasks in classes of type view
    //In order to draw on a View we need a higher level custom class that holds the color the geometry the type of brush and art you are using etc
    //also we are going to be using a Bitmap that we will be drawing on

    //VARIABLES REQUIRED TO DRAW ON THE SCREEN OF THIS APPLICATION
    //customPath is a custom class that we will create
    private var mDrawPath : CustomPath?= null
    private var mCanvasBitmap : Bitmap? = null
    private var mDrawPaint : Paint? = null
    private var mCanvasPaint : Paint? = null
    private var mBrushSize : Float = 0.toFloat()
    private var color = Color.BLACK
    //This is the canvas that will allow us to draw geometry on top of it
    private var canvas: Canvas? = null

    //inorder to make sure that our line that we draw on the screen remain on the screen for the duration of the an Activity lifeCycle we need an arrayList
    //we need to add our mdrawPaths to this paths which is an ArrayList
    //we are essentially storing the drawn path by the user into a variable and then redrawing it onto the canvas of the screen
    private val mPaths = ArrayList<CustomPath>()

    //SETTING UP THE VARIABLES
    init{
        //call the method that will initialize the variables required by this application
        setupDrawing()
    }

    //this function will initialize all the variables that is required by this application
    private fun setupDrawing(){
        mDrawPaint = Paint()
        mDrawPath =  CustomPath(color,mBrushSize)
        mDrawPaint!!.color = color
        mDrawPaint!!.style = Paint.Style.STROKE //style of the brush stroke drawn on the canvas
        mDrawPaint!!.strokeJoin = Paint.Join.ROUND //setting up the starting and the ending of the brush stroke to be rounded
        mDrawPaint!!.strokeCap= Paint.Cap.ROUND //paint's Line cap style :->  The Cap specifies the treatment for the beginning and ending of stroked lines and paths.
        mCanvasPaint = Paint(Paint.DITHER_FLAG)
        //mBrushSize = 20.toFloat() we don't need it now because we are going to setup the brush size in our main activity
    }

    //overriding the onsizeChanged function in View class
    //This fun will display our canvas on the application screen in the activity
    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w,h,oldw,oldh)
        //setting up the canvas Bitmap
        //ARGB_8888 means is that each pixel is stored in 4 bytes i.e we are setting up the amount of colors that we are able to use
        mCanvasBitmap = Bitmap.createBitmap(w,h,Bitmap.Config.ARGB_8888)

        //setting up our canvas
        //here we will set our canvas each time our size is changed
        //next thing is we need to use this bitmap as the canvasBitmap
        canvas = Canvas(mCanvasBitmap!!)
    }

    //now we need to add what should happen we draw on our canvas
    //onDraw is method which will help us handle the draw functionality on our canvas
    //here onDraw method is gonna need a canvas on which it is going to draw something
    //NOTE-> change canvas to canvas? if the application crashes
    override fun onDraw(canvas:Canvas){
        super.onDraw(canvas)
        //set the canvas to draw a bitmap
        //canvas.drawBitmap(pass the Bitmap that you want to draw on the canvas, pass the position where you want to draw on the canvas,pass the paint here, pass the mCanvasPaint)
        canvas.drawBitmap(mCanvasBitmap!!, 0f,0f, mCanvasPaint)

        //and then here we are going to reDraw the path in the canvas on the application screen which is stroed in the mDraw ArrayList
        for(path in mPaths){
            //setting up how thick our paint should be from the ArrayList mPaths
            mDrawPaint!!.strokeWidth = path.brushThickness

            //setting up the color from the ArrayList mPaths
            mDrawPaint!!.color = path.color

            //draw the path onto the canvas only if the path is not empty
            canvas.drawPath(path,mDrawPaint!!)
        }

        //now draw our path
        //before we draw our path we need to check if the path we want to draw is not empty
        //because when open up a new file or open up the app for the first time the path will be empty
        if(!mDrawPath!!.isEmpty)
        {
            //setting up how thick our paint should be
                //we are able to use brushThickness because android path is of custom path
            mDrawPaint!!.strokeWidth = mDrawPath!!.brushThickness

            //setting up the color of our custom path
            mDrawPaint!!.color = mDrawPath!!.color

            //draw the path onto the canvas only if the path is not empty
            canvas.drawPath(mDrawPath!!,mDrawPaint!!)
        }
    }

    //Now we need to define when should we draw on the canvas
    /*
    So essentially we need to fill the mDrawPath with the color defined in our application in the setupDrawing function
    when we touch the screen
    inorder to do that we need to implement on touch event functionality
     */
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        //here we will say what should happen when we touch the canvas on our application screen on the device
        val touchX = event?.x
        val touchY = event?.y

        //Three events that are important that we are going to take care of here are
        //Action Down -> when we ge our finger on the screen
        //Action move -> when we drag our finger on the screen
        //Action up -> when release the touch screen
        when (event?.action){

           //what should happen when we put our finger down on the screen
           MotionEvent.ACTION_DOWN->{
               //set up the mDraw path color to be the color defined in the ondraw function
               mDrawPath!!.color = color
               //setup the mDrawPath thickness OR the brush thickness
               mDrawPath!!.brushThickness = mBrushSize

               //after drawing resetting the canvas
               mDrawPath!!.reset()
               mDrawPath!!.moveTo(touchX!!, touchY!!)
           }

            //what should happen when we drag our finger on the screen
            MotionEvent.ACTION_MOVE->{
                //we need top draw a line when the user drang his finger on the screen
                mDrawPath!!.lineTo(touchX!!,touchY!!)
            }

            //what should happen when we release the touch
            MotionEvent.ACTION_UP->{
                //here we need to first store the path drawn by the user on the screen inside the mPaths ArrayList variable
                mPaths.add(mDrawPath!!)
                mDrawPath = CustomPath(color,mBrushSize)
            }
            //add a default case here
            else -> return false
        }
        invalidate()
        return true
    }

    //This function will set the size of the brush
    fun setSizeForBrush(newSize : Float){
        //assigning the new size of the brush to mBrushSize variable while taking the screen dimension of the device into account
        //COMPLEX_UNIT_DIP allows us to take pixel density of a device into consideration
        //now this code below will adjust the new Size of the brush according to the metrics of the screen display size
        mBrushSize = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, newSize , resources.displayMetrics)

        mDrawPaint!!.strokeWidth = mBrushSize
    }

    //this function will set the color of the brush after the user selects the color from the menu in the application
    fun setColor(newColor: String)
    {
        color = Color.parseColor(newColor)
        mDrawPaint!!.color = color
    }

    //This CustomPath is going to be nested class
    //Path() is class here NOTE:-> Path() here is from android.graphics library
    //so this Path() class will require a canvas that will be used for drawing lines and other geometry on the screen within this application
    internal inner class CustomPath(var color: Int, var brushThickness: Float) : Path() {
         //The contents of this class will only be accessible to the DrawingView class

    }
}