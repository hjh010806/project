package com.example.Project.Global;

import lombok.Getter;

import java.util.List;

public enum OSType {
    Window("c:/web"), Linux("/home/ubuntu/sbb")
    //
    ;
    @Getter
    private final String loc;
    OSType(String loc) {
        this.loc=loc;
    }
    public static OSType getOSType(){
        String osName= System.getProperty("os.name").toLowerCase();

        if(osName.contains("window"))  return Window;
        if(osName.contains("linux"))  return Linux;


        return null;
    }
}
