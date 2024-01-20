package encoway.springframework.videoapplication.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class MovieController {
    @Autowired
    private final ModelMapper modelMapper;

    public MovieController(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }
}
