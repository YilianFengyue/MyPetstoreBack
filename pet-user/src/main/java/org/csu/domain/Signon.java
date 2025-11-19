package org.csu.dao;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
/**
 * <p>
 * 
 * </p>
 *
 * @author YinBo
 * @since 2025-03-08
 */
@Getter
@Setter
@ToString
@TableName("signon")
public class Signon implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("username")
    private String username;

    @TableField("password")
    private String password;

    @TableField("github_uuid")
    private String githubUuid;

    @TableField("avatar_url")
    private String avatarUrl;
}
