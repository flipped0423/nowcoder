package com.nowcoder.community.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author xindong
 * @create 2022-05-12 13:50
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginTicket {

    private Integer id;
    private Integer userId;
    private String ticket;
    private Integer status;
    private Date expired;

}
