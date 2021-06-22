public class User02 {

    private String email;

    private String name;

    private String password;

    private Integer age;

    private Double height;

    public Double getHeight() {
        return height;
    }

    public Integer getAge() {
        return age;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User02(String email, String name, String password, Integer age, Double height) {
        this.email = email;
        this.name = name;
        this.password = password;
        this.age = age;
        this.height = height;
    }

    public String print() {
        return toString();
    }
}
