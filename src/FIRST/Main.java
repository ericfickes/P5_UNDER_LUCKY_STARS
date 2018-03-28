package FIRST;


import processing.core.*;
import processing.pdf.*;


import fixlib.*;

/*
PROPS:
 * A complete tutorial can be found over at:
 * http://sighack.com/post/exporting-high-resolution-images-in-processing


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
    int CONFIG_SCALE_FACTOR = 10;
    Boolean high = false;
    Boolean pdf = false;
    Boolean saved = false;
    float hue;


//    ThinkDifferent steveJobs;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * Start and run FIRST.Main
     * @param args
     */
    public  static  void  main ( String []  args ) {
        PApplet.main( "FIRST.Main" );
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public  void  settings ()  {
        size(540, 720 );  //  + CONFIG_SCALE_FACTOR = 10 for rendering
        smooth(8);  //  smooth() can only be used in settings();
        pixelDensity(displayDensity());
    }


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    /**
     * The setup() function is run once, when the program starts.
     hint() can go in here
     */
    public void setup(){
        setupStage();

        seed = millis();
        seededRender();
    }


    public  void  setupStage ()  {

//        textMode(CENTER);
        textAlign(CENTER);
        textSize(42);
        textFont(createFont("Bebas Neue Bold", 42));

        fill(0);

    }


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public  void  draw ()  {
    //  do drawing in render()
    }


    /**
     * Drawing helper
     */
    private void seededRender() {
        setupStage ();
        /* Sets the seed value for random(). By default, random() produces different results each time the program is run. Set the seed parameter to a constant to return the same pseudo-random numbers each time the software is run. */
        randomSeed(seed);
        noiseSeed(seed);
        render();
    }


    /**
     * MAIN DRAWING FUNCTION - not draw()
     */
    public void render(){

        /* Write your drawing code here */

        noStroke();
        background(-1);
        hue = random(360);





        //  PROJECTION
        //  placeholders are 4662 × 4662
        for (int i = 0; i < 10000; i++) {
            float r = random(10, 50);
            fill(hue, 50 + random(-50, 50), 50 + random(-50, 50), 50);
            ellipse(random(-width/2, width * 5/4), random(-height/2, height * 5/4), r, r);
        }

        //  TITLE
        fill(0);
        text("TITLE", width/2, (int)(height*.1));

        stroke(fix.hexToInt("#ef2018"));
        line(width/2, 0,
                width/2, height);

        //  FOOTNOTE
        fill(0);
        text("FOOTNOTE", width/2, (int)(height*.95) );

//        if(!saved){
//            saved = true;
//            doExit();
//        }


    }


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**/
    @Override
    public void keyPressed(){

        if (key == 'h') {
            saveHighRes(CONFIG_SCALE_FACTOR);
        } else if (key == 's') {
            saveSVG(CONFIG_SCALE_FACTOR);
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
