class Main {
  public static void main(String[] args) {
    //Creating the set
    Set<Integer> set = new Set<Integer>();

    set.add(1);
    set.add(2);
    set.add(3);
    set.add(4);
    set.add(5);

    System.out.println(set.getLength());
    System.out.println(set.toString()); 
    System.out.println(set.contains(6)); 
    System.out.println(set.getElement(5)); //doesnt run
  }
}
