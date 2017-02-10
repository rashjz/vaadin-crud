package rashjz.info.domain;

import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;

/**
 * Created by rasha_000 on 2/10/2017.
 */
@Entity
@Table(name = "event_type")
@Transactional
public class EventType {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    public EventType() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "EventType{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
