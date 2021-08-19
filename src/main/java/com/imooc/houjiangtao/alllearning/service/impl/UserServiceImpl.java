package com.imooc.houjiangtao.alllearning.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.imooc.houjiangtao.alllearning.domain.common.PageQuery;
import com.imooc.houjiangtao.alllearning.domain.common.PageResult;
import com.imooc.houjiangtao.alllearning.domain.dto.UserDTO;
import com.imooc.houjiangtao.alllearning.domain.dto.UserQueryDTO;
import com.imooc.houjiangtao.alllearning.domain.entity.UserDO;
import com.imooc.houjiangtao.alllearning.mapper.UserMapper;
import com.imooc.houjiangtao.alllearning.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public int save(UserDTO userDTO) {
        UserDO userDO = new UserDO();
        //TODO 浅拷贝，属性名相同才能拷贝
        BeanUtils.copyProperties(userDTO,userDO);
        return userMapper.insert(userDO);
    }

    @Override
    public int update(Long id, UserDTO userDTO) {
        UserDO userDO = new UserDO();
        BeanUtils.copyProperties(userDTO,userDO);
        userDO.setId(id);;
        return userMapper.updateById(userDO);
    }

    @Override
    public int delete(Long id) {
        return userMapper.deleteById(id);
    }

    @Override
    public PageResult<List<UserDTO>> query(PageQuery<UserQueryDTO> pageQuery) {
        /**
         * UserQueryDTO里面就有一个属性，username，然后，DTO就是传输数据的，可能比entity多
         * 也可能比entity少，这里就是少，不需要那么多的字段，就传输层中间弄个DTO对象，
         * 但是落库的时候，还是用的DO对象
         */
        Page page = new Page(pageQuery.getPageNo(), pageQuery.getPageSize());
        UserDO query = new UserDO();
        BeanUtils.copyProperties(pageQuery.getQuery(),query);
        //如果属性不一致，需要做特殊处理
        QueryWrapper<UserDO> queryWrapper = new QueryWrapper<>(query);
        //查询
        //IPage<UserDO> userDTOIPage = userMapper.selectPage(page, queryWrapper);
        IPage<UserDO> userDTOIPage = userMapper.selectPage(page, queryWrapper);
        //结果解析
        PageResult pageResult = new PageResult();
        pageResult.setPageNo((int) userDTOIPage.getCurrent());
        pageResult.setPageSize((int) userDTOIPage.getSize());
        pageResult.setTotal(userDTOIPage.getTotal());
        pageResult.setPageNum(userDTOIPage.getPages());
        List<UserDTO> userDTOList = Optional.ofNullable(userDTOIPage.getRecords())
                .map(new Function<List<UserDO>, Stream<UserDO>>() {
                    @Override
                    public Stream<UserDO> apply(List<UserDO> userDOS) {
                        return userDOS.stream();
                    }
                })
                .orElseGet(new Supplier<Stream<UserDO>>() {
                    @Override
                    public Stream<UserDO> get() {
                        return Stream.empty();
                    }
                })
                .map(new Function<UserDO, UserDTO>() {
                    //userDTOIPage的泛型是UserDO，所以这里能传过来
                    @Override
                    public UserDTO apply(UserDO userDO) {
                        UserDTO userDTO = new UserDTO();
                        BeanUtils.copyProperties(userDO, userDTO);
                        return userDTO;
                    }
                }).collect(Collectors.toList());
        pageResult.setData(userDTOList);

        return pageResult;
    }
}
