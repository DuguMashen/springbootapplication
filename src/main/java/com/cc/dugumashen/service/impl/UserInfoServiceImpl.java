package com.cc.dugumashen.service.impl;

import com.cc.dugumashen.mapper.user.BaseinfoMapper;
import com.cc.dugumashen.model.user.Baseinfo;
import com.cc.dugumashen.service.IUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Description：
 * Date: 2019/9/2
 * Author：
 */
@Service
public class UserInfoServiceImpl implements IUserInfoService {
    @Autowired
    private BaseinfoMapper baseinfoMapper;

    @Override
    public Map getAllUserBaseInfo() {
        Map<String, Object> result = new HashMap();
        List<Baseinfo> baseinfos = null;
        try {
            baseinfos = baseinfoMapper.selectAllUserBaseInfo();
        } catch (Exception e) {
            e.printStackTrace();
            result.put("resultCode", "001");
            result.put("error", "查询数据库异常");
            return result;
        }

        result.put("resultCode", "000");
        if (baseinfos != null && baseinfos.size() > 0)
            result.put("allUserBaseInfo", baseinfos);

        return result;

    }
}
