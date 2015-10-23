//// Midterm code for 59CST112

/**************************************************************
BE SURE TO CHANGE THE NEXT SEVERAL LINES, USING YOUR REAL NAME.
    MY FIRST NAME IS......Teng
    MY MIDDLE NAME IS.....Henry
    MY LAST NAME IS.......Lin
    
PICK 3 WORDS THAT START WITH YOUR INITIALS, using lower case letters:
    first word............red
    second word...........green
    third word............blue
    
USE THESE 3 WORDS TO NAME ALL VARIABLES FOR 3 POOL BALLS.
(You may abbreviate any words, but start with same letter.)
***************************************************************/


//// GLOBALS FOR 3 colored balls ////
//// (Assume ball diameter is 30.) ////
float redX,  redY,  redDX,  redDY;          //++++ MODIFY THIS.  Don' use "wolf". ++++
float greenX,  greenY,  greenDX,  greenDY;              //++++ MODIFY THIS.  Don' use "ham". ++++
float blueX, blueY,  blueDX,  blueDY;      //++++ MODIFY THIS.  Don' use "hippo". ++++
            //++++ MODIFY THESE DECLARATIONS -- Do not use "wolf" or "ham" or "hippo" ++++


//// OTHER GLOBALS:  strings, pool table, etc ////

String title=  "CST112 MIDTERM EXAM";
String news=   "Click any ball to reset it to right half of table.  (r resets all.)";
String author=  "Teng Lin";

float left=50, right=450, top=100, bottom=250;        // Table boundaries
float middle=250;
boolean wall=true;

int tableRed=0, tableGreen=255, tableBlue=65;      // green pool table
int score=0,m=0,k=0;

    // ADD YOUR OWN DECLARATIONS HERE. ++++


//// SETUP:  size and table
void setup() {
    size( 640, 480 );          //++++ CHANGE THE SIZE -- see instructions! ++++
    left=   50;
    right=  width-50;
    top=    100;
    bottom= height-50;
    middle= left + (right-left) / 2;
    
    redX=  random( middle+20,right );     redY=  random( top, bottom );
    greenX=  random( middle+20,right );   greenY=  random( top, bottom );
    blueX=  random( middle+20,right );    blueY=  random( top, bottom );
    
    redDX=  random( 1,3 );     redDY=  random( 1,3 );
    greenDX=  random( 1,3 );   greenDY=  random( 1,3 );
    blueDX=  random( 1,3 );    blueDY=  random( 1,3 );
 }


//// NEXT FRAME:  table, bounce off walls, collisions, show all
void draw() {
  background( 250,250,200 );
  rectMode( CORNERS );
  table( left, top, right, bottom );
  buttons();
  show();
  bounce();
  collisions();
  messages();
}


//// HANDLERS:  keys, click
void keyPressed() {
  if (key == 'q') { 
    exit();  
  }
  
  if (key == 'r') {
    reset();
  }
  
  if (key == 'w') {
    
  }
    
}


//// SCENE:  draw the table with wall down the middle
void table( float east, float north, float west, float south ) {
  fill( tableRed, tableGreen, tableBlue );    // pool table
  strokeWeight(20);
  stroke( 240, 150, 50 );   
  rect( east-20, north-20, west+20, south+20 );

            //++++ MODIFY THIS CODE, as necessary. ++++

  // Start with a WALL down the middle of the table! 
  if (wall) {
    float middle=  (east+west)/2;    
    stroke( 0, 127, 0 );
    line( middle,north+10, middle,south-10 );
  }
  stroke(0);
  strokeWeight(1);
}

//// ACTION:  bounce off walls, collisions
void bounce() {
    
    if ( redX < middle+20 || redX > right ) {
    redDX *= -1;
    }
    
    if ( redY < top || redY > bottom )  {
    redDY *= -1;
   }
    
    if ( greenX < middle+20 || greenX > right )  {
    greenDX *= -1;
   }
   
   if ( greenY < top || greenY > bottom )  {
    greenDY *= -1;
   }
   
   if ( blueX < middle+20 || blueX > right )  {
    blueDX *= -1;
   }
   
   if ( blueY < top || blueY > bottom )  {
    blueDY *= -1;
   }


}  
  

void collisions() {
    float tmp;
    // Swap velocities! , bounce off each other.
    if ( dist( redX,redY, greenX,greenY ) < 30 ) {
    tmp=greenDX;  greenDX=redDX;  redDX=tmp;
    tmp=greenDY;  greenDY=redDY;  redDY=tmp;
   }
    
    if ( dist( redX,redY, blueX,blueY ) < 30 ) {
    tmp=blueDX;  blueDX=redDX;  redDX=tmp;
    tmp=blueDY;  blueDY=redDY;  redDY=tmp;
   }
    
    if ( dist( greenX,greenY, blueX,blueY ) < 30 ) {
    tmp=blueDX;  blueDX=greenDX;  greenDX=tmp;
    tmp=blueDY;  blueDY=greenDY;  greenDY=tmp;
   }
   
   
}

//// SHOW:  balls, messages, etc.
void show() {
  
  fill( 255,0,0 );    
  ellipse( redX,redY, 30,30 );
  fill( 0,255,0 );    
  ellipse( greenX,greenY, 30,30 );
  fill( 0,0,255 );    
  ellipse( blueX,blueY, 30,30 );
  
  //moving the balls
  redX += redDX;      redY += redDY;
  greenX += greenDX;  greenY += greenDY;
  blueX += blueDX;    blueY += blueDY;
 
}
void buttons() {
  rectMode(CORNER);
  fill(120,0,0,5);
  rect(25,40, 60,25);
  fill(0);
  text( "RESET" , 35, 60);
 
  fill(120,0,0,5);
  rect(120,40, 100,25);
  fill(0);
  text( "WALL REMOVE" , 130, 60);
  
  fill(120,0,0,5);
  rect(250,40, 100,25);
  fill(0);
  text( "PINK TABLE" , 260, 60);
  
}

void mousePressed() {
    if ( mouseX>25 && mouseX<85 && mouseY>40 && mouseY<65){
    reset();
  }

   if ( mouseX>250 && mouseX<350 && mouseY>40 && mouseY<65){
   tableRed=247; tableGreen=90; tableBlue=130; 
  }




  

}










void messages() {
  fill(0);
  text( title, width/3, 15 );
  text( news, width/9, 30 );
  text( author, 10, height-5 );

  // Also, display the number of collisions.

    //++++ ADD YOUR OWN CODE HERE. ++++

}




void reset() {
  redX=  random( middle+20,right );     redY=  random( top, bottom );
  greenX=  random( middle+20,right );   greenY=  random( top, bottom );
  blueX=  random( middle+20,right );    blueY=  random( top, bottom );
  
  redDX=  random( 1,3 );     redDY=  random( 1,3 );
  greenDX=  random( 1,3 );   greenDY=  random( 1,3 );
  blueDX=  random( 1,3 );    blueDY=  random( 1,3 );

  tableRed=0; tableGreen=255; tableBlue=65;

  
}



