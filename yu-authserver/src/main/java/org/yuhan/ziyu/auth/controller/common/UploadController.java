package org.yuhan.ziyu.auth.controller.common;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

/**
 * @author Howell
 */
@Slf4j
@RestController
@Api(description = "文件上传接口")
@RequestMapping("/oss/upload")
@Transactional
public class UploadController {

//    @Autowired
//    private RedisRaterLimiter redisRaterLimiter;

//    @Autowired
//    private QiniuUtil qiniuUtil;
//
//    @Autowired
//    private IpInfoUtil ipInfoUtil;
//
//    @RequestMapping(value = "/file",method = RequestMethod.POST)
//    @ApiOperation(value = "文件上传")
//    public Result<Object> upload(@RequestParam("file") MultipartFile file,
//                                 HttpServletRequest request) {
//
//        // IP限流 在线Demo所需 5分钟限1个请求
//        String token = redisRaterLimiter.acquireTokenFromBucket("upload:"+ipInfoUtil.getIpAddr(request), 1, 300000);
//        if (StrUtil.isBlank(token)) {
//            throw new OSSException("上传那么多干嘛，等等再传吧");
//        }
//
//        String imagePath = null;
//        String fileName = qiniuUtil.renamePic(file.getOriginalFilename());
//        try {
//            FileInputStream inputStream = (FileInputStream) file.getInputStream();
//            //上传七牛云服务器
//            imagePath= qiniuUtil.qiniuInputStreamUpload(inputStream,fileName);
//            if(StrUtil.isBlank(imagePath)){
//                return new ResultUtil<Object>().setErrorMsg("上传失败，请检查七牛云配置");
//            }
//            if(imagePath.contains("error")){
//                return new ResultUtil<Object>().setErrorMsg(imagePath);
//            }
//        } catch (Exception e) {
//            log.error(e.toString());
//            return new ResultUtil<Object>().setErrorMsg(e.toString());
//        }
//
//        return new ResultUtil<Object>().setData(imagePath);
//    }
}
