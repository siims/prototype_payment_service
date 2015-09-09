package eu.onepay.db.data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import lombok.Data;

@Data
@MappedSuperclass
public abstract class AbstractData {

    public static final String COL_ID = "id";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public AbstractData() {}

    public AbstractData(Long id) {
        this.id = id;
    }
}
