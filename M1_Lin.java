//// Midterm code for 59CST112

/**************************************************************
BE SURE TO CHANGE THE NEXT SEVERAL LINES, USING YOUR REAL NAME.
    MY FIRST NAME IS......Teng
    MY MIDDLE NAME IS.....Henry
    MY LAST NAME IS.......Lin
    
PICK 3 WORDS THAT START WITH YOUR INITIALS, using lower case letters:
    first word............tiger
    second word...........hat
    third word............lion
    
USE THESE 3 WORDS TO NAME ALL VARIABLES FOR 3 POOL BALLS.
(You may abbreviate any words, but start with same letter.)
***************************************************************/


//// GLOBALS FOR 3 colotiger balls ////
//// (Assume ball diameter is 30.) ////
float tigerX,  tigerY,  tigerDX,  tigerDY;          //++++ MODIFY THIS.  Don' use "wolf". ++++
float hatX,  hatY,  hatDX,  hatDY;              //++++ MODIFY THIS.  Don' use "ham". ++++
float lionX, lionY,  lionDX,  lionDY;      //++++ MODIFY THIS.  Don' use "hippo". ++++
            //++++ MODIFY THESE DECLARATIONS -- Do not use "wolf" or "ham" or "hippo" ++++


//// OTHER GLOBALS:  strings, pool table, etc ////

String title=  "CST112 MIDTERM EXAM";
String news=   "Click any ball to reset it to right half of table.  (r resets all.)";
String author=  "Teng Lin";

float left=50, right=450, top=100, bottom=250;        // Table boundaries
float middle=250;
boolean wall=true;

int tableRed=0, tableGreen=255, tableBlue=65;      // hat pool table
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

    tigerX=  random( middle+20,right );     tigerY=  random( top, bottom );
    hatX=  random( middle+20,right );   hatY=  random( top, bottom );
    lionX=  random( middle+20,right );    lionY=  random( top, bottom );
    
    tigerDX=  random( 1,3 );     tigerDY=  random( 1,3 );
    hatDX=  random( 1,3 );   hatDY=  random( 1,3 );
    lionDX=  random( 1,3 );    lionDY=  random( 1,3 );
    
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
    wall=!wall;
    middle=left-20;
  }
  
  if (key == 'p') {
     tableRed=247; tableGreen=90; tableBlue=130;
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
    
    if ( tigerX < middle+20 || tigerX > right ) {
    tigerDX *= -1;
    }
    
    if ( tigerY < top || tigerY > bottom )  {
    tigerDY *= -1;
   }
    
    if ( hatX < middle+20 || hatX > right )  {
    hatDX *= -1;
   }
   
   if ( hatY < top || hatY > bottom )  {
    hatDY *= -1;
   }
   
   if ( lionX < middle+20 || lionX > right )  {
    lionDX *= -1;
   }
   
   if ( lionY < top || lionY > bottom )  {
    lionDY *= -1;
   }


}  
  
  
void collisions() {
    float tmp;
    // Swap velocities! , bounce off each other.
    if ( dist( tigerX,tigerY, hatX,hatY ) < 30 ) {
    tmp=hatDX;  hatDX=tigerDX;  tigerDX=tmp;
    tmp=hatDY;  hatDY=tigerDY;  tigerDY=tmp;
   }
    
    if ( dist( tigerX,tigerY, lionX,lionY ) < 30 ) {
    tmp=lionDX;  lionDX=tigerDX;  tigerDX=tmp;
    tmp=lionDY;  lionDY=tigerDY;  tigerDY=tmp;
   }
    
    if ( dist( hatX,hatY, lionX,lionY ) < 30 ) {
    tmp=lionDX;  lionDX=hatDX;  hatDX=tmp;
    tmp=lionDY;  lionDY=hatDY;  hatDY=tmp;
   }
   
   
}

//// SHOW:  balls, messages, etc.
void show() {
  
  fill( 255,0,0 );    
  ellipse( tigerX,tigerY, 30,30 );
  fill( 0,255,0 );    
  ellipse( hatX,hatY, 30,30 );
  fill( 0,0,255 );    
  ellipse( lionX,lionY, 30,30 );
  
  //moving the balls
  tigerX += tigerDX;      tigerY += tigerDY;
  hatX += hatDX;  hatY += hatDY;
  lionX += lionDX;    lionY += lionDY;
 
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
   //reset
   if ( mouseX>25 && mouseX<85 && mouseY>40 && mouseY<65){
    reset();
  }

   //chage table to pink
   if ( mouseX>250 && mouseX<350 && mouseY>40 && mouseY<65){
   tableRed=247; tableGreen=90; tableBlue=130; 
  }
   
  // remove the wall
  if ( mouseX>120 && mouseX<220 && mouseY>40 && mouseY<65){
   wall=!wall;
   middle=left-20;
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
  tigerX=  random( middle+20,right );     tigerY=  random( top, bottom );
  hatX=  random( middle+20,right );   hatY=  random( top, bottom );
  lionX=  random( middle+20,right );    lionY=  random( top, bottom );
  
  tigerDX=  random( 1,3 );     tigerDY=  random( 1,3 );
  hatDX=  random( 1,3 );   hatDY=  random( 1,3 );
  lionDX=  random( 1,3 );    lionDY=  random( 1,3 );

  tableRed=0; tableGreen=255; tableBlue=65;

  wall=true;
  middle=320;
  
}
