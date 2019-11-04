package online.shixun.demo.elearning.dto;

public class SocialMediaType extends BaseDto {
    private Long name;

    private String icon;

    public Long getName() {
        return name;
    }

    public void setName(Long name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon == null ? null : icon.trim();
    }
}