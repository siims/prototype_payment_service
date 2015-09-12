package eu.onepay.db.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = FinServiceImage.TABLE)
public class FinServiceImage extends AbstractStatefulData {

    public static final String TABLE = "fin_service_image";

    public static final String COL_IMAGE_URL= "image_url";

    public static final String COL_FIN_SERVICE_ID = "fin_service_id";

    public static final String COL_DESCRIPTION = "description";


    public static final String FLD_IMAGE_URL= "url";

    public static final String FLD_DESCRIPTION = "description";

    public static final String FLD_FIN_SERVICE_ID = "finServiceId";

    @Column(name = COL_IMAGE_URL)
    private String url;

    @Column(name = COL_DESCRIPTION)
    private String description;

    @Column(name = COL_FIN_SERVICE_ID)
    private Long finServiceId;
}
