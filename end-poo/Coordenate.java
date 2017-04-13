public  class Coordenate {
  private int x;
  private int y;


/*
Constructors of class Coordenate.

*/
  public Coordenate(int nx,int ny){
    this.x=nx;
    this.y=ny;
  }

  public Coordenate(){
    this.x=0;
    this.y=0;
  }

  public Coordenate(Coordenate ref){
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

public void setx (int nx){
  this.x=nx;
}

public void sety (int ny){
  this.y=ny;
}

//Clone

public Coordenate clone (){
  Coordenate res = new Coordenate(this);
  return res;
}

//Equals

public boolean equals (Object ref){
  if (ref==this) return true;
  if ((ref==null)|| (ref.getClass()!=this.getClass())) return false;
  Coordenate l = (Coordenate) ref;
  return ((l.getx() == this.x) && (l.gety() == this.y));
  }
}
