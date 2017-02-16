package rashjz.info.domain;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.GenerationType.AUTO;
import static javax.persistence.GenerationType.IDENTITY;

/**
 * Created by Mobby on 2/13/2017.
 */
@Entity(name = "accident")
public class Accident {
    private int id;
    private String location;
    private Date insertDate;
    private Date actionDate;
    private String note;
    private Integer eventTypeId;
    private Set<Citizen> citizens = new HashSet<Citizen>(0);
    private Set<Vechile> vechiles = new HashSet<Vechile>(0);

    @Id
    @GeneratedValue(strategy = AUTO)
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "location")
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Column(name = "insertDate")// ,columnDefinition = "DATETIME default now()"
    public Date getInsertDate() {
        return insertDate;
    }

    public void setInsertDate(Date insertDate) {
        this.insertDate = insertDate;
    }

    @Column(name = "actionDate")
    public Date getActionDate() {
        return actionDate;
    }

    public void setActionDate(Date actionDate) {
        this.actionDate = actionDate;
    }

    @Column(name = "note")
    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Column(name = "eventType_id")
    public Integer getEventTypeId() {
        return eventTypeId;
    }

    public void setEventTypeId(Integer eventTypeId) {
        this.eventTypeId = eventTypeId;
    }

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "accidentId",cascade = CascadeType.ALL)
    public Set<Citizen> getCitizens() {
        return citizens;
    }

    public void setCitizens(Set<Citizen> citizens) {
        this.citizens = citizens;
    }

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "accidentId",cascade = CascadeType.ALL)
    public Set<Vechile> getVechiles() {
        return vechiles;
    }

    public void setVechiles(Set<Vechile> vechiles) {
        this.vechiles = vechiles;
    }


}
