package com.koushan.niles.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@Setter
@Getter
public class DealDto implements Serializable {
    /**
	 * 
	 */
	@Serial
    private static final long serialVersionUID = 1L;
	private Long apparelId;
    private Double percentage;
    private String validFrom;
    private String validUpto;

    public DealDto(){}

    public DealDto(Long apparelId, Double percentage, String validFrom, String validUpto) {
        this.apparelId = apparelId;
        this.percentage = percentage;
        this.validFrom = validFrom;
        this.validUpto = validUpto;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DealDto entity = (DealDto) o;
        return Objects.equals(this.apparelId, entity.apparelId) &&
                Objects.equals(this.percentage, entity.percentage) &&
                Objects.equals(this.validFrom, entity.validFrom) &&
                Objects.equals(this.validUpto, entity.validUpto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(apparelId, percentage, validFrom, validUpto);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "apparelId = " + apparelId + ", " +
                "percentage = " + percentage + ", " +
                "validFrom = " + validFrom + ", " +
                "validUpto = " + validUpto + ")";
    }
}
