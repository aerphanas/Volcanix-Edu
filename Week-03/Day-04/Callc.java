public class Callc {
    int var1, var2;
    int ressult;
  
    // constructor
    public Callc(int var1, int var2) {
      this.var1 = var1;
      this.var2 = var2;
    }
  
    // method
    public void add()
    {
      this.ressult = var1 + var2;
    }
  
    // getter
    public int getVar1() {
      return var1;
    }
  
    // getter
    public int getRes()
    {
      return ressult;
    }
  
    // setter
    public void setRes(int result){
      this.ressult = result;
    }
  
    // setter
    public void setVar1(int var1) {
      this.var1 = var1;
    }
  
    // getter
    public int getVar2() {
      return var2;
    }
  
    // setter
    public void setVar2(int var2) {
      this.var2 = var2;
    };
    
  }
  