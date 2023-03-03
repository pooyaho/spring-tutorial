package ir.mapsa.secondspringproject.tutorials1.models;

public class CourseDto extends AbstractDto {

    private String name;
    private String topic;
    private Integer unit;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public Integer getUnit() {
        return unit;
    }

    public void setUnit(Integer unit) {
        this.unit = unit;
    }
}
