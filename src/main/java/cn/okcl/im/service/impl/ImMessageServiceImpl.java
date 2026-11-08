package cn.okcl.im.service.impl;


import cn.okcl.im.mapper.ImMessageMapper;
import cn.okcl.im.model.entity.ImMessage;
import cn.okcl.im.service.ImMessageService;
import com.baomidou.mybatisplus.spring.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author 20846
 * @description 针对表【im_message(聊天消息表)】的数据库操作Service实现
 * @createDate 2026-11-09 00:02:09
 */
@Service
public class ImMessageServiceImpl extends ServiceImpl<ImMessageMapper, ImMessage> implements ImMessageService {

}




