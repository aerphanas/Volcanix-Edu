public class Main {
  public static void main(String[] args) {
    Callc callculate = new Callc(2, 2);
    callculate.add();
    System.out.println(callculate.getRes());

    Animal dog = new Dog();
    dog.sound();

    Animal cat = new Cat();
    cat.sound();
  }
}