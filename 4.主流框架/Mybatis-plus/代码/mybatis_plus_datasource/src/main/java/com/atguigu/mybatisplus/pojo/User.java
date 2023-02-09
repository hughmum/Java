package com.atguigu.mybatisplus.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * Date:2022/2/15
 * Author:ybc
 * Description:
 */
@Data
@TableName("t_user")
public class User {

    @TableId
    private Integer uid;

    private String userName;

    private Integer age;

    private Integer sex;

    private String email;

    private Integer isDeleted;

}
