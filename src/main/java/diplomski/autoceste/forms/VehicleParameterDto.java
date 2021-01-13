package diplomski.autoceste.forms;

import org.springframework.data.util.Pair;

import java.util.Arrays;
import java.util.List;

public class VehicleParameterDto {

    private Long key;
    private String name;
    private String type;

    public VehicleParameterDto(Pair<String, String> info) {
        List<String> tmp = Arrays.asList("smallint", "integer", "bigint", "decimal", "numeric", "real",
                "double precision", "smallserial", "serial", "bigserial");
        this.key = Math.round(Math.random() * 10000);
        this.name = info.getFirst();
        if (tmp.contains(info.getSecond())) {
            this.type = "numerical";
        } else {
            this.type = "other";
        }

    }

    public VehicleParameterDto(Long key, String name, String type) {
        this.key = key;
        this.name = name;
        this.type = type;
    }

    public Long getKey() {
        return key;
    }

    public void setKey(Long key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
