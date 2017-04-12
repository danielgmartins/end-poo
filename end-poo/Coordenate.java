public  class Coordenates {
  private int x;
  private int y;


/*
Constructors of class Coordenates.

*/
  public Coordenates(int nx,int ny){
    this.x=nx;
    this.y=ny;
  }

  public Coordenates(){
    this.x=0;
    this.y=0;
  }

public Coordenates(Coordenates ref){
  this.x=ref.getx();
  this.y=ref.gety();
}

//Getters e setters

public int getx (){
  return this.x;
}


public int gety(){
  return this.y;
}

public int setx (int nx){
  this.x=nx;
}

public int sety (int ny){
  this.y=ny;
}

//Clone

public Coordenates clone (){
  return Coordenates(this)
}
