package tqs.p2.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Car {

    @Id
    @GeneratedValue
    private long carId;

    public Car(){};

    private String name;

    private String maker;

    public Car(String name, String maker){
        this.name = name;
        this.maker = maker;
    }
}
