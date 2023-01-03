package com.glee.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class FriendsDto {

    public int offset;

    public int limit;

    public String order;

    public String friend_order;
}
