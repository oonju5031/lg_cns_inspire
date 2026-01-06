package lgcns.domain.user;

public class Teacher {
    public String   name;
    public int      age;
    public char     gender;
    public String   mbti;
    public String   birthPlace;
    public boolean  isMarrage;

    // Default constructor
    public Teacher() {

    }

    // Special Constructor
    public Teacher(String name, int age, char gender, String mbti, String birthPlace, boolean isMarrage) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.mbti = mbti;
        this.birthPlace = birthPlace;
        this.isMarrage = isMarrage;
    }
}
