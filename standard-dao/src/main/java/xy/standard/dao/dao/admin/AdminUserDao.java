package xy.standard.dao.dao.admin;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import xy.standard.dao.vo.admin.AdminUserVo;

@Mapper
public interface AdminUserDao extends BaseMapper<AdminUserVo> {
}