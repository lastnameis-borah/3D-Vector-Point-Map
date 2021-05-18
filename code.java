import geomerative.*;

RShape grp;
RPoint[][] pointPaths;

float xmag, ymag, newYmag, newXmag = 0;
float z = 0;

boolean ignoringStyles = false;

void setup(){
  size(displayWidth, displayHeight, P3D);
  RG.init(this);
  RG.ignoreStyles(ignoringStyles);
  RG.setPolygonizer(RG.ADAPTATIVE);
  grp = RG.loadShape("D:/Programming/Python/Pencil sketch/me.svg");
  grp.centerIn(g, 100, 1, 1);
  
  pointPaths = grp.getPointsInPaths();  
}
void draw(){
  translate(width/2, height/2);
  
  newXmag = mouseX/float(width) * TWO_PI;
  newYmag = mouseY/float(height) * TWO_PI;
  
  float diff = xmag-newXmag;
  if (abs(diff) >  0.01) { xmag -= diff/100.0; }
  
  diff = ymag-newYmag;
  if (abs(diff) >  0.01) { ymag -= diff/100.0; }
  
  rotateX(-ymag); 
  rotateY(-xmag); 
  
  background(0);
fill(0,0,0);
//stroke(255,248,10);
stroke(255);
stroke(255,150,255);
strokeWeight(1.25);
  directionalLight(126, 126, 126, 0, 0, -1);
ambientLight(255,255,255);
 
 float newMouseY = map(mouseY,0,1000,0,100);
 println(newMouseY);
  
  z = 1 * sin( newMouseY/500.0 * PI);
  
  for(int i = 0; i<pointPaths.length; i++){
    translate(0,0,z);

    
    if(mouseX > displayWidth/2){
      if (pointPaths[i] != null) {
        beginShape(POINTS);
        for(int j = 0; j<pointPaths[i].length; j++){
          vertex(pointPaths[i][j].x, pointPaths[i][j].y);
          if(mouseX > 500){
stroke(255,150,255);
                  vertex(pointPaths[i][j].x, pointPaths[i][j].y);
                   stroke(255);
                  vertex(pointPaths[i][j].x+10, pointPaths[i][j].y+10);             
            }        
        }
        endShape();
        
      }
    }
    
    if (mouseX < displayWidth/2){
    if (pointPaths[i] != null) {
        beginShape(POINTS);
        for(int j = 0; j<pointPaths[i].length; j++){
          vertex(pointPaths[i][j].x, pointPaths[i][j].y);
          if(mouseX < displayWidth/2){
stroke(0,150,255);
                  vertex(pointPaths[i][j].x, pointPaths[i][j].y);
                  
                  stroke(255);
                  vertex(pointPaths[i][j].x+2, pointPaths[i][j].y+2);

                  
                  

  
          }
        }
        endShape();
        
    }
     
    }
    

  }
}

void mousePressed(){
  ignoringStyles = !ignoringStyles;
  RG.ignoreStyles(ignoringStyles);            
}
