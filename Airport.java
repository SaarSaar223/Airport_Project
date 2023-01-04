import java.util.Objects;

public class Airport implements Comparable<Airport>{
  String name;

  public Airport() {}

  public Airport(String name) {
    this.name = Objects.requireNonNull(name);
  }

  @Override
  public String toString() {
    return name;
  }

  @Override
  public boolean equals(Object o) {
    if(!(o instanceof Airport)) return false;
    return this.name.equals(((Airport) o).name);
  }


  @Override
  public int compareTo(Airport o) {
    return this.name.compareTo(o.name);
  }

  @Override
  public int hashCode() {
    return name.hashCode();
  }
}
