package com.app.ipl.model.enums;

/**
 * @Author saurabh vaish
 * @Date 04-07-2022
 */
public enum CommonEnums {

    RUNS("Runs"), WICKETS("Wickets"), FIELD("Field"), BAT("Bat");

    private final String name;

    private CommonEnums(String name){this.name=name;}

    public static CommonEnums getValue(String toss_decision) {
        return switch (toss_decision){
            case "runs"->  RUNS;
            case "wickets"->  WICKETS;
            case "field"->  FIELD;
            case "bat"->  BAT;
            case "null"->  null;
            default -> null;
        };
    }

    public String getName(){return this.name;}

}
