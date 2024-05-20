package com.example.Project.Global;

import com.example.Project.List.ListMain;
import com.example.Project.User.SiteUser;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class ListDTO {
    private ListMain listMain;
    private boolean heart;
    private String listUrl;
}
