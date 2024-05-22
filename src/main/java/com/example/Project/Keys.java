package com.example.Project;

import lombok.Getter;

public enum Keys {
    uploadDir("C:/web")
    //
    ;
    @Getter
    private final String location;
    Keys(String location) {
        this.location=location;
    }

}
