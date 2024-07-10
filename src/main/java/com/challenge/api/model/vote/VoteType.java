package com.challenge.api.model.vote;

public enum VoteType {
    UP("up"),
    DOWN("down");

    String type;

    VoteType(String type){
        this.type = type;
    }

    public String getType(){
        return this.type;
    }

    public static VoteType getTypeByString(String type) {
        for (VoteType voteType : VoteType.values()) {
            if (voteType.getType().equalsIgnoreCase(type.toLowerCase())) {
                return voteType;
            }
        }
        throw new IllegalArgumentException("No enum constant with type " + type);
    }
}
