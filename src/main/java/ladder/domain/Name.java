package ladder.domain;

public class Name {

    private static final int MAX_LENGTH = 5;

    private final String name;

    public Name(String name) {
        validate(name);
        this.name = name;
    }

    private void validate(String name) {
        if (name.length() > MAX_LENGTH) {
            throw new IllegalArgumentException("사람 이름은 5자를 초과할 수 없습니다");
        }
    }

}
