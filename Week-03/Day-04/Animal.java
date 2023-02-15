public interface Animal {
    void sound();
}

class Dog implements Animal {
    @Override
    public void sound(){
        System.out.println("bark! bark!");
    }
    
}

class Cat implements Animal {
    @Override
    public void sound(){
        System.out.println("Meow Meow");
    }
}