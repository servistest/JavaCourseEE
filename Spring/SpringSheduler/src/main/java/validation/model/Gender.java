package validation.model;

/**
 * Created by ALex on 02.10.2016.
 */
public enum  Gender {
    MALE("M"),FEMALE("F");

    private String code;

    private Gender (String code){
        this.code=code;
    }

    @Override
    public String toString() {
        return this.code;
    }
}
