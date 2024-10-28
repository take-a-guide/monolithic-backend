package br.com.takeaguide.takeaguide.enums;

public enum UserTypeEnum {

    HIRER(1, "hirer"),
    GUIDE(2, "guide"),
    ADMIN(3, "admin");

    public final int key;
    public final String value;

    private UserTypeEnum(int key, String value){
        this.key = key;
        this.value = value;
    }


    public String getValues(int key){

        for(UserTypeEnum userTypeEnum : UserTypeEnum.values()){

            if(userTypeEnum.key == key){

                return userTypeEnum.value;

            }

        }

        return null;

    }
    
}
