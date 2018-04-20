package com.udhaii.zuiver;

class FirebaseUserEntity {

    private String uId;

    private String email;

    private String name;

    private String age;

    private String gender;

    public FirebaseUserEntity() {

    }

    public String getuId() {
        return uId;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    public FirebaseUserEntity(String uId, String email, String name, String age, String gender) {
        this.uId = uId;
        this.email = email;
        this.name = name;
        this.age = age;
        this.gender = gender;
    }

}
