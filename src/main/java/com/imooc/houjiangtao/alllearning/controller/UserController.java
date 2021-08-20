package com.imooc.houjiangtao.alllearning.controller;

import com.imooc.houjiangtao.alllearning.domain.common.PageQuery;
import com.imooc.houjiangtao.alllearning.domain.common.PageResult;
import com.imooc.houjiangtao.alllearning.domain.common.ResponseResult;
import com.imooc.houjiangtao.alllearning.domain.dto.UserDTO;
import com.imooc.houjiangtao.alllearning.domain.dto.UserQueryDTO;
import com.imooc.houjiangtao.alllearning.domain.vo.UserVO;
import com.imooc.houjiangtao.alllearning.exception.ErrorCodeEnum;
import com.imooc.houjiangtao.alllearning.service.ExcelExportService;
import com.imooc.houjiangtao.alllearning.service.UserService;
import com.imooc.houjiangtao.alllearning.util.InsertValidationGroup;
import com.imooc.houjiangtao.alllearning.util.UpdateValidationGroup;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("/api/users")
@Slf4j
@Validated
@Api(
        value = "用户管理Controller",
        tags = {"用户管理Controller"},
        protocols = "http, https",
        hidden = false
)
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private ExcelExportService excelExportService;

    /**
     * POST
     */
    @CacheEvict(cacheNames = "users-cache",allEntries = true)
    @PostMapping
    public ResponseResult<String> save(@Validated(InsertValidationGroup.class)
                                   @RequestBody
                                           UserDTO userDTO) {
        int save = userService.save(userDTO);
        if(save == 1){
            return ResponseResult.success("新增成功");
        }else {
            return ResponseResult.failure(ErrorCodeEnum.INSERT_FAILURE);
        }
    }

    /**
     * PUT  更新用户信息
     * api/users{id} UserDTO userDTO
     */
    @PutMapping("/{id}")
    @ApiOperation(value = "更新用户信息",
            notes = "备注说明信息",
            response = ResponseResult.class,
            httpMethod = "PUT"
    )
    @ApiImplicitParams({
            @ApiImplicitParam(
                    name = "id",
                    value = "参数说明，主键",
                    required = true,
                    paramType = "path",
                    dataType = "Long",
                    example = "12345"
            ), @ApiImplicitParam(
                    name = "userDTO",
                    value = "用户信息",
                    required = true,
                    paramType = "body",
                    dataType = "UserDTO",
                    dataTypeClass = UserDTO.class
    )
    })
    @ApiResponses(
            {
                    @ApiResponse(code = 0000,message = "操作成功"),
                    @ApiResponse(code = 3004,message = "更新失败")
            }
    )
    public ResponseResult update(@NotNull @PathVariable("id") Long id,
                                 @Validated(UpdateValidationGroup.class)
                                 @RequestBody UserDTO userDTO) {
        int update = userService.update(id,userDTO);
        if(update == 1){
            return ResponseResult.success("更新成功");
        }else{
            return ResponseResult.failure(ErrorCodeEnum.UPDATE_FAILURE);
        }
    }


    @Cacheable(cacheNames = "users-cache")
    @GetMapping
    public ResponseResult<PageResult> query(
            @NotNull Integer pageNo, @NotNull Integer pageSize,
            @Validated UserQueryDTO query) {

        System.out.println("未使用缓存");
        PageQuery<UserQueryDTO> pageQuery = new PageQuery<>();
        pageQuery.setPageNo(pageNo);
        pageQuery.setPageSize(pageSize);
        pageQuery.setQuery(query);
        //上面是查询条件，下面用上了。
        PageResult<List<UserDTO>> pageResult = userService.query(pageQuery);
        //实体转换
        List<UserVO> userVOList = Optional.ofNullable(pageResult.getData())
                .map(List::stream)
                .orElseGet(Stream::empty)
                .map(userDTO -> {
                    UserVO userVO = new UserVO();
                    BeanUtils.copyProperties(userDTO, userVO);
                    //对特殊字段做处理
                    userVO.setPassword("******");
                    if(!StringUtils.isEmpty(userDTO.getPhone())){
                        userVO.setPhone(userDTO.getPhone().replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2"));
                    }
                    return userVO;
                }).collect(Collectors.toList());
        PageResult<List<UserVO>> result = new PageResult<>();
        BeanUtils.copyProperties(pageResult,result);
        //注意，这两个pageResult里面存放的data是不一样的，一个是List的VO对象，一个是List的DTO对象
        //上面那个copyProperties是因为里面有分页的信息。
        result.setData(userVOList);
        return ResponseResult.success(result);
    }
    @DeleteMapping("/{id}")
    public ResponseResult delete(@NotNull(message = "用户id不能为空")
                                     @PathVariable("id")
                                             Long id) {
        int delete = userService.delete(id);
        if(delete == 1){

            return ResponseResult.success("删除成功");
        }else {
            return ResponseResult.failure(ErrorCodeEnum.DELETE_FAILURE);
        }
    }

    /**
     * 数据导出
     * @param query
     * @param fileName
     * @return
     */
    @GetMapping("/export")
    public ResponseResult<Boolean> export(
            @Validated UserQueryDTO query,
            @NotEmpty String fileName
    ){
//        excelExportService.export(query,fileName);
        excelExportService.asyncExport(query,fileName);
        return ResponseResult.success(Boolean.TRUE);
    }
}
