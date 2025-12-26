package com.memento.practice.api;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//put the path of the route here
@RequestMapping("api/v1/champions")
public class ChampionController {

    //json is returned automatically, no need to encode or decode
    //objects into json (power of springboot o:)
    @GetMapping
    public List<Champion> getChampions() {
        return List.of(
            new Champion(
                1,
                "Akali",
                false,
                true,
                "ap"
            ),
            new Champion(
                2,
                "Syndra",
                true,
                false,
                "ap"
            ),
            new Champion(
                3,
                "Zaahen",
                false,
                true,
                "ad"
            ),
            new Champion(
                4,
                "Swain",
                true,
                false,
                "ap"
            ),
            new Champion(
                5,
                "Ryze",
                true,
                false,
                "ap"
            )
        );
    }
}
