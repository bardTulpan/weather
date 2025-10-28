package bard.wether.controller;

import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/locations")
@RestController
public class LocationController {

    @GetMapping("/search")
    public String searchLocation() {
        return "search";
    }

    @PostMapping
    public String save() {
        return "post location";
    }

    @GetMapping
    public String getLocations() {
        return "getLocations";
    }

    @DeleteMapping("/{locationId}")
    public String deleteLocation(@PathVariable Long locationId) {
        return "Локация с ID " + locationId + " удалена из вашего списка";
    }


}


//GET    /api/locations/search?q={query}          # Поиск локаций
//POST   /api/locations                           # Добавление локации в список
//GET    /api/locations                           # Просмотр списка локаций пользователя
//DELETE /api/locations/{locationId}              # Удаление локации из списка
