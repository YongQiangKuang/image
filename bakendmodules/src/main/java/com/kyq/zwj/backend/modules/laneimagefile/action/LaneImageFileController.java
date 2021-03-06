package com.kyq.zwj.backend.modules.laneimagefile.action;

import com.kyq.zwj.backend.entity.LaneImageFileManager;
import com.kyq.zwj.backend.modules.laneimagefile.services.intf.LaneImageFileMangerIntf;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * Description:
 * Copyright: © 2017 CSNT. All rights reserved.
 * Company:CSTC
 *
 * @version 1.0
 * @author: kyq1024
 * @timestamp: 2017-10-24 16:25
 */
@Scope("singleton")
@Controller
@RequestMapping("/laneimage")
public class LaneImageFileController {

    @Resource(name="laneimagefile-manager")
    LaneImageFileMangerIntf laneImageFileMangerIntf;

    private static final Logger logger = LoggerFactory.getLogger(LaneImageFileController.class);
    /**
     * 实现rest风格的请求
     * */
    @ResponseBody
    @RequestMapping(value="getImageInfo.do", method= RequestMethod.GET)
    public Map getUserInfo(){
        logger.info("test logger...");
        return laneImageFileMangerIntf.getFileData();
    }

    /**
     * 获取单条图片信息
     * 参数：车辆通行收费站、车道号、时间、车牌号、等关键信息
     * 返回：图片信息
     * */
    @ResponseBody
    @RequestMapping(value="getLaneImageFileInfo.do", method= RequestMethod.GET)
    public Map getLaneImageFileInfo(LaneImageFileManager laneImageFileManager, HttpServletRequest req, HttpServletResponse resp){
        return laneImageFileMangerIntf.getLaneImageFileInfo(laneImageFileManager);
    }

    /**
     * 获取单条图片信息
     * 参数：车辆通行收费站、车道号、时间、车牌号、等关键信息
     * 返回：图片信息
     * */
    @ResponseBody
    @RequestMapping(value="getBatchLaneImageFileInfo.do", method= RequestMethod.GET)
    public Map getBatchLaneImageFileInfo(LaneImageFileManager laneImageFileManager){
        return laneImageFileMangerIntf.getBatchLaneImageFileInfo(laneImageFileManager);
    }

    //主要是上传文件.
    @ResponseBody
    @RequestMapping(value="postLaneImageFile.do", method= RequestMethod.POST)
    public Map postLaneImageFile(HttpServletRequest req){
        //处理参数
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) req;
//        CommonsMultipartFile file = (CommonsMultipartFile) multipartRequest.getFile("uploadFiles");
        List<MultipartFile> files = multipartRequest.getFiles("uploadFiles");
        //ps:参数是根据文件名来进行的标识。将文件存储之后需要将文件下载地址返回到前台
        //保存数据
        return laneImageFileMangerIntf.addLaneImageFileInfo(files);
    }
}
