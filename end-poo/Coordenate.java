public  class Coordenada {
  private int x;
  private int y;


/*
Constructors of class Coordenada.

*/
  public Coordenada(int nx,int ny){
    this.x=nx;
    this.y=ny;
  }

  public Coordenada(){
    this.x=0;
    this.y=0;
  }

public Coordenada(Coordenada ref){
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

public Coordenada clone (){
  return Coordenada(this)
}

public boolean equals (Coordenada p){

}
