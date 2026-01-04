package org.example.dormtaskmanagerapi.Dto;

import java.util.List;

public record RoomResponse(
        Long id,
        String name,
        List<UserShortResponse> users
) {}
