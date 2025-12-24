public abstract class Person {
    private String name;

    public Person(String name){
        this.name = name;
    }
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }
    @Override
    public String toString(){
        return "Name: " + name;
    }
    @Override
    public int hashCode(){
        return name.hashCode();
    }
    @Override
    public boolean equals(Object obj) {
        return this == obj;
    }

}
