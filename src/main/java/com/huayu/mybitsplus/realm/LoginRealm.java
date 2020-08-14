package com.huayu.mybitsplus.realm;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.huayu.mybitsplus.exception.MyException;
import com.huayu.mybitsplus.interfaces.EmployeeUtils;
import com.huayu.mybitsplus.mapper.AuthorityMapper;
import com.huayu.mybitsplus.mapper.EmployeeMapper;
import com.huayu.mybitsplus.mapper.PositionMapper;
import com.huayu.mybitsplus.mapper.PostAuthMapper;
import com.huayu.mybitsplus.pojo.Authority;
import com.huayu.mybitsplus.pojo.Employee;
import com.huayu.mybitsplus.pojo.Position;
import com.huayu.mybitsplus.pojo.PostAuth;
import com.huayu.mybitsplus.vo.Emp;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

/**
 * 身份认证
 *
 * @author Administrator
 */
public class LoginRealm extends AuthorizingRealm {
    @Autowired
    private EmployeeMapper employeeMapper;
    @Autowired
    private PositionMapper positionMapper;
    @Autowired
    private PostAuthMapper postAuthMapper;
    @Autowired
    private AuthorityMapper authorityMapper;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        Set<String> list = null;
        SimpleAuthorizationInfo simpleAuthorizationInfo = null;

        String username = (String) principalCollection.getPrimaryPrincipal();
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("name", username);
        Employee employee = employeeMapper.selectOne(queryWrapper);
        list = new HashSet<>();
        Set<String> list1 = new HashSet<>();
        if (employee != null) {
            String roles = employee.getPostid();
            String str[] = roles.split(",");
            for (int i = 0; i < str.length; i++) {
                QueryWrapper queryWrapper1 = new QueryWrapper();
                queryWrapper1.eq("pid", str[i]);
                Position position = positionMapper.selectOne(queryWrapper1);
                list.add(position.getPname());
                QueryWrapper queryWrapper2 = new QueryWrapper();
                queryWrapper2.eq("pid", position.getPid());
                List<PostAuth> l = postAuthMapper.selectList(queryWrapper2);
                if (l.size() > 0) {
                    for (PostAuth postAuth : l) {
                        QueryWrapper queryWrapper3 = new QueryWrapper();
                        queryWrapper3.eq("aid", postAuth.getAid());
                        list1.add(authorityMapper.selectOne(queryWrapper3).getUrl());
                    }
                }
            }
        }
        try {
            simpleAuthorizationInfo = new SimpleAuthorizationInfo();
            simpleAuthorizationInfo.setRoles(list);
            simpleAuthorizationInfo.addStringPermissions(list1);
            return simpleAuthorizationInfo;
        } catch (UnauthorizedException e) {
            try {
                throw new MyException("您没有访问的角色");
            } catch (MyException myException) {
                myException.printStackTrace();
            }
        }
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) authenticationToken;
        String username = usernamePasswordToken.getUsername();
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("name", username);
        /*根据名称查询是否为合法用户*/
        Employee employee = employeeMapper.selectOne(queryWrapper);
        if (employee == null) {
            throw new UnknownAccountException("此用户不存在");
        }
        ByteSource salt = ByteSource.Util.bytes(username);
        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(employee.getName(), employee.getPassword(), salt, getName());
        return simpleAuthenticationInfo;
    }
}
