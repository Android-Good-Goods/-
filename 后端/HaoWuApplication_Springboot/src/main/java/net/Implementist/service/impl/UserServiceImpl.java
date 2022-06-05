package net.Implementist.service.impl;

import cn.hutool.json.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import net.Implementist.entity.Account;
import net.Implementist.entity.User;
import net.Implementist.mapper.UserMapper;
import net.Implementist.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import net.Implementist.util.HXUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.servlet.ServletException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author
 * @since 2022-05-21
 */

