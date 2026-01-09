package lgcns.domain.oop.util;

public enum DivisionFlag {
    STUDENT("학생"), TEACHER("강사");

    private final String division;
    private DivisionFlag(String division) {
        this.division = division;
    }
    public String getDivision() {
        return division;
    }
}
