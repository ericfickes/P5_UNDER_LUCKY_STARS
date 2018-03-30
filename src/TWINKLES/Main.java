package TWINKLES;

import processing.core.PApplet;
import processing.core.*;
import processing.pdf.*;
import fixlib.*;

/*
FIRST = template to follow.

TWINKLES = TWINKLES tweaked for stars
* og : JOSH_CLASS_2015/_BURNED/twinkles

UNDER LUCKY STARS specs

 The design has to work for all three of the following print sizes, either by cropping or otherwise adjusting it:
                        = 300 dpi pixels

◆ 18x24 inches          = 5400, 7200
◆ 24x36 inches          = 7200, 10800
◆ A1 (23.4 x 33.1 in)   = 7020, 9930

 */



/**
 * NOTE: This sketch FIRST originated from P5_JAVA / HMetalBeta1.
 *
 * this is purely a reference JAVA P5 sketch for the Under Lucky Stars projeseed
 */
public class Main extends PApplet {

    //  base paths
    String PROJECT_ROOT = System.getProperty("user.dir");
    String SKETCH_PATH = PROJECT_ROOT + "/src/" + getClass().getName().replace(".Main","");
    //  output to this sketch's /out/ folder
    String SAVE_PATH = SKETCH_PATH + "/out/";


    Fixlib fix = Fixlib.init(this);
    int seed;
    int CONFIG_SCALE_FACTOR = 2;    //10;

    //  TWINKLES
    float cX, cY, xx, yy, xx2, yy2, rad,angle, angle2, sw = 12;
    Boolean dec = false;
    int decStop = 0, decAmt = 9;


//    ThinkDifferent steveJobs;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * Start and run FIRST.Main
     * @param args
     */
    public  static  void  main ( String []  args ) {
        // TODO: replace FIRST. with this sketches package name.  Also adjust your run config
        PApplet.main( "TWINKLES.Main" );
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public  void  settings ()  {
        //  SIZE1
//        size(540, 720 );  //  + CONFIG_SCALE_FACTOR = 10 for rendering

        //  SIZE2
//        size(720, 1080 );  //  + CONFIG_SCALE_FACTOR = 10 for rendering

        //  SIZE3
        size(702, 993);  //  + CONFIG_SCALE_FACTOR = 10 for rendering

        smooth(8);  //  smooth() can only be used in settings();
        pixelDensity(displayDensity());
    }


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    /**
     * The setup() function is run once, when the program starts.
     h(int)() can go in here
     */
    public void setup(){
        frameRate(666);
        background(-1);
        seed = millis();
        seededRender();
    }


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * Drawing helper
     */
    private void seededRender() {
println("seededRender() : " + frameCount);
        /* Sets the seed value for random(). By default, random() produces different results each time the program is run. Set the seed parameter to a constant to return the same pseudo-random numbers each time the software is run. */
        randomSeed(seed);
        noiseSeed(seed);

        setupStage ();

        render();
    }



    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * font and other re-visitable stage setup
     */
    public  void  setupStage ()  {
println("setupStage() : " + frameCount);

        textAlign(CENTER);
        textSize(42);

// TODO: install all over open fonts and update here
        textFont(createFont("Slaytanic", 42));

        cX = width/2;
        cY = height/2;
        rad = width;    //666;  // TODO: this may need a tweaking

        noFill();

    }


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public  void  draw ()  {
    //  do drawing in render()
//println("draw() : " + frameCount);
        render();
    }

    /**
     * MAIN DRAWING FUNCTION - not draw()
     */
    public void render(){
println("render() : " + frameCount + " : " + this.frameCount );



// TODO: this sketch needs to be rewritten to not be so tied to FRAMECOUNT starting @ 0
// TODO: FIX saveSVG and saveHighRes


        /* Write your drawing code here */

        angle = this.frameCount%width;
        xx = cX - (int)( cos(radians(angle)) * rad );
        yy = cY - (int)( sin(radians(angle)) * rad );

        angle2 = -this.frameCount%width;

        xx2 = cX - (int)( cos(radians(angle2)) * rad );
        yy2 = cY - (int)( sin(radians(angle2)) * rad );

        strokeWeight(sw);

        // strokeWeight(sw/PI);
        stroke(random(255));
        fix.hexagon( xx, yy, rad);//rad, angle2/(rad/sw) );

        stroke(frameCount%2==0?0:255);
        fix.star( 5,
                xx, yy,
                rad*1.4f,rad*1.4f,
                frameCount%180,
                random(.2f, .9f) * cos(PI / angle) );

        // strokeWeight(sw/PI);

        stroke(random(255));
        fix.hexagon( xx2, yy2, rad);//, rad, angle2/(rad/sw) );

        stroke(frameCount%2==0?255:0);
        fix.star( 5,
                xx2, yy2,
                rad*1.4f,rad*1.4f,
                frameCount%180,
                random(.2f, .9f) * cos(PI / angle2) );


        if(this.frameCount%15==0)
        {
            rad -= decAmt;
        }


        //  TEXT ACTION STOPPER
        if( (rad) < sw/3 ){
            // if( frameCount > (width*1.5) ){

            twinkles();

            //  DO THE TEXT ACTION BELOW


            //  TITLE
            fill(0);
            text("TITLE", width/2, (int)(height*.1));

            stroke(fix.hexToInt("#ef2018"));
            line(width/2, 0,
                    width/2, height);

            //  FOOTNOTE
            fill(0);
            text("FOOTNOTE", width/2, (int)(height*.95) );


            println("DONE");

        }

    }




    //////////////////////////////////////////////////////////////////////////
    void twinkles()
    {
        textAlign(CENTER,CENTER);
//        textFont( createFont("Slaytanic", 666 ));

        cY -= 75;

        textSize(555);
        fill(0);
        text("TWINKLES", cX, cY );

        textSize(550);
        fill(100);
        text("TWINKLES", cX, cY );


        textSize(545);
        fill(150);
        text("TWINKLES", cX, cY );

        textSize(540);
        fill(0);
        text("TWINKLES", cX-1, cY-1 );

        fill(255);
        text("TWINKLES", cX, cY );

    }

















    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**/
    @Override
    public void keyPressed(){

        if (key == 's' || key == 'S') {
//println("TODO: re-enable saveHighRes before submitting, currently only writing SVG");
            saveHighRes(CONFIG_SCALE_FACTOR);
            saveSVG(CONFIG_SCALE_FACTOR);
            doExit();

        } else if (key == ESC) {
            doExit();
        } else {
            doExit();
        }
    }



    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * exit function
     */
    private void doExit(){
        //  save on exit
        save(SAVE_PATH + fix.pdeName() + "_" + fix.getTimestamp() + ".png");

        noLoop();
        exit();


        //  OSX HACK TO KILL JAVA?
        System.gc();
        System.exit(0);
    }


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * save HIGH res
     */

    private void saveHighRes(int scaleFactor) {

        PGraphics hires = createGraphics(
                width * scaleFactor,
                height * scaleFactor,
                JAVA2D);
        println("Saving high-resolution image...");
        beginRecord(hires);
            hires.scale(scaleFactor);
            seededRender();
        endRecord();

        hires.save(SAVE_PATH + seed + "-highres.png");

        println("Finished");

        System.gc();
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * save PDF
     */
    private void saveSVG(int scaleFactor) {
        println("Saving SVG image...");


setup();
settings();


        PGraphics hires = createGraphics(
                width * scaleFactor,
                height * scaleFactor,
                SVG,
                SAVE_PATH+seed+".SVG");


        beginRecord( hires );

        seededRender();

        endRecord();

        System.gc();




        println("Finished");
    }


}
