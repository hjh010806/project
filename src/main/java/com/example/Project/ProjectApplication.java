package com.example.Project;

import com.example.Project.Global.OSType;
import com.example.Project.Main.DataNotFoundException;
import lombok.Getter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProjectApplication {
    @Getter
    private static OSType osType;

    public static void main(String[] args) {
        osType = OSType.getOSType();
        if (osType != null)
            SpringApplication.run(ProjectApplication.class, args);
        else
            throw new DataNotFoundException("없는 OS 입니다.");
    }
}
